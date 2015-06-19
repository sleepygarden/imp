package com.sleepygarden.imp;

import processing.core.PApplet;
import processing.core.PFont;

public class Label extends View {
    
    public PFont font;
    public String text;
    public Color textColor;
    public int hAlignment;
    public int vAlignment;
    
    public Label() {
        super();
        this.textColor = new Color(0);
        this.hAlignment = PApplet.LEFT;
        this.vAlignment = PApplet.CENTER;
    }
    
    public Label(float x, float y, float width, float height){
        super(x,y,width,height);
        this.textColor = new Color(0);
        this.hAlignment = PApplet.LEFT;
        this.vAlignment = PApplet.CENTER;
    }
    
    public void draw(ImpLayer imp){
        super.draw(imp);
        imp.textFont(this.font);
        imp.fill(this.textColor);
        imp.textAlign(this.hAlignment,this.vAlignment);
        imp.text(this.stringForRender(),this.frame);
    }

    protected String stringForRender() {
        return this.text;
    }
}