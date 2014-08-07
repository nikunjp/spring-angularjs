spring-angularjs
================

Demo of Spring MVC REST and AngularJS [extended robharrop/spring-angularjs @ github]

Application demonstrating an AngularJS client talking to a Spring REST-powered backend.

## Building

To build the WAR file simply run `mvn package`. This puts the WAR
file in `target/spring-angularjs.war`.

You can deploy this WAR in any servlet container.

## Running

You can run the app in Jetty using `mvn jetty:run`.

Once started, the app is available at `http://localhost:8080`.

## Testing

The standard Java tests can be run with `mvn test`.

### JavaScript tests

The JavaScript tests use casperjs. Rather than rely on a
global install of casperjs, this project uses a local install.

To setup casperjs run `mvn pre-integration-test -Pcasper-setup`. This command will download and setup casperjs (and phantomjs) in basedir of project. 

To run casperjs tests run `mvn integration-test -Pqa`. This command will run all tests under `src/test/js/casper/*.js` (Make sure your server is up and running at specified location in pom.xml)

## To Do

- By default JavaScript tests run can be executed from windows machine, it should be executed from linux box as well
- Existing casperjs tests are very simple, they should be improved to test CRUD operations

[ ![Codeship Status for nikunjp/spring-angularjs](https://www.codeship.io/projects/b882aca0-0023-0132-7895-0aeb6fd0d794/status)](https://www.codeship.io/projects/29869)



