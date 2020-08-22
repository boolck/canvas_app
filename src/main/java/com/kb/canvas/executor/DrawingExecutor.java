package com.kb.canvas.executor;

import com.kb.canvas.command.CommandType;
import com.kb.canvas.excp.CanvasException;
import com.kb.canvas.model.*;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.function.Supplier;


public class DrawingExecutor {

    public static final char CHAR_LINE = 'x';
    public static final char CHAR_HORIZONTAL = '-';
    public static final char CHAR_VERTICAL = '|';
    public static final char CHAR_BLANK = ' ';

    private final Canvas canvas;
    private final PrintStream printStream;


    public DrawingExecutor() {
        this(System.out);
    }

    public DrawingExecutor(PrintStream printStream) {
        this.printStream = printStream;
        this.canvas = new Canvas();
    }

    public static void fillBlank(char[] x) {
        Arrays.fill(x, CHAR_BLANK);
    }

    private void drawEntityAndRenderCanvas(Entity entity, Canvas canvas, boolean addToCanvas) {
        entity.draw();
        if (addToCanvas) {
            canvas.add(entity);
        }
        this.renderCanvas();
    }

    private void createEntity(Canvas canvas, Supplier<Entity> supplier, String[] parameters, CommandType cmdType) throws CanvasException {
        canvas.validateDimension();
        Entity entity = supplier.get();
        entity.validateAndSet(parameters, cmdType);
        this.drawEntityAndRenderCanvas(entity, canvas, true);
    }

    public Canvas draw(CommandType cmdType, String... parameters) throws CanvasException {
        switch (cmdType) {
            case CMD_CANVAS:
                canvas.validateAndSet(parameters, CommandType.CMD_CANVAS);
                drawEntityAndRenderCanvas(canvas, null, false);
                break;
            case CMD_LINE:
                this.createEntity(canvas, () -> new Line(canvas.getDimension()), parameters, cmdType);
                break;
            case CMD_RECTANGLE:
                this.createEntity(canvas, () -> new Rectangle(canvas.getDimension()), parameters, cmdType);
                break;
            case CMD_BOX:
                this.createEntity(canvas, () -> new BoxFill(canvas.getDimension()), parameters, cmdType);
                break;
            case CMD_QUIT:
                break;
            default:
                throw new CanvasException("Invalid Command Type encountered");
        }
        return canvas;
    }

    private void renderCanvas() {
        Canvas.CanvasDimension dimension = canvas.getDimension();
        for (int i = 0; i < dimension.getHeight(); i++) {
            for (int j = 0; j < dimension.getWidth(); j++) {
                printStream.print(dimension.getMatrix()[i][j]);
            }
            printStream.println();
        }
    }

}
