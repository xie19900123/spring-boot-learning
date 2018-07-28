set MAVEN_OPTS= -Xms512m -Xmx2048m

mvn clean package docker:build -Dmaven.test.skip=true


