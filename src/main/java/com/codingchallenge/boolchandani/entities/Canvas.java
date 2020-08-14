package com.codingchallenge.boolchandani.entities;

import com.codingchallenge.boolchandani.helper.CanvasHelper;

import java.util.*;

public class Canvas implements Entity
{
    private Set<Entity> entities;
    private CanvasDimension canvasDimension;

    public Canvas() {
        this.entities = new HashSet<>();
    }

    @Override
    public void draw() {
       this.draw(true);
    }

    /**
     * allows to render the canvas with option to redraw all child enties when refresh true
     * refresh false will only render canvas without redraing any child entity
     * @param refresh
     */
    private void draw(boolean refresh) {
        if (refresh) {
            fillArray();
            for (Entity entity : entities) {
                entity.draw();
            }
            this.render();
        } else {
            this.render();
        }
    }

    /**
     * populate the array with width & height
     */
    private void fillArray() {
        for (int row = 0; row < canvasDimension.getHeight(); row++) {
            for (int col = 0; col < canvasDimension.getWidth(); col++) {
                if (row == 0 || row == canvasDimension.getHeight() - 1) {
                    canvasDimension.getCanvasArray()[row][col] = CanvasHelper.CHAR_HORIZONTAL;
                } else if (col == 0 || col == canvasDimension.getWidth()- 1) {
                    canvasDimension.getCanvasArray()[row][col] = CanvasHelper.CHAR_VERTICAL;
                }
            }
        }
    }

    /**
     * Prints the canvas
     */
    public void render() {
        for (int i = 0; i < canvasDimension.getHeight(); i++) {
            for (int j = 0; j < canvasDimension.getWidth(); j++) {
                System.out.print(canvasDimension.getCanvasArray()[i][j]);
            }
            System.out.println();
        }
    }

    @Override
    public void setArguments(String[] args) throws NumberFormatException, IndexOutOfBoundsException{
        int w = Integer.valueOf(args[0]) ;
        int h = Integer.valueOf(args[1]) ;

        if(!CanvasHelper.isAllPositive(w,h)){
            throw new NumberFormatException(CanvasHelper.NEGATIVE_NUMBER_ERROR_MSG);
        }
        canvasDimension = new CanvasDimension(w+2,h+2);
        Arrays.stream(canvasDimension.getCanvasArray()).forEach(x -> Arrays.fill(x,CanvasHelper.CHAR_BLANK));
    }


    public void add(Entity entity){
        this.entities.add(entity);
    }

    public CanvasDimension getDimension() {
        return canvasDimension;
    }

}
