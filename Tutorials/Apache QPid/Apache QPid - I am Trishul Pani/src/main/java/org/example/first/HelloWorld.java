package org.example.first;

import static jakarta.jms.DeliveryMode.NON_PERSISTENT;
import static jakarta.jms.Message.DEFAULT_PRIORITY;
import static jakarta.jms.Message.DEFAULT_TIME_TO_LIVE;
import static jakarta.jms.Session.AUTO_ACKNOWLEDGE;

import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.Destination;
import jakarta.jms.ExceptionListener;
import jakarta.jms.JMSException;
import jakarta.jms.MessageConsumer;
import jakarta.jms.MessageProducer;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class HelloWorld {

  public static void main(String[] args) {
    try {
      // The configuration for the Qpid InitialContextFactory has been supplied in
      // a jndi.properties file in the classpath, which results in it being picked
      // up automatically by the InitialContext constructor.
      final Context context = new InitialContext();

      // Get the name of my ConnectionFactory from jndi.properties -> connectionfactory.myFactoryLookup
      final ConnectionFactory factory = (ConnectionFactory) context.lookup("myFactoryLookup");
      // Get destination queue from jndi.properties -> queue.myQueueLookup
      final Destination queue = (Destination) context.lookup("myQueueLookup");

      // Connect to QPid using provided login and password (via VM options)
      final Connection connection = factory.createConnection(System.getProperty("USER"), System.getProperty("PASSWORD"));
      connection.setExceptionListener(new MyExceptionListener());
      connection.start();

      final Session session = connection.createSession(false, AUTO_ACKNOWLEDGE);

      // Create message producer
      final MessageProducer messageProducer = session.createProducer(queue);
      // Create message consumer
      final MessageConsumer messageConsumer = session.createConsumer(queue);

      // Create text message
      final TextMessage message = session.createTextMessage("Hello world!");
      messageProducer.send(message, NON_PERSISTENT, DEFAULT_PRIORITY, DEFAULT_TIME_TO_LIVE);
      // Get message (like a client) sent by producer
      final TextMessage receivedMessage = (TextMessage) messageConsumer.receive(2000L);

      // Display message
      if (receivedMessage != null) {
        System.out.println(receivedMessage.getText());
      } else {
        System.out.println("No message received within the given timeout!");
      }

      connection.close();
    } catch (Exception exp) {
      System.out.println("Caught exception, exiting.");
      exp.printStackTrace(System.out);
      System.exit(1);
    }
  }

  private static class MyExceptionListener implements ExceptionListener {

    @Override
    public void onException(JMSException exception) {
      System.out.println("Connection ExceptionListener fired, exiting.");
      exception.printStackTrace(System.out);
      System.exit(1);
    }
  }
}