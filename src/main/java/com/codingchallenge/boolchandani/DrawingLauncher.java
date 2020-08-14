package com.codingchallenge.boolchandani;

import com.codingchallenge.boolchandani.entities.Canvas;
import com.codingchallenge.boolchandani.helper.CanvasHelper;
import com.codingchallenge.boolchandani.model.CommandType;
import com.codingchallenge.boolchandani.helper.DrawingExecutor;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;


public class DrawingLauncher {

    public static void main(String... args) {

        String helpMessage = CanvasHelper.getHelpMessage();
        DrawingExecutor drawingExecutor = new DrawingExecutor();

        try (Scanner scanner = new Scanner(System.in);
             PrintStream outStream = System.out) {

            outStream.println(helpMessage);
            Canvas canvas = null;
            while (true) {
                String userInput = scanner.nextLine();
                if (!CanvasHelper.validateInputString(userInput)) {
                    outStream.println(helpMessage);
                }
                String[] splitCmd = userInput.split(" ");
                String cmdStr = splitCmd[0] != null ? splitCmd[0].toUpperCase() : null;
                if (!CanvasHelper.validateInputString(userInput)) {
                    outStream.println(helpMessage);
                }

                CommandType cmdType = CommandType.getTypeFor(cmdStr);
                if(CommandType.CMD_QUIT == cmdType){
                    outStream.println("Quitting");
                    scanner.close();
                    System.exit(0);
                }

                String[] parameters = Arrays.copyOfRange(splitCmd, 1, splitCmd.length);
                canvas = drawingExecutor.draw(cmdType, parameters, canvas, outStream);
            }
        }
    }

  }
