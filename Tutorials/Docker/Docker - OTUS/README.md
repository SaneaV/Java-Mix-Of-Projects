# How to start project

### Prerequisites

1. Windows OS (`/Docker/.` contain `bat` files, for UNIX systems you can change to `sh` files and add 
`#!/usr/bin/env bash` at the top of file. Also change file format in `maven-antrun-plugin`<br>
2. Docker Desktop (or any other tool for working with containers)<br>

### Start

1. Run `mvn clean package`.<br>
2. Create new container with specific image.<br>
Example:
```
docker run java-docker-centos
docker run java-docker-alpine
docker run java-docker-alpine-musl
```
3. Run bash console in specific image (winpty just for Windows):
```
winpty docker run -it java-docker-centos bash
winpty docker run -it java-docker-alpine bash
winpty docker run -it java-docker-alpine-musl bash
```
4. Run new container with specified limit:
```
docker run --memory=100m --memory-swap=100m --cpus 2 java-docker-centos
```
5. Run VisualVM on application in container (remotely):
    - Start VisualVM
    - Create new container with specified port `docker run -p 9010:9010 java-docker-centos`
    - Add new JMX connection (`localhost:9010`)