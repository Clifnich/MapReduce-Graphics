mvn clean compile assembly::single
mv target/chord-0.0.1-SNAPSHOT-jar-with-dependencies.jar ~/Desktop/slave-server.jar
cd ~/Desktop
java -jar slave-server.jar 8080 8081 &
java -jar slave-server.jar 8080 8082 &
java -jar slave-server.jar 8080 8083 &
