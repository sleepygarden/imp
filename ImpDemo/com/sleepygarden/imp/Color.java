package com.sleepygarden.imp;

public class Color {
    public int red, green, blue;
    float alpha;
    public Color (int red, int green, int blue){
        this.alpha = 255;
        this.red = red;
        this.blue = blue;
        this.green = green;
    }
    
    public Color (int red, int green, int blue, float alpha){
        this.alpha = alpha;
        this.red = red;
        this.blue = blue;
        this.green = green;
    }
    
    public Color (int grayscale){
        this.alpha = 255;
        this.red = grayscale;
        this.green = grayscale;
        this.blue = grayscale;
    }
    
    public Color (int grayscale, float alpha){
        this.alpha = alpha;
        this.red = grayscale;
        this.green = grayscale;
        this.blue = grayscale;
    }
    
    public Color (String hexstring){
        this.alpha = 1;
        this.red   = Integer.valueOf(hexstring.substring(1, 3), 16);
        this.green = Integer.valueOf(hexstring.substring(3, 5), 16);
        this.blue  = Integer.valueOf(hexstring.substring(5, 7), 16);
    }
}