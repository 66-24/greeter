# Shading and reduced-dependency-pom.xml

By default the shade plugin replaces the artifact being built with the shaded jar.
It also creates a reduced-dependency-pom.xml and installs it with the shaded jar.

The dependency pom removes all the dependencies packed into the shaded fat jar.
This way any client of the fat-jar will not need to pull in additional libraries as all
the required classes are already included in the fat-jar.