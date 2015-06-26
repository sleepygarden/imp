package com.sleepygarden.imp.views;

import com.sleepygarden.imp.CheckboxResponder;
import com.sleepygarden.imp.ImpLayer;
import com.sleepygarden.imp.MouseEventResponder;
import com.sleepygarden.imp.pojo.Color;
import com.sleepygarden.imp.pojo.Frame;

/**
 * Created by mcornell on 6/24/15.
 *
 */
public class Checkbox extends Button {

    public boolean isChecked;
    public Color checkedFill;

    private Frame checkFrame;
    private final int checkInset = 10;

    public CheckboxResponder checkboxResponder;

    public Checkbox(float x, float y){
        super(x,y,0,0);
        Template().Checkbox.style(this);
        setMouseEventResponder(new MouseEventResponder() {
            @Override
            public void mouseDown(View v) { }

            @Override
            public void mouseUp(View v) {
                Checkbox checkbox = (Checkbox)v;
                checkbox.isChecked = !checkbox.isChecked;
                if (checkbox.checkboxResponder != null){
                    checkbox.checkboxResponder.checkboxDidUpdate(checkbox,checkbox.isChecked);
                }
            }
        });
        this.checkFrame = new Frame(
                this.checkInset,
                this.checkInset,
                this.frame.width - this.checkInset*2,
                this.frame.height - this.checkInset*2);
    }

    public void impdraw(ImpLayer imp){
        super.impdraw(imp);
        if (this.isChecked) {
            imp.fill(this.checkedFill);
            imp.rect(this.checkFrame);
        }
    }
}
