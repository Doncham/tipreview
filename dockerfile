FROM openjdk:17

WORKDIR /app

# Assuming the jar file is present in the build/libs/ directory of the host
COPY build/libs/tip-application.jar /app/

EXPOSE 8080

CMD ["java", "-jar", "tip-application.jar"]
