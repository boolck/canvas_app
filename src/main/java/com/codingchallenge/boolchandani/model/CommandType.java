package com.codingchallenge.boolchandani.model;

public enum CommandType
{
    CMD_CANVAS("C",2),
    CMD_LINE("L", 4),
    CMD_RECTANGLE("R", 4),
    CMD_BOX("B", 3),
    CMD_QUIT("Q", 0);

    private String value;
    private int parameterLength;

    public int getParameterLength() {
        return parameterLength;
    }

    CommandType(String value, int parameterLength) {
        this.value = value;
        this.parameterLength = parameterLength;
    }

    /**
     *  Matches the enum based on string value used in DrawingLauncher
     * @param type
     * @return
     */
    public static CommandType getTypeFor(String type) {
        for (CommandType b : CommandType.values()) {
            if (b.value.equalsIgnoreCase(type)) {
                return b;
            }
        }
        return null;
    }
}

