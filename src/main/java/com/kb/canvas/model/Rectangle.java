package com.kb.canvas.model;

import com.kb.canvas.command.CommandHelper;
import com.kb.canvas.excp.CanvasException;
import com.kb.canvas.excp.CanvasWrongArgsException;

import java.util.Arrays;
import java.util.Objects;

public class Rectangle implements Entity {

    private int x1,y1,x2,y2;
    private final Canvas.CanvasDimension canvasDimension ;

    public Rectangle(Canvas.CanvasDimension canvas) {
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
    public void setArguments(String[] args) throws CanvasException {
        CommandHelper.validateInputArguments(args);

        this.x1 = Integer.parseInt(args[0]);
        this.y1 = Integer.parseInt(args[1]);
        this.x2 = Integer.parseInt(args[2]);
        this.y2 = Integer.parseInt(args[3]);

        if(x1 >= canvasDimension.getWidth() || y1>=canvasDimension.getHeight()
                || x2 >= canvasDimension.getWidth() || y2>=canvasDimension.getHeight()){
            throw new CanvasWrongArgsException("Input argument should not exceed canvas border");
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
