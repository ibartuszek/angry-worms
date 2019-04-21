package hu.elte.angryworms.components.catapult;

import hu.elte.angryworms.Main;

import lombok.Data;
import processing.core.PApplet;
import processing.core.PVector;

@Data
public class Catapult {
    private CatapultBody body;
    private CatapultRubber rubber;

    public void drawBackSide(final PVector groundDisplacement, final boolean clicked) {
        body.setPositions(groundDisplacement);
        body.drawBackSide();
        rubber.setPositions(groundDisplacement);
        rubber.drawBackSide(clicked);
    }

    public void drawFrontSide(final boolean clicked) {
        body.drawFrontSide();
        rubber.drawFrontSide(clicked);
    }

    public PVector getTopPosition() {
        return body.getTopPosition();
    }

    public static boolean validateFirstCatapultAiming(final PVector mousePosition, final Catapult catapult) {
        boolean result = false;
        if (catapult.body.getLeftTopPosition().y <= mousePosition.y) {
            if (catapult.body.getBasePosition().x >= mousePosition.x &&
                catapult.body.getBasePosition().copy().sub(mousePosition).mag() <= Main.CATAPULT_RUBBER_MAGNITUDE_LIMIT) {
                result = true;
            }
        }
        return result;
    }

    public static boolean validateSecondCatapultAiming(final PVector mousePosition, final Catapult catapult) {
        boolean result = false;
        if (catapult.body.getLeftTopPosition().y <= mousePosition.y) {
            if (catapult.body.getBasePosition().x <= mousePosition.x &&
                catapult.body.getBasePosition().copy().sub(mousePosition).mag() <= Main.CATAPULT_RUBBER_MAGNITUDE_LIMIT) {
                result = true;
            }
        }
        return result;
    }

    public static Catapult createFirstCatapult(final PApplet pApplet, final float surfaceLevel) {
        final Catapult catapult = Catapult.createCatapult();
        catapult.body = CatapultBody.createFirstCatapultBody(pApplet,
            Catapult.getCatapultPosition(Main.CATAPULT_HORIZONTAL_POSITION, surfaceLevel));
        catapult.rubber = CatapultRubber.createFirstCatapultRubber(pApplet, catapult.body);
        return catapult;
    }

    public static Catapult createSecondCatapult(final PApplet pApplet, final int surfaceLevel) {
        final Catapult catapult = Catapult.createCatapult();
        catapult.body = CatapultBody.createSecondCatapultBody(pApplet,
            Catapult.getCatapultPosition(Main.GROUND_WIDTH - Main.CATAPULT_HORIZONTAL_POSITION, surfaceLevel));
        catapult.rubber = CatapultRubber.createFirstCatapultRubber(pApplet, catapult.body);
        return catapult;
    }

    private static Catapult createCatapult() {
        final Catapult catapult = new Catapult();
        return catapult;
    }

    private static PVector getCatapultPosition(final float horizontalPosition, final float surfaceLevel) {
        return new PVector(horizontalPosition,
            surfaceLevel - Main.CATAPULT_HEIGHT * Main.CATAPULT_VERTICAL_SHIFT_FACTOR);
    }

}
