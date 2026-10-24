# syntax=docker/dockerfile:1

FROM node:24-bookworm-slim AS node
FROM babashka/babashka:1.12.218 AS babashka

FROM clojure:temurin-21-tools-deps-bookworm AS builder
WORKDIR /app

COPY --from=node /usr/local/ /usr/local/
COPY --from=babashka /usr/local/bin/bb /usr/local/bin/bb

ENV CI=true \
    STORYBOOK_DISABLE_TELEMETRY=1

# Install build dependencies too: Storybook and Shadow CLJS are devDependencies.
COPY package.json package-lock.json ./
RUN --mount=type=cache,target=/root/.npm npm ci --include=dev --no-audit --no-fund

COPY deps.edn ./
RUN --mount=type=cache,target=/root/.m2 clojure -P -M:cljs-dev

COPY . .
# Keep the existing compile workflow: the custom Storybook indexer reads
# Shadow CLJS's unoptimized ESM metadata when constructing the story catalogue.
RUN --mount=type=cache,target=/root/.m2 npm run build-storybook

FROM nginxinc/nginx-unprivileged:stable-alpine AS runtime
COPY --from=builder /app/storybook-static/ /usr/share/nginx/html/

USER nginx
EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
    CMD wget -q -O /dev/null http://127.0.0.1:8080/index.html || exit 1
