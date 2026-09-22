# Build examples

Maven and Gradle fundamentals for Java projects.

## Maven

```bash
# Simple app
mvn -q exec:java -f build/maven/simple-app/pom.xml

# Multi-module (build all, run app)
mvn -q install -f build/maven/multi-module/parent/pom.xml
mvn -q exec:java -f build/maven/multi-module/parent/app/pom.xml

# BOM (Bill of Materials) — import in child POMs:
# <dependencyManagement>
#   <dependencies>
#     <dependency>
#       <groupId>com.javamastery</groupId>
#       <artifactId>javamastery-bom</artifactId>
#       <version>1.0.0</version>
#       <type>pom</type>
#       <scope>import</scope>
#     </dependency>
#   </dependencies>
# </dependencyManagement>
```

## Gradle

Install Gradle or use a wrapper (`gradle wrapper` once Gradle is available).

```bash
cd build/gradle/simple-app
gradle run
```

See [docs/04-reference/13-BuildTools.md](../docs/04-reference/13-BuildTools.md) for concepts.
