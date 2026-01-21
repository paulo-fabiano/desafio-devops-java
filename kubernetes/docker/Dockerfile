FROM tomcat:10-jre17

ADD https://get.jenkins.io/war-stable/2.528.3/jenkins.war /usr/local/tomcat/webapps/jenkins.war

ADD https://github.com/prometheus/jmx_exporter/releases/download/1.5.0/jmx_prometheus_javaagent-1.5.0.jar /opt/jmx_exporter/jmx_exporter.jar
RUN echo "rules: [{ pattern: '.*' }]" > /opt/jmx_exporter/config.yaml

HEALTHCHECK --interval=30s --timeout=3s --retries=3 CMD curl -f http://localhost:8080/jenkins/health/ || exit 1

EXPOSE 8080 9404