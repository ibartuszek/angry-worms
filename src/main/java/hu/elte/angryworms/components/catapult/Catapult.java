package hu.elte.angryworms.components.catapult;

import processing.core.PApplet;
import processing.core.PVector;

public class Catapult {
    private final CatapultBody body;


    public Catapult(PApplet pApplet, PVector position) {
        this.body = new CatapultBody(pApplet, position);
    }

    public void draw() {
        body.draw();
    }

}
