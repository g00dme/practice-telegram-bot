FROM eclipse-temurin:21-jdk
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package
CMD ["java", "-jar", "target/Logg-1.0-SNAPSHOT.jar"]
