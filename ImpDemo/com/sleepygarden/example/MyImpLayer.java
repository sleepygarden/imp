package com.sleepygarden.example;

import com.sleepygarden.imp.*;
import processing.core.PApplet;

public class MyImpLayer extends ImpLayer {
    
    final View view;
    final Label label;

    final Button button;

    final TextField textField;

    final Label anotherLabel;
    
    public MyImpLayer(PApplet app, int x, int y, int width, int height){
        super(app,x,y,width,height);
        this.clipsToBounds = false;

        this.background.fill = new Color(200,255);

        this.view = new View(0,0,100,100);
        this.view.fill = new Color (33,33,55);

        this.label = new Label(110,110,60,30);
        this.label.text = "Hi";
        this.label.fill = new Color(24,55,24);
        this.label.textColor = new Color(255,255,66);

        this.anotherLabel = new Label(400,400,100,40);
        this.anotherLabel.text = "Hi Matt";
        this.anotherLabel.fill = new Color(0);
        this.anotherLabel.textColor = new Color(255);

        this.button = new Button(10,200,80,40);
        this.button.setMouseEventResponder( new MouseEventResponder() {
            public void mouseDown(View v) {
                label.text = "Down";
            }
            public void mouseUp(View v) {
                label.text = "Up";
            }
        });
        this.button.text = "Click me!";
        this.button.hovorFill = new Color(122,233,144,200);
        this.button.downFill = new Color(88);

        this.textField = new TextField(300,300,80,40);
        this.textField.text = "Edit me";
        this.textField.focusStroke = new Color(0);

        ViewContainer container = new ViewContainer();
        container.insert(this.label,-1);
        container.insert(this.button,-2);
        container.insert(this.anotherLabel,5);
        container.insert(this.textField,-1);

    }
    
    public void setup() {
        super.setup();
        this.label.font = createFont("Mermaid-Bold",20);
    }

    public void impdraw() {
        super.impdraw();
        this.view.draw(this);
        this.label.draw(this);
        this.button.draw(this);
        this.textField.draw(this);
        this.anotherLabel.draw(this);
    }
}


