FROM tomcat:10-jre17

ADD https://get.jenkins.io/war-stable/2.528.3/jenkins.war /usr/local/tomcat/webapps/jenkins.war

ADD https://search.maven.org/remotecontent?filepath=org/jolokia/jolokia-agent-jvm/2.4.2/jolokia-agent-jvm-2.4.2-javaagent.jar /opt/jolokia.jar

EXPOSE 8080 8778

ENV CATALINA_OPTS="-javaagent:/opt/jolokia.jar=port=8778,host=0.0.0.0"
