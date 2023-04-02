<h1>How to start project</h1>

<p>1. Run <code>mvn clean package</code></p>
<p>2. Start project from IntelliJ Idea / Maven / etc.</p>

<h2>Important notes</h2>
<p>Before closing of requestObserver or responseObserver it's necessary to wait for some time to receive a response from a server or client.<br>
Example:<br></p>
<code>Thread.sleep(100);</code>