# BUILD FE
FROM alpine:latest as FE-build
RUN apk upgrade \
    && apk add npm

COPY frontend /source_code

WORKDIR /source_code
ENV NODE_OPTIONS=--openssl-legacy-provider \
    DISABLE_ESLINT_PLUGIN=true

RUN --mount=type=cache,target=node_modules \
    npm install --progress=false --prefer-offline --no-audit \
RUN --mount=type=cache,target=node_modules \
    npm run build




# BUILD BE and bundle FE
FROM alpine:latest as BE-build
RUN apk upgrade \
    && apk add openjdk11-jdk maven

COPY . /source_code/
COPY --from=FE-build /source_code/dist /source_code/src/main/resources/static

WORKDIR /source_code
RUN --mount=type=cache,target=/root/.m2 \
    mvn clean package




# RUN
FROM alpine:latest
RUN apk upgrade \
    && apk add openjdk11-jre

COPY --from=BE-build /source_code/target/ourstory-0.0.1-SNAPSHOT.jar /app/ourstory.jar
ENTRYPOINT java -jar /app/ourstory.jar

