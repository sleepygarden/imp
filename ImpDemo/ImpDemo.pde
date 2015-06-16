final int appWidth = 800;
final int appHeight = 900;
final int fps = 60;
final MyImpLayer imp = new MyImpLayer(this, 20, 20, appWidth-40, appHeight-40);

void setup() {  
    println("Starting Arduino Skinner");
    size(appWidth, appHeight);
    frameRate(fps);
    colorMode(RGB);
    rectMode(CORNER);
    imp.setup();
}
void draw() {
    imp.draw();
}
void mouseClicked() {
    imp.mouseClicked();
}
void mousePressed() {
    imp.mousePressed();
}
void mouseReleased() {
    imp.mouseReleased();
}
void mouseMoved() {
    imp.mouseMoved();
}
void mouseDragged() {
    imp.mouseDragged();
}
void keyPressed() {
    imp.keyPressed();
}
void keyReleased() {
    imp.keyReleased();
}
