import processing.core.*;

class TextField extends Button {

    public Color focusFill;
    public Color focusStroke;

    private long timeSinceLastBlink = 0;
    private boolean cursorOn = false;

    public TextField(float x, float y, float width, float height){
        super(x,y,width,height);
        this.stroke = new Color(200);
        this.strokeWeight = 1;
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

    protected String stringForRender() {
        if (this.isInFocus){
            long millis = System.currentTimeMillis();
            if (millis - this.timeSinceLastBlink > ImpLayer.cursorBlinkRate) {
                this.timeSinceLastBlink = millis;
                this.cursorOn = !this.cursorOn;
            }
            if (this.cursorOn){
                return this.text + "|";
            }
        }
        return super.stringForRender();
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

