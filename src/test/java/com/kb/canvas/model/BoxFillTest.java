package com.kb.canvas.model;

import com.kb.canvas.excp.CanvasException;
import com.kb.canvas.excp.CanvasWrongArgsException;
import org.junit.Assert;
import org.junit.Test;

public class BoxFillTest {

    private final Canvas.CanvasDimension canvasDimension = new Canvas.CanvasDimension(20,4);

    @Test
    public void whenValidArgsPassedThenBoxFilled() throws CanvasException{
        BoxFill b = new BoxFill(canvasDimension);
        b.setArguments(new String[]{"1","2","x"});
    }

    @Test(expected= CanvasWrongArgsException.class)
    public void whenNegativeArgsPassedThenExcpThrown() throws CanvasException{
        BoxFill b = new BoxFill(canvasDimension);
        b.setArguments(new String[]{"-1","2","x"});
    }

    @Test(expected=CanvasWrongArgsException.class)
    public void whenInvalidArgsPassedThenExcpThrown() throws CanvasException{
        BoxFill b = new BoxFill(canvasDimension);
        b.setArguments(new String[]{"y","2","x"});
    }

    @Test(expected=CanvasWrongArgsException.class)
    public void whenBadFillColorThenExcpThrown() throws CanvasException{
        BoxFill b = new BoxFill(canvasDimension);
        b.setArguments(new String[]{"2","2",""});
    }

    @Test(expected=CanvasWrongArgsException.class)
    public void whenXExceedBorderThenExcpThrown() throws CanvasException{
        BoxFill b = new BoxFill(canvasDimension);
        b.setArguments(new String[]{"20","2","x"});
    }

    @Test(expected=CanvasWrongArgsException.class)
    public void whenYExceedBorderThenExcpThrown() throws CanvasException{
        BoxFill b = new BoxFill(canvasDimension);
        b.setArguments(new String[]{"1","5","x"});
    }

    @Test
    public void whenCanvasCreatedWithSameDimensionThenEquals() throws CanvasException {
        BoxFill b = new BoxFill(canvasDimension);
        b.setArguments(new String[]{"1", "2", "c"});

        BoxFill b2 = new BoxFill(canvasDimension);
        b2.setArguments(new String[]{"1", "2", "c"});
        Assert.assertEquals(b, b2);
    }

    @Test
    public void whenCanvasCreatedWithDiffDimensionThenNotEquals() throws CanvasException {
        BoxFill b = new BoxFill(canvasDimension);
        b.setArguments(new String[]{"1", "2", "c"});

        BoxFill b3 = new BoxFill(canvasDimension);
        b3.setArguments(new String[]{"1", "2", "f"});
        Assert.assertNotEquals(b, b3);
    }
}
