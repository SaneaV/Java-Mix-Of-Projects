# How to start Application

1. Go to the root folder. <br>
2. Run `mvn clean package` <br>
3. Run `docker-compose build` <br>
4. Run `docker-compose up` <br>
5. Expected result:
```text
        docker-compose-otus-app-1       | application is starting...
        docker-compose-otus-app-1       | true
        docker-compose-otus-app-1       | application finished
        docker-compose-otus-app-1 exited with code 0
```

# Interesting facts

`Dockerfile.development` -  development docker file<br>
`Dockerfile` - production docker file