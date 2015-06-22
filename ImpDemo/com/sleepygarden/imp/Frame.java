package com.sleepygarden.imp;

public class Frame extends Object {
    float x, y, width, height;

    public Frame(float x, float y, float w, float h){
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }

    public Frame(Frame toCopy){
        this.x = toCopy.x;
        this.y = toCopy.y;
        this.width = toCopy.width;
        this.height = toCopy.height;
    }

    public float x1() { return this.x; }
    public float y1() { return this.y; }
    public float x2() { return this.x + this.width; }
    public float y2() { return this.y + this.height; }

    public boolean containsFrame(Frame subframe){
        return (subframe.x >= this.x &&
            subframe.y >= this.y &&
            subframe.width <= this.width &&
            subframe.height <= this.height);
    }

    public void cropToFit(Frame fit){
        if ((this.x1() > fit.x2() && this.y1() > fit.y2()) ||
            (this.x2() < fit.x1() && this.y2() < fit.y1())) { // it's totally off the screen
            this.x = 0;
            this.y = 0;
            this.width = 0;
            this.height = 0;
        }

        float xOriginOffset = 0;
        float yOriginOffset = 0;
        if (this.x < fit.x) {
            xOriginOffset = fit.x - this.x;
            this.x = 0;
        }
        if (this.y < fit.y) {
            yOriginOffset = fit.y - this.y;
            this.y = 0;
        }

        this.width = this.width - xOriginOffset;
        this.height = this.height - yOriginOffset;

        if (this.x + this.width > fit.width){
            this.width = fit.width - this.x;
        }
        if (this.y + this.height > fit.height){
            this.height = fit.height - this.y;
        }
    }
}