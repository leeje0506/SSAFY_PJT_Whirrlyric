FROM gradle:8.7-jdk17 as builder
WORKDIR /app
COPY build.gradle settings.gradle ./
RUN gradle build -x test --parallel --continue > /dev/null 2>&1 || true # 도커 이미지에 의존성 캐싱

COPY . ./
RUN gradle build -x test --parallel

FROM azul/zulu-openjdk:17
WORKDIR /app
COPY --from=builder /app/build/libs/jayul-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "jayul-0.0.1-SNAPSHOT.jar"]
