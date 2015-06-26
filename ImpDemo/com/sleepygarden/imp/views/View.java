package com.sleepygarden.imp.views;

import com.sleepygarden.imp.Templates;
import com.sleepygarden.imp.pojo.Color;
import com.sleepygarden.imp.ImpLayer;
import com.sleepygarden.imp.pojo.Polygon;
import com.sleepygarden.imp.pojo.Frame;
import processing.core.PConstants;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by mcornell on 6/22/15.
 *
 */

public class View implements PConstants {
    public Frame frame;

    public Color fill;
    public Color hoverFill;
    public Color stroke;
    public Color hoverStoke;

    public int strokeWeight;
    public boolean isHovered;

    private ArrayList<View> subviews;

    public View(float x, float y, float width, float height){
        super();
        this.subviews = new ArrayList<>();
        this.frame = new Frame(x,y,width,height);
        Template().View.style(this);
    }

    protected Templates Template() {
        return Templates.Templates();
    }

    public Frame bounds() {
        return new Frame(0,0,this.frame.width,this.frame.height);
    }

    protected Color fillForState() {
        if (this.isHovered && this.hoverFill != null){
            return this.hoverFill;
        }
        return this.fill;
    }

    protected Color strokeForState() {
        if (this.isHovered && this.hoverStoke != null){
            return this.hoverStoke;
        }
        return this.stroke;
    }

    public void impdraw(ImpLayer imp) {
        imp.fill(fillForState());
        imp.stroke(strokeForState());
        imp.strokeWeight(this.strokeWeight);
        imp.rect(this.bounds());
    }

    public void updateMouse(ImpLayer imp){
        Polygon transformedFrame = imp.transformedFrame(this.bounds());
        this.isHovered = transformedFrame.contains(imp.mouseX,imp.mouseY);
    }

    public ArrayList<View> subviews() {
        return this.subviews;
    }

    public void addSubview(View subview){
        this.subviews.add(subview);
    }

    public void addSubview(int idx, View subview){
        this.subviews.add(idx,subview);
    }

}
