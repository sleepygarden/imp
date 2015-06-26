package com.sleepygarden.imp.views;

import com.sleepygarden.imp.ImpLayer;
import com.sleepygarden.imp.SliderResponder;
import com.sleepygarden.imp.pojo.Color;
import com.sleepygarden.imp.pojo.Frame;
import com.sleepygarden.imp.pojo.Point;

/**
 * Created by mcornell on 6/25/15.
 *
 */

public class Slider extends Button {

    public int minValue, maxValue, value;
    public float floatValue;
    public Color sliderIndicatorFill;

    public float indicatorSize;

    public SliderResponder sliderResponder;

    protected Frame sliderIndicatorFrame;
    protected int absoluteMinPos, absoluteMaxPos;
    protected int relativeMinPos, relativeMaxPos;
    protected boolean hasCalculatedAbsoluteMinMax;
    protected final int indicatorInset = 5;

    public Slider(float x, float y, float width, float height){
        super(x,y,width,height);
        Template().Slider.style(this);
        this.sliderIndicatorFrame = new Frame(this.indicatorSize/2 + this.indicatorInset,this.indicatorSize/2 + this.indicatorInset,0,0);
        this.hasCalculatedAbsoluteMinMax = false;
        resizeIndicator();
        this.floatValue = (this.value - this.minValue) / (this.maxValue - this.minValue);
    }

    protected void measureAbsoluteMinMax(ImpLayer imp) {
        if (!this.hasCalculatedAbsoluteMinMax){
            this.relativeMinPos = (int)Math.floor(this.indicatorSize/2 + this.indicatorInset);
            this.relativeMaxPos = (int)Math.floor(this.frame.width - this.indicatorSize/2 - this.indicatorInset);
            Point minPoint = new Point(this.relativeMinPos, this.sliderIndicatorFrame.y);
            Point maxPoint = new Point(this.relativeMaxPos, this.sliderIndicatorFrame.y);
            this.absoluteMinPos = (int)Math.floor(imp.screenX(minPoint));
            this.absoluteMaxPos = (int)Math.floor(imp.screenX(maxPoint));
            this.hasCalculatedAbsoluteMinMax = true;
        }
    }

    protected void resizeIndicator() {
        this.sliderIndicatorFrame.width = this.indicatorSize;
        this.sliderIndicatorFrame.height = this.frame.height - this.indicatorInset*2;
    }

    protected void updateSliderFrame() {
        float newPosition = this.relativeMinPos + (this.relativeMaxPos - this.relativeMinPos) * this.floatValue;
        this.sliderIndicatorFrame.x = newPosition;
        this.sliderIndicatorFrame.y = this.relativeMinPos;
    }

    protected float calculateSliderValue(ImpLayer imp){
        int mousePos = imp.mouseX;
        measureAbsoluteMinMax(imp);
        if (mousePos <= this.absoluteMinPos){
            return 0;
        }
        else if (mousePos >= this.absoluteMaxPos) {
            return 1;
        }
        else {
            return (float)(mousePos - this.absoluteMinPos) / (float)(this.absoluteMaxPos - this.absoluteMinPos);
        }
    }

    @Override
    public void impdraw(ImpLayer imp){
        super.impdraw(imp);
        imp.pushMatrix();
        {
            imp.fill(this.sliderIndicatorFill);
            imp.rectMode(CENTER);
            imp.rect(this.sliderIndicatorFrame);
        }
        imp.popMatrix();
    }

    @Override
    public void updateMouse(ImpLayer imp){
        super.updateMouse(imp);
        if (this.isDown){
            float newValue = calculateSliderValue(imp);
            if (this.floatValue != newValue) {
                this.floatValue = newValue;
                this.value = (int)(this.floatValue * (this.maxValue - this.minValue) + this.minValue);
                if (this.sliderResponder != null) {
                    this.sliderResponder.sliderDidUpdate(this, this.value);
                }
            }
            updateSliderFrame();
        }
    }
}


