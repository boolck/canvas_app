package com.codingchallenge.boolchandani.entities;

public  class CanvasDimension {

    private int width;
    private int height;
    private char[][] canvasArray;

    public CanvasDimension(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.canvasArray = new char[this.height][this.width];
    }

    public char[][] getCanvasArray(){
        return canvasArray;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
