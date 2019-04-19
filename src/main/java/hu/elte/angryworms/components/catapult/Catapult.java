package hu.elte.angryworms.components.catapult;

import hu.elte.angryworms.Main;
import hu.elte.angryworms.Visualization;

import processing.core.PApplet;
import processing.core.PVector;

public class Catapult {
    private CatapultBody body;
    private CatapultRubber rubber;

    public void drawBackSide(PVector groundDisplacement) {
        body.setPositions(groundDisplacement);
        body.drawBackSide();
        // rubber.drawBackSide();
    }

    public void drawFrontSide() {
        body.drawFrontSide();
        // rubber.drawFrontSide();
    }

    public static Catapult createFirstCatapult(PApplet pApplet, int surfaceLevel) {
        Catapult catapult = createCatapult();
        catapult.body = CatapultBody.createFirstCatapultBody(pApplet,
            getCatapultPosition(Main.CATAPULT_HORIZONTAL_POSITION, surfaceLevel));
        return catapult;
    }

    public static Catapult createSecondCatapult(PApplet pApplet, int surfaceLevel) {
        Catapult catapult = createCatapult();
        catapult.body = CatapultBody.createFirstCatapultBody(pApplet,
            getCatapultPosition(Main.GROUND_WIDTH - Main.CATAPULT_HORIZONTAL_POSITION, surfaceLevel));
        return catapult;
    }

    private static Catapult createCatapult() {
        Catapult catapult = new Catapult();
        return catapult;
    }

    private static PVector getCatapultPosition(int horizontalPosition, int surfaceLevel) {
        return new PVector(horizontalPosition,
            surfaceLevel - Main.CATAPULT_HEIGHT * Main.CATAPULT_VERTICAL_SHIFT_FACTOR);
    }

}
