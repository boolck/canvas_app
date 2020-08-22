package com.kb.canvas.model;

import com.kb.canvas.command.CommandHelper;
import com.kb.canvas.excp.CanvasException;
import com.kb.canvas.excp.CanvasWrongArgsException;

import java.util.Arrays;
import java.util.Objects;

public class BoxFill implements Entity {

    private int x,y;
    private char c;
    private final Canvas.CanvasDimension canvas ;

    public BoxFill(Canvas.CanvasDimension canvas) {
        this.canvas = canvas;
    }

    @Override
    public void draw() {
        fill(canvas.getMatrix(),x,y);
    }

    /**
     * Recursively populates the array if empty space is encountered
     * @param charArr
     * @param x
     * @param y
     */
    private void fill(char[][] charArr, int x, int y) {
        if(x > canvas.getWidth() || x<0 || y>canvas.getHeight() || y<0){
            return;
        }
        char currentColor = charArr[y][x];
        if (currentColor == ' ') {
            charArr[y][x] = c;
            fill(charArr, x + 1, y);
            fill(charArr, x - 1, y);
            fill(charArr, x, y + 1);
            fill(charArr, x , y - 1);
        }
    }

    @Override
    public void setArguments(String[] args) throws CanvasException {
        CommandHelper.validateInputArguments(Arrays.copyOfRange( args, 0, 2));

        x = Integer.parseInt(args[0]);
        y = Integer.parseInt(args[1]);

        if(x >= canvas.getWidth() || y>=canvas.getHeight()){
            throw new CanvasWrongArgsException("Input argument should not exceed canvas border");
        }

        if(args[2].isEmpty()){
            throw new CanvasWrongArgsException("Input argument is missing box fill color");
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
