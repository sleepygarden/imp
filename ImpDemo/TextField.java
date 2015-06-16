import processing.core.*;

class TextField extends Button {

    public Color focusFill;
    public Color focusStroke;

    public TextField(float x, float y, float width, float height){
        super(x,y,width,height);
        this.stroke = new Color(200);
        this.strokeWeight = 1;
    }

    public void draw(ImpLayer imp){
        super.draw(imp);
        // todo cursor blink
    }

    protected Color fillForState() {
        if (this.isInFocus && this.focusFill != null){
            return this.focusFill;
        }
        return super.fillForState();
    }

    protected Color strokeForState() {
        if (this.isInFocus && this.focusStroke != null){
            return this.focusStroke;
        }
        return super.strokeForState();
    }

    void keyPressed(PApplet app) {
        if (app.keyCode == app.BACKSPACE) {
            if (this.text.length() > 0) {
                this.text = this.text.substring(0, this.text.length()-1);
            }
        } else if (app.keyCode == app.DELETE) {
            this.text = "";
        } else if (app.keyCode != app.SHIFT && app.keyCode != app.CONTROL && app.keyCode != app.ALT) {
            this.text = this.text + app.key;
        }
    }

    public void keyReleased(PApplet app){ }

}

