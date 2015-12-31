@ECHO OFF
del /F src\test\resources\features-processed\*.feature
mvn clean test
