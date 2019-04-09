# Did I Raid That? 
Keep tabs on your Pokemon Go raids! With this desktop application you can log raids, sign up for raids and see where you have raided as well as check which EX raids you're eligible to attend. 

## Documentation
[Instructions]() N/A

[Software requirements specification](https://github.com/sinilandia/ohte2019/blob/master/Documentation/Srs.md)

[Architecture]() N/A

[Tests]() N/A

[Timekeeping](https://github.com/sinilandia/ohte2019/blob/master/Documentation/Timekeeping.md)

## Releases

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

### Execute jar

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
