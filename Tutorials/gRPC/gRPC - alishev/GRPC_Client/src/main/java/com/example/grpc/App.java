package com.example.grpc;

import com.example.grpc.GreetingServiceGrpc.GreetingServiceBlockingStub;
import com.example.grpc.GreetingServiceGrpc.GreetingServiceStub;
import com.example.grpc.GreetingServiceOuterClass.HelloRequest;
import com.example.grpc.GreetingServiceOuterClass.HelloResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.util.Iterator;

public class App {

  public static void main(String[] args) {
    final ManagedChannel channel = ManagedChannelBuilder
        .forTarget("localhost:8080")
        .usePlaintext()
        .build();

    System.out.println("\nNon-stream approach: ");
    nonStream(channel);
    System.out.println("\nServer stream approach: ");
    serverStream(channel);
    System.out.println("\nClient stream approach: ");
    clientStream(channel);
    System.out.println("\nBidirectional stream approach: ");
    bidirectionalStream(channel);

    channel.shutdownNow();
  }

  private static void nonStream(ManagedChannel channel) {
    final GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
    final HelloRequest request = HelloRequest.newBuilder()
        .setName("Neil")
        .build();

    final HelloResponse response = stub.greetingServiceNonStream(request);
    System.out.println(response);
  }

  private static void serverStream(ManagedChannel channel) {
    final GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
    final HelloRequest request = HelloRequest.newBuilder()
        .setName("Neil")
        .build();

    final Iterator<HelloResponse> response = stub.greetingServiceServerStream(request);
    while (response.hasNext()) {
      System.out.println(response.next());
    }
  }

  private static void clientStream(ManagedChannel channel) {
    StreamObserver<HelloResponse> responseObserver = new StreamObserver<HelloResponse>() {

      @Override
      public void onNext(HelloResponse response) {
        System.out.println(response);
      }

      @Override
      public void onError(Throwable throwable) {

      }

      @Override
      public void onCompleted() {
      }
    };

    final StreamObserver<HelloRequest> requestObserver = GreetingServiceGrpc.newStub(channel)
        .greetingServiceClientStream(responseObserver);

    for (int i = 0; i < 5; i++) {
      final HelloRequest request = HelloRequest.newBuilder()
          .setName("Neil")
          .build();

      requestObserver.onNext(request);
    }
    requestObserver.onCompleted();

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private static void bidirectionalStream(ManagedChannel channel) {
    StreamObserver<HelloResponse> responseObserver = new StreamObserver<HelloResponse>() {

      @Override
      public void onNext(HelloResponse response) {
        System.out.println(response);
      }

      @Override
      public void onError(Throwable throwable) {

      }

      @Override
      public void onCompleted() {
      }
    };

    final StreamObserver<HelloRequest> requestObserver = GreetingServiceGrpc.newStub(channel)
        .greetingServiceBidirectionalStream(responseObserver);

    for (int i = 0; i < 10; i++) {
      final HelloRequest request = HelloRequest.newBuilder()
          .setName("Neil" + i + " - ")
          .build();
      requestObserver.onNext(request);
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    requestObserver.onCompleted();

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
