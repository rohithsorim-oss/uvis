set JAVA_HOME=d:\java\jdk-21.0.11
path=%path%;D:\apache-maven-3.9.16\bin;
mvn clean verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=uvis -Dsonar.projectName='uvis' -Dsonar.host.url=http://localhost:9000 -Dsonar.token=sqp_dff47e0d02f2e1c4ab085f68ca3898dbceb535f4