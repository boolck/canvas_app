# CanvasApp

CanvasApp is an canvas drawing application that takes command line arguments to draw shapes 
of canvas, lines, rectangles and box fill.

## How was TDD Done/ What's new in this release ?
Initially I picked up the solution submitted in version 0.1.0 and tried to upgrade it. It got too 
 complicated to format in any justified way to do TDD. So instead I created a new application & 
 started with top-down behaviour testing combined with Red-Green-Refactor approach. 
 Taking ideas from few blogs like  [TDD Bob Martin](https://blog.cleancoder.com/uncle-bob/2014/12/17/TheCyclesOfTDD.html)
 and [AgileData](http://agiledata.org/essays/tdd.html), I broke down my TDD approach into two :
 
 1. Acceptance TDD - I started with this and created a simple test in CanvasAppTest that takes user input and quits when Q is passed. 
 When this failed, I added the new functionality to CanvasApp and let it branch out to internal implementation to build up the behavior.
 Then I wrote the developer tests for those build up internal implementation classes like CommandHelper & DrawingExecutor which did 
 the heavy lifting (parsing input commands,writing canvas, etc.)
 After which I wrote  a test which takes input command of drawing Canvas and that test failed as expected -> refactored it -> ran again -> 
 and included DrawingExecutor to create a matrix ->  tested in its own test -> test succeeded.
 
 2. Developer TDD - Here I enhanced the tests for models Boxfill, Rectangle & Line from previous version, because they were pure no side effect functions .  
 Ultimately I also created CanvasEnum & CanvasException that were helpful to navigate the logic. 
 All the models and their logic wer enhanced by  writing edge test cases.

For reference please check below examples

## Example of a TDD implementation
```
1) Test for Quit Command
CanvasTest.whenFirstCommandIsQuitThenNoConsole (ATDD) 
CommandHelperTest.whenValidQuitCommandPassedThenStringTypeParsed (TDD)->
CommandTypeTest.whenQuitCommandPassedThenCommandTypeCreated (TDD)->

2) Test for Canvas Command
CanvasAppTest.whenCanvasCommandPassedThenCanvasPrintedToConsole (ATDD) 
CommandHelperTest.whenValidCanvasCommandPassedThenStringTypeParsed (TDD)->
CommandTypeTest.whenCanvasCommandPassedThenCommandTypeCreated (TDD)->
CanvasTest.whenCanvasIsCreatedThenDimensionAreValid (TDD)->
DrawingExecutorTest.whenCanvasCommandPassedThenCanvasCreatedWithValidBorders (TDD)

```

## Installation

Use the maven target to install the binary

```bash
mvn clean install
```

## Usage

```python
1. using jar 
java -jar <projecthome>/target/canvasapp-0.2.0-SNAPSHOT.jar

2. using application class
java CanvasApp

```

## Running the tests

```bash
mvn clean test
```

## Code Coverage

Added jacoco maven target to ensure net code coverage is above 90% \
[jococo coverage](http://localhost:63342/drawing-program/target/site/jacoco/index.html)

Element | Line Coverage 
--- | --- 
Total   |   96%
com.kb.canvas   |   70%
com.kb.canvas.model |   99%
com.kb.canvas.executor	|	96%	
com.kb.canvas.command	|	98%	
com.kb.canvas.excp      |      100%	


### Break down into end to end tests

CanvasAppTest - 
Main Class to test CanvasApp that takes user input commands & prints console. 
The main class is updated to allow dependenc injection of ByteArrayInputStream and PrintStream from tests for supportability.

DrawingExecutorTest - 
Test for DrawingExecutor that does the heavy lifting of updating console datastructures & printing them.
Each child model(line, rectange & boxfill) are checked against an Enum & created as suppliers for maintainibility.

Tests in com.kb.canvas.model - This has 4 models used for testing specific entity creation: \
    a. BoxFillTest - creates box fill with a color \
    b. LineTest - supports horizontal and vertical line creation \
    c. CanvasTest - initializes canvas with borders \
    d. RectangleTest - creates hollow rectangle between 4 coordinates.

Tests in com.kb.canvas.command - \
    a. CommandHelperTest - Tests the command parsing and validation logic in CommandHelper \
    b. CommandTypeTest - Tests the Enum CommandType creation \
    
Tests in com.kb.canvas.excp - Exception Test classes

## Authors

* **Kunal Boolchandani** - *Initial work* - [boolck](https://github.com/boolck)

## References

* http://agiledata.org/essays/tdd.html
* https://blog.cleancoder.com/uncle-bob/2014/12/17/TheCyclesOfTDD.html
* https://www.jacoco.org/jacoco/trunk/doc/index.html

