package com.kb.canvas.model;

import com.kb.canvas.command.CommandHelper;
import com.kb.canvas.excp.CanvasException;
import com.kb.canvas.excp.CanvasWrongArgsException;
import com.kb.canvas.executor.DrawingExecutor;

import java.util.Objects;

public class Line implements Entity {

    private int x1,y1,x2,y2;
    private final Canvas.CanvasDimension canvasDimension;

    public Line(Canvas.CanvasDimension canvasDimension) {
        this.canvasDimension = canvasDimension;
    }

    public Line(Canvas.CanvasDimension c, int x1, int y1, int x2, int y2) {
        this(c);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * fills canvasDimension array from (x1,y1) to (x2,y2)
     */

    @Override
    public void draw() {
        char[][] canvasArray = canvasDimension.getMatrix();
        for (int row = y1; row <= y2  && row < canvasDimension.getHeight()-1; row++) {
            for (int col = x1; col <= x2  && col < canvasDimension.getWidth()-1; col++) {
                canvasArray[row][col] = DrawingExecutor.CHAR_LINE;
            }
        }
    }

    @Override
    public void setArguments(String[] args) throws CanvasException {
        CommandHelper.validateInputArguments(args);

        this.x1 = Integer.parseInt(args[0]);
        this.y1 = Integer.parseInt(args[1]);
        this.x2 = Integer.parseInt(args[2]);
        this.y2 = Integer.parseInt(args[3]);

        if(x1!=x2 && y1!=y2){
            throw new CanvasWrongArgsException("Only horizontal or vertical lines are supported");
        }

        if(x1 >= canvasDimension.getWidth() || y1>=canvasDimension.getHeight()
            || x2 >= canvasDimension.getWidth() || y2>=canvasDimension.getHeight()){
            throw new CanvasWrongArgsException("Input argument should not exceed canvas border");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Line) {
            Line other = (Line) obj;
            return (other.x1 == x1 && other.x2 == x2 && other.y1 == y1 && other.y2 == y2);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, x2, y2);
    }

}
