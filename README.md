# Тестовое задание

## Сборка и запуск dev (для разработки)
Необходим запущенная база postgreSQL на localhost:5432 с БД estore, пользователем user и паролем password

Или установить переменные окружения:
- `DB_URL` - адрес подключения БД (например "jdbc:postgresql://localhost:5432/estore")
- `DB_USER` - пользователь БД
- `DB_PASSWORD` - паролль пользователя БД

Собрать проект `mvn clean install`

Запуск backend `mvn -pl backend-service spring-boot:run`

Запуск frontend `npm run dev --prefix ./frontend-service/`

Сервис доступен по [http://localhost:3000](http://localhost:3000)

## Сборка и запуск в Docker

Выполнить команды в корне проекта:
- `docker build -f .\backend-service\Dockerfile -t back .` - сборка backend
- `docker build -f .\frontend-service\Dockerfile -t front .` - сборка frontend
- `docker-compose up -d` - запуск всего необходимого

Сервис доступен по [http://localhost:8080](http://localhost:8080)

Порт сервиса backend 8081 выведен, для просмотра swagger. При эксплуатации порт должен быть закрыт (доступ к rest организован через обратный прокси nginx в контейнере frontend)

**EStore-microservice**

Шаблон микросервиса запускается в среде выполнения **JVM 11**

Собран для крайней версии spring-boot **2.7.18**, добавлен springdoc-ui для автогенерации документации по разработанным rest-методам

**Доступ к swagger-ui:** http://localhost:8081/estore-api.html