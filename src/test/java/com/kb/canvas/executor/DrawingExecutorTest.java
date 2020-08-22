package com.kb.canvas.executor;

import com.kb.canvas.excp.CanvasException;
import com.kb.canvas.command.CommandType;
import com.kb.canvas.excp.CanvasMissingException;
import com.kb.canvas.excp.CanvasWrongArgsException;
import com.kb.canvas.model.Canvas;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class DrawingExecutorTest {

    private DrawingExecutor executor ;

    @Before
    public void setup() {
        executor = new DrawingExecutor();
    }

    @Test
    public void whenCanvasCommandPassedThenCanvasCreatedWithValidBorders() throws CanvasException {
        Canvas canvas = executor.draw(CommandType.CMD_CANVAS, "20","4");
        Canvas.CanvasDimension dimension = canvas.getDimension();
        Assert.assertEquals(20+2, dimension.getWidth());
        Assert.assertEquals(4+2, dimension.getHeight());
    }

    @Test
    public void whenCanvasCommandPassedThenCanvasCreatedWithValidMatrix() throws CanvasException {
        Canvas canvas = executor.draw(CommandType.CMD_CANVAS, "20","4");
        char[][] matrix = canvas.getDimension().getMatrix();
        Assert.assertEquals(4+2, matrix.length);
        Assert.assertEquals(20+2, matrix[0].length);
    }

    @Test
    public void whenCanvasCommandPassedThenCanvasCreatedWithDimension() throws CanvasException {
        Canvas canvas = executor.draw(CommandType.CMD_CANVAS, "20","4");
        Canvas.CanvasDimension dimension = canvas.getDimension();
        Assert.assertNotNull(dimension);
    }

    @Test(expected=CanvasWrongArgsException.class)
    public void whenNoCommandPassedThenExcpThrown() throws CanvasException {
        Canvas canvas = executor.draw(CommandType.CMD_CANVAS, null);
    }

    @Test(expected=CanvasWrongArgsException.class)
    public void whenMismatchedCommandPassedThenExcpThrown() throws CanvasException {
        Canvas canvas = executor.draw(CommandType.CMD_CANVAS, "1");
    }

    @Test
    public void whenQuitCommandPassedThenNoExcpThrown() throws CanvasException {
        executor.draw(CommandType.CMD_QUIT, "");
    }

    @Test
    public void whenCanvasCommandPassedThenCanvasCreatedWithMatrixFilled() throws CanvasException {
        Canvas canvas = executor.draw(CommandType.CMD_CANVAS, "20","4");
        char[][] matrix = canvas.getDimension().getMatrix();
        for(int row=0;row<=5;row++){
            for(int col=0;col<=21;col++){
                if(row==0 || row==5){
                    Assert.assertEquals('-',matrix[row][col]);
                }
                else if(col==0 || col==21){
                    Assert.assertEquals('|',matrix[row][col]);
                }
                else{
                    Assert.assertEquals(' ',matrix[row][col]);
                }
            }
        }
    }

    @Test
    public void whenLineCommandPassedThenAddedToCanvas() throws CanvasException {
        executor.draw(CommandType.CMD_CANVAS, "20","4");
        Canvas line = executor.draw(CommandType.CMD_LINE, "1","2","6","2");
        Assert.assertNotNull(line);
        Assert.assertNotNull(line.getDimension());
    }

    @Test(expected= CanvasMissingException.class)
    public void whenLineAddedToMissingCanvasThenExceptionThrown() throws CanvasException {
        executor.draw(CommandType.CMD_LINE, "1","2","6","2");
    }

    @Test
    public void whenHorizontalLineCommandPassedThenCanvasFilled() throws CanvasException {
        executor.draw(CommandType.CMD_CANVAS, "20","4");
        Canvas line = executor.draw(CommandType.CMD_LINE, "1","2","6","2");
        char[][] matrix = line.getDimension().getMatrix();
        for(int col=1;col<=6;col++){
            Assert.assertEquals('x',matrix[2][col]);
        }
    }

    @Test
    public void whenVerticalLineCommandPassedThenCanvasFilled() throws CanvasException {
        executor.draw(CommandType.CMD_CANVAS, "20","4");
        Canvas line = executor.draw(CommandType.CMD_LINE, "6","3","6","4");
        char[][] matrix = line.getDimension().getMatrix();
        for(int row=3;row<=4;row++){
            Assert.assertEquals('x',matrix[row][6]);
        }
    }

    @Test(expected= CanvasWrongArgsException.class)
    public void whenInvalidLineCommandPassedThenExceptionThrown() throws CanvasException {
        executor.draw(CommandType.CMD_CANVAS, "20","4");
        executor.draw(CommandType.CMD_LINE, "1","2","3","4");
    }

    @Test
    public void whenRectangleCommandPassedThenAddedToCanvas() throws CanvasException {
        executor.draw(CommandType.CMD_CANVAS, "20","4");
        Canvas rectangle = executor.draw(CommandType.CMD_RECTANGLE, "16","1","20","3");
        Assert.assertNotNull(rectangle);
    }

    @Test
    public void whenRectangleCommandPassedThenCanvasFilled() throws CanvasException {
        executor.draw(CommandType.CMD_CANVAS, "20","4");
        Canvas rectangle = executor.draw(CommandType.CMD_RECTANGLE, "16","1","20","3");
        char[][] matrix = rectangle.getDimension().getMatrix();
        for(int row=1;row<=3;row++){
            for(int col=16;col<=20;col++){
                if(row==1 || row==3){
                    Assert.assertEquals('x',matrix[row][col]);
                }
                else if(col==16 || col==20){
                    Assert.assertEquals('x',matrix[row][col]);
                }
                else{
                    Assert.assertEquals(' ',matrix[row][col]);
                }
            }
        }
    }

    @Test
    public void whenBoxCommandPassedThenAddedToCanvas() throws CanvasException {
        executor.draw(CommandType.CMD_CANVAS, "20","4");
        Canvas box = executor.draw(CommandType.CMD_BOX, "10","3","o");
        Assert.assertNotNull(box);
    }

    @Test
    public void whenBoxCommandPassedThenCanvasFilled() throws CanvasException {
        executor.draw(CommandType.CMD_CANVAS, "20","4");
        executor.draw(CommandType.CMD_LINE, "1","2","6","2");
        executor.draw(CommandType.CMD_LINE, "6","3","6","4");
        executor.draw(CommandType.CMD_RECTANGLE, "16","1","20","3");
        Canvas box = executor.draw(CommandType.CMD_BOX, "10","3","o");
        char[][] matrix = box.getDimension().getMatrix();
        for(int row=1;row<=4;row++){
            for(int col=1;col<=20;col++) {
                if(row==1 && col<16){
                    Assert.assertEquals('o', matrix[row][col]);
                }
                else if(row==4 && col>7){
                    Assert.assertEquals('o', matrix[row][col]);
                }
                else if(col>7 && col<16){
                    Assert.assertEquals('o', matrix[row][col]);
                }
            }
        }
    }

    @Test
    public void whenRefreshedThenCanvasRenderedAgain() throws CanvasException {
        executor.draw(CommandType.CMD_CANVAS, "20","4");
        executor.draw(CommandType.CMD_LINE, "1","2","6","2");
        executor.draw(CommandType.CMD_LINE, "6","3","6","4");
        executor.draw(CommandType.CMD_RECTANGLE, "16","1","20","3");
        Canvas box = executor.draw(CommandType.CMD_BOX, "10","3","o");
        box.draw(true);
        char[][] matrix = box.getDimension().getMatrix();
        for(int row=1;row<=4;row++){
            for(int col=1;col<=20;col++) {
                if(row==1 && col<16){
                    Assert.assertEquals('o', matrix[row][col]);
                }
                else if(row==4 && col>7){
                    Assert.assertEquals('o', matrix[row][col]);
                }
                else if(col>7 && col<16){
                    Assert.assertEquals('o', matrix[row][col]);
                }
            }
        }
    }



}
