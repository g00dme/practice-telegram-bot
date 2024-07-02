FROM eclipse-temurin:21-jdk
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install
CMD ["java", "-jar", "target/myapp-1.0-SNAPSHOT.jar"]
