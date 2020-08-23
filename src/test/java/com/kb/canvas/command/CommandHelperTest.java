package com.kb.canvas.command;

import com.kb.canvas.excp.CanvasWrongArgsException;
import org.junit.Assert;
import org.junit.Test;

public class CommandHelperTest {

    @Test
    public void whenValidQuitCommandPassedThenStringTypeParsed() throws CanvasWrongArgsException {
        String cmd = CommandHelper.getCommandTypeString("Q");
        Assert.assertEquals("Q",cmd);
    }
    
    @Test
    public void whenValidCanvasCommandPassedThenStringTypeParsed() throws CanvasWrongArgsException {
       String cmd = CommandHelper.getCommandTypeString("C 20 4");
       Assert.assertEquals("C",cmd);
    }

    @Test
    public void whenValidCanvasCommandPassedThenArgumentsParsed() throws CanvasWrongArgsException {
        String[] args = CommandHelper.getCommandStringArgs("C 20 4");
        Assert.assertArrayEquals(new String[]{"20","4"},args);
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
