package hu.elte.angryworms.model;

import java.text.MessageFormat;
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
    private Player nextPlayer;
    private Player winner;

    private boolean firstCatapultIsClicked = false;
    private boolean secondCatapultIsClicked = false;

    private boolean end;
    private int timer;

    public void draw() {
        updateCenterPosition(calculatePassedTime());
        final PVector groundDisplacement = PVector.sub(baseCenter, center);
        final PVector hillsDisplacement = PVector.mult(groundDisplacement, hillsSpeed / groundSpeed);
        ground.draw(groundDisplacement);
        hills.draw(hillsDisplacement);
        writeOutPlayerNames();

        if (!end) {
            fire();
            firstPlayer.draw(groundDisplacement, firstCatapultIsClicked, nextPlayer.getCatapultTopPosition());
            secondPlayer.draw(groundDisplacement, secondCatapultIsClicked, nextPlayer.getCatapultTopPosition());
            if (arrow != null && arrow.isEnabled()) {
                arrow.draw();
            }
            if (currentPlayer.getResult().isPresent()) {
                finishRound();
            }
        } else {
            finishGame();
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

    private void writeOutPlayerNames() {
        pApplet.textSize(32);
        pApplet.fill(0);
        pApplet.textAlign(PApplet.LEFT);
        pApplet.text(firstPlayer.getName(), Main.PLAYER_NAME_IDENT, Main.PLAYER_NAME_VERTICAL_GAP);
        pApplet.textAlign(PApplet.RIGHT);
        pApplet.text(secondPlayer.getName(), Main.WIDTH - Main.PLAYER_NAME_IDENT, Main.PLAYER_NAME_VERTICAL_GAP);
    }

    private void fire() {
        firstCatapultIsClicked();
        secondCatapultIsClicked();
        if (firstCatapultIsClicked || secondCatapultIsClicked) {
            currentPlayer.prepareForFire();
            arrow.prepareForShooting(currentPlayer.getCatapultTopPosition());
            if (!isMousePressed) {
                firstCatapultIsClicked = false;
                secondCatapultIsClicked = false;
                arrow.setEnabled(false);
                currentPlayer.fire();
            }
        }
    }

    private void finishRound() {
        currentPlayer.setCurrentWorm(null);
        final Result result = currentPlayer.getResult().get();
        currentPlayer.setResult(Optional.empty());
        currentPlayer.setNextWorm();

        if (result.isHitOpponent()) {
            end = true;
            timer = pApplet.millis();
            winner = currentPlayer;
        }

        if (nextPlayer.getCurrentWorm() == null && currentPlayer.getCurrentWorm() == null) {
            end = true;
            timer = pApplet.millis();
        }

        nextPlayer = currentPlayer;
        currentPlayer = currentPlayer == firstPlayer ? secondPlayer : firstPlayer;
    }

    private void finishGame() {
        pApplet.textSize(64);
        pApplet.fill(0);
        pApplet.textAlign(PApplet.CENTER);
        final String message = winner != null ?
            MessageFormat.format(Main.WIN_MESSAGE, winner.getName()) : Main.DEAD_END_MESSAGE;
        pApplet.text(message, Main.WIDTH / 2, Main.PLAYER_NAME_VERTICAL_GAP * 3);
        if (pApplet.millis() - timer > 5000) {
            System.exit(0);
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
