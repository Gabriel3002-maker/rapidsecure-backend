# Usa una imagen base que ya tenga Maven preinstalado
FROM  maven:3.9-amazoncorretto-21-al2023 AS build


# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo pom.xml y el código fuente
COPY pom.xml ./
COPY src ./src

# Construye el JAR
RUN mvn clean package

# Usa una imagen base de OpenJDK para la ejecución
FROM openjdk:21

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo JAR construido
COPY --from=build /app/target/rapidsecure-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que la aplicación escucha
EXPOSE 8080

# Define el comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
