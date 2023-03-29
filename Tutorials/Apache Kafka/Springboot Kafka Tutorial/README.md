<h1>Spring Boot Kafka Tutorial</h1>

<h2>How to start project</h2>
1. Download the latest Kafka release and extract it.<br>
2. Start the Kafka environment:<br>
   1. Open terminal and kafka folder: `cd kafka`<br>
   2. Run command: `.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties`<br>
   3. Open another terminal and run following: `.\bin\windows\kafka-server-start.bat .\config\server.properties`<br>
   4. Open another terminal and create two topics:<br>
      1. javaguides - `./kafka-topics.bat --create --topic javaguides --bootstrap-server=localhost:9092`<br>
      2. javaguides_json - `./kafka-topics.bat --create --topic javaguides_json --bootstrap-server=localhost:9092`<br>
3. Run the project<br>
4. How to send messages into topics:<br>
   1. javaguides - GET `http://localhost:8080/api/v1/kafka/publish` with parameter `message`<br>
   2. javaguides_json - POST `http://localhost:8080/api/v1/kafka/publish` with body JSON body:<br>
   ```<br>
   {
      "id": 1,
      "firstName": "firstName",
      "lastName": "lastName"
   }
   ```
5. How to read messages:<br>
   1. Open terminal and run following:<br>
      1. javaguides - `./kafka-console-consumer.bat --from-beginning --bootstrap-server=localhost:9092 --topic javaguides`<br>
      2. javaguides_json - `./kafka-console-consumer.bat --from-beginning --bootstrap-server=localhost:9092 --topic javaguides_json`<br>