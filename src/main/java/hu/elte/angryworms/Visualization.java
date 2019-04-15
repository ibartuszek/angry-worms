package hu.elte.angryworms;

import java.awt.Color;

import hu.elte.angryworms.components.catapult.Catapult;
import hu.elte.angryworms.components.ground.Ground;
import hu.elte.angryworms.model.GameModel;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * The purpose of this type is to
 */
public class Visualization extends PApplet {

    public static final String BACKGROUND_COLOR = "#00baff";
    public static final String GROUND_COLOR = "#f4a460";
    public static final String CATAPULT_BODY_COLOR_FRONTSIDE = "#62320d";
    public static final String CATAPULT_BODY_COLOR_BACKSIDE = "#4b270a";
    public static final String CATAPULT_RUBBER_COLOR = "#be2409";

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public static final int DETAIL = 100;
    public static final double SURFACE_RATIO = 2.0 / 3.0;

    public static final int CATAPULT_WIDTH = 10;
    public static final int CATAPULT_HEIGHT = 40;
    public static final int CATAPULT_HORIZONTAL_POSITION = 40;
    public static final double CATAPULT_ANGLE = Math.PI / 8;
    public static final int CATAPULT_RUBBER_SHIFT_VALUE = 0;
    public static final int CATAPULT_FORK_SHIFT = 10;

    private GameModel model;

    public Visualization() {
        super();
    }

    public static void main(String[] args) {
        Visualization mainClass = new Visualization();
        mainClass.main("hu.elte.angryworms.Visualization");
    }

    @Override
    public void draw(){
        update();
        this.fill(0, 0, 0);
        this.stroke(0, 0, 0);
        this.ellipse(width/2,height/2,second(),second());
    }

    public void update() {
        model.draw();
    }

    // Init + Setup

    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    @Override
    public void setup() {
        init();
    }

    public void init() {
        Color backgroundColor = Color.decode(BACKGROUND_COLOR);
        background(color(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue()));
        initModel();
    }

    private void initModel() {
        model = new GameModel(this);
        Ground ground = new Ground(this);
        int surfacePosition = (int)(HEIGHT * SURFACE_RATIO) - CATAPULT_HEIGHT;
        Catapult firstCatapult = new Catapult(this, new PVector(CATAPULT_HORIZONTAL_POSITION, surfacePosition));
        Catapult secondCatapult = new Catapult(this, new PVector(WIDTH - CATAPULT_HORIZONTAL_POSITION, surfacePosition));
        model.setGround(ground);
        model.setFirstCatapult(firstCatapult);
        model.setSecondCatapult(secondCatapult);
    }

}