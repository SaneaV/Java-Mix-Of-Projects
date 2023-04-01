package com.example.grpc;

import com.example.grpc.GreetingServiceGrpc.GreetingServiceImplBase;
import com.example.grpc.GreetingServiceOuterClass.HelloRequest;
import com.example.grpc.GreetingServiceOuterClass.HelloResponse;
import io.grpc.stub.StreamObserver;

public class GreetingServiceImpl extends GreetingServiceImplBase {

  private static final String GREETING_MESSAGE = "Hello from server, %s";

  //  Stream approach(one direction)
  @Override
  public void greeting(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
    System.out.println(request);

    for (int i = 0; i < 10000; i++) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      final HelloResponse response = HelloResponse.newBuilder()
          .setGreeting(String.format(GREETING_MESSAGE, request.getName()))
          .build();

      responseObserver.onNext(response);
    }

    responseObserver.onCompleted();
  }

//  Non-Stream approach
//  @Override
//  public void greeting(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
//    System.out.println(request);
//
//    final HelloResponse response = HelloResponse.newBuilder()
//        .setGreeting(String.format(GREETING_MESSAGE, request.getName()))
//        .build();
//
//    responseObserver.onNext(response);
//    responseObserver.onCompleted();
//  }
}
