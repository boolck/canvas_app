package com.codingchallenge.boolchandani.entities;

import org.junit.Assert;
import org.junit.Test;

public class RectangleTest {

    private CanvasDimension canvasDimension = new CanvasDimension(20,4);

    @Test
    public void testValidArguments(){
        Rectangle r = new Rectangle(canvasDimension);
        r.setArguments(new String[]{"1","2","6","2"});
    }

    @Test(expected=NumberFormatException.class)
    public void testNegativeNumbers(){
        Rectangle r = new Rectangle(canvasDimension);
        r.setArguments(new String[]{"-1","2","6","2"});
    }

    @Test(expected=NumberFormatException.class)
    public void testNonNumericArguments(){
        Rectangle r = new Rectangle(canvasDimension);
        r.setArguments(new String[]{"x","2","6","2"});
    }

    @Test
    public void testEquality(){
        Rectangle r = new Rectangle(canvasDimension);
        r.setArguments(new String[]{"1","2","6","2"});
        Rectangle r2 = new Rectangle(canvasDimension);
        r2.setArguments(new String[]{"1","2","6","2"});
        Assert.assertEquals(r,r2);

        Rectangle r3 = new Rectangle(canvasDimension);
        r2.setArguments(new String[]{"2","2","6","2"});
        Assert.assertNotEquals(r,r3);
    }
}
