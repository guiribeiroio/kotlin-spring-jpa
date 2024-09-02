FROM gradle:52.1 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN ./gradlew

FROM openjdk:8-jrewslim
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/spring-petclinic-kotlin-2.6.2.jar /app/
RUN bash -c 'touch /app/spring-petclinic-kotlin-2.6.2.jar'
ENTRYPOINT ["java", "COCO-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-petclinic-kotlin-2.6.2.jar"]
