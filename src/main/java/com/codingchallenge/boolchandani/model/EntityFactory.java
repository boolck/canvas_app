package com.codingchallenge.boolchandani.model;

import com.codingchallenge.boolchandani.entities.*;

public class EntityFactory
{
    /**
     * Gets the entity based on command type and sets canvas on each
     * @param commandType
     * @param canvasDimension
     * @return
     */
    public Entity getEntity(CommandType commandType, CanvasDimension canvasDimension) {
        Entity entity = null;
        switch(commandType)
        {
            case CMD_LINE:
                entity = new Line(canvasDimension);
                break;
            case CMD_RECTANGLE:
                entity = new Rectangle(canvasDimension);
                break;
            case CMD_BOX:
                entity = new BoxFill(canvasDimension);
                break;
            default:
                break;
        }
        return entity;
    }
}
