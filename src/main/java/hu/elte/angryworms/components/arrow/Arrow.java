package hu.elte.angryworms.components.arrow;

import lombok.Data;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

@Data
public class Arrow extends PShape {

    private PApplet pApplet;
    private boolean enabled;
    private float arrowHeight;
    private float arrowWidth;
    private double angle;
    private float length;
    private PVector start;
    private float displacement;

    private Arrow() {
    }

    public void draw() {
        pApplet.pushMatrix();
        pApplet.translate(start.x, start.y);
        pApplet.rotate(-(float) angle);
        pApplet.scale(length / arrowHeight / 2);
        drawShape();
        pApplet.popMatrix();
    }

    private void drawShape() {
        pApplet.beginShape();
        pApplet.fill(255);
        pApplet.stroke(0);
        pApplet.vertex(0, 0);
        pApplet.vertex(0, arrowWidth / 2);
        pApplet.vertex(arrowHeight, arrowWidth / 2);
        pApplet.vertex(arrowHeight, arrowWidth);
        pApplet.vertex(arrowHeight * 1.5f, 0);
        pApplet.vertex(arrowHeight, -arrowWidth);
        pApplet.vertex(arrowHeight, -arrowWidth / 2);
        pApplet.vertex(0, -arrowWidth / 2);
        pApplet.vertex(0, 0);
        pApplet.endShape();
    }

    public void prepareForShooting(final PVector topPosition) {
        final PVector direction = topPosition.sub(new PVector(pApplet.mouseX, pApplet.mouseY));
        length = direction.mag();
        angle = PVector.angleBetween(new PVector(1, 0), direction);
        start = new PVector(pApplet.mouseX - (float) Math.sin(angle) * displacement,
            pApplet.mouseY - (float) Math.cos(angle) * displacement);
        enabled = true;
    }

    public static Arrow createArrow(final PApplet pApplet, final float height, final float width, final float displacement) {
        final Arrow arrow = new Arrow();
        arrow.pApplet = pApplet;
        arrow.enabled = false;
        arrow.arrowHeight = height;
        arrow.arrowWidth = width;
        arrow.displacement = displacement;
        return arrow;
    }

}
