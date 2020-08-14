package com.codingchallenge.boolchandani.entities;

import com.codingchallenge.boolchandani.model.CommandType;

public interface Entity
{
    void draw();

    void setArguments(String[] args) throws NumberFormatException, IndexOutOfBoundsException;

    default boolean validateAndSet(String[] args, CommandType cmdType) {
        if (args == null || cmdType == null) {
            return false;
        } else if (args.length != cmdType.getParameterLength()) {
            return false;
        }

        try {
            setArguments(args);
        } catch (NumberFormatException | IndexOutOfBoundsException nx) {
            System.out.println("Exception- "+nx.getMessage());
            return false;
        }
        return true;
    }

}
