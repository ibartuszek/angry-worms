package hu.elte.angryworms.components.catapult;

import hu.elte.angryworms.Main;
import hu.elte.angryworms.Visualization;

import processing.core.PApplet;
import processing.core.PVector;

public class Catapult {
    private CatapultBody body;
    private CatapultRubber rubber;

    public void drawBackSide(PVector groundDisplacement, boolean clicked) {
        body.setPositions(groundDisplacement);
        body.drawBackSide();
        rubber.setPositions(groundDisplacement);
        rubber.drawBackSide(clicked);
    }

    public void drawFrontSide(boolean clicked) {
        body.drawFrontSide();
        rubber.drawFrontSide(clicked);
    }

    public static boolean validateFirstCatapultPosition(PVector mousePosition, Catapult catapult) {
        boolean result = false;
        if (catapult.body.getLeftTopPosition().y <= mousePosition.y) {
            if (catapult.body.getPosition().x >= mousePosition.x &&
            catapult.body.getPosition().copy().sub(mousePosition).mag() <= Main.CATAPULT_RUBBER_MAGNITUDE_LIMIT) {
                result = true;
            }
        }
        return result;
    }

    public static boolean validateSecondCatapultPosition(PVector mousePosition, Catapult catapult) {
        boolean result = false;
        if (catapult.body.getLeftTopPosition().y <= mousePosition.y) {
            if (catapult.body.getPosition().x <= mousePosition.x &&
                catapult.body.getPosition().copy().sub(mousePosition).mag() <= Main.CATAPULT_RUBBER_MAGNITUDE_LIMIT) {
                result = true;
            }
        }
        return result;
    }

    public static Catapult createFirstCatapult(PApplet pApplet, int surfaceLevel) {
        Catapult catapult = createCatapult();
        catapult.body = CatapultBody.createFirstCatapultBody(pApplet,
            getCatapultPosition(Main.CATAPULT_HORIZONTAL_POSITION, surfaceLevel));
        catapult.rubber = CatapultRubber.createFirstCatapultRubber(pApplet, catapult.body);
        return catapult;
    }

    public static Catapult createSecondCatapult(PApplet pApplet, int surfaceLevel) {
        Catapult catapult = createCatapult();
        catapult.body = CatapultBody.createSecondCatapultBody(pApplet,
            getCatapultPosition(Main.GROUND_WIDTH - Main.CATAPULT_HORIZONTAL_POSITION, surfaceLevel));
        catapult.rubber = CatapultRubber.createFirstCatapultRubber(pApplet, catapult.body);
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
