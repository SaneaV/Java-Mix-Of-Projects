# What is Apache Avro?

<p>Apache Avro is a data serialization format that allows for efficient storage and transmission of data between different systems and programming languages. It is used as an intermediate data exchange format between various components of a system, such as between a server and client, between different services, and so on.

Avro allows you to define a data schema and serialize data according to that schema. The data schema defines the structure of the data, including data types and nested fields. Using this schema, you can easily determine what data was sent and how to receive it. This reduces the amount of data transmitted and simplifies the development of multi-component systems.

Avro also supports schema evolution, which means that changes to the schema do not break compatibility between different versions of the application. This makes it easy to update systems without breaking the functionality of older applications.

Overall, Apache Avro is a useful tool for exchanging data between different components of a system and reducing the cost of data transmission, while providing flexibility and evolutionary compatibility.</p>

# How to create schema?

1. Create `.avsc` file in JSON format.<br>
2. You can simplify this process using `SchemaBuilder.toString()` (Example: `AvroSchemaBuilder`).<br>
3. Add Apache Avro dependencies in pom.xml.<br>
4. Add Apache Avro plugin in pom.xml.<br>
5. Run `mvn clean package` or main class in `AvroClassGenerator`.<br>
6. New classes should appear in the `tut.baeldung.avro.model` folder.<br>

# Serialization and Deserialization

<p>Avro serialization/deserialization supports: JSON format and Binary format.</p>

# Conclusion
<p>Apache Avro is especially useful while dealing with big data. It offers data serialization in binary as well as JSON format which can be used as per the use case.

The Avro serialization process is faster, and it's space efficient as well. Avro does not keep the field type information with each field; instead, it creates metadata in a schema.

Last but not the least Avro has a great binding with a wide range of programming languages, which gives it an edge.</p>