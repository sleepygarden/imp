import processing.core.*;

public class ImpLayer extends Object {
    public final int x,y,layerWidth,layerHeight;
    public final PApplet app;
    public final View background;

    public boolean clipsToBounds;
    
    public ImpLayer(PApplet app, int x, int y, int width, int height, int fps){
        this.app = app;
        this.x = x;
        this.y = y;
        this.layerWidth = 300;
        this.layerHeight = 300;
        this.background = new View(0,0,width,height);
        this.clipsToBounds = true;
    }
    
    public void setup() { }
    public void draw() { 
        this.background.draw(this);
    }

    public void echoFonts() {
        String[] fontList = PFont.list();
        for (String fontname : fontList){
            System.out.println(fontname);
        }
    }

    // ------------------
    // Key Events
    // ------------------
    public void keyPressed() {
        Button focusedView = FocusManager.sharedManager().focusedView;
        if (focusedView != null && focusedView instanceof TextField) {
            ((TextField)focusedView).keyPressed(this.app);
        }
    }
    public void keyReleased() {
        Button focusedView = FocusManager.sharedManager().focusedView;
        if (focusedView != null && focusedView instanceof TextField) {
            ((TextField)focusedView).keyReleased(this.app);
        }
    }

    // ------------------
    // Mouse Events
    // ------------------
    public void mouseClicked() {}
    public void mousePressed() {}
    public void mouseReleased() {}
    public void mouseMoved() {}
    public void mouseDragged() {}


    // ------------------
    // PApplet Helpers
    // ------------------

    public void rect(View v){
        if (v != null){
            float width = v.width;
            float height = v.height;

            if (this.clipsToBounds){
                if (this.layerWidth < width + v.x){
                    width = this.layerWidth;
                }
                if (this.layerHeight < height + v.y){
                    height = this.layerHeight;
                }
            }
            
            this.app.rect(this.x + v.x, this.y + v.y, width, height);            
        }
    }

    public void text(Label l) {
        if (l != null){
            float width = l.width;
            float height = l.height;
            if (this.clipsToBounds){
                if (this.layerWidth < width + l.x){
                    width = this.layerWidth;
                }
                if (this.layerHeight < height + l.y){
                    height = this.layerHeight;
                }
            }
            this.app.text(l.text, this.x + l.x, this.y + l.y, width, height);
        }
    }

    public void textAlign(int hAlign){
        this.app.textAlign(hAlign);
    }
    public void textAlign(int hAlign, int vAlign){
        this.app.textAlign(hAlign, vAlign);
    }

    public int color(Color c) {
        if (c != null){
            return this.app.color(c.red,c.green,c.blue, c.alpha);
        }
        return 0;
    }

    public void fill(Color c){
        if (c != null){
            if (c.alpha <= 0){
                this.app.noFill();
            }
            else {
                this.app.fill(this.color(c),c.alpha);
            }
        }
    }

    public void stroke(Color c){
        if (c != null){
            if (c.alpha <= 0){
                this.app.noStroke();
            }
            else {
                this.app.stroke(this.color(c),c.alpha);
            }
        }
    }

    public void strokeWeight(int weight){
        if (weight == 0){
            this.app.noStroke();
        }
        else {
            this.app.strokeWeight(weight);
        }
    }

    public void textFont(PFont font){
        if (font != null){
            this.app.textFont(font);
        }
    }
    public PFont loadFont(String filename){
        return this.app.loadFont(filename);
    }
    public PFont createFont(String fontname, int fontSize) {
        return this.app.createFont(fontname,fontSize,true);
    }
}


