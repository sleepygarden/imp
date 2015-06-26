package com.sleepygarden.imp;

import processing.core.PFont;

/**
 * Created by mcornell on 6/23/15.
 *
 */
public class ImpFont {
    public String name;
    public int size;
    public boolean loadsFromData;
    public boolean antialias;
    public PFont pfont;

    public ImpFont(String name, int size){
        this.name = name;
        this.size = size;
        this.loadsFromData = false;
        this.antialias = true;
    }

    public boolean equals(Object other) {
        if (!(other instanceof ImpFont)) {
            return false;
        }
        ImpFont that = (ImpFont)other;
        return this.name.equals(that.name) && this.size == that.size &&
                this.loadsFromData == that.loadsFromData && this.antialias == that.antialias;
    }

    public int hashCode() {
        int hashCode = 1;
        hashCode = hashCode * 37 + this.name.hashCode();
        hashCode = hashCode * 37 + this.size;
        hashCode = hashCode * 37 + (this.loadsFromData ? 1 : 0);
        hashCode = hashCode * 37 + (this.antialias ? 1 : 0);
        return hashCode;
    }

    public String toString() {
        return this.name + ", size:"+this.size+", antialias?:"+this.antialias+", load from data?:"+this.loadsFromData;
    }

    public static void echoFonts(){
        String[] fontList = PFont.list();
        for (String fontname : fontList){
            System.out.println(fontname);
        }
    }
}
