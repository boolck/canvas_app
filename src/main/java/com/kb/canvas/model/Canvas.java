package com.kb.canvas.model;

import com.kb.canvas.command.CommandHelper;
import com.kb.canvas.excp.CanvasException;
import com.kb.canvas.excp.CanvasMissingException;
import com.kb.canvas.executor.DrawingExecutor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Canvas implements Entity
{
    private final Set<Entity> entities;
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
     * refresh false will only render canvas without redrawing any child entity
     * @param refresh
     */
    public void draw(boolean refresh) {
        if (refresh) {
            for (Entity entity : entities) {
                entity.draw();
            }
            fillMatrix();
        } else {
            fillMatrix();
        }
    }

    /**
     * populate the array with width & height
     */
    private void fillMatrix() {
        for (int row = 0; row < canvasDimension.getHeight(); row++) {
            for (int col = 0; col < canvasDimension.getWidth(); col++) {
                if (row == 0 || row == canvasDimension.getHeight() - 1) {
                    canvasDimension.getMatrix()[row][col] = DrawingExecutor.CHAR_HORIZONTAL;
                } else if (col == 0 || col == canvasDimension.getWidth()- 1) {
                    canvasDimension.getMatrix()[row][col] = DrawingExecutor.CHAR_VERTICAL;
                }
            }
        }
    }


    @Override
    public void setArguments(String[] args) throws CanvasException {
        CommandHelper.validateInputArguments(args);

        int w = Integer.parseInt(args[0]) ;
        int h = Integer.parseInt(args[1]) ;

        canvasDimension = new CanvasDimension(w+2,h+2);
        Arrays.stream(canvasDimension.getMatrix()).forEach(DrawingExecutor::fillBlank);
    }


    public void add(Entity entity){
        this.entities.add(entity);
    }

    public CanvasDimension getDimension() {
        return canvasDimension;
    }

    public void validateDimension() throws CanvasMissingException {
        if(canvasDimension==null){
            throw new CanvasMissingException("Canvas does not exists");
        }
    }

    public static class CanvasDimension {

        private final int width;
        private final int height;
        private final char[][] matrix;

        public CanvasDimension(int width, int height)
        {
            this.width = width;
            this.height = height;
            this.matrix = new char[this.height][this.width];
        }

        public char[][] getMatrix(){
            return matrix;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

    }
}
