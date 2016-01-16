@ECHO OFF
del /F src\test\resources\features-processed\*.feature
xcopy /s src\test\resources\properties\TestConfig-LocalFirefox.properties src\test\resources\properties\TestConfig.properties
mvn clean test
