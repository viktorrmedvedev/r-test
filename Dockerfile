# Stage 1: Build the application
FROM amazoncorretto:17 as build
WORKDIR /workspace/app

# Copy gradle configuration
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Copy source code
COPY src src

# Build the JAR file
RUN ./gradlew build

# Stage 2: Create the final image
FROM amazoncorretto:17
WORKDIR /app
COPY --from=build /workspace/app/build/libs/r-test.jar fda.jar
ENTRYPOINT ["java", "-jar", "fda.jar"]