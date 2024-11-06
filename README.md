# kotlin-spring-jpa

```
docker run -p 8080:8080 spring-kotlin-jpa
```

```
docker pull postgres:9 
```

```
docker run -p 5432:5432 --name postgres9 -e POSTGRES_PASSWORD=docker -d  postgres:9
```

```
./gradlew bootRun

mvn spring-boot:run
```

```
docker build . --tag user-api

docker run --network host --name user-api -p 8080:8080 user-api
```

```
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"email":"e-mail"}' \
  localhost:8080/email
```
