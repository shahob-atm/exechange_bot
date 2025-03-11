# Maven va JDK bilan build container
FROM maven:3.9.0-eclipse-temurin-17 AS build

WORKDIR /app

# Loyiha fayllarini container ichiga nusxalash
COPY . .

# Maven build (jar faylni yaratish)
RUN mvn -B clean package -DskipTests

# Final image
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Jar faylni to‘g‘ri yo‘ldan olish
COPY --from=build /app/target/*.jar app.jar

# Run bot
CMD ["java", "-jar", "app.jar"]
