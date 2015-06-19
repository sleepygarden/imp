package com.sleepygarden.imp;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class ImpLayer implements PConstants {
    public final Frame layerFrame;
    public final PApplet app;
    public final View background;

    public boolean clipsToBounds;

    public int mouseX;
    public int mouseY;
    public boolean mousePressed;

    public static int cursorBlinkRate = 530;


    
    public ImpLayer(PApplet app, int x, int y, int width, int height){
        this.app = app;
        this.layerFrame = new Frame(x,y,width,height);
        this.background = new View(0,0,width,height);
        this.clipsToBounds = true;
        registerImp();
    }

    public void registerImp() {
        this.app.registerMethod("mouseEvent", this);
        this.app.registerMethod("keyEvent", this);
    }

    public void mouseEvent(MouseEvent event) {
        this.mouseX = event.getX();
        this.mouseY = event.getY();

        if (event.getAction() == MouseEvent.PRESS) {
            this.mousePressed = true;
        }
        else if (event.getAction() == MouseEvent.RELEASE) {
            this.mousePressed = false;
        }
    }

    public void keyEvent(KeyEvent event){
        if (event.getAction() == KeyEvent.TYPE){
            return;
        }

        int keyCode = event.getKeyCode();
        char key = event.getKey();

        if (event.getAction() == KeyEvent.PRESS){
            keyPressed(key,keyCode);
        }
        else {
            keyReleased(key,keyCode);
        }
    }

    public void setup() { 

    }
    public void draw() { 
        this.background.draw(this);
    }

    public void echoFonts() {
        String[] fontList = PFont.list();
        for (String fontname : fontList){
            System.out.println(fontname);
        }
    }

    // ------------------
    // Key Events
    // ------------------
    public void keyPressed(char key, int keyCode) {
        Button focusedView = FocusManager.sharedManager().focusedView;
        if (focusedView != null) {
            focusedView.keyPressed(key,keyCode);
        }
    }
    public void keyReleased(char key, int keyCode) {
        Button focusedView = FocusManager.sharedManager().focusedView;
        if (focusedView != null) {
            focusedView.keyReleased(key, keyCode);
        }
    }

    // ------------------
    // Mouse Events
    // ------------------
    public void mouseClicked() {}
    public void mousePressed() {}
    public void mouseReleased() {}
    public void mouseMoved() {}
    public void mouseDragged() {}




    // ------------------
    // PApplet Helpers
    // ------------------

    private Frame layerCroppedFrame(Frame frame){
        if (frame != null){
            Frame cropped = new Frame(frame);
            cropped.x += this.layerFrame.x;
            cropped.y += this.layerFrame.y;
            if (this.clipsToBounds){
                if (!this.layerFrame.containsFrame(cropped)) {
                    cropped.cropToFit(this.layerFrame);
                }
            }
            return cropped;
        }
        return new Frame(0,0,0,0);
    }

    public void rect(Frame frame){
        if (frame != null){
            Frame cropped = layerCroppedFrame(frame);
            this.app.rect(cropped.x, 
                cropped.y, 
                cropped.width, 
                cropped.height);            
        }
    }

    public void text(String text, Frame frame) {
        if (text != null && frame != null){
            Frame cropped = layerCroppedFrame(frame);
            this.app.text(text, 
                cropped.x, 
                cropped.y, 
                cropped.width, 
                cropped.height);            
        }
    }

    public void textAlign(int hAlign){
        this.app.textAlign(hAlign);
    }
    public void textAlign(int hAlign, int vAlign){
        this.app.textAlign(hAlign, vAlign);
    }

    public int color(Color c) {
        if (c != null){
            return this.app.color(c.red,c.green,c.blue, c.alpha);
        }
        return 0;
    }

    public void fill(Color c){
        if (c != null){
            if (c.alpha <= 0){
                this.app.noFill();
            }
            else {
                this.app.fill(this.color(c),c.alpha);
            }
        }
    }

    public void stroke(Color c){
        if (c != null){
            if (c.alpha <= 0){
                this.app.noStroke();
            }
            else {
                this.app.stroke(this.color(c),c.alpha);
            }
        }
    }

    public void strokeWeight(int weight){
        if (weight == 0){
            this.app.noStroke();
        }
        else {
            this.app.strokeWeight(weight);
        }
    }

    public void textFont(PFont font){
        if (font != null){
            this.app.textFont(font);
        }
    }
    public PFont loadFont(String filename){
        return this.app.loadFont(filename);
    }
    public PFont createFont(String fontname, int fontSize) {
        return this.app.createFont(fontname,fontSize,true);
    }
}


