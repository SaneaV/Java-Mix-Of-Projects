package org.example;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.example.CrossPlatformService.Client;

public class CrossPlatformServiceClient {

  public static void main(String[] args) {
    try {
      TTransport transport = new TSocket("localhost", 9090);
      transport.open();

      TProtocol protocol = new TBinaryProtocol(transport);
      Client client = new Client(protocol);

      System.out.println("Calling remote method...");

      System.out.println("\nFind CrossPlatformService with id=1: ");
      try {
        CrossPlatformResource crossPlatformResource = client.get(1);
        System.out.println(client.toString(crossPlatformResource));
      } catch (EntityNotFoundException e) {
        System.out.println("CrossPlatformService with id=1 not found");
      } catch (TException e) {
        System.out.println("Thrift exception");
      }

      System.out.println("\nFind CrossPlatformService with id=5: ");
      try {
        System.out.println(client.get(5));
      } catch (EntityNotFoundException e) {
        System.out.println("CrossPlatformService with id=5 not found");
      } catch (TException e) {
        System.out.println("Thrift exception");
      }

      System.out.println("\nAdd new CrossPlatformService with id=5: ");
      try {
        final CrossPlatformResource crossPlatformResource = new CrossPlatformResource(5, "System5");
        client.save(crossPlatformResource);
      } catch (TException e) {
        System.out.println("Thrift exception");
      }

      System.out.println("\nFind CrossPlatformService with id=5: ");
      try {
        CrossPlatformResource crossPlatformResource = client.get(5);
        System.out.println(client.toString(crossPlatformResource));
      } catch (EntityNotFoundException e) {
        System.out.println("CrossPlatformService with id=5 not found");
      } catch (TException e) {
        System.out.println("Thrift exception");
      }

      System.out.println("\nShow full list of CrossPlatformServices: ");
      try {
        for (CrossPlatformResource crossPlatformResource : client.getList()) {
          System.out.println(client.toString(crossPlatformResource));
        }
      } catch (TException e) {
        System.out.println("Thrift exception");
      }

      transport.close();

    } catch (TTransportException e) {
      throw new RuntimeException(e);
    }
  }
}
