package com.sleepygarden.imp.pojo;

import java.util.Arrays;

/**
 * Created by mcornell on 6/22/15.
 *
 */

public class Polygon {

    public Point[] points;
    public Polygon(Point[] points){
        this.points = points;
    }

    public Polygon(Frame frame){
        this.points = new Point[]{
                new Point(frame.x, frame.y),
                new Point(frame.x2(),frame.y),
                new Point(frame.x2(),frame.y2()),
                new Point(frame.x,frame.y2())};
    }

    // horizontal ray cast
    public boolean contains(float x, float y){
        Point test = new Point(x,y);
        int i; // current index
        int j; // == i - 1
        boolean result = false;
        for (i = 0, j = points.length - 1; i < points.length; j = i++) {
            if ((points[i].y > test.y) != (points[j].y > test.y) &&
                    (test.x < (points[j].x - points[i].x) * (test.y - points[i].y) / (points[j].y-points[i].y) + points[i].x)) {
                result = !result;
            }
        }
        return result;
    }

    public boolean equals(Object other) {
        if (!(other instanceof Polygon)) {
            return false;
        }

        Polygon that = (Polygon)other;
        if (this.points.length == that.points.length){
            for (int idx = 0; idx < this.points.length; idx++){
                if (!this.points[idx].equals(that.points[idx])){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = 1;
        for (Point p : this.points) {
            hashCode = hashCode * 37 + p.hashCode();
        }
        return hashCode;
    }

    public String toString(){
        return "Polygon:(\n" + Arrays.toString(this.points) +"\n)";
    }
}
