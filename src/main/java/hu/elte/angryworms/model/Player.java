package hu.elte.angryworms.model;

import java.util.List;

import hu.elte.angryworms.components.catapult.Catapult;
import hu.elte.angryworms.components.worm.Worm;

import lombok.Data;
import processing.core.PVector;

@Data
public class Player {
    private final String name;
    private int shoots;
    private Catapult catapult;
    private List<Worm> wormList;
    private Worm currentWorm;

    private Player(final String name) {
        this.name = name;
    }

    public static Player createPlayer(final String name, final int shoots) {
        final Player player = new Player(name);
        player.shoots = shoots;
        return player;
    }

    void draw(final PVector groundDisplacement, final boolean catapultIsClicked) {
        if (catapult != null) {
            catapult.drawBackSide(groundDisplacement, catapultIsClicked);
        }

        if (currentWorm != null) {
            currentWorm.draw(groundDisplacement);
        }

        if (catapult != null) {
            catapult.drawFrontSide(catapultIsClicked);
        }

        if (wormList != null) {
            for (final Worm worm : wormList) {
                worm.draw(groundDisplacement);
            }
        }
    }

    void prepareForFire() {
        currentWorm.setAiming(true);
        wormList.remove(currentWorm);
    }

    void fire() {
        final PVector currentPosition = currentWorm.getPosition().copy();
        final PVector difference = catapult.getTopPosition().copy().sub(currentPosition);
        currentWorm.setFlyingParameters(currentPosition, difference.x, Math.atan(-difference.y / difference.x));
    }

    void cancelFire() {
        currentWorm.setAiming(false);
        wormList.add(currentWorm);
        currentWorm.setPosition(currentWorm.getOriginalPosition().copy());
    }
}
