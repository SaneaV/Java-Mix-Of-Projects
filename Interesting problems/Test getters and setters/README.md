# TEST GETTERS AND SETTERS

## Basic details
This project was developed to simplify code coverage of getters and setters.

`GetterSetterPair.java` - represents get and set methods of specific field (e.g `String.java`)<br>
`GetterSetterTest.java` - represents abstract class with basic logic to collect default suppliers, add custom suppliers, setup 
ignored methods and test getters and setters.<br>
`DomainGetterSetterTest.java` - represents extension of `GetterSetterTest.java` where you can specify target classes for 
testing, provide list of ignored methods and map of custom suppliers.<br>

To execute test you have to specify list of classes into `DomainGetterSetterTest.java` -> `getData` static `Stream` and run 
`testDomain` method.

## Additional details
`GetterSetterTest.java` has two public methods that allow you to run get/set tests. These methods are: 
`getStringGetterSetterPairMapFirstOption` and `getStringGetterSetterPairMapSecondOption`. Second option represents simpler way 
to extract get/set methods. Execution time ~ is the same for both methods.

## Used libraries
`lombok` - to generate get and set methods.<br>
`jacoco` - to generate test reports (run `mvn clean verify` to generate `target/site/index.html`). 
`junit` - used for basic test purposes.