package com.kb.canvas.model;

import com.kb.canvas.excp.CanvasException;
import com.kb.canvas.excp.CanvasWrongArgsException;
import org.junit.Assert;
import org.junit.Test;

public class RectangleTest {

    private final Canvas.CanvasDimension canvasDimension = new Canvas.CanvasDimension(20, 4);

    @Test
    public void whenValidArgsPassedThenRectangleCreated() throws CanvasException {
        Rectangle r = new Rectangle(canvasDimension);
        r.setArguments(new String[]{"1", "2", "6", "2"});
    }

    @Test(expected = CanvasWrongArgsException.class)
    public void whenNegativeArgsPassedThenExcpThrown() throws CanvasException {
        Rectangle r = new Rectangle(canvasDimension);
        r.setArguments(new String[]{"-1", "2", "6", "2"});
    }

    @Test(expected = CanvasWrongArgsException.class)
    public void whenNonNumericArgsPassedThenExcpThrown() throws CanvasException {
        Rectangle r = new Rectangle(canvasDimension);
        r.setArguments(new String[]{"x", "2", "6", "2"});
    }

    @Test(expected=CanvasWrongArgsException.class)
    public void whenXExceedBorderThenExcpThrown() throws CanvasException{
        Rectangle r = new Rectangle(canvasDimension);
        r.setArguments(new String[]{"21", "2", "6", "2"});
    }

    @Test(expected=CanvasWrongArgsException.class)
    public void whenYExceedBorderThenExcpThrown() throws CanvasException{
        Rectangle r = new Rectangle(canvasDimension);
        r.setArguments(new String[]{"1", "6", "6", "6"});
    }

    @Test
    public void whenSameDimensionPassedThenRectangleIsEqual() throws CanvasException {
        Rectangle r = new Rectangle(canvasDimension);
        r.setArguments(new String[]{"1", "2", "6", "2"});
        Rectangle r2 = new Rectangle(canvasDimension);
        r2.setArguments(new String[]{"1", "2", "6", "2"});
        Assert.assertEquals(r, r2);
    }

    @Test
    public void whenSameDimensionPassedThenRectangleIsNotEqual() throws CanvasException {
        Rectangle r = new Rectangle(canvasDimension);
        r.setArguments(new String[]{"1", "2", "6", "2"});
        Rectangle r3 = new Rectangle(canvasDimension);
        r3.setArguments(new String[]{"2", "2", "6", "2"});
        Assert.assertNotEquals(r, r3);
    }
}
