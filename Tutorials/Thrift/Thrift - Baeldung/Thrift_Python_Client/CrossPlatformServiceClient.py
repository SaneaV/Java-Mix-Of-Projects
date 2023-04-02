import sys

from org.example import CrossPlatformService
from org.example import CrossPlatformService
from org.example.ttypes import *

from thrift import Thrift
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol

try:

  # Make socket
  transport = TSocket.TSocket('localhost', 9090)

  # Buffering is critical. Raw sockets are very slow
  transport = TTransport.TBufferedTransport(transport)

  # Wrap in a protocol
  protocol = TBinaryProtocol.TBinaryProtocol(transport)

  # Create a client to use the protocol encoder
  client = CrossPlatformService.Client(protocol)

  # Connect!
  transport.open()

  print('\nFind CrossPlatformService with id=1: ')
  service = client.get(1)
  print(client.toString(service))

  print('\nAdd new CrossPlatformService with id=5: ')
  client.save(CrossPlatformResource(5, "System5", "Python Salutation"))

  print('\nFind CrossPlatformService with id=5: ')
  service = client.get(5)
  print(client.toString(service))

  print('\nShow full list of CrossPlatformServices: ')
  services = client.getList()
  for service in services:
    print(client.toString(service))

  print('\nFind CrossPlatformService with id=6: ')
  client.get(6)
  
  # Close!
  transport.close()

except EntityNotFoundException as e:
    print("Code - '%d', Message - '%s'" % (e.code, e.description))
except (Thrift.TException) as e:
  pass