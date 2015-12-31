@ECHO OFF
del /F src\test\resources\features-processed\*.feature
mvn clean test
mvn test site
