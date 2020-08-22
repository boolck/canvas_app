package com.kb.canvas.command;

public enum CommandType
{
    CMD_CANVAS("C",2),
    CMD_LINE("L", 4),
    CMD_RECTANGLE("R", 4),
    CMD_BOX("B", 3),
    CMD_QUIT("Q", 0);

    private final String value;
    private final int parametersRequired;

    public int getParametersRequired() {
        return parametersRequired;
    }

    CommandType(String value, int parametersRequired) {
        this.value = value;
        this.parametersRequired = parametersRequired;
    }

    /**
     *  Matches the enum based on string value used in DrawingLauncher
     * @param type
     * @return
     */
    public static CommandType getTypeFor(String type) {
        for (CommandType cmdType : CommandType.values()) {
            if (cmdType.value.equalsIgnoreCase(type)) {
                return cmdType;
            }
        }
        return null;
    }
}

