import processing.core.*;

public class MyImpLayer extends ImpLayer {
    
    final View view;
    final Label label;

    final Button button;

    final TextField textField;
    
    public MyImpLayer(PApplet app, int x, int y, int width, int height){
        super(app,x,y,width,height);
        this.background.fill = new Color(255,100);

        this.view = new View(0,0,100,100);
        this.view.fill = new Color (33,33,55);

        this.label = new Label(110,110,60,30);
        this.label.text = "Hi";
        this.label.fill = new Color(24,55,24);
        this.label.textColor = new Color(255,255,66);

        this.button = new Button(10,200,80,40);
        this.button.setMouseEventResponder( new MouseEventResponder() {
            public void mouseDown(View v) {
                label.text = "Down";
            }
            public void mouseUp(View v) {
                label.text = "Up";
            }
        });
        this.button.text = "Click me!";
        this.button.hovorFill = new Color(122);
        this.button.downFill = new Color(88);

        this.textField = new TextField(300,300,80,40);
        this.textField.text = "Edit me";
        this.textField.focusStroke = new Color(0);
    }
    
    public void setup() {
        super.setup();
        this.label.font = createFont("Mermaid-Bold",20);
    }

    public void draw() {
        super.draw();
        this.view.draw(this);
        this.label.draw(this);
        this.button.draw(this);
        this.textField.draw(this);
    }
}


