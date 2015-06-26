package com.sleepygarden.imp;

import com.sleepygarden.imp.pojo.Color;
import com.sleepygarden.imp.pojo.Point;
import com.sleepygarden.imp.pojo.Polygon;
import com.sleepygarden.imp.views.Button;
import com.sleepygarden.imp.pojo.Frame;
import com.sleepygarden.imp.views.View;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.util.HashMap;

/**
 * Created by mcornell on 6/22/15.
 *
 */

public class ImpLayer implements PConstants {

    private static HashMap<ImpFont,PFont> impFontCache = new HashMap<>();

    public final PApplet app;
    public final View view;

    public int mouseX, mouseY;
    public boolean mousePressed;

    public static int cursorBlinkRate = 530;

    public ImpLayer(PApplet app, int x, int y, int width, int height){
        this.app = app;
        this.view = new View(x, y, width, height);
        registerImp();
    }

    public void registerImp() {
        this.app.registerMethod("mouseEvent", this);
        this.app.registerMethod("keyEvent", this);
    }

    private void recursiveUpdate(View view, boolean superIsHovered){
        pushStyle();
        pushMatrix();
        {
            this.app.translate(view.frame.x, view.frame.y);

            if (superIsHovered){
                view.updateMouse(this);
                superIsHovered = view.isHovered;
            }
            else {
                view.isHovered = false;
            }

            view.impdraw(this);
            for (View subview : view.subviews()){
                recursiveUpdate(subview, superIsHovered);
            }
        }
        popMatrix();
        popStyle();

    }

    public void setup() {
        ImpFont.echoFonts();
    }

    public void draw() {
        Logger.startFrame(this.app.frameCount);
        pushStyle();
        pushMatrix();
        {
            this.app.hint(DISABLE_DEPTH_TEST);
            this.app.camera();
            this.app.noLights();

            recursiveUpdate(this.view,true);

            this.app.hint(ENABLE_DEPTH_TEST);

        }
        popMatrix();
        popStyle();
        Logger.nextFrame();
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

    public Point transformedPoint(Point p){
        float dX = this.screenX(p);
        float dY = this.screenY(p);
        return new Point(dX,dY);
    }

    public Polygon transformedFrame(Frame f){
        return new Polygon(new Point[] {
                transformedPoint(new Point(f.x,f.y)),
                transformedPoint(new Point(f.x2(),f.y)),
                transformedPoint(new Point(f.x2(),f.y2())),
                transformedPoint(new Point(f.x,f.y2()))
        });
    }

    // ------------------
    // PApplet Helpers
    // ------------------

    public void translate(Point p){
        this.app.translate(p.x,p.y);
    }

    public void rectMode(int rectMode){
        this.app.rectMode(rectMode);
    }

    public void pushStyle() {
        this.app.pushStyle();
    }
    public void popStyle() {
        this.app.popStyle();
    }
    public void pushMatrix() {
        this.app.pushMatrix();
    }
    public void popMatrix() {
        this.app.popMatrix();
    }

    public float textWidth(String text){
        return this.app.textWidth(text);
    }
    public float textHeight(){
        return this.app.textAscent() + this.app.textDescent();
    }

    public float screenX(Point p){
        return this.app.screenX(p.x,p.y);
    }
    public float screenY(Point p){
        return this.app.screenY(p.x,p.y);
    }

    public void rect(Frame frame){
        if (frame != null){
            this.app.rect(frame.x,
                    frame.y,
                    frame.width,
                    frame.height);
        }
    }

    public void text(String text, Frame frame) {
        if (text != null && frame != null){
            this.app.text(text,
                    frame.x,
                    frame.y,
                    frame.width,
                    frame.height);
        }
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
            this.app.noStroke();
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

    public void textFont(ImpFont impFont) {
        if (impFont != null) {
            PFont pFont = impFont.pfont;

            if (pFont == null) {
                pFont = impFontCache.get(impFont);

                if (pFont == null) {
                    if (impFont.loadsFromData) {
                        pFont = this.app.loadFont(impFont.name);
                    } else {
                        pFont = this.app.createFont(impFont.name, impFont.size, impFont.antialias);
                    }
                }
                if (pFont != null){
                    impFont.pfont = pFont;
                    impFontCache.put(impFont, pFont);
                }
            }
            if (pFont != null) {
                this.app.textFont(pFont,impFont.size);
            }
        }
    }
}


