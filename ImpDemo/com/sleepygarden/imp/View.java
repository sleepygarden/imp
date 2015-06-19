package com.sleepygarden.imp;

import processing.core.PConstants;

public class View implements PConstants {

    public Frame frame;

    public Color fill;
    public Color hovorFill;
    public Color stroke;
    public Color hovorStoke;

    public int strokeWeight;
    public boolean isHovered;

    public View() {
        super();
        this.strokeWeight = 0;
        this.fill = new Color(255);
        this.stroke = new Color(255);
        this.frame = new Frame(0,0,0,0);
    }

    public View(float x, float y, float width, float height){
        this();
        this.frame = new Frame(x,y,width,height);
    }

    protected Color fillForState() {
        if (this.isHovered && this.hovorFill != null){
            return this.hovorFill;
        }
        return this.fill;
    }

    protected Color strokeForState() {
        if (this.isHovered && this.hovorStoke != null){
            return this.hovorStoke;
        }
        return this.stroke;
    }

    public void draw(ImpLayer imp) {
        updateMouse(imp);
        imp.stroke(strokeForState());
        imp.strokeWeight(this.strokeWeight);
        imp.fill(fillForState());
        imp.rect(this.frame);
    }

    public void updateMouse(ImpLayer imp){
        this.isHovered = (imp.app.mouseX >= this.frame.x + imp.layerFrame.x &&
                          imp.app.mouseX <= this.frame.x + this.frame.width + imp.layerFrame.x && 
                          imp.app.mouseY >= this.frame.y + imp.layerFrame.y && 
                          imp.app.mouseY <= this.frame.y + this.frame.height + imp.layerFrame.y);
    }
}
