package com.sleepygarden.example;

import com.sleepygarden.imp.*;
import com.sleepygarden.imp.pojo.Color;
import com.sleepygarden.imp.views.*;
import processing.core.PApplet;

/**
 * Created by mcornell on 6/22/15.
 *
 */

public class MyImpLayer extends ImpLayer {

    final Label label;
    final Button button;
    final TextField textField;
    final Label anotherLabel;

    final Checkbox checkbox;

    final Slider slider;

    final View simpleView;
    
    public MyImpLayer(PApplet app, int x, int y, int width, int height){
        super(app,x,y,width,height);

        this.view.fill = new Color(200,255);

        this.simpleView = new View(0,0,100,100);
        this.simpleView.fill = new Color(200,200,255);
        this.simpleView.hoverFill = new Color(255,200,200);

        Templates.Templates().Label.hoverFill = new Color(44,44,255);

        this.label = new Label(110,110,110,60);
        this.label.text = "Hi";
        this.label.fill = new Color(24,55,24);
        this.label.textColor = new Color(255,255,66);
        this.label.font = new ImpFont("Mermaid-Bold",40);
        this.label.font.antialias = true;

        this.checkbox = new Checkbox(20,20);
        this.checkbox.checkboxResponder = new CheckboxResponder() {
            @Override
            public void checkboxDidUpdate(Checkbox checkbox, boolean isChecked) {
                System.out.println("Check box was checked");
            }
        };

        this.slider = new Slider(200,500,200,40);
        this.slider.sliderResponder = new SliderResponder() {
            @Override
            public void sliderDidUpdate(Slider slider, float value) {
                System.out.println("slider did update value to "+value);
            }
        };

        this.anotherLabel = new Label(400,400,110,40);
        this.anotherLabel.text = "Hi Matt";
        this.anotherLabel.fill = new Color(0);
        this.anotherLabel.textColor = new Color(255);

        this.button = new Button(10,200,140,40);
        this.button.mouseEventResponder = new MouseEventResponder() {
            @Override
            public void mouseDown(View v) {
                label.text = "Down";
            }

            @Override
            public void mouseUp(View v) {
                label.text = "Up";
            }
        };
        this.button.text = "Click me!";
        this.button.hoverFill = new Color(122,233,144,200);
        this.button.downFill = new Color(88);

        this.textField = new TextField(300,300,80,40);
        this.textField.text = "Edit me";

        //this.view.addSubview(this.simpleView);
        //this.view.addSubview(this.label);
        //this.view.addSubview(this.button);
        //this.view.addSubview(this.textField);
        //this.view.addSubview(this.slider);
        this.view.addSubview(this.checkbox);

    }
}


