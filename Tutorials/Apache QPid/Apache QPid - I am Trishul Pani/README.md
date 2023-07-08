# Apache QPid tutorial

Apache Qpid JMS AMQP 0-8/0-9-1/0-10 is an implementation of JMS specification 1.1. It utilises an AMQP transport layer for the 
performing of messaging operations. The client is intended to be used for writing of JMS compatible messaging applications. Such applications can send and receive messages via any AMQP-compatible brokers like RabbitMQ, Apache Qpid Broker-J which supports AMQP protocols 0-8, 0-9, or 0-9-1. 

## Terminology
1. Apache QPid - makes messaging tools that speak AMQP and support many languages and platforms.
2. AMQP (Advanced Message Queueing Protocol) - It is an open standard designed to support reliable, high-performance messaging 
   over the internet. AMQP can be used for any distributed application and supports common messaging patterns such as 
   point-to-point, fan-out, publish-subscribe, and request-response.
3. Message server - is message-transfer intermediaries that provide additional behaviors such as store-and-forward for 
   improved reliability.
4. Broker-J - A pure-Java AMQP message broker

## Tutorial chronology

### Setup Messaging Server (Broker-J)
1. Pull docker image `docker pull abh1sh3k/apache-qpid`
2. Run docker container `docker run -d -p 8080:8080 -p 5672:5672 --restart=unless-stopped --name=qpidbrokerj-7.1.3 
   abh1sh3k/apache-qpid:latest`
3. Go to the `http://localhost:8080/`
4. Enter into account with credentials login (for admin role: `admin:admin`, for guest role: `guest:guest`)

### Setup Messaging Client
1. Add `qpid-jms-parent` parent into pom.xml.
2. Add `qpid-jms-client` dependency into pom.xml.
3. Setup jndi.properties (`JmsInitialContextFactory`, `connectionfactory`, `queue` and `topic`)
4. Setup environment variables to authorize user `-DUSER=guest -DPASSWORD=guest`
5. Run `HelloWorld.java`