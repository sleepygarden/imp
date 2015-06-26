package com.sleepygarden.imp.views;

import com.sleepygarden.imp.ImpLayer;
import com.sleepygarden.imp.pojo.Point;

/**
 * Created by mcornell on 6/26/15.
 *
 */
public class VerticalSlider extends Slider {

    public VerticalSlider(float x, float y, float width, float height){
        super(x,y,width,height);
        this.sliderIndicatorFrame.x = (float)Math.floor(this.indicatorSize/2 + this.indicatorInset);
    }

    @Override
    protected void measureAbsoluteMinMax(ImpLayer imp) {
        if (!this.hasCalculatedAbsoluteMinMax){
            this.relativeMinPos = (int)Math.floor(this.indicatorSize/2 + this.indicatorInset);
            this.relativeMaxPos = (int)Math.floor(this.frame.height - this.indicatorSize/2 - this.indicatorInset);
            Point minPoint = new Point(this.sliderIndicatorFrame.x, this.relativeMinPos);
            Point maxPoint = new Point(this.sliderIndicatorFrame.x, this.relativeMaxPos);
            this.absoluteMinPos = (int)Math.floor(imp.screenY(minPoint));
            this.absoluteMaxPos = (int)Math.floor(imp.screenY(maxPoint));
            this.hasCalculatedAbsoluteMinMax = true;
        }
    }

    @Override
    protected void resizeIndicator() {
        this.sliderIndicatorFrame.width = this.frame.width - this.indicatorInset*2;
        this.sliderIndicatorFrame.height = this.indicatorSize;
    }

    @Override
    protected float calculateSliderValue(ImpLayer imp){
        int mousePos = imp.mouseY;
        measureAbsoluteMinMax(imp);
        if (mousePos <= this.absoluteMinPos){
            return 1;
        }
        else if (mousePos >= this.absoluteMaxPos) {
            return 0;
        }
        else {
            return 1 - (float)(mousePos - this.absoluteMinPos) / (float)(this.absoluteMaxPos - this.absoluteMinPos);
        }
    }

    @Override
    protected void updateSliderFrame() {
        float newPosition = (this.relativeMaxPos - this.relativeMinPos) * this.floatValue + this.relativeMinPos;
        this.sliderIndicatorFrame.x = this.relativeMinPos;
        this.sliderIndicatorFrame.y = this.frame.height - newPosition;
    }
}
