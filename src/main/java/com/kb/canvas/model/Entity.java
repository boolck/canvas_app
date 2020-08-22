package com.kb.canvas.model;

import com.kb.canvas.command.CommandType;
import com.kb.canvas.excp.CanvasException;
import com.kb.canvas.excp.CanvasWrongArgsException;

public interface Entity
{
    void draw();

    void setArguments(String[] args) throws CanvasException;

    default void validateAndSet(String[] args, CommandType cmdType) throws CanvasException{
        if (args == null || cmdType == null) {
            throw new CanvasWrongArgsException("Wrong Arguments Passed");
        } else if (args.length != cmdType.getParametersRequired()) {
            throw new CanvasWrongArgsException("Arguments length dont match expected count for Commandtype");
        }

        setArguments(args);
    }

}
