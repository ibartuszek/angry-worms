package hu.elte.angryworms.model;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import hu.elte.angryworms.components.catapult.Catapult;
import hu.elte.angryworms.components.worm.Worm;

import lombok.Data;
import processing.core.PVector;

@Data
public class Player {
    private final String name;
    private int shoots;
    private Catapult catapult;
    private Set<Worm> wormSet;
    private Worm currentWorm;
    private Optional<Result> result;

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
            result = Optional.ofNullable(currentWorm.draw(groundDisplacement));
        }

        if (catapult != null) {
            catapult.drawFrontSide(catapultIsClicked);
        }

        if (wormSet != null) {
            for (final Worm worm : wormSet) {
                worm.draw(groundDisplacement);
            }
        }
    }

    void prepareForFire() {
        currentWorm.setAiming(true);
        if (wormSet.contains(currentWorm)) {
            wormSet.remove(currentWorm);
        }
    }

    void fire() {
        final PVector currentPosition = currentWorm.getPosition().copy();
        final PVector difference = catapult.getTopPosition().copy().sub(currentPosition);
        currentWorm.setFlyingParameters(currentPosition, difference.x, Math.atan(Math.abs(difference.y) / Math.abs(difference.x)));
    }

    public void setNextWorm() {
        currentWorm = !wormSet.isEmpty() ? wormSet.stream().findFirst().get() : null;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
