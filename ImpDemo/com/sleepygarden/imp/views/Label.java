package com.sleepygarden.imp.views;

import com.sleepygarden.imp.*;
import com.sleepygarden.imp.pojo.Color;
import com.sleepygarden.imp.pojo.Frame;
import processing.core.PApplet;

/**
 * Created by mcornell on 6/22/15.
 *
 */

public class Label extends View {

    private Frame textFrame;
    public ImpFont font;
    public String text;
    public Color textColor;
    public int hAlignment;
    public int vAlignment;

    public float topPadding,leftPadding,rightPadding,bottomPadding;

    public Label(float x, float y, float width, float height){
        super(x,y,width,height);
        Template().Label.style(this);
        resizeTextFrame();
    }

    public void resizeTextFrame() {
        this.textFrame = new Frame(this.leftPadding, this.bottomPadding, this.frame.width, this.frame.height);
        this.textFrame.width -= (this.leftPadding + this.rightPadding);
        this.textFrame.height -= (this.bottomPadding + this.topPadding);
    }

    public void sizeToFit(ImpLayer imp){
        imp.pushStyle();
        imp.textFont(this.font);
        float width = imp.textWidth(this.text) + this.leftPadding + this.rightPadding;
        imp.popStyle();
        float height = imp.textHeight() + this.topPadding + this.bottomPadding;
        this.frame.width = width;
        this.frame.height = height;
        resizeTextFrame();
    }

    public void impdraw(ImpLayer imp){
        super.impdraw(imp);
        imp.textFont(this.font);
        imp.fill(this.textColor);
        imp.textAlign(this.hAlignment,this.vAlignment);
        imp.text(this.stringForRender(),this.textFrame);
    }

    protected String stringForRender() {
        return this.text;
    }
}