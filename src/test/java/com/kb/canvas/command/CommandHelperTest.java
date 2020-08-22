package com.kb.canvas.command;

import com.kb.canvas.excp.CanvasWrongArgsException;
import org.junit.Assert;
import org.junit.Test;

public class CommandHelperTest {


    @Test
    public void whenValidCommandPassedThenStringTypeParsed() throws CanvasWrongArgsException {
       String cmd = CommandHelper.getCommandTypeString("C 10 20");
       Assert.assertEquals("C",cmd);
    }

    @Test
    public void whenValidCommandPassedThenArgumentsParsed() throws CanvasWrongArgsException {
        String[] args = CommandHelper.getCommandStringArgs("C 10 20");
        Assert.assertArrayEquals(new String[]{"10","20"},args);
    }

    @Test(expected=CanvasWrongArgsException.class)
    public void whenInvalidCommandPassedThenExceptionThrown() throws CanvasWrongArgsException {
        String type = CommandHelper.getCommandTypeString("C1020");
    }

    @Test(expected=CanvasWrongArgsException.class)
    public void whenEmptyCommandPassedThenExceptionThrown() throws CanvasWrongArgsException {
        String[] args = CommandHelper.getCommandStringArgs("");
    }

    @Test(expected=CanvasWrongArgsException.class)
    public void whenNoCommandPassedThenExceptionThrown() throws CanvasWrongArgsException {
        String[] args = CommandHelper.getCommandStringArgs(null);
    }

    @Test(expected=CanvasWrongArgsException.class)
    public void whenIncompleteCommandsPassedThenExceptionThrown() throws CanvasWrongArgsException {
        String[] args = CommandHelper.getCommandStringArgs("C");
    }

    @Test
    public void whenValidInputArgsPassedThenNoException() throws CanvasWrongArgsException{
        CommandHelper.validateInputArguments("20","2");
    }

    @Test(expected=CanvasWrongArgsException.class)
    public void whenInValidInputArgsPassedThenExceptionThrown() throws CanvasWrongArgsException{
        CommandHelper.validateInputArguments("-20","2");
    }

}
