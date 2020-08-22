package com.kb.canvas.model;

import com.kb.canvas.excp.CanvasException;
import com.kb.canvas.excp.CanvasWrongArgsException;
import com.kb.canvas.executor.DrawingExecutor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CanvasTest {

    Canvas canvas = null;

    @Before
    public void setup() {
        canvas = new Canvas();
    }

    @Test
    public void whenValidArgumetsPassedThenNoException() throws CanvasException {
        canvas.setArguments(new String[]{"1", "2"});
    }

    @Test(expected = CanvasWrongArgsException.class)
    public void whenNegativeArgumentThenCanvasWrongArgsException() throws CanvasException {
        canvas.setArguments(new String[]{"-1", "2"});
    }

    @Test(expected = CanvasWrongArgsException.class)
    public void whenNonNumericArgumentThenCanvasWrongArgsException() throws CanvasException {
        canvas.setArguments(new String[]{"2", "y"});
    }

    @Test(expected = CanvasWrongArgsException.class)
    public void whenNonNumericArgumentsThenCanvasWrongArgsException() throws CanvasException {
        canvas.setArguments(new String[]{"x", "@"});
    }

    @Test
    public void whenCanvasIsCreatedThenDimensionAreValid() throws CanvasException {
        canvas.setArguments(new String[]{"6", "3"});
        Canvas.CanvasDimension canvasDimension = canvas.getDimension();
        Assert.assertNotNull(canvasDimension);
        Assert.assertEquals(6 + 2, canvasDimension.getWidth());
        Assert.assertEquals(3 + 2, canvasDimension.getHeight());
        Assert.assertNotNull(canvasDimension.getMatrix());
        Assert.assertEquals(3 + 2, canvasDimension.getMatrix().length);
        Assert.assertEquals(6 + 2, canvasDimension.getMatrix()[0].length);
    }

    @Test
    public void whenCanvasIsCreatedThenMatrixValid() throws CanvasException {
        canvas.setArguments(new String[]{"6", "3"});
        Canvas.CanvasDimension canvasDimension = canvas.getDimension();
        char[][] canvasArray = canvasDimension.getMatrix();
        for (char[] aCanvasArray : canvasArray) {
            for (int j = 0; j < canvasArray[0].length; j++) {
                Assert.assertEquals(DrawingExecutor.CHAR_BLANK, aCanvasArray[j]);
            }
        }
    }

    @Test
    public void whenRefreshFalseThenOnlyMatrixUpdated() throws CanvasException {
        canvas.setArguments(new String[]{"6", "3"});
        canvas.draw(false);
        Canvas.CanvasDimension canvasDimension = canvas.getDimension();
        for (int row = 0; row < canvasDimension.getHeight(); row++) {
            for (int col = 0; col < canvasDimension.getWidth(); col++) {
                if (row == 0 || row == canvasDimension.getHeight() - 1) {
                    Assert.assertEquals(DrawingExecutor.CHAR_HORIZONTAL,canvasDimension.getMatrix()[row][col]) ;
                } else if (col == 0 || col == canvasDimension.getWidth()- 1) {
                    Assert.assertEquals(DrawingExecutor.CHAR_VERTICAL,canvasDimension.getMatrix()[row][col]) ;
                }
            }
        }
    }

}
