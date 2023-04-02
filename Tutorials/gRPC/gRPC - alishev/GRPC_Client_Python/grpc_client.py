import grpc

import greeting_service_pb2
import greeting_service_pb2_grpc

def runNonStream():
	print("Non-stream approach: ")
	with grpc.insecure_channel('localhost:8080') as channel:
		stub = greeting_service_pb2_grpc.GreetingServiceStub(channel)
		response = stub.greetingServiceNonStream(greeting_service_pb2.HelloRequest(name='Neil'))
		print("Greeting from server: " + response.greeting)

def runServerStream():
	print("Server stream approach: ")
	with grpc.insecure_channel('localhost:8080') as channel:
		stub = greeting_service_pb2_grpc.GreetingServiceStub(channel)
		for response in stub.greetingServiceServerStream(greeting_service_pb2.HelloRequest(name='Neil')):
			print("Greeting from server: " + response.greeting)

runNonStream()
runServerStream()