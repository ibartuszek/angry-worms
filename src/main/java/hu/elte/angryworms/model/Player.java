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

        if (catapult != null) {
            catapult.drawFrontSide(catapultIsClicked);
        }

        if (wormList != null) {
            for (final Worm worm : wormList) {
                worm.draw();
            }
        }
    }
}
