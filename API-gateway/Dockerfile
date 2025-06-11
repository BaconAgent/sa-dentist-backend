FROM jelastic/maven:3.9.9-openjdk-23-almalinux-9 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -pl API-gateway -am -DskipTests

FROM openjdk:23
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 9000

CMD ["java", "-jar", "app.jar"]