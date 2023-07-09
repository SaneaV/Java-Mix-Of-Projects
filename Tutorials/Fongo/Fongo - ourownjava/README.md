# Fongo

Fongo is an in-memory java implementation of MongoDB. It intercepts calls to the standard mongo-java-driver for finds, 
updates, inserts, removes and other methods. The primary use is for lightweight unit testing where you don't want to
spin up a mongod process.

## Tutorial

This instrument has not been supported since 2020. It is difficult to find any good tutorial. It would not be a good 
idea to use this tool, as it contains many vulnerabilities and there is no support from the developers.

The tool itself is quite handy and would probably have some popularity among developers if it were still supported.

In the current situation, if you start updating Spring Boot or any of the test dependencies, it becomes almost 
impossible to work with Fongo (conflicts can be almost unresolvable).

A good alternative to this tool: mongo-java-server