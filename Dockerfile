# Usa una imagen base de OpenJDK para Java 22
FROM openjdk:21-jdk as build

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo pom.xml y el código fuente
COPY pom.xml ./
COPY src ./src

# Construye el JAR
RUN apt-get update && apt-get install -y maven
RUN mvn clean package

# Usa una imagen base de OpenJDK para la ejecución
FROM openjdk:21-jdk

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo JAR construido
COPY --from=build /app/target/rapidsecure-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que la aplicación escucha
EXPOSE 8080

# Define el comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
