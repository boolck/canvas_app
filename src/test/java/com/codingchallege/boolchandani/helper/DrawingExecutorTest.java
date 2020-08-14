package com.codingchallege.boolchandani.helper;

import com.codingchallenge.boolchandani.entities.Canvas;
import com.codingchallenge.boolchandani.helper.CanvasHelper;
import com.codingchallenge.boolchandani.helper.DrawingExecutor;
import com.codingchallenge.boolchandani.model.CommandType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.codingchallenge.boolchandani.helper.CanvasHelper.VALID_ARGS_ERROR_MSG;
import static org.junit.Assert.assertEquals;

public class DrawingExecutorTest {

    private DrawingExecutor executor = new DrawingExecutor();
    private String helpMessage = CanvasHelper.getHelpMessage();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream out = new PrintStream(outContent);
    private final PrintStream originalOut = new PrintStream(System.out);

    /**
     * Overriding the default print stream to be bytestream
     */
    @Before
    public void setUpStreams() {
        System.setOut(out);
    }

    /**
     * Resetting the bytestream back to default System.out
     */
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testNullCommand(){
        executor.draw(null,null,null,out);
        Assert.assertTrue(outContent.toString().contains(VALID_ARGS_ERROR_MSG));
        Assert.assertTrue(outContent.toString().contains(helpMessage));
    }

    @Test
    public void testEmptyParameters(){
        executor.draw(CommandType.CMD_CANVAS,new String[0],null,out);
        Assert.assertTrue(outContent.toString().contains(VALID_ARGS_ERROR_MSG));
        Assert.assertTrue(outContent.toString().contains(helpMessage));
    }

    @Test
    public void testCreateCanvas(){
        Canvas canvas = executor.draw(CommandType.CMD_CANVAS,new String[]{"20","4"},null,out);

        String actual = "----------------------\r\n" +
                "|                    |\r\n" +
                "|                    |\r\n" +
                "|                    |\r\n" +
                "|                    |\r\n" +
                "----------------------\r\n";

        assertEquals(outContent.toString(),actual);
    }

    @Test
    public void testCreateLine(){
        Canvas canvas = executor.draw(CommandType.CMD_CANVAS,new String[]{"20","4"},null,out);
        outContent.reset();
        executor.draw(CommandType.CMD_LINE,new String[]{"1","2","6","2"},canvas,out);
        String actual = "----------------------\r\n" +
                "|                    |\r\n" +
                "|xxxxxx              |\r\n" +
                "|                    |\r\n" +
                "|                    |\r\n" +
                "----------------------\r\n";

        assertEquals(outContent.toString(),actual);
    }

    @Test
    public void testCreateLine2(){
        Canvas canvas = executor.draw(CommandType.CMD_CANVAS,new String[]{"20","4"},null,out);
        outContent.reset();
        executor.draw(CommandType.CMD_LINE,new String[]{"1","2","6","2"},canvas,out);
        outContent.reset();
        executor.draw(CommandType.CMD_LINE,new String[]{"6","3","6","4"},canvas,out);

        String actual = "----------------------\r\n" +
                "|                    |\r\n" +
                "|xxxxxx              |\r\n" +
                "|     x              |\r\n" +
                "|     x              |\r\n" +
                "----------------------\r\n";

        assertEquals(outContent.toString(),actual);
    }

    @Test
    public void testCreateRectangle(){
        Canvas canvas = executor.draw(CommandType.CMD_CANVAS,new String[]{"20","4"},null,out);
        outContent.reset();
        executor.draw(CommandType.CMD_LINE,new String[]{"1","2","6","2"},canvas,out);
        outContent.reset();
        executor.draw(CommandType.CMD_LINE,new String[]{"6","3","6","4"},canvas,out);
        outContent.reset();
        executor.draw(CommandType.CMD_RECTANGLE,new String[]{"14","1","18","3"},canvas,out);

        String actual = "----------------------\r\n" +
                "|             xxxxx  |\r\n" +
                "|xxxxxx       x   x  |\r\n" +
                "|     x       xxxxx  |\r\n" +
                "|     x              |\r\n" +
                "----------------------\r\n";

        assertEquals(outContent.toString(),actual);
    }

    @Test
    public void testBoxFull(){
        Canvas canvas = executor.draw(CommandType.CMD_CANVAS,new String[]{"20","4"},null,out);
        outContent.reset();
        executor.draw(CommandType.CMD_LINE,new String[]{"1","2","6","2"},canvas,out);
        outContent.reset();
        executor.draw(CommandType.CMD_LINE,new String[]{"6","3","6","4"},canvas,out);
        outContent.reset();
        executor.draw(CommandType.CMD_RECTANGLE,new String[]{"14","1","18","3"},canvas,out);
        outContent.reset();
        executor.draw(CommandType.CMD_BOX,new String[]{"10","3","o"},canvas,out);

        String actual = "----------------------\r\n" +
                "|oooooooooooooxxxxxoo|\r\n" +
                "|xxxxxxooooooox   xoo|\r\n" +
                "|     xoooooooxxxxxoo|\r\n" +
                "|     xoooooooooooooo|\r\n" +
                "----------------------\r\n";

        assertEquals(outContent.toString(),actual);
    }

    @Test
    public void testDuplicateLine(){
        Canvas canvas = executor.draw(CommandType.CMD_CANVAS,new String[]{"20","4"},null,out);
        executor.draw(CommandType.CMD_LINE,new String[]{"1","2","6","2"},canvas,out);
        executor.draw(CommandType.CMD_LINE,new String[]{"6","3","6","4"},canvas,out);

    }

}
