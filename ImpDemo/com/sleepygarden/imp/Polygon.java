package com.sleepygarden.imp;

import java.awt.geom.Path2D;
import java.util.Arrays;

/**
 * Created by mcornell on 6/22/15.
 *
 */
public class Polygon {

    public Point[] points;
    private Path2D path;
    public Polygon(Point[] points){
        this.points = points;
        this.path = new Path2D.Float();
    }

    public Polygon(Frame frame){
        this.points = new Point[]{new Point(frame.x1(), frame.y1()),
                new Point(frame.x2(),frame.y1()),
                new Point(frame.x2(),frame.y2()),
                new Point(frame.x1(),frame.y2())};
        this.path = new Path2D.Float();
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

/*

    public final Point[] points;
    private Path2D path;
    public Polygon(Point[] points){
        this.points = points;
        this.path = new Path2D.Float();
        updatePath();
    }

    public Polygon(Frame frame){
        this.points = new Point[]{new Point(frame.x1(), frame.y1()),
                new Point(frame.x2(),frame.y1()),
                new Point(frame.x2(),frame.y2()),
                new Point(frame.x1(),frame.y2())};
        this.path = new Path2D.Float();
        updatePath();
    }

    private void updatePath() {
        if (this.points != null && this.points.length > 3) {
            this.path.moveTo(this.points[0].x, this.points[0].y);
            for (int idx = 0; idx < points.length; idx++) {
                this.path.lineTo(this.points[idx].x, this.points[idx].y);
            }
            this.path.closePath();
        }
    }

    public boolean contains(Point p){
        return this.contains(p.x,p.y);
    }

    public boolean contains(float x, float y){
        if (this.points == null || this.path == null || this.points.length < 2){
            return false;
        }
        return this.path.contains(x,y);
    }


 */
