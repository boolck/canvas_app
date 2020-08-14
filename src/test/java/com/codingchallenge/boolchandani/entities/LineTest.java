package com.codingchallenge.boolchandani.entities;

import org.junit.Assert;
import org.junit.Test;

public class LineTest {

    private CanvasDimension canvasDimension = new CanvasDimension(20,4);

    @Test
    public void testValidArguments(){
        Line l = new Line(canvasDimension);
        l.setArguments(new String[]{"1","2","6","2"});
    }

    @Test(expected=NumberFormatException.class)
    public void testNegativeNumbers(){
        Line l = new Line(canvasDimension);
        l.setArguments(new String[]{"-1","2","6","2"});
    }

    @Test(expected=NumberFormatException.class)
    public void testNonNumericArguments(){
        Line l = new Line(canvasDimension);
        l.setArguments(new String[]{"x","2","6","2"});
    }

    @Test
    public void testEquality(){
        Line l = new Line(canvasDimension);
        l.setArguments(new String[]{"1","2","6","2"});
        Line l2 = new Line(canvasDimension);
        l2.setArguments(new String[]{"1","2","6","2"});
        Assert.assertEquals(l,l2);

        Line l3 = new Line(canvasDimension);
        l3.setArguments(new String[]{"2","2","6","2"});
        Assert.assertNotEquals(l,l3);
    }

}
