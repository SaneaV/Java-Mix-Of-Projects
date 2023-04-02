<h1>How to start project</h1>

<p>1. Install Python and pip</p>
<p>2. Upgrade pip: <code>python -m pip install --upgrade pip</code></p>
<p>3. Install gRPC: <code>python -m pip install grpcio</code></p>
<p>4. Install gRPC-tools: <code>python -m pip install grpcio-tools</code></p>
<p>5. Create proto file</p>
<p>6. Create gRPC output files: <code>python -m grpc_tools.protoc -I./ --python_out=. --grpc_python_out=. greeting_service.proto</code></p>
<p>7. Start GRPC_Server java project</p>
<p>8. Run Python file: <code>python grpc_client.py</code></p>