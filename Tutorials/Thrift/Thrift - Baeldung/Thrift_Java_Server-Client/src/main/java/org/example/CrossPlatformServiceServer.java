package org.example;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.example.CrossPlatformService.Iface;
import org.example.CrossPlatformService.Processor;

public class CrossPlatformServiceServer {

  public static CrossPlatformServiceImpl service;

  public static Processor<Iface> processor;

  public static void main(String[] args) {
    try {
      service = new CrossPlatformServiceImpl();
      processor = new Processor<>(service);

      Runnable simple = () -> simple(processor);

      new Thread(simple).start();
    } catch (Exception x) {
      x.printStackTrace();
    }
  }

  public static void simple(Processor<Iface> processor) {
    try {
      TServerTransport serverTransport = new TServerSocket(9090);
      TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));

      System.out.println("Starting the simple server...");
      server.serve();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
