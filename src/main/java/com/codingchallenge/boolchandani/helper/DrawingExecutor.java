package com.codingchallenge.boolchandani.helper;

import com.codingchallenge.boolchandani.entities.Canvas;
import com.codingchallenge.boolchandani.entities.Entity;
import com.codingchallenge.boolchandani.model.CommandType;
import com.codingchallenge.boolchandani.model.EntityFactory;

import java.io.PrintStream;

import static com.codingchallenge.boolchandani.helper.CanvasHelper.CANVAS_MISSING_ERROR_MSG;
import static com.codingchallenge.boolchandani.helper.CanvasHelper.VALID_ARGS_ERROR_MSG;

public class DrawingExecutor {

    private EntityFactory entityFactory = new EntityFactory();

    /**
     * draws the new entity based on commmand type, it's parameters, existing canvas & sends the output to printstream
     * printstream can be overridden for testing
     * @param cmdType
     * @param parameters
     * @param canvas
     * @param printStream
     * @return
     */

    public Canvas draw(CommandType cmdType, String[] parameters, Canvas canvas, PrintStream printStream) {
        if(printStream ==null){
            System.out.println(VALID_ARGS_ERROR_MSG);
            System.out.println(CanvasHelper.getHelpMessage());
            return canvas;
        }
        if (cmdType == null || parameters==null || parameters.length==0 ) {
            printStream.println(VALID_ARGS_ERROR_MSG);
            printStream.println(CanvasHelper.getHelpMessage());
            return canvas;
        }

        switch (cmdType) {
            case CMD_CANVAS:
                canvas = new Canvas();
                if (canvas.validateAndSet(parameters, CommandType.CMD_CANVAS)) {
                    canvas.draw();
                } else {
                    printStream.println(VALID_ARGS_ERROR_MSG);
                    printStream.println(CanvasHelper.getHelpMessage());
                }
                break;
            case CMD_LINE:
            case CMD_RECTANGLE:
            case CMD_BOX:
                if (canvas == null) {
                    printStream.println(CANVAS_MISSING_ERROR_MSG);
                    break;
                }
                Entity entity = entityFactory.getEntity(cmdType, canvas.getDimension());
                if (entity.validateAndSet(parameters, cmdType)) {
                    entity.draw();
                    canvas.render();
                    canvas.add(entity);
                } else {
                    printStream.println(VALID_ARGS_ERROR_MSG);
                    printStream.println(CanvasHelper.getHelpMessage());
                }
                break;
            default:
                printStream.println(CanvasHelper.getHelpMessage());
        }
        return canvas;
    }
}
