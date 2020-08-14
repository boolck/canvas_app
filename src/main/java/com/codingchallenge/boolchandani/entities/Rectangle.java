package com.codingchallenge.boolchandani.entities;

import com.codingchallenge.boolchandani.helper.CanvasHelper;

import java.util.Arrays;
import java.util.Objects;

public class Rectangle implements Entity {

    private int x1,y1,x2,y2;
    private CanvasDimension canvasDimension ;

    public Rectangle(CanvasDimension canvas) {
        this.canvasDimension = canvas;
    }

    /**
     * Creates four lines for rectangle and calls line.draw
     */
    @Override
    public void draw() {
        Line[] lines = {
                new Line(canvasDimension, x1, y1, x2, y1),
                new Line(canvasDimension, x1, y1, x1, y2),
                new Line(canvasDimension, x2, y1, x2, y2),
                new Line(canvasDimension, x1, y2, x2, y2)
        };
        Arrays.stream(lines).forEach(Line::draw);
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
        if (obj instanceof Rectangle) {
            Rectangle other = (Rectangle) obj;
            return (other.x1 == x1 && other.x2 == x2 && other.y1 == y1 && other.y2 == y2);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, x2, y2);
    }

}
