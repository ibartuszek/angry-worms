package hu.elte.angryworms;

import hu.elte.angryworms.components.catapult.Catapult;
import hu.elte.angryworms.components.envinronment.Ground;
import hu.elte.angryworms.components.envinronment.Hills;
import hu.elte.angryworms.model.GameModel;

/**
 * The purpose of this type is to
 */
public class Main {

    public static final String BACKGROUND_COLOR = "#00baff";
    public static final String GROUND_COLOR = "#f4a460";
    public static final String CATAPULT_BODY_COLOR_FRONTSIDE = "#62320d";
    public static final String CATAPULT_BODY_COLOR_BACKSIDE = "#4b270a";
    public static final String CATAPULT_RUBBER_COLOR = "#be2409";

    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int GROUND_WIDTH = 2500;
    public static final int HILLS_WIDTH = 2000;
    public static final double SURFACE_RATIO = 2.0 / 3.0;
    public static final float GROUND_MOVING_FACTOR = 1.0f;

    public static final String GROUND_IMAGE = "ground_2500x295.png";
    public static final String HILLS_IMAGE = "hills_2000x450.png";

    public static final int CATAPULT_WIDTH = 10;
    public static final int CATAPULT_HEIGHT = 40;
    public static final int CATAPULT_HORIZONTAL_POSITION = 200;
    public static final float CATAPULT_VERTICAL_SHIFT_FACTOR = 0.25f;


    public static final double CATAPULT_ANGLE = Math.PI / 8;
    public static final int CATAPULT_RUBBER_SHIFT_VALUE = 0;
    public static final int CATAPULT_FORK_SHIFT = 10;

    public Main() {
        super();
    }

    public static void main(String[] args) {
        new Visualization().main("hu.elte.angryworms.Visualization");
    }

    protected static GameModel initModel(Visualization visualization) {
        GameModel model = GameModel.createGameModel(visualization);
        int surfaceLevel = (int)(HEIGHT * SURFACE_RATIO);
        model.setGround(Ground.createGround(visualization, surfaceLevel));
        model.setHills(Hills.createHills(visualization, surfaceLevel));
        model.setFirstCatapult(Catapult.createFirstCatapult(visualization, surfaceLevel));

        // Catapult firstCatapult = new Catapult(this, new PVector(CATAPULT_HORIZONTAL_POSITION, surfaceLevel));
        // Catapult secondCatapult = new Catapult(this, new PVector(WIDTH - CATAPULT_HORIZONTAL_POSITION, surfaceLevel));
        // model.setFirstCatapult(firstCatapult);
        // model.setSecondCatapult(secondCatapult);

        visualization.setModel(model);
        return model;
    }

}
