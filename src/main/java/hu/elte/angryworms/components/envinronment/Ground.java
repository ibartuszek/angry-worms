package hu.elte.angryworms.components.envinronment;

import hu.elte.angryworms.Main;

import lombok.Data;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;
import processing.core.PVector;

@Data
public class Ground extends PShape {

    private PApplet pApplet;
    private int groundWidth;
    private int surfaceLevel;
    private PImage groundImage;
    private float imageWidth;
    private float imageHeight;

    public void draw(PVector groundDisplacement) {
        float horizontalPosition = 0 + groundDisplacement.x;
        float verticalPosition = surfaceLevel;
        pApplet.image(groundImage, horizontalPosition, verticalPosition, imageWidth, imageHeight);
    }

    public static Ground createGround(PApplet pApplet, int surfaceLevel) {
        Ground ground = new Ground();
        ground.pApplet = pApplet;
        ground.groundWidth = Main.GROUND_WIDTH;
        ground.surfaceLevel = surfaceLevel;
        ground.groundImage = pApplet.loadImage(Main.GROUND_IMAGE);
        ground.imageWidth = ground.groundWidth / ground.groundImage.width * ground.groundWidth;
        ground.imageHeight = ground.groundWidth / ground.groundImage.width * ground.groundImage.height;
        return ground;
    }

}
