package hu.elte.angryworms;

import java.awt.Color;

import hu.elte.angryworms.model.GameModel;

import lombok.Data;
import processing.core.PApplet;

@Data
public class Visualization extends PApplet {

    private GameModel model;

    @Override
    public void draw() {
        update();
        // Absolute fixed elements:
        this.fill(0, 0, 0);
        this.stroke(0, 0, 0);
        this.ellipse(50, 50, second(), second());
        // Relativ elements
    }

    public void update() {
        drawBackground();
        model.draw();
    }

    @Override
    public void keyPressed(){
        if (keyCode == LEFT) {
            model.moveToLeft();
        } else if (keyCode == RIGHT){
            model.moveToRight();
        }
    }

    // Init + Setup

    @Override
    public void settings() {
        size(Main.WIDTH, Main.HEIGHT);
    }

    @Override
    public void setup() {
        Main.initModel(this);
    }

    public void drawBackground() {
        Color backgroundColor = Color.decode(Main.BACKGROUND_COLOR);
        background(color(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue()));
    }

}
