# Roullete
Microservice for online betting roulette

## Table of contents
* [Technologies](#technologies)
* [Prerequisites](#prerequisites)
* [Setup](#setup)
* [Usage](#usage)

## Technologies
Project is created with:
* Java version: 1.8
* Spring-boot version: 2.4.1
* Jedis version: 3.4.1
* Maven version: 3.6.1

## Prerequisites
* Maven version: 3.6.1
* Docker installed
* [Redis installed](#redis)
* Network created with Docker: ```docker network create --subnet 172.168.0.1/24 --gateway 172.168.0.2 -d bridge roulette-network```

## Redis
Run Redis with Docker: ```docker run -d -p 6379:6379 --name roulette-redis --network roulette-network --ip 172.168.0.21 redis```

## Setup
To run this project, execute it using maven and docker:

```
$ cd .../.../roulette
$ mvn clean install
$ docker build -t roulette-microservice:1.0.0 .
$ docker run -d -p 8080:8080 --name RouletteMicroservice --network roulette-network --ip 172.168.0.22 roulette-microservice:1.0.0
```
To view the service initialization log running: `docker logs container_id`

## Usage
Load this [collection](Roulette.postman_collection.json) in postman

