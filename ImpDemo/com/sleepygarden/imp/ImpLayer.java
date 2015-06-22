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

    public boolean isTransformed;

    public PFont defaultFont;

    public static int cursorBlinkRate = 530;
    
    public ImpLayer(PApplet app, int x, int y, int width, int height){
        this.app = app;
        this.layerFrame = new Frame(x,y,width,height);
        this.background = new View(0,0,width,height);
        this.clipsToBounds = true;
        this.isTransformed = false;
        registerImp();
    }

    public void registerImp() {
        this.app.registerMethod("mouseEvent", this);
        this.app.registerMethod("keyEvent", this);
    }

    public void impdraw() {
        this.background.draw(this);
    }

    public void setup() {
        echoFonts();
        this.defaultFont = createFont("Helvetica",14);
    }

    public void draw() {
        this.app.pushMatrix();
        {
            this.app.translate(this.layerFrame.x, this.layerFrame.y);
            this.isTransformed = isTransformedOnThisDraw();
            impdraw();
        }
        this.app.popMatrix();
    }

    // ------------------
    // Key Events
    // ------------------

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
    // Helpers
    // ------------------

    public void echoFonts() {
        String[] fontList = PFont.list();
        for (String fontname : fontList){
            System.out.println(fontname);
        }
    }

    private Frame layerCroppedFrame(Frame frame){
        if (frame != null){
            Frame cropped = new Frame(frame);
            if (this.clipsToBounds){
                if (!this.background.frame.containsFrame(cropped)) {
                    cropped.cropToFit(this.background.frame);
                }
            }
            return cropped;
        }
        return new Frame(0,0,0,0);
    }

    public Point transformedPoint(Point p){
        float dX = this.app.screenX(p.x, p.y);
        float dY = this.app.screenY(p.x, p.y);
        return new Point(dX,dY);
    }

    public Polygon transformedFrame(Frame f){
        Polygon framePoly = new Polygon(f);
        return new Polygon(new Point[] {
                transformedPoint(framePoly.points[0]),
                transformedPoint(framePoly.points[1]),
                transformedPoint(framePoly.points[2]),
                transformedPoint(framePoly.points[3])
        });
    }

    private boolean isTransformedOnThisDraw() {
        Polygon framePoly = new Polygon(this.layerFrame);
        Polygon transPoly = transformedFrame(this.layerFrame);
        return (!framePoly.equals(transPoly));
    }

    // ------------------
    // PApplet Helpers
    // ------------------

    public void polygon(Polygon polygon){
        this.app.beginShape();
        for (Point p : polygon.points){
            this.app.vertex(p.x, p.y);
        }
        this.app.endShape(CLOSE);
    }

    public float screenX(Point p){
        return this.app.screenX(p.x,p.y);
    }

    public float screenY(Point p){
        return this.app.screenY(p.x,p.y);
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
        else {
            this.app.noFill();
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
        else {
            this.app.noSmooth();
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
        else {
            this.app.textFont(this.defaultFont);
        }
    }
    public PFont loadFont(String filename){ return this.app.loadFont(filename); }
    public PFont createFont(String fontname, int fontSize) { return this.app.createFont(fontname, fontSize, true); }
}


