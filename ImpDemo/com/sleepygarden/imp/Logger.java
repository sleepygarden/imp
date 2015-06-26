package com.sleepygarden.imp;

/**
 * Created by mcornell on 6/26/15.
 *
 */
public class Logger {

    public int fps = 60;
    public int logsPerSec = 1;
    private boolean drawOnThisPass = false;
    private static Logger instance = new Logger();
    private Logger() { }
    public static Logger Logger() {
        return instance;
    }

    public static void startFrame(long frame){
        instance.drawOnThisPass = (frame % (instance.fps / instance.logsPerSec) == 0);
    }
    public static void nextFrame() {
        instance.drawOnThisPass = false;
    }

    public static void drawLog(String string) {
        if (instance.drawOnThisPass) {
            System.out.println(string);
        }
    }
}
