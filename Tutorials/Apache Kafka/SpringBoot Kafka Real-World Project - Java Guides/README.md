<h1>Spring Boot Kafka Real-World Project</h1>
<h2>How to start the project</h2>
<ol>
<li class="has-line-data" data-line-start="3" data-line-end="4">Download the latest Kafka release and extract it.</li>
<li class="has-line-data" data-line-start="4" data-line-end="5">Start the Kafka environment:</li>
<li class="has-line-data" data-line-start="5" data-line-end="6">Open terminal and kafka folder: cd kafka</li>
<li class="has-line-data" data-line-start="6" data-line-end="7">Run command: .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties</li>
<li class="has-line-data" data-line-start="7" data-line-end="10">Open another terminal and run following: .\bin\windows\kafka-server-start.bat .\config\server.properties
<ol>
<li class="has-line-data" data-line-start="8" data-line-end="10">Open new terminal and create new kafka topic:<br>
<code>./kafka-topics.bat --create --topic wikimedia_recentchange --bootstrap-server=localhost:9092</code></li>
</ol>
</li>
<li class="has-line-data" data-line-start="10" data-line-end="16">Download MySQL and create new database:<pre><code class="has-line-data" data-line-start="12" data-line-end="16"> url=jdbc:mysql://localhost:3306/wikimedia
 username=root
 password=
</code></pre>
</li>
<li class="has-line-data" data-line-start="16" data-line-end="17">Run the project</li>
<li class="has-line-data" data-line-start="17" data-line-end="18">All data from <code>stream.wikimedia.org/v2/stream/recentchange</code> will be saved in <code>wikimedia</code> database</li>
<li class="has-line-data" data-line-start="18" data-line-end="21">How to read messages:
<ol>
<li class="has-line-data" data-line-start="19" data-line-end="21">Open terminal and run following:<br>
<code>./kafka-console-consumer.bat --from-beginning --bootstrap-server=localhost:9092 --topic wikimedia_recentchange</code></li>
</ol>
</li>
</ol>