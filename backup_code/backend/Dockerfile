# 阶段1：Maven 编译打包
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# 阶段2：JRE 运行（镜像体积从 ~600MB 降至 ~200MB）
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/campusbuddy-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
# 生产环境推荐通过环境变量注入配置，而非写死在 yml
ENTRYPOINT ["java", "-jar", "app.jar"]