FROM maven:3.9.9-eclipse-temurin-11 AS builder

RUN mkdir /build
WORKDIR /build

ENV MAVEN_OPTS "-Duser.language=ru -Duser.country=RU -Dfile.encoding=UTF-8"
ENV LANG ru_RU.UTF-8

COPY . /build

RUN mvn -pl backend-service clean install

# Распаковка JAR для послойной записи Spring Boot приложения
FROM bellsoft/liberica-openjdk-alpine-musl:11 AS unpack
WORKDIR /workspace
COPY --from=builder /build/backend-service/target/backend-service-1.0.0.jar ./app.jar
RUN java -Djarmode=layertools -jar app.jar extract

# Сборка образа
FROM bellsoft/liberica-openjdk-alpine-musl:11

RUN apk add --update --no-cache curl jq && \
    rm -rf /var/cache/* && \
    rm -rf /var/lib/apk/* && \
    rm -f /sbin/apk && \
    rm -rf /etc/apk && \
    rm -rf /lib/apk && \
    rm -rf /usr/share/apk && \
    rm -rf /var/lib/apk

# Пользователь для приложения
RUN addgroup -S java && adduser -S javauser -G java \
    && mkdir /workspace && chown -R javauser:java /workspace

WORKDIR /workspace

# Слои приложения
## Spring|Библиотеки
COPY --from=unpack /workspace/dependencies/ ./
COPY --from=unpack /workspace/spring-boot-loader/ ./
COPY --from=unpack /workspace/snapshot-dependencies/ ./
## Приложение
COPY --from=unpack /workspace/application/ ./

# Запускать от своего имени, т.к. запуск от root - bad practice
USER javauser

# Spring Boot Launcher
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]

