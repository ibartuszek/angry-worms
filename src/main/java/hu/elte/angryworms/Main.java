package hu.elte.angryworms;

import java.util.ArrayList;
import java.util.List;

import hu.elte.angryworms.components.catapult.Catapult;
import hu.elte.angryworms.components.envinronment.Ground;
import hu.elte.angryworms.components.envinronment.Hills;
import hu.elte.angryworms.components.worm.Worm;
import hu.elte.angryworms.model.GameModel;
import hu.elte.angryworms.model.Player;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * The purpose of this type is to
 */
public class Main {

    public static final String BACKGROUND_COLOR = "#00baff";
    public static final String CATAPULT_BODY_COLOR_FRONTSIDE = "#62320d";
    public static final String CATAPULT_BODY_COLOR_BACKSIDE = "#4b270a";
    public static final String CATAPULT_RUBBER_COLOR = "#be2409";
    public static final String WORM_COLOR = "#ffb6c1";

    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int GROUND_WIDTH = 2500;
    public static final int HILLS_WIDTH = 2000;
    public static final double SURFACE_RATIO = 2.0 / 3.0;
    public static final float GROUND_MOVING_FACTOR = 1.0f;

    public static final float GRAVITY_CONSTANT = 5.0f;
    public static final float WORM_VELOCITY_FACTOR = 0.8f;
    public static final float WORM_FLYING_SPEED_FACTOR = 150.0f;

    public static final String GROUND_IMAGE = "ground_2500x295.png";
    public static final String HILLS_IMAGE = "hills_2000x450.png";

    public static final float CATAPULT_WIDTH = 10;
    public static final float CATAPULT_HEIGHT = 80;
    public static final float CATAPULT_RUBBER_WIDTH = 10.0f;
    public static final float CATAPULT_HORIZONTAL_POSITION = 200;
    public static final float CATAPULT_VERTICAL_SHIFT_FACTOR = 0.9f;
    public static final float CATAPULT_FORK_SHIFT = 10.0f;
    public static final double CATAPULT_ANGLE = Math.PI / 8;
    public static final float CATAPULT_RUBBER_MAGNITUDE_LIMIT = 200.0f;

    public static final float WORM_START_POSITION_X = 30;
    public static final float WORM_START_POSITION_y = 30;
    public static final float WORM_HORIZONTAL_GAP = 30;
    public static final float WORM_WIDTH = 20;
    public static final float WORM_HEIGHT = 40;

    public Main() {
        super();
    }

    public static void main(final String[] args) {
        new Visualization().main("hu.elte.angryworms.Visualization");
    }

    static GameModel initModel(final Visualization visualization) {
        final GameModel model = GameModel.createGameModel(visualization);
        final int surfaceLevel = (int) (Main.HEIGHT * Main.SURFACE_RATIO);
        model.setGround(Ground.createGround(visualization, surfaceLevel));
        model.setHills(Hills.createHills(visualization, surfaceLevel));

        final Player firstPlayer = Player.createPlayer("First player", 3);
        final Player secondPlayer = Player.createPlayer("Second player", 3);

        firstPlayer.setCatapult(Catapult.createFirstCatapult(visualization, surfaceLevel));
        secondPlayer.setCatapult(Catapult.createSecondCatapult(visualization, surfaceLevel));

        Main.initWorms(visualization,
            Main.WORM_HORIZONTAL_GAP,
            Main.WORM_START_POSITION_X,
            firstPlayer);
        Main.initWorms(visualization,
            Main.WORM_HORIZONTAL_GAP * -1,
            Main.WIDTH - Main.WORM_START_POSITION_X,
            secondPlayer);

        model.setFirstPlayer(firstPlayer);
        model.setSecondPlayer(secondPlayer);
        model.setCurrentPlayer(firstPlayer);
        visualization.setModel(model);
        return model;
    }

    private static void initWorms(final PApplet pApplet, final float horizontalGap, final float horizontalStartPosition, final Player player) {
        final int shoots = player.getShoots();
        final List<Worm> wormList = new ArrayList<>();
        for (int i = 0; i < shoots; i++) {
            wormList.add(Worm.createWorm(
                pApplet,
                new PVector(horizontalStartPosition + i * horizontalGap, Main.WORM_START_POSITION_y)));
        }
        player.setWormList(wormList);
        player.setCurrentWorm(wormList.get(shoots - 1));
    }

}
