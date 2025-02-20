FROM docker.io/library/eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /src/advshop
COPY . .
RUN ./gradlew clean bootJar

FROM docker.io/library/eclipse-temurin:21-jre-alpine AS runner

ARG USER_NAME=advshop
ARG USER_UID=1001
ARG USER_GID=1001

RUN groupadd -g ${USER_GID} ${USER_NAME} && \
    useradd -h /opt/advshop -D -u ${USER_UID} -g ${USER_GID} ${USER_NAME}

USER ${USER_NAME}

WORKDIR /opt/advshop
COPY --from=builder --chown=${USER_UID}:${USER_GID} /src/advshop/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java"]
CMD ["-jar", "app.jar"]
