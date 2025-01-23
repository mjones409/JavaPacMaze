package example;

import org.teavm.jso.JSBody;

public class Js {


    @JSBody(params = {"message"}, script = "console.log(message);")
    public static native void log(String message);


    @JSBody(params = {}, script = "push();")
    public static native void push();

    @JSBody(params = {}, script = "pop();")
    public static native void pop();

    @JSBody(params = {"x", "y", "diameter"}, script = "circle(x, y, diameter);")
    public static native void circle(float x, float y, float diameter);


    @JSBody(params = {"width", "height"}, script = "createCanvas(width,height);")
    public static native void createCanvas(float width, float height);


    @JSBody(params = {"color"}, script = "background(color);")
    public static native void background(int color);

    @JSBody(params = {"color"}, script = "fill(color);")
    public static native void fill(float color);


    @JSBody(params = {"s", "x", "y"}, script = "text(s,x,y);")
    public static native void text(String s, int x, int y);


    @JSBody(params = {"x", "y", "size"}, script = "square(x,y,size);")
    public static native void square(int x, int y, int size);

    @JSBody(params = {"r", "g", "b"}, script = "fill(r,g,b);")
    public static native void fill(int r, int g, int b);


    @JSBody(params = {}, script = "noStroke();")
    public static native void noStroke();

    @JSBody(params = {"r", "g", "b"}, script = "background(r,g,b);")
    public static native void background(int r, int g, int b);

    @JSBody(params = {"x", "y"}, script = "movePlayer(x,y);")
    public static native void movePlayer(int x, int y);


    @JSBody(params = {"x", "y"}, script = "moveEnemyPink(x,y);")
    public static native void moveEnemyPink(int x, int y);

    @JSBody(params = {"x", "y"}, script = "moveEnemyBlue(x,y);")
    public static native void moveEnemyBlue(int x, int y);

    @JSBody(params = {"x", "y"}, script = "moveEnemyRed(x,y);")
    public static native void moveEnemyRed(int x, int y);

    @JSBody(params = {"x", "y"}, script = "moveEnemyOrange(x,y);")
    public static native void moveEnemyOrange(int x, int y);

    @JSBody(params = {"x", "y"}, script = "moveEnemyPurple(x,y);")
    public static native void moveEnemyPurple(int x, int y);

    @JSBody(params = {"x", "y"}, script = "moveEnemyGreen(x,y);")
    public static native void moveEnemyGreen(int x, int y);

}
