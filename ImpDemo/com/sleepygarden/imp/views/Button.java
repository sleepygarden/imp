package com.sleepygarden.imp.views;

import com.sleepygarden.imp.*;
import com.sleepygarden.imp.pojo.Color;
import com.sleepygarden.imp.pojo.Frame;

/**
 * Created by mcornell on 6/22/15.
 *
 */

public class Button extends Label {

    public boolean isDown;
    public boolean isInFocus;

    public Color downFill;
    public Color downStroke;

    public MouseEventResponder mouseEventResponder;

    public Button(float x, float y, float width, float height){
        super(x,y,width,height);
        Template().Button.style(this);
    }

    public void setMouseEventResponder(MouseEventResponder responder) {
        this.mouseEventResponder = responder;
    }

    protected Color fillForState() {
        if (this.isDown && this.downFill != null){
            return this.downFill;
        }
        return super.fillForState();
    }

    protected Color strokeForState() {
        if (this.isDown && this.downStroke != null){
            return this.downStroke;
        }
        return super.strokeForState();
    }

    public void updateMouse(ImpLayer imp){
        super.updateMouse(imp);
        if (this.isHovered){
            if (imp.app.mousePressed){
                FocusManager.sharedManager().reportGainFocus(this);
            }
            if (this.isDown != imp.app.mousePressed && this.mouseEventResponder != null){ // did change
                if (imp.app.mousePressed){
                    this.mouseEventResponder.mouseDown(this);
                }
                else {
                    this.mouseEventResponder.mouseUp(this);
                }
            }
            this.isDown = imp.app.mousePressed;
        }
        else {
            // if isDown, did drag out, unimplemented for brevity
            this.isDown = false;
        }
    }

    public void willGainFocus() {
        this.isInFocus = true;
     }
    public void willLoseFocus() {
        this.isInFocus = false; 
    }

    public void keyPressed(char key, int keyCode){ }
    public void keyReleased(char key, int keyCode){ }
}
