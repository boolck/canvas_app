package com.kb.canvas;

import com.kb.canvas.excp.CanvasException;
import com.kb.canvas.excp.CanvasWrongArgsException;
import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class CanvasAppTest {

    private CanvasApp app;

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream printStream = new PrintStream(outContent);

    @Before
    public void setup(){
        app = new CanvasApp(printStream);
        outContent.reset();
    }

    @AfterClass
    public static void tearDown() throws IOException{
        outContent.close();
        printStream.close();
    }

    @Test
    public void whenCanvasCommandPassedThenCanvasPrintedToConsole()  throws CanvasException{
        String command = new StringBuilder("C 20 4").append("%n").append("Q").toString();
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(String.format(command).getBytes());
        app.run(inputStream);
        String expected =
                        "----------------------\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "----------------------\n";

        Assert.assertEquals(expected,outContent.toString());
    }

    @Test
    public void whenLineCommandPassedTThenCanvasPrintedToConsole() throws CanvasException{
        String command = new StringBuilder("C 20 4%n").append("L 1 2 6 2%n").append("Q").toString();
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(String.format(command).getBytes());
        app.run(inputStream);

        String expected =
                                "----------------------\n" +
                                "|                    |\n" +
                                "|                    |\n" +
                                "|                    |\n" +
                                "|                    |\n" +
                                "----------------------\n" +
                                "----------------------\n" +
                                "|                    |\n" +
                                "|xxxxxx              |\n" +
                                "|                    |\n" +
                                "|                    |\n" +
                                "----------------------\n";
        Assert.assertEquals(expected,outContent.toString());
    }

    @Test
    public void whenTwoLinesCommandPassedThenCanvasPrintedToConsole() throws CanvasException {
        String command = new StringBuilder("C 20 4%n").append("L 1 2 6 2%n").append("L 6 3 6 4%n").append("Q").toString();
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(String.format(command).getBytes());
        app.run(inputStream);

        String expected =
                        "----------------------\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "----------------------\n" +
                        "----------------------\n" +
                        "|                    |\n" +
                        "|xxxxxx              |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "----------------------\n" +
                        "----------------------\n" +
                        "|                    |\n" +
                        "|xxxxxx              |\n" +
                        "|     x              |\n" +
                        "|     x              |\n" +
                        "----------------------\n";

        Assert.assertEquals(expected,outContent.toString());
    }

    @Test
    public void whenQuitCommandPassedThenFlagReturned() throws CanvasException {
        app.createCanvasUntilQuitCommand("C 20 4");
        boolean continueDrawing = app.createCanvasUntilQuitCommand("Q");
        Assert.assertFalse(continueDrawing);
    }

    @Test(expected= CanvasWrongArgsException.class)
    public void whenUnknownCommandPassedThenExceptionThrown() throws CanvasException {
        app.createCanvasUntilQuitCommand("XYZ 2 10");
    }

    @Test(expected= CanvasWrongArgsException.class)
    public void whenInvalidCommandPassedThenExceptionThrown() throws CanvasException {
        app.createCanvasUntilQuitCommand("C210");
    }

    @Test(expected= CanvasWrongArgsException.class)
    public void whenEmptyCommandPassedThenExceptionThrown() throws CanvasException {
        app.createCanvasUntilQuitCommand("");
    }


    @Test
    public void whenLineCreatedThenConsoleDrawn() throws CanvasException {
        app.createCanvasUntilQuitCommand("C 20 4");
        outContent.reset();
        app.createCanvasUntilQuitCommand("L 1 2 6 2");
        String expected =
                "----------------------\n" +
                "|                    |\n" +
                "|xxxxxx              |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "----------------------\n";

        Assert.assertEquals(expected,outContent.toString());
    }

    @Test(expected=CanvasWrongArgsException.class)
    public void whenLineCreatedOutsideCanvasThenExcpThrown() throws CanvasException {
        app.createCanvasUntilQuitCommand("C 20 4");
        outContent.reset();
        app.createCanvasUntilQuitCommand("L 1 21 6 21");
    }

    @Test
    public void whenTwoLinesCreatedThenConsoleDrawn() throws CanvasException {
        app.createCanvasUntilQuitCommand("C 20 4");
        outContent.reset();
        app.createCanvasUntilQuitCommand("L 1 2 6 2");
        outContent.reset();
        app.createCanvasUntilQuitCommand("L 6 3 6 4");

        String expected =
                "----------------------\n" +
                "|                    |\n" +
                "|xxxxxx              |\n" +
                "|     x              |\n" +
                "|     x              |\n" +
                "----------------------\n";

        Assert.assertEquals(expected,outContent.toString());
    }

    @Test
    public void whenOverlappingLinesCreatedThenConsoleDrawnCorrectly() throws CanvasException {
        app.createCanvasUntilQuitCommand("C 20 4");
        outContent.reset();
        app.createCanvasUntilQuitCommand("L 1 2 6 2");
        outContent.reset();
        app.createCanvasUntilQuitCommand("L 3 2 6 2");

        String expected =
                "----------------------\n" +
                "|                    |\n" +
                "|xxxxxx              |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "----------------------\n";

        Assert.assertEquals(expected,outContent.toString());
    }

    @Test
    public void whenRectangeCreatedThenConsoleDrawn() throws CanvasException {
        app.createCanvasUntilQuitCommand("C 20 4");
        outContent.reset();
        app.createCanvasUntilQuitCommand("L 1 2 6 2");
        outContent.reset();
        app.createCanvasUntilQuitCommand("L 6 3 6 4");
        outContent.reset();
        app.createCanvasUntilQuitCommand("R 14 1 18 3");

        String expected =
                "----------------------\n" +
                "|             xxxxx  |\n" +
                "|xxxxxx       x   x  |\n" +
                "|     x       xxxxx  |\n" +
                "|     x              |\n" +
                "----------------------\n";

        Assert.assertEquals(expected,outContent.toString());
    }

    @Test
    public void whenBoxCreatedThenConsoleDrawn() throws CanvasException {
        app.createCanvasUntilQuitCommand("C 20 4");
        outContent.reset();
        app.createCanvasUntilQuitCommand("L 1 2 6 2");
        outContent.reset();
        app.createCanvasUntilQuitCommand("L 6 3 6 4");
        outContent.reset();
        app.createCanvasUntilQuitCommand("R 14 1 18 3");
        outContent.reset();
        app.createCanvasUntilQuitCommand("B 10 3 o");

        String expected =
                "----------------------\n" +
                "|oooooooooooooxxxxxoo|\n" +
                "|xxxxxxooooooox   xoo|\n" +
                "|     xoooooooxxxxxoo|\n" +
                "|     xoooooooooooooo|\n" +
                "----------------------\n";

        Assert.assertEquals(expected,outContent.toString());
    }

}
