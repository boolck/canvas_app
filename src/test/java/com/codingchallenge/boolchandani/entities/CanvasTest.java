package com.codingchallenge.boolchandani.entities;

import com.codingchallenge.boolchandani.helper.CanvasHelper;
import org.junit.Assert;
import org.junit.Test;

public class CanvasTest {

    @Test
    public void testValidArguments(){
        Canvas  c = new Canvas();
        c.setArguments(new String[]{"1","2"});
    }

    @Test(expected=NumberFormatException.class)
    public void testNegativeNumbers(){
        Canvas  c = new Canvas();
        c.setArguments(new String[]{"-1","2"});
    }

    @Test(expected=NumberFormatException.class)
    public void testNonNumericArguments(){
        Canvas  c = new Canvas();
        c.setArguments(new String[]{"y","2"});
    }

    @Test
    public void testCanvasDimension(){
        Canvas  c = new Canvas();
        c.setArguments(new String[]{"6","3"});
        CanvasDimension canvasDimension = c.getDimension();
        Assert.assertNotNull(canvasDimension);
        Assert.assertEquals(8, canvasDimension.getWidth());
        Assert.assertEquals(5, canvasDimension.getHeight());
        Assert.assertNotNull(canvasDimension.getCanvasArray());
        Assert.assertEquals(5,canvasDimension.getCanvasArray().length);
        Assert.assertEquals(8,canvasDimension.getCanvasArray()[0].length);
        this.testCanvasIsBlank(canvasDimension.getCanvasArray());
    }

    private void testCanvasIsBlank(char[][] canvasArray) {
        for (char[] aCanvasArray : canvasArray) {
            for (int j = 0; j < canvasArray[0].length; j++) {
                Assert.assertEquals(CanvasHelper.CHAR_BLANK, aCanvasArray[j]);
            }
        }
    }

}
