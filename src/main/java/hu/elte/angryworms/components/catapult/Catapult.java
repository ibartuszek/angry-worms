package hu.elte.angryworms.components.catapult;

import processing.core.PApplet;
import processing.core.PVector;

public class Catapult {
    private final CatapultBody body;
    private final CatapultRubber rubber;


    public Catapult(PApplet pApplet, PVector position) {
        this.body = new CatapultBody(pApplet, position);
        this.rubber = new CatapultRubber(pApplet, this.body);
    }

    public void drawBackSide() {
        body.drawBackSide();
        rubber.drawBackSide();
    }

    public void drawFrontSide() {
        body.drawFrontSide();
        rubber.drawFrontSide();
    }

}
