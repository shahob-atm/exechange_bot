# Java 17 bazaviy imidj
FROM eclipse-temurin:17-jdk

# Ishchi papkani yaratish
WORKDIR /app

# Loyiha fayllarini nusxalash
COPY . .

# Maven build
RUN mvn -B clean install -DskipTests

# Run
CMD ["java", "-jar", "target/exechange_bot.jar"]
