# Apache QPid with Spring Boot - Real-World project

## Installation guide
1. Pull docker image `docker pull abh1sh3k/apache-qpid`
2. Run docker container `docker run -d -p 8080:8080 -p 5672:5672 --restart=unless-stopped --name=qpidbrokerj-7.1.3
   abh1sh3k/apache-qpid:latest`
3. Go to the `http://localhost:8080/`
4. Enter into account with credentials login (for admin role: `admin:admin`, for guest role: `guest:guest`)
5. Run the project (in console you will see the list of Sending/Received logs)

## Idea of this project

The main idea of this project is to introduce Apache QPid and customize it using Spring Boot's handy tools. For this purpose 
spring.factories and multiple autoconfiguration from Spring Boot were used. The project itself queries 
`https://stream.wikimedia.org/v2/stream/recentchange`, gets various messages from there and registers them in Apache Qpid, 
then the client connects to the specified queue and retrieves the messages.  