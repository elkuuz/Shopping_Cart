# Shopping Cart (Maven)

This project is now set up as a Maven Java project.

## Requirements

- JDK 21
- Maven 3.9+

## Project layout

- `src/main/java` - application code
- `src/test/java` - tests
- `pom.xml` - Maven build definition

##Start

```bash
mvn clean test
mvn -q exec:java -Dexec.mainClass="com.shoppingcart.Main"
```

