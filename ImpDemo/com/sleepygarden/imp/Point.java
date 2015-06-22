package com.sleepygarden.imp;

/**
 * Created by mcornell on 6/22/15.
 *
 */

public class Point {
    public float x, y;
    public Point(float x, float y){
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object other) {
        if (!(other instanceof Point)) {
            return false;
        }
        Point that = (Point)other;
        return this.x == that.x && this.y == that.y;
    }

    public int hashCode() {
        int hashCode = 1;
        hashCode = hashCode * 37 + Float.floatToIntBits(this.x);
        hashCode = hashCode * 37 + Float.floatToIntBits(this.y);
        return hashCode;
    }

    public String toString(){
        return "Point:(" + this.x + ","+this.y+")";
    }
}