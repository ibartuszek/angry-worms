package hu.elte.angryworms.model;

import java.util.Optional;

import hu.elte.angryworms.Main;
import hu.elte.angryworms.components.arrow.Arrow;
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
    private Arrow arrow;

    private Player firstPlayer;
    private Player secondPlayer;
    private Player currentPlayer;

    private boolean firstCatapultIsClicked = false;
    private boolean secondCatapultIsClicked = false;

    public void draw() {
        updateCenterPosition(calculatePassedTime());
        final PVector groundDisplacement = PVector.sub(baseCenter, center);
        final PVector hillsDisplacement = PVector.mult(groundDisplacement, hillsSpeed / groundSpeed);
        fire();

        if (ground != null) {
            ground.draw(groundDisplacement);
        }

        if (hills != null) {
            hills.draw(hillsDisplacement);
        }

        if (firstPlayer != null) {
            firstPlayer.draw(groundDisplacement, firstCatapultIsClicked);
        }

        if (secondPlayer != null) {
            secondPlayer.draw(groundDisplacement, secondCatapultIsClicked);
        }

        if (arrow != null && arrow.isEnabled()) {
            arrow.draw();
        }

        if (currentPlayer.getResult().isPresent()) {
            currentPlayer.setCurrentWorm(null);
            final Result result = currentPlayer.getResult().get();
            currentPlayer.setResult(Optional.empty());

            if (!currentPlayer.getWormSet().isEmpty()) {
                currentPlayer.setNextWorm();
            }

            if (result.isHitOpponent()) {
                System.out.println("WIN");
            }

            currentPlayer = currentPlayer == firstPlayer ? secondPlayer : firstPlayer;

        }
    }

    private float calculatePassedTime() {
        final float deltaTime = (pApplet.millis() - lastTime);
        lastTime = pApplet.millis();
        return deltaTime;
    }

    private void updateCenterPosition(final float deltaTime) {
        PVector newPosition = null;
        if (isRight) {
            newPosition = new PVector(center.x + groundSpeed * deltaTime, center.y);
        } else if (isLeft) {
            newPosition = new PVector(center.x - groundSpeed * deltaTime, center.y);
        }
        center = validateNewPosition(newPosition) ? newPosition : center;
    }

    private boolean validateNewPosition(final PVector newPosition) {
        boolean valid = false;
        if (newPosition != null
            && newPosition.x - Main.WIDTH / 2 >= 0
            && newPosition.x + Main.WIDTH / 2 <= Main.GROUND_WIDTH) {
            valid = true;
        }
        return valid;
    }

    private void firstCatapultIsClicked() {
        if (isMousePressed && currentPlayer.equals(firstPlayer)) {
            firstCatapultIsClicked = Catapult.validateFirstCatapultAiming(new PVector(pApplet.mouseX, pApplet.mouseY),
                firstPlayer.getCatapult());
        }
    }

    private void secondCatapultIsClicked() {
        if (isMousePressed && currentPlayer.equals(secondPlayer)) {
            secondCatapultIsClicked = Catapult.validateSecondCatapultAiming(new PVector(pApplet.mouseX, pApplet.mouseY),
                secondPlayer.getCatapult());
        }
    }

    private void fire() {
        firstCatapultIsClicked();
        secondCatapultIsClicked();
        if (firstCatapultIsClicked || secondCatapultIsClicked) {
            currentPlayer.prepareForFire();
            arrow.prepareForShooting(currentPlayer.getCatapult().getTopPosition());
            if (!isMousePressed) {
                firstCatapultIsClicked = false;
                secondCatapultIsClicked = false;
                arrow.setEnabled(false);
                currentPlayer.fire();
            }
        }
    }

    public static GameModel createGameModel(final PApplet pApplet) {
        final GameModel model = new GameModel();
        model.pApplet = pApplet;
        model.baseCenter = new PVector(Main.WIDTH / 2.0f, Main.HEIGHT / 2.0f);
        model.center = new PVector(Main.WIDTH / 2.0f, Main.HEIGHT / 2.0f);
        model.isLeft = false;
        model.isRight = false;
        model.isMousePressed = false;
        model.groundSpeed = Main.GROUND_MOVING_FACTOR;
        model.hillsSpeed = model.groundSpeed
            * (Main.HILLS_WIDTH - Main.WIDTH) / (Main.GROUND_WIDTH - Main.WIDTH);
        return model;
    }

}
