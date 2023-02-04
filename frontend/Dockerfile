# BUILD
FROM alpine:latest as build
RUN apk upgrade \
    && apk add npm

COPY . /source_code/

WORKDIR /source_code
ENV NODE_OPTIONS=--openssl-legacy-provider \
    DISABLE_ESLINT_PLUGIN=true
RUN --mount=type=cache,target=node_modules \
    npm install --progress=false --prefer-offline --no-audit
RUN --mount=type=cache,target=node_modules \
    npm run serve