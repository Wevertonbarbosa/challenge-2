FROM openjdk:17.0.2

ARG RUN_JAVA_VERSION=1.3.8
ENV LANG='en_US.UTF-8' LANGUAGE='en_US:en'
  # ENV JAVA_OPTIONS="-javaagent:/agent/agent.jar"
  
  
  # Install java and the run-java script
  # Also set up permissions for user 1001
RUN mkdir -p /application/files
RUN chown -vR 1001:1001 /application
RUN mkdir /deployments \
&& chown 1001 /deployments \
&& chmod "g+rwX" /deployments \
&& chown 1001:root /deployments \
&& curl https://repo1.maven.org/maven2/io/fabric8/run-java-sh/${RUN_JAVA_VERSION}/run-java-sh-${RUN_JAVA_VERSION}-sh.sh -o /deployments/run-java.sh \
&& chown 1001 /deployments/run-java.sh \
&& chmod 540 /deployments/run-java.sh


COPY /target/*.jar /deployments/app.jar

EXPOSE 8080
USER 1001

ENTRYPOINT [ "/deployments/run-java.sh" ]