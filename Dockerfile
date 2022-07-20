FROM gradle as builder

COPY --chown=gradle:gradle . /src
WORKDIR /src
RUN gradle shadowJar --no-daemon

FROM openjdk

COPY --from=builder /src/build/libs/translation_bot.jar /bin/runner/run.jar
COPY .env /bin/runner/

WORKDIR /bin/runner

CMD ["java","-jar","run.jar"]