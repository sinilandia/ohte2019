# Did I Raid That? 
Keep tabs on your Pokemon Go raids! With this desktop application you can log raids, sign up for raids and see where you have raided. 

## Documentation
[Instructions](https://github.com/sinilandia/ohte2019/blob/master/Documentation/instructions.md) 

[Software requirements specification](https://github.com/sinilandia/ohte2019/blob/master/Documentation/Srs.md)

[Architecture](https://github.com/sinilandia/ohte2019/blob/master/Documentation/Architecture.md)

[Tests]() N/A Not many tests and no document explaining them.

[Timekeeping](https://github.com/sinilandia/ohte2019/blob/master/Documentation/Timekeeping.md)

## Releases
[All releases](https://github.com/sinilandia/ohte2019/releases)

[Newest release: Loppupalautus](https://github.com/sinilandia/ohte2019/releases/tag/loppupalautus)

## Terminal commands

### Tests
Execute tests by typing into the terminal

```
mvn test
```

Test coverage report

```
mvn jacoco:report
```

View the test coverage report from _target/site/jacoco/index.html_

### Generate executable jar file

Command

```
mvn package
```

will create into the folder _target_ a jar file _Didiraidthat-1.0-SNAPSHOT.jar_

### JavaDoc

Create JavaDoc command

```
mvn javadoc:javadoc
```

View javadoc with your browser from the location _target/site/apidocs/index.html_

### Checkstyle

To execute audits in  [checkstyle.xml]() use the command

```
 mvn jxr:jxr checkstyle:checkstyle
```

You can view the errors with your browser from the location _target/site/checkstyle.html_
