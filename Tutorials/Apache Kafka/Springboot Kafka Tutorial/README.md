<h1>Spring Boot Kafka Tutorial</h1>

<h2>How to start project</h2>
1. Download the latest Kafka release and extract it.
2. Start the Kafka environment:
   1. Open terminal and kafka folder: `cd kafka`
   2. Run command: `.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties`
   3. Open another terminal and run following: `.\bin\windows\kafka-server-start.bat .\config\server.properties`
   4. Open another terminal and create two topics:
      1. javaguides - `./kafka-topics.bat --create --topic javaguides --bootstrap-server=localhost:9092`
      2. javaguides_json - `./kafka-topics.bat --create --topic javaguides_json --bootstrap-server=localhost:9092`
3. Run the project
4. How to send messages into topics:
   1. javaguides - GET `http://localhost:8080/api/v1/kafka/publish` with parameter `message`
   2. javaguides_json - POST `http://localhost:8080/api/v1/kafka/publish` with body JSON body:
   ```
   {
      "id": 1,
      "firstName": "firstName",
      "lastName": "lastName"
   }
   ```
5. How to read messages:
   1. Open terminal and run following:
      1. javaguides - `./kafka-console-consumer.bat --from-beginning --bootstrap-server=localhost:9092 --topic javaguides`
      2. javaguides_json - `./kafka-console-consumer.bat --from-beginning --bootstrap-server=localhost:9092 --topic javaguides_json`