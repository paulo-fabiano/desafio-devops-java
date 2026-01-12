# ---- Build ---- #
FROM maven:3.8.3-openjdk-17 AS build

WORKDIR /app

COPY ./app/ .

RUN mvn clean install -DskipTests

# ---- Aplication ---- #
FROM tomcat:10.1-jdk17-corretto

ADD https://search.maven.org/remotecontent?filepath=org/jolokia/jolokia-agent-jvm/2.4.2/jolokia-agent-jvm-2.4.2-javaagent.jar /opt/jolokia.jar

COPY --from=build /app/target/task-list.war /usr/local/tomcat/webapps/task-list.war

EXPOSE 8080 8778

ENV CATALINA_OPTS="-javaagent:/opt/jolokia.jar=port=8778,host=0.0.0.0"
