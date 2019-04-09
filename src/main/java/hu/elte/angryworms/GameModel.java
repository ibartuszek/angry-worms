package hu.elte.angryworms;

import hu.elte.angryworms.shapes.ground.Ground;
import processing.core.PApplet;

public class GameModel {
    private final PApplet pApplet;
    private Ground ground;

    public GameModel(PApplet pApplet) {
        this.pApplet = pApplet;
    }

    public Ground getGround() {
        return ground;
    }

    public void setGround(Ground ground) {
        this.ground = ground;
    }

    public void draw() {
        if (ground != null) {
            ground.draw();
        }
    }
}
