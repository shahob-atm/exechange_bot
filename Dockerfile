# 1️⃣ Build bosqichi: Maven va JDK bilan build container
FROM maven:3.9.0-eclipse-temurin-17 AS build

# Ishchi katalogni o‘rnatish
WORKDIR /app

# Loyiha fayllarini nusxalash
COPY . .

# Maven build (jar faylni yaratish)
RUN mvn -B clean package -DskipTests

# 2️⃣ Final bosqich: Yengil Java runtime uchun container
FROM eclipse-temurin:17-jdk

# Ishchi katalogni o‘rnatish
WORKDIR /app

# Jar faylni build container'dan nusxalash
COPY --from=build /app/target/*.jar app.jar

# Run bot
CMD ["java", "-jar", "app.jar"]
