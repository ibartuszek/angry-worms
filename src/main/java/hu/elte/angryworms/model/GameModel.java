package hu.elte.angryworms.model;

import hu.elte.angryworms.Main;
import hu.elte.angryworms.components.catapult.Catapult;
import hu.elte.angryworms.components.envinronment.Ground;
import hu.elte.angryworms.components.envinronment.Hills;

import lombok.Data;
import processing.core.PApplet;
import processing.core.PVector;

@Data
public class GameModel {
    private PApplet pApplet;

    private PVector baseCenter;
    private PVector center;
    private boolean isLeft;
    private boolean isRight;
    private float lastTime = 0.0f;

    private float groundSpeed;
    private Ground ground;
    private float hillsSpeed;
    private Hills hills;

    private Catapult firstCatapult;
    private Catapult secondCatapult;

    public void draw() {
        updateCenterPosition(calculatePassedTime());
        PVector groundDisplacement = PVector.sub(baseCenter, center);
        PVector hillsDisplacement = PVector.mult(groundDisplacement, hillsSpeed / groundSpeed);

        if (ground != null) {
            ground.draw(groundDisplacement);
        }

        if (hills != null) {
            hills.draw(hillsDisplacement);
        }

        //        if (firstCatapult != null) {
        //            firstCatapult.drawBackSide();
        //        }
        //
        //        if (secondCatapult != null) {
        //            secondCatapult.drawBackSide();
        //        }
        //
        //        if (firstCatapult != null) {
        //            firstCatapult.drawFrontSide();
        //        }
        //
        //        if (secondCatapult != null) {
        //            secondCatapult.drawFrontSide();
        //        }
    }

    private float calculatePassedTime() {
        float deltaTime = (pApplet.millis() - lastTime);
        lastTime = pApplet.millis();
        return deltaTime;
    }

    private void updateCenterPosition(float deltaTime) {
        PVector newPosition = null;
        if (isRight) {
            newPosition = new PVector(center.x + groundSpeed * deltaTime, center.y);
        } else if (isLeft) {
            newPosition = new PVector(center.x - groundSpeed * deltaTime, center.y);
        }
        center = validateNewPosition(newPosition) ? newPosition : center;
    }

    private boolean validateNewPosition(PVector newPosition) {
        boolean valid = false;
        if (newPosition != null
            && newPosition.x - Main.WIDTH / 2 >= 0
            && newPosition.x + Main.WIDTH / 2 <= Main.GROUND_WIDTH) {
            valid = true;
        }
        return valid;
    }

    public static GameModel createGameModel(PApplet pApplet) {
        GameModel model = new GameModel();
        model.pApplet = pApplet;
        model.baseCenter = new PVector(Main.WIDTH / 2, Main.HEIGHT / 2);
        model.center = new PVector(Main.WIDTH / 2, Main.HEIGHT / 2);
        model.isLeft = false;
        model.isRight = false;
        model.groundSpeed = Main.GROUND_MOVING_FACTOR;
        model.hillsSpeed = model.groundSpeed
            * (Main.HILLS_WIDTH - Main.WIDTH) / (Main.GROUND_WIDTH - Main.WIDTH);
        return model;
    }

}
