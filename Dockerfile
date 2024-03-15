FROM gradle:8.6.0-jdk17 as builder
WORKDIR application
COPY --chown=gradle:gradle build.gradle.kts settings.gradle.kts store-summary-data.csv ./
COPY --chown=gradle:gradle src ./src
#
RUN gradle assemble --no-daemon
#
FROM eclipse-temurin:17
WORKDIR application
###
### Copy the jar file in and name it app.jar.
###
COPY --from=builder home/gradle/application/build/libs/minMax.jar minMax.jar
COPY --from=builder home/gradle/application/store-summary-data.csv store-summary-data.csv
#
##
## The command to run when the container starts.
#
ENTRYPOINT java -jar minMax.jar