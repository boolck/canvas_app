package com.codingchallenge.boolchandani.helper;

public class CanvasHelper {

    public static String NEGATIVE_NUMBER_ERROR_MSG = "All numvers must be positive";
    public static String VALID_ARGS_ERROR_MSG = "Please pass valid arguments";
    public static String CANVAS_MISSING_ERROR_MSG = "Please create a canvas first";
    public static char CHAR_LINE = 'x';
    public static char CHAR_HORIZONTAL = '-';
    public static char CHAR_VERTICAL = '|';
    public static char CHAR_BLANK = ' ';


    public static String getHelpMessage() {
        return new StringBuilder().append("Please input one of the following commands:")
                .append("\n")
                .append("Entity 		Description")
                .append("\n")
                .append("C w h           Should create a new canvas of width w and height h.")
                .append("\n")
                .append("L x1 y1 x2 y2   Should create a new line from (x1,y1) to (x2,y2).")
                .append("\n")
                .append("R x1 y1 x2 y2   Should create a new rectangle, whose upper left corner is (x1,y1) and lower right corner is (x2,y2). ")
                .append("\n")
                .append("B x y c         Should fill the entire area connected to (x,y) with colour c.")
                .append("\n").append("Q               Should quit the program.")
                .append("\n").append("----------------------------------------------->")
                .append("\n")
                .toString();

    }

    public static boolean isAllPositive(int... intArray){
        for(int x : intArray){
            if(x < 0){
                return false;
            }
        }
        return true;
    }

    public static boolean validateInputString(String userInput) {
        return userInput != null && !userInput.isEmpty() ;
    }
}
