# Shading and dependency-reduced-pom.xml

By default the shade plugin replaces the artifact being built with the shaded jar.
It also creates a dependency-reduced-pom.xml and installs it with the shaded jar.

The `dependency-reduced-pom.xml` has no references to any of the dependencies packed into the shaded fat jar.
This way any client of the fat-jar will not need to pull in additional libraries as all
the required classes are already included in the fat-jar.

The `greeter-integrationtests` module sees the classes from the `greeter` module.
Hence this is no reason to explicitly add dependency on the `greeter` module.

There is no automatic way atleast in a module that uses spring-boot to shrink the fat-jar.

Adding `<minimize>true</minimized>` to the shade plugin's configuration does not include
all the jars. This is probably because of spring-boot providing the links to the different 
classes.

[`Proguard`](https://www.guardsquare.com/en/products/proguard/manual/examples) also is not able to shrink the jar correctly because it is not able to determine
all the classes required. Again this may be due to spring-boot handling the relationships 
between the classes.

The only way to shrink the fat-jar with all the required dependencies included is to manually
include all the required artifacts.
The downside to this strategy is that one does not always remember to add an `include` filter
for any new jar added to the original `pom.xml`.

A safety net would be to add a separate [`greeter-integration-tests`](https://github.com/66-24/greeter_integration_tests) 
project to host all the integration tests for the `greeter` module. The `greeter-integration-tests` module only
depends on the `greeter` fat-jar installed with the dependency-reduced-pom.xml.
Now any missing jar will hopefully throw a `ClassDefNotFoundException`. 

Caveat
--- 
`ExecutorService` as well as java lambdas may not always bubble up the `ClassDefNotFoundException`
This means that the integration test may not always fail