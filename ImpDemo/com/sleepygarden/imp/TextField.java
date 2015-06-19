package com.sleepygarden.imp;

public class TextField extends Button {

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

    public void keyPressed(char key, int keyCode) {
        if (key == CODED){
            System.out.print("coded key code: ");
            if (keyCode == LEFT) {
                System.out.println("left");
            }
            if (keyCode == RIGHT) {
                System.out.println("right");
            }
        }
        else {
            switch (keyCode){
                case BACKSPACE:
                    if (this.text.length() > 0) {
                        this.text = this.text.substring(0, this.text.length()-1);
                    }
                    break;
                case TAB: 
                    this.text = this.text + "    ";
                    break;

                case ENTER:
                case RETURN:
                    FocusManager.sharedManager().reportGainFocus(null);
                    break;
                case ESC:
                    break;
                case DELETE:
                    break;
                default:
                    this.text = this.text + key;
                    break;
            }
        }
    }

    public void keyReleased(char key, int keyCode){ }

}

