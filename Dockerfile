FROM java:8
WORKDIR /app
COPY /build/libs/my-exercise-1.0-all.jar /app
CMD ["java", "-Xmx200m", "-jar", "my-exercise-1.0-all.jar"]
EXPOSE 8080
