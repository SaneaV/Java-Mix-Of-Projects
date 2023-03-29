<h1>Spring Boot Kafka Tutorial</h1></p>
<h2>How to start project</h2></p>
<ol>
<li class="has-line-data" data-line-start="3" data-line-end="4">Download the latest Kafka release and extract it.</li>
<li class="has-line-data" data-line-start="4" data-line-end="11">Start the Kafka environment:
<ol>
<li class="has-line-data" data-line-start="5" data-line-end="6">Open terminal and kafka folder: <code>cd kafka</code></li>
<li class="has-line-data" data-line-start="6" data-line-end="7">Run command: <code>.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties</code></li>
<li class="has-line-data" data-line-start="7" data-line-end="8">Open another terminal and run following: <code>.\bin\windows\kafka-server-start.bat .\config\server.properties</code></li>
<li class="has-line-data" data-line-start="8" data-line-end="11">Open another terminal and create two topics:
<ol>
<li class="has-line-data" data-line-start="9" data-line-end="10">javaguides - <code>./kafka-topics.bat --create --topic javaguides --bootstrap-server=localhost:9092</code></li>
<li class="has-line-data" data-line-start="10" data-line-end="11">javaguides_json - <code>./kafka-topics.bat --create --topic javaguides_json --bootstrap-server=localhost:9092</code></li>
</ol>
</li>
</ol>
</li>
<li class="has-line-data" data-line-start="11" data-line-end="12">Run the project</li>
<li class="has-line-data" data-line-start="12" data-line-end="22">How to send messages into topics:
<ol>
<li class="has-line-data" data-line-start="13" data-line-end="14">javaguides - GET <code>http://localhost:8080/api/v1/kafka/publish</code> with parameter <code>message</code></li>
<li class="has-line-data" data-line-start="14" data-line-end="15">javaguides_json - POST <code>http://localhost:8080/api/v1/kafka/publish</code> with body JSON body:</li>
</ol>
<pre><code class="has-line-data" data-line-start="16" data-line-end="22">{
   &quot;id&quot;: 1,
   &quot;firstName&quot;: &quot;firstName&quot;,
   &quot;lastName&quot;: &quot;lastName&quot;
}
</code></pre>
</li>
<li class="has-line-data" data-line-start="22" data-line-end="26">How to read messages:
<ol>
<li class="has-line-data" data-line-start="23" data-line-end="26">Open terminal and run following:
<ol>
<li class="has-line-data" data-line-start="24" data-line-end="25">javaguides - <code>./kafka-console-consumer.bat --from-beginning --bootstrap-server=localhost:9092 --topic javaguides</code></li>
<li class="has-line-data" data-line-start="25" data-line-end="26">javaguides_json - <code>./kafka-console-consumer.bat --from-beginning --bootstrap-server=localhost:9092 --topic javaguides_json</code></li>
</ol>
</li>
</ol>
</li>
</ol>
