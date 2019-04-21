package hu.elte.angryworms.components.worm;

import java.awt.Color;

import hu.elte.angryworms.Main;

import lombok.Data;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

@Data
public class Worm extends PShape {

    private PApplet pApplet;
    private PVector position;
    private PVector direction;
    private PVector originalPosition;
    private Color color;
    private boolean aiming;
    private boolean flying;
    private float wormWidth;
    private float wormHeight;

    public void draw() {
        pApplet.beginShape();
        pApplet.fill(color.getRed(), color.getGreen(), color.getBlue());
        pApplet.stroke(color.getRed(), color.getGreen(), color.getBlue());
        if (!aiming && !flying) {
            drawStandardWorm();
        } else if (aiming) {
            position.set(new PVector(pApplet.mouseX, pApplet.mouseY));
            drawStandardWorm();
        }
        this.pApplet.endShape();
    }

    private void drawStandardWorm() {
        final float stepHeight = wormHeight / 4;
        final float stepWidth = wormWidth / 4;

        drawFirstThreeVertex(stepHeight, stepWidth);
        pApplet.curveVertex(position.x + 2 * stepWidth, position.y - stepHeight);
        pApplet.curveVertex(position.x + 5 * stepWidth / 2, position.y - 3 * stepHeight / 2);
        pApplet.curveVertex(position.x + 2 * stepWidth, position.y - 2 * stepHeight);
        pApplet.curveVertex(position.x, position.y - 2 * stepHeight);
        pApplet.curveVertex(position.x - stepWidth, position.y - stepHeight);
        pApplet.curveVertex(position.x - stepWidth, position.y);
        pApplet.curveVertex(position.x - stepWidth, position.y + stepHeight);
        pApplet.curveVertex(position.x - 2 * stepWidth, position.y + stepHeight);
        pApplet.curveVertex(position.x - 2 * stepWidth, position.y + 2 * stepHeight);
        pApplet.curveVertex(position.x, position.y + 2 * stepHeight);
        pApplet.curveVertex(position.x + stepWidth, position.y + stepHeight);
        drawFirstThreeVertex(stepHeight, stepWidth);
    }

    private void drawFirstThreeVertex(final float stepHeight, final float stepWidth) {
        pApplet.curveVertex(position.x + stepWidth, position.y + stepHeight);
        pApplet.curveVertex(position.x + stepWidth, position.y);
        pApplet.curveVertex(position.x + stepWidth, position.y - stepHeight);

    }

    public static Worm createWorm(final PApplet pApplet, final PVector startPosition) {
        final Worm worm = new Worm();
        worm.pApplet = pApplet;
        worm.originalPosition = startPosition;
        worm.position = startPosition;
        worm.color = Color.decode(Main.WORM_COLOR);
        worm.aiming = false;
        worm.flying = false;
        worm.wormWidth = Main.WORM_WIDTH;
        worm.wormHeight = Main.WORM_HEIGHT;
        return worm;
    }
}
