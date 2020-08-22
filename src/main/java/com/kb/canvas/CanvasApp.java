package com.kb.canvas;

import com.kb.canvas.command.*;
import com.kb.canvas.excp.CanvasException;
import com.kb.canvas.executor.DrawingExecutor;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;


public class CanvasApp {

    private final DrawingExecutor drawingExecutor;

    public CanvasApp(){
        this(System.out);
    }

    public CanvasApp(PrintStream printStream){
        drawingExecutor = new DrawingExecutor(printStream);
    }

    public static void main(String... args) {
        CanvasApp app = new CanvasApp();
        try {
            app.run(System.in);
        }
        catch (CanvasException e) {
            System.out.println("Exception encountered: " + e.getMessage());
        }
    }

    public void run(InputStream inStream) throws CanvasException{
        boolean continueDrawing = true;
        try (Scanner scanner = new Scanner(inStream)) {
            while (continueDrawing) {
                System.out.println("enter command: ");
                String userInput = scanner.nextLine();
                continueDrawing = this.createCanvasUntilQuitCommand(userInput);
            }
        }
    }

    public boolean createCanvasUntilQuitCommand(String userInput) throws CanvasException{
        String cmdStr = CommandHelper.getCommandTypeString(userInput);
        CommandType cmdType = CommandType.getTypeFor(cmdStr);
        Objects.requireNonNull(cmdType,"CommandType should not be null");

        if (CommandType.CMD_QUIT == cmdType) {
            return false;
        }
        else {
            String[] cmdArguments = CommandHelper.getCommandStringArgs(userInput);
            drawingExecutor.draw(cmdType,cmdArguments);
        }
        return true;
    }

  }
