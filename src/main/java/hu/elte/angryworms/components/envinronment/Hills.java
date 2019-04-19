package hu.elte.angryworms.components.envinronment;

import hu.elte.angryworms.Main;

import lombok.Data;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

@Data
public class Hills {

    private PApplet pApplet;
    private int hillsWidth;
    private int surfaceLevel;
    private PImage hillsImage;
    private float imageWidth;
    private float imageHeight;

    public void draw(PVector groundDisplacement) {
        float horizontalPosition = 0 + groundDisplacement.x;
        float verticalPosition = surfaceLevel;
        pApplet.image(hillsImage, horizontalPosition, verticalPosition, imageWidth, imageHeight);
    }

    public static Hills createHills(PApplet pApplet, int surfaceLevel) {
        Hills hills = new Hills();
        hills.pApplet = pApplet;
        hills.hillsWidth = Main.HILLS_WIDTH;
        hills.hillsImage = pApplet.loadImage(Main.HILLS_IMAGE);
        hills.imageWidth = (float)hills.hillsWidth / hills.hillsImage.width * hills.hillsWidth;
        hills.imageHeight = (float)hills.hillsWidth / hills.hillsImage.width * hills.hillsImage.height;
        hills.surfaceLevel = surfaceLevel - (int)hills.imageHeight;
        return hills;
    }

}
