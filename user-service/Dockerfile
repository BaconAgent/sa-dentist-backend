FROM jelastic/maven:3.9.9-openjdk-23-almalinux-9 AS build
WORKDIR /app

COPY . .
RUN mvn clean package -pl user-service -am -DskipTests

FROM openjdk:23
WORKDIR /app
COPY --from=build /app/user-service/target/*.jar app.jar

EXPOSE 8082

CMD ["java", "-jar", "app.jar"]