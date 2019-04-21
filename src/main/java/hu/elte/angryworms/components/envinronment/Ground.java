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
    private float surfaceLevel;
    private PImage groundImage;
    private float imageWidth;
    private float imageHeight;

    public void draw(final PVector groundDisplacement) {
        final float horizontalPosition = 0 + groundDisplacement.x;
        final float verticalPosition = surfaceLevel;
        pApplet.image(groundImage, horizontalPosition, verticalPosition, imageWidth, imageHeight);
    }

    public static Ground createGround(final PApplet pApplet, final float surfaceLevel) {
        final Ground ground = new Ground();
        ground.pApplet = pApplet;
        ground.groundWidth = Main.GROUND_WIDTH;
        ground.surfaceLevel = surfaceLevel;
        ground.groundImage = pApplet.loadImage(Main.GROUND_IMAGE);
        ground.imageWidth = (float) ground.groundWidth / ground.groundImage.width * ground.groundWidth;
        ground.imageHeight = (float) ground.groundWidth / ground.groundImage.width * ground.groundImage.height;
        return ground;
    }

}
