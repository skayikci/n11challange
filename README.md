# n11challange
- prerequisites
  * NPM
  * Node
  * Bower
  * Mongodb
  * Java 8
  * Maven

- to run the application please do the following:
  * mvn clean install (from the project directory)
  * java -Dserver.port=$PORT -jar target/Conference-0.0.1-SNAPSHOT.jar

- the application gets inputs from the "Add Item" tab, then creates a timesheet if the total duration reaches 540 minutes.
- the second tab shows the results.
