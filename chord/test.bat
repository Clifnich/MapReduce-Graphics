call mvn clean package -Dmaven.test.skip=true
cd target
del chord.war
ren chord-0.0.1-SNAPSHOT.war chord.war
copy chord.war C:\\Users\puqian\Programs\jetty2\webapps\ /Y
pause