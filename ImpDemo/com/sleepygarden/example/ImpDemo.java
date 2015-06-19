package com.sleepygarden.example;

import processing.core.PApplet;

/**
 * Created by mcornell on 6/19/15.
 */

public class ImpDemo extends PApplet {

    final int appWidth = 700;
    final int appHeight = 700;
    final int fps = 60;
    final MyImpLayer imp = new MyImpLayer(this, 20, 20, appWidth-40, appHeight-40);

    public void setup() {
        println("Starting Imp Demo");
        size(appWidth, appHeight);
        frameRate(fps);
        colorMode(RGB);
        rectMode(CORNER);
        imp.setup();
    }
    public void draw() {
        background(255);
        imp.draw();
    }
}
