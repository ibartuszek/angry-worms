package hu.elte.angryworms.model;

import hu.elte.angryworms.components.catapult.Catapult;
import hu.elte.angryworms.components.ground.Ground;
import processing.core.PApplet;

public class GameModel {
    private final PApplet pApplet;
    private Ground ground;
    private Catapult firstCatapult;
    private Catapult secondCatapult;

    public GameModel(PApplet pApplet) {
        this.pApplet = pApplet;
    }

    public void draw() {

        if (ground != null) {
            ground.draw();
        }

        if (firstCatapult != null) {
            firstCatapult.draw();
        }

        if (secondCatapult != null) {
            secondCatapult.draw();
        }
    }

    public Ground getGround() {
        return ground;
    }

    public void setGround(Ground ground) {
        this.ground = ground;
    }

    public Catapult getFirstCatapult() {
        return firstCatapult;
    }

    public void setFirstCatapult(Catapult firstCatapult) {
        this.firstCatapult = firstCatapult;
    }

    public Catapult getSecondCatapult() {
        return secondCatapult;
    }

    public void setSecondCatapult(Catapult secondCatapult) {
        this.secondCatapult = secondCatapult;
    }
}
