package com.example.grpc;

import com.example.grpc.GreetingServiceGrpc.GreetingServiceImplBase;

import com.example.grpc.GreetingServiceOuterClass.HelloRequest;
import com.example.grpc.GreetingServiceOuterClass.HelloResponse;
import io.grpc.stub.StreamObserver;

public class GreetingServiceImpl extends GreetingServiceImplBase {

  private static final String GREETING_MESSAGE = "Hello from server, %s";

  @Override
  public void greetingServiceNonStream(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
    System.out.println("Non-stream approach: " + request);

    final HelloResponse response = HelloResponse.newBuilder()
        .setGreeting(String.format(GREETING_MESSAGE, request.getName()))
        .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void greetingServiceServerStream(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
    System.out.println("Client stream approach: " + request);

    for (int i = 0; i < 5; i++) {
      final HelloResponse response = HelloResponse.newBuilder()
          .setGreeting(String.format(GREETING_MESSAGE, request.getName() ))
          .build();

      responseObserver.onNext(response);
    }

    responseObserver.onCompleted();
  }

  @Override
  public StreamObserver<HelloRequest> greetingServiceClientStream(StreamObserver<HelloResponse> responseObserver) {
    return new StreamObserver<HelloRequest>() {
      int count;
      String name;

      @Override
      public void onNext(HelloRequest request) {
        count++;
        name = request.getName();
      }

      @Override
      public void onError(Throwable throwable) {

      }

      @Override
      public void onCompleted() {
        final HelloResponse response = HelloResponse.newBuilder()
            .setGreeting(String.format(GREETING_MESSAGE, name + " " + count))
            .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
      }
    };
  }

  @Override
  public StreamObserver<HelloRequest> greetingServiceBidirectionalStream(StreamObserver<HelloResponse> responseObserver) {
    return new StreamObserver<HelloRequest>() {

      @Override
      public void onNext(HelloRequest request) {
        for (int i = 0; i < 5; i++) {
          final HelloResponse response = HelloResponse.newBuilder()
              .setGreeting(String.format(GREETING_MESSAGE, request.getName() + " " + i))
              .build();

          responseObserver.onNext(response);
        }
      }

      @Override
      public void onError(Throwable throwable) {

      }

      @Override
      public void onCompleted() {
        responseObserver.onCompleted();
      }
    };
  }
}
