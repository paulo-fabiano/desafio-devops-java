FROM tomcat:10-jre17

ADD https://get.jenkins.io/war-stable/2.528.3/jenkins.war /usr/local/tomcat/webapps/jenkins.war

ADD https://search.maven.org/remotecontent?filepath=org/jolokia/jolokia-agent-jvm/2.4.2/jolokia-agent-jvm-2.4.2-javaagent.jar /opt/jolokia.jar

HEALTHCHECK --interval=30s --timeout=3s --retries=3 CMD curl -f http://localhost:8080/jenkins/health || exit 1

EXPOSE 8080 8778