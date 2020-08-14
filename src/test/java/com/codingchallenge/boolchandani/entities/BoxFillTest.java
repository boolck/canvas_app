package com.codingchallenge.boolchandani.entities;

import org.junit.Assert;
import org.junit.Test;

public class BoxFillTest {

    private CanvasDimension canvasDimension = new CanvasDimension(20,4);

    @Test
    public void testValidArguments(){
        BoxFill b = new BoxFill(canvasDimension);
        b.setArguments(new String[]{"1","2","x"});
    }

    @Test(expected=NumberFormatException.class)
    public void testNegativeNumbers(){
        BoxFill b = new BoxFill(canvasDimension);
        b.setArguments(new String[]{"-1","2","x"});
    }

    @Test(expected=NumberFormatException.class)
    public void testNonNumericArguments(){
        BoxFill b = new BoxFill(canvasDimension);
        b.setArguments(new String[]{"y","2","x"});
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testIndexOutOfBounds(){
        BoxFill b = new BoxFill(canvasDimension);
        b.setArguments(new String[]{"2","2",""});
    }

    @Test
    public void testEquality(){
        BoxFill b = new BoxFill(canvasDimension);
        b.setArguments(new String[]{"1","2","c"});
        BoxFill b2 = new BoxFill(canvasDimension);
        b2.setArguments(new String[]{"1","2","c"});
        Assert.assertEquals(b,b2);

        BoxFill b3 = new BoxFill(canvasDimension);
        b2.setArguments(new String[]{"1","2","f"});
        Assert.assertNotEquals(b,b3);
    }
}
