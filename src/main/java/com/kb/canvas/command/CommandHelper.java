package com.kb.canvas.command;

import com.kb.canvas.excp.CanvasWrongArgsException;

import java.util.Arrays;

public final class CommandHelper {

    private static final String CMD_SEPERATOR = " ";

    public static final String HELP_MSG = "Please input one of the following commands:\n" +
            "C w h           Should create a new canvas of width w and height h.\n"+
            "L x1 y1 x2 y2   Should create a new line from (x1,y1) to (x2,y2).\n"+
            "R x1 y1 x2 y2   Should create a new rectangle, whose upper left corner is (x1,y1) and lower right corner is (x2,y2).\n"+
            "B x y c         Should fill the entire area connected to (x,y) with colour c.\n"+
            "Q               Should quit the program.";

    public static void validateInputArguments(String... argsArray) throws CanvasWrongArgsException {
        for(String str : argsArray){
            try {
                int x = Integer.parseInt(str);
                if (x < 0) {
                    throw new CanvasWrongArgsException("Input command has negative number");
                }
            }
            catch(NumberFormatException ex){
                throw new CanvasWrongArgsException("Input command has non numeric arguments");
            }
        }
    }

    private static void validateInputString(String userInput) throws CanvasWrongArgsException {
        if (userInput == null || userInput.isEmpty()){
            throw new CanvasWrongArgsException("Input String should not be null or empty");
        }
    }

    public static String getCommandTypeString(String userInput) throws CanvasWrongArgsException {
        validateInputString(userInput);
        String[] splitCmd = userInput.split(CMD_SEPERATOR);
        String cmd = splitCmd[0].toUpperCase();
        if(cmd.length()!=1){
            throw new CanvasWrongArgsException("Input String should have space seperated commands");
        }
        return cmd;
    }

    public static String[] getCommandStringArgs(String userInput) throws CanvasWrongArgsException {
        validateInputString(userInput);
        String[] splitCmd = userInput.split(CMD_SEPERATOR);
        if(splitCmd.length<2){
            throw new CanvasWrongArgsException("Input String should have space seperated commands");
        }
        return Arrays.copyOfRange(splitCmd, 1, splitCmd.length);
    }

}
