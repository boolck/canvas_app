package com.kb.canvas.command;

import org.junit.Assert;
import org.junit.Test;

public class CommandTypeTest {

    @Test
    public void whenQuitCommandPassedThenCommandTypeCreated(){
        CommandType type = CommandType.getTypeFor("Q");
        Assert.assertEquals(CommandType.CMD_QUIT,type);
    }

    @Test
    public void whenInvalidCommandPassedThenTypeNull(){
        CommandType type = CommandType.getTypeFor("X");
        Assert.assertNull(type);
    }

    @Test
    public void whenCanvasCommandPassedThenCommandTypeCreated(){
        CommandType type = CommandType.getTypeFor("C");
        Assert.assertEquals(CommandType.CMD_CANVAS,type);
    }

    @Test
    public void whenCanvasCommandPassedThenCommandTypeCreatedWith2Params(){
        CommandType type = CommandType.getTypeFor("C");
        Assert.assertNotNull(type);
        Assert.assertEquals(2,type.getArity());
    }


    @Test
    public void whenLineCommandPassedThenCommandTypeCreated(){
        CommandType type = CommandType.getTypeFor("L");
        Assert.assertNotNull(type);
        Assert.assertEquals(CommandType.CMD_LINE,type);
        Assert.assertEquals(4,type.getArity());
    }

    @Test
    public void whenRectangeCommandPassedThenCommandTypeCreated(){
        CommandType type = CommandType.getTypeFor("R");
        Assert.assertNotNull(type);
        Assert.assertEquals(CommandType.CMD_RECTANGLE,type);
        Assert.assertEquals(4,type.getArity());
    }

    @Test
    public void whenBoxCommandPassedThenCommandTypeCreated(){
        CommandType type = CommandType.getTypeFor("B");
        Assert.assertNotNull(type);
        Assert.assertEquals(CommandType.CMD_BOX,type);
        Assert.assertEquals(3,type.getArity());
    }

}
