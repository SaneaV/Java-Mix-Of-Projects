package com.example.grpc;

import com.example.grpc.GreetingServiceGrpc.GreetingServiceBlockingStub;
import com.example.grpc.GreetingServiceOuterClass.HelloRequest;
import com.example.grpc.GreetingServiceOuterClass.HelloResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.Iterator;

public class Client {

  public static void main(String[] args) {
    final ManagedChannel channel = ManagedChannelBuilder
        .forTarget("localhost:8080")
        .usePlaintext()
        .build();

    final GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
    final HelloRequest request = HelloRequest.newBuilder()
        .setName("Neil")
        .build();

//    Non-Stream approach
//    final HelloResponse response = stub.greeting(request);

//    Stream approach (one direction)
    final Iterator<HelloResponse> response = stub.greeting(request);
    while (response.hasNext()) {
      System.out.println(response.next());
    }

    channel.shutdownNow();
  }
}
