# How to start application?

1. Run `mvn clean package`<br>
2. If you use Confluent Kafka run `confluent local start`<br>
3. If you use Docker run `docker-compose up -d`
4. Run the project<br>
5. If you are using Docker, you can check the result with the following command:<br>
```docker exec -it <container-id> kafka-console-consumer --bootstrap-server localhost:9092 --topic customer-avro  --from-beginning --property schema.registry.url=http://127.0.0.1:8081```
6. UI Confluent Control Center: `http://localhost:9021/clusters`

# Schema registry

<p>Apache Avro uses a Schema Registry to store, register, and manage Avro schemas. When client code wants to use a specific Avro schema for data serialization or deserialization, it can query the Schema Registry at the URL specified in the schema.registry.url parameter to retrieve the schema.

When a client sends data in Avro format, it includes a header that contains the schema identifier. The Schema Registry uses this identifier to find the corresponding schema by its version number and return it to the client. If the schema is not yet registered in the registry, the client can send it along with the data to register it in the Schema Registry.

When a client receives data in Avro format, it can use the header to determine which schema to use for deserializing the data. If the client does not have access to the Schema Registry, it will not be able to find the schema by its identifier and an error may occur when deserializing the data.</p>