FROM maven:3.9.9-eclipse-temurin-11 AS builder

#RUN apk add libstdc++6 libstdc++

RUN mkdir /build
WORKDIR /build

ENV MAVEN_OPTS "-Duser.language=ru -Duser.country=RU -Dfile.encoding=UTF-8"
ENV LANG ru_RU.UTF-8

COPY . /build

RUN mvn -pl frontend-service clean install

# Сборка образа
FROM nginx:stable-alpine AS ngi

COPY --from=builder /build/frontend-service/dist /usr/share/nginx/html
COPY --from=builder /build/frontend-service/nginx.conf  /etc/nginx/conf.d/default.conf
EXPOSE 8080
