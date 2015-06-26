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
    public float value;
    public Color sliderIndicatorFill;

    private boolean isVertical;
    public float indicatorSize;

    private Frame sliderIndicatorFrame;
    private float minValueCoord, maxValueCoord;

    private final int indicatorInset = 5;

    public SliderResponder sliderResponder;

    public Slider(float x, float y, float width, float height){
        super(x,y,width,height);
        Template().Slider.style(this);
        this.sliderIndicatorFrame = new Frame(x = this.indicatorSize/2 + this.indicatorInset,y,0,0);
        this.setIsVertical(false);
    }

    public boolean isVertical(){
        return this.isVertical;
    }

    public void setIsVertical(boolean isVertical){
        this.isVertical = isVertical;
        resizeIndicator();

        if (this.isVertical){
            this.minValueCoord = this.indicatorSize/2 + this.indicatorInset;
            this.maxValueCoord = this.frame.height - this.indicatorSize/2 - this.indicatorInset;
        }
        else {
            this.minValueCoord = this.indicatorSize/2 - this.indicatorInset;
            this.maxValueCoord = this.frame.width - this.indicatorSize/2 - this.indicatorInset;
        }
    }

    private void resizeIndicator() {
        if (this.isVertical) {
            this.sliderIndicatorFrame.width = this.frame.width - this.indicatorInset*2;
            this.sliderIndicatorFrame.height = this.indicatorSize;
            this.sliderIndicatorFrame.x = this.indicatorSize/2 + this.indicatorInset;
        }
        else {
            this.sliderIndicatorFrame.width = this.indicatorSize;
            this.sliderIndicatorFrame.height = this.frame.height - this.indicatorInset*2;
            this.sliderIndicatorFrame.y = this.indicatorSize/2 + this.indicatorInset;
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
            float mousePos, absoluteMin, absoluteMax;
            float newValue;
            if (this.isVertical){
                absoluteMin = imp.screenY(new Point(this.sliderIndicatorFrame.x,this.minValueCoord));
                absoluteMax = imp.screenY(new Point(this.sliderIndicatorFrame.x,this.maxValueCoord));
                mousePos = imp.mouseY;
            }
            else {
                absoluteMin = imp.screenX(new Point(this.minValueCoord, this.sliderIndicatorFrame.y));
                absoluteMax = imp.screenX(new Point(this.maxValueCoord, this.sliderIndicatorFrame.y));
                mousePos = imp.mouseX;
            }
            if (mousePos <= absoluteMin){
                newValue = 0;
            }
            else if (mousePos >= absoluteMax) {
                newValue = 1;
            }
            else {
                newValue = (mousePos - this.minValueCoord) / (this.maxValueCoord - this.minValueCoord) * 100;
            }
            if (this.value != newValue) {
                this.value = newValue;
                if (this.sliderResponder != null) {
                    this.sliderResponder.sliderDidUpdate(this, this.value);
                }
                updateSliderFrame();
            }
        }
    }

    private void updateSliderFrame() {

        float newPosition = (this.maxValueCoord - this.minValueCoord) * this.value  / 100 + this.minValueCoord;

        if (this.isVertical){
            this.sliderIndicatorFrame.y = newPosition;
        }
        else {
            this.sliderIndicatorFrame.x = newPosition;
        }
    }
}


