package hu.elte.angryworms;

import java.awt.Color;

import hu.elte.angryworms.shapes.ground.Ground;
import processing.core.PApplet;

/**
 * The purpose of this type is to
 */
public class Visualization extends PApplet {

    public static final String BACKGROUND_COLOR = "#007fff";
    public static final String GROUND_COLOR = "#f4a460";
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public static final int DETAIL = 100;

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
        initGround();
    }

    private void initGround() {
        model = new GameModel(this);
        // Ground ground = new Ground(WIDTH, HEIGHT, DETAIL, Color.decode(GROUND_COLOR));
        Ground ground = new Ground(this);
        model.setGround(ground);
    }

}