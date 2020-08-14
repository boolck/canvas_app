package com.codingchallenge.boolchandani.entities;

import com.codingchallenge.boolchandani.helper.CanvasHelper;

import java.util.Objects;

public class Line implements Entity {

    private int x1,y1,x2,y2;
    private CanvasDimension canvasDimension;

    public Line(CanvasDimension canvasDimension) {
        this.canvasDimension = canvasDimension;
    }

    public Line(CanvasDimension c, int x1, int y1, int x2, int y2) {
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
        char[][] canvasArray = canvasDimension.getCanvasArray();
        for (int row = y1; row <= y2  && row < canvasDimension.getHeight()-1; row++) {
            for (int col = x1; col <= x2  && col < canvasDimension.getWidth()-1; col++) {
                canvasArray[row][col] = CanvasHelper.CHAR_LINE;
            }
        }
    }

    @Override
    public void setArguments(String[] args) throws NumberFormatException, IndexOutOfBoundsException{
        this.x1 = Integer.valueOf(args[0]);
        this.y1 = Integer.valueOf(args[1]);
        this.x2 = Integer.valueOf(args[2]);
        this.y2 = Integer.valueOf(args[3]);

        if(!CanvasHelper.isAllPositive(x1,y1,x2,y2)){
            throw new NumberFormatException(CanvasHelper.NEGATIVE_NUMBER_ERROR_MSG);
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
