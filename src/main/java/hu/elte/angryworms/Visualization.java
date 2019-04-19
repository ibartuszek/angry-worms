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
    }

    public void update() {
        drawBackground();
        model.draw();
    }

    @Override
    public void keyPressed() {
        if (keyCode == LEFT) {
            model.setLeft(true);
        } else if (keyCode == RIGHT) {
            model.setRight(true);
        }
    }

    @Override
    public void keyReleased() {
        if (keyCode == LEFT) {
            model.setLeft(false);
        } else if (keyCode == RIGHT) {
            model.setRight(false);
        }
    }

    @Override
    public void mousePressed() {
        if (mouseButton == LEFT) {
            model.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased() {
        if (mouseButton == LEFT) {
            model.setMousePressed(false);
        }
    }

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
