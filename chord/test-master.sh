mvn clean package -Dmaven.test.skip=true
cp target/chord-0.0.1-SNAPSHOT.war ~/Programs/Jetty/webapps/chord.war
cd ~/Programs/Jetty
java -jar start.jar
