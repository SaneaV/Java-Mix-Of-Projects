<h1>How to start project</h1>

<p>1. Install Python and pip</p>
<p>2. Upgrade pip: `python -m pip install --upgrade pip`</p>
<p>3. Install gRPC: `python -m pip install grpcio`</p>
<p>4. Install gRPC-tools: `python -m pip install grpcio-tools`</p>
<p>5. Create proto file</p>
<p>6. Create gRPC output files: `python -m grpc_tools.protoc -I./ --python_out=. --grpc_python_out=. greeting_service.proto`</p>
<p>7. Start GRPC_Server java project</p>
<p>8. Run Python file: `python grpc_client.py`</p>