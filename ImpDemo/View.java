import processing.core.*;

class View extends Object {

    public float x,y,width,height;

    public Color fill;
    public Color hovorFill;
    public Color stroke;
    public Color hovorStoke;

    public int strokeWeight;
    public boolean isHovered;

    public View() {
        super();
        this.strokeWeight = 0;
        this.fill = new Color(255);
        this.stroke = new Color(255);
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
    }

    public View(float x, float y, float width, float height){
        this();
        setFrame(x,y,width,height);
    }

    public void setFrame(float x, float y, float w, float h){
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }

    protected Color fillForState() {
        if (this.isHovered && this.hovorFill != null){
            return this.hovorFill;
        }
        return this.fill;
    }

    protected Color strokeForState() {
        if (this.isHovered && this.hovorStoke != null){
            return this.hovorStoke;
        }
        return this.stroke;
    }

    public void draw(ImpLayer imp) {
        updateMouse(imp);
        imp.stroke(strokeForState());
        imp.strokeWeight(this.strokeWeight);
        imp.fill(fillForState());
        imp.rect(this);
    }

    public void updateMouse(ImpLayer imp){
        this.isHovered = (imp.app.mouseX >= this.x + imp.x &&
                          imp.app.mouseX <= this.x + this.width + imp.x && 
                          imp.app.mouseY >= this.y + imp.y && 
                          imp.app.mouseY <= this.y + this.height + imp.y);
    }
}
