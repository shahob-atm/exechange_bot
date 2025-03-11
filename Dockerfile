# Maven va JDK 17 o‘rnatilgan imidj
FROM maven:3.9.0-eclipse-temurin-17 AS build

# Ishchi katalog
WORKDIR /app

# Loyiha fayllarini nusxalash
COPY . .

# Maven build
RUN mvn -B clean install -DskipTests

# Final image (faqat JDK kerak bo'lsa)
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/exechange_bot.jar .

# Run bot
CMD ["java", "-jar", "exechange_bot.jar"]

