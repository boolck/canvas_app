package com.codingchallenge.boolchandani.entities;

import com.codingchallenge.boolchandani.helper.CanvasHelper;

import java.util.Objects;

public class BoxFill implements Entity {

    private int x,y;
    private char c;
    private CanvasDimension canvas ;

    public BoxFill(CanvasDimension canvas) {
        this.canvas = canvas;
    }

    @Override
    public void draw() {
        bucketFill(canvas.getCanvasArray(),x,y);
    }

    /**
     * Recursively populates the array if empty space is encountered
     * @param charArr
     * @param x
     * @param y
     */
    private void bucketFill(char[][] charArr, int x, int y) {
        char currentColor = charArr[y][x];
        if (currentColor == ' ') {
            charArr[y][x] = c;
            bucketFill(charArr, x + 1, y);
            bucketFill(charArr, x - 1, y);
            bucketFill(charArr, x, y + 1);
            bucketFill(charArr, x + 1, y - 1);
        }
    }

    @Override
    public void setArguments(String[] args) throws NumberFormatException, IndexOutOfBoundsException{
        x = Integer.valueOf(args[0]);
        y = Integer.valueOf(args[1]);
        if(!CanvasHelper.isAllPositive(x,y))
        {
            throw new NumberFormatException(CanvasHelper.NEGATIVE_NUMBER_ERROR_MSG);
        }
        c = args[2].charAt(0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BoxFill) {
            BoxFill other = (BoxFill) obj;
            return (other.x == x && other.y == y && other.c == c);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, c);
    }

}
