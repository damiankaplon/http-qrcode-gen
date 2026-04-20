FROM eclipse-temurin:25-jdk-alpine
WORKDIR /app
COPY ./build ./
EXPOSE 8080
ENTRYPOINT ["/app/install/qrgenerator/bin/qrgenerator"]
