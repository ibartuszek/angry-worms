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
    private boolean isMousePressed;
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
        boolean firstCatapultIsClicked = firstCatapultIsClicked();
        boolean secondCatapultIsClicked = secondCatapultIsClicked();

        if (ground != null) {
            ground.draw(groundDisplacement);
        }

        if (hills != null) {
            hills.draw(hillsDisplacement);
        }

        if (firstCatapult != null) {
              firstCatapult.drawBackSide(groundDisplacement, firstCatapultIsClicked);
        }

        if (secondCatapult != null) {
            secondCatapult.drawBackSide(groundDisplacement, secondCatapultIsClicked);
        }

        if (firstCatapult != null) {
            firstCatapult.drawFrontSide(firstCatapultIsClicked);
        }

        if (secondCatapult != null) {
            secondCatapult.drawFrontSide(secondCatapultIsClicked);
        }
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

    private boolean firstCatapultIsClicked() {
        boolean result = false;
        if (isMousePressed) {
            result = Catapult.validateFirstCatapultPosition(new PVector(pApplet.mouseX, pApplet.mouseY), firstCatapult);
        }
        return result;
    }

    private boolean secondCatapultIsClicked() {
        boolean result = false;
        if (isMousePressed) {
            result = Catapult.validateSecondCatapultPosition(new PVector(pApplet.mouseX, pApplet.mouseY), secondCatapult);
        }
        return result;
    }

    public static GameModel createGameModel(PApplet pApplet) {
        GameModel model = new GameModel();
        model.pApplet = pApplet;
        model.baseCenter = new PVector(Main.WIDTH / 2, Main.HEIGHT / 2);
        model.center = new PVector(Main.WIDTH / 2, Main.HEIGHT / 2);
        model.isLeft = false;
        model.isRight = false;
        model.isMousePressed = false;
        model.groundSpeed = Main.GROUND_MOVING_FACTOR;
        model.hillsSpeed = model.groundSpeed
            * (Main.HILLS_WIDTH - Main.WIDTH) / (Main.GROUND_WIDTH - Main.WIDTH);
        return model;
    }

}
