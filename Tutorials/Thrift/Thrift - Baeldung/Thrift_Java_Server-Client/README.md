<h1>How to run project</h1>

<p>1. Download <a href="https://thrift.apache.org/download">Thrift</a> (for Windows exe)</p>
<p>2. Run <code>mvn clean install</code></p>
<p>3. Generate Java code: <code>thrift.exe -r -out generated --gen java src\main\thrift\service.thrift</code> (optional)</p>
<p>4. Generate C++ code: <code>thrift.exe -r -out generated --gen cpp src\main\thrift\service.thrift</code> (optional)</p>
<p>5. Generate Python code: <code>thrift.exe -r -out generated --gen py src\main\thrift\service.thrift</code> (optional)</p>
<p>6. Start CrossPlatformServiceServer.java</p>
<p>7. Start CrossPlatformServiceClient.java</p>