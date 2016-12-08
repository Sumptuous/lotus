FROM tomcat:7
ADD lotus/lotus/target/lotus.war /usr/local/tomcat/webapps/
CMD ["catalina.sh", "run"]
