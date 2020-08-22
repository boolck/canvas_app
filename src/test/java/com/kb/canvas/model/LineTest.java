package com.kb.canvas.model;

import com.kb.canvas.excp.CanvasException;
import com.kb.canvas.excp.CanvasWrongArgsException;
import org.junit.Assert;
import org.junit.Test;

public class LineTest {

    private final Canvas.CanvasDimension canvasDimension = new Canvas.CanvasDimension(20, 4);

    @Test
    public void whenValidArgsPassedThenLineCreated() throws CanvasException {
        Line l = new Line(canvasDimension);
        l.setArguments(new String[]{"1", "2", "6", "2"});
    }

    @Test(expected = CanvasWrongArgsException.class)
    public void whenNegativeArgsPassedThenExcpThrown() throws CanvasException {
        Line l = new Line(canvasDimension);
        l.setArguments(new String[]{"-1", "2", "6", "2"});
    }

    @Test(expected = CanvasWrongArgsException.class)
    public void whenNonNumericArgsPassedThenExcpThrown() throws CanvasException {
        Line l = new Line(canvasDimension);
        l.setArguments(new String[]{"x","2","6","2"});
    }

    @Test(expected=CanvasWrongArgsException.class)
    public void whenXExceedBorderThenExcpThrown() throws CanvasException{
        Line l = new Line(canvasDimension);
        l.setArguments(new String[]{"6", "21", "6", "2"});
    }

    @Test(expected=CanvasWrongArgsException.class)
    public void whenYExceedBorderThenExcpThrown() throws CanvasException{
        Line l = new Line(canvasDimension);
        l.setArguments(new String[]{"1", "2", "1", "5"});
    }

    @Test
    public void whenSameDimensionThenLineIsEquals() throws CanvasException {
        Line l = new Line(canvasDimension);
        l.setArguments(new String[]{"1", "2", "6", "2"});
        Line l2 = new Line(canvasDimension);
        l2.setArguments(new String[]{"1", "2", "6", "2"});
        Assert.assertEquals(l, l2);
    }

    @Test
    public void whenDifferentDimensionPassedThenLineIsDifferent() throws CanvasException {
        Line l = new Line(canvasDimension);
        l.setArguments(new String[]{"1", "2", "6", "2"});
        Line l3 = new Line(canvasDimension);
        l3.setArguments(new String[]{"2", "2", "6", "2"});
        Assert.assertNotEquals(l, l3);
    }

}
