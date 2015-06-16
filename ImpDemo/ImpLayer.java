import processing.core.*;

public class ImpLayer extends Object {
    public final Frame layerFrame;
    public final PApplet app;
    public final View background;

    public boolean clipsToBounds;

    public static int cursorBlinkRate = 530;
    
    public ImpLayer(PApplet app, int x, int y, int width, int height){
        this.app = app;
        this.layerFrame = new Frame(x,y,width,height);
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

    private Frame layerCroppedFrame(Frame frame){
        if (frame != null){
            Frame cropped = new Frame(frame);
            cropped.x += this.layerFrame.x;
            cropped.y += this.layerFrame.y;
            if (this.clipsToBounds){
                if (!this.layerFrame.containsFrame(cropped)) {
                    cropped.cropToFit(this.layerFrame);
                }
            }
            return cropped;
        }
        return new Frame(0,0,0,0);
    }

    public void rect(Frame frame){
        if (frame != null){
            Frame cropped = layerCroppedFrame(frame);
            this.app.rect(cropped.x, 
                cropped.y, 
                cropped.width, 
                cropped.height);            
        }
    }

    public void text(String text, Frame frame) {
        if (text != null && frame != null){
            Frame cropped = layerCroppedFrame(frame);
            this.app.text(text, 
                cropped.x, 
                cropped.y, 
                cropped.width, 
                cropped.height);            
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


