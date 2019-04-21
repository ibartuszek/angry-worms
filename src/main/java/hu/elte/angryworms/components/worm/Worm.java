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
    private float startFlyingTime;
    private double flyingAngle;
    private float flyingVelocity;
    private float wormWidth;
    private float wormHeight;

    public void draw(final PVector groundDisplacement) {
        if (!aiming && !flying) {
            // drawFlyingWorm(position);
            drawStandardWorm(position);
        } else if (aiming) {
            position.set(new PVector(pApplet.mouseX, pApplet.mouseY));
            drawStandardWorm(position);
        } else if (flying) {
            // setFlyingPosition(groundDisplacement);
            drawFlyingWorm(setFlyingPosition(groundDisplacement));
            // drawStandardWorm();
            // System.out.println(position.x + " " + position.y);
        }
    }

    private void drawStandardWorm(final PVector position) {
        pApplet.beginShape();
        pApplet.fill(color.getRed(), color.getGreen(), color.getBlue());
        pApplet.stroke(color.getRed(), color.getGreen(), color.getBlue());

        final float stepHeight = wormHeight / 4;
        final float stepWidth = wormWidth / 4;

        drawFirstThreeVertex(stepHeight, stepWidth, position);
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
        drawFirstThreeVertex(stepHeight, stepWidth, position);

        this.pApplet.endShape();
    }

    private void drawFirstThreeVertex(final float stepHeight, final float stepWidth, final PVector position) {
        pApplet.curveVertex(position.x + stepWidth, position.y + stepHeight);
        pApplet.curveVertex(position.x + stepWidth, position.y);
        pApplet.curveVertex(position.x + stepWidth, position.y - stepHeight);
    }

    private void drawFlyingWorm(final PVector newPosition) {
        pApplet.pushMatrix();
        pApplet.translate(newPosition.x, newPosition.y);
        pApplet.rotate(pApplet.millis() / 900.0f);
        drawStandardWorm(new PVector(0, 0));
        pApplet.popMatrix();
    }

    /**
     * Calculate next position of flying worm source:
     * https://www.atarimagazines.com/startv5n2/physics.html?fbclid=IwAR12q1Qxz6K2yI0wzkUnXJJiiksHHNqX55kIF5gO_v2RAQxf0YifKdPYD64
     * <p>
     * V0 - startVelocity
     * A - angle
     * T - passedTime
     * G - gravityConstant
     * X = X0+V0*COS(A)*T
     * Y = Y0+V0*SIN(A)*T-G*T*T/2
     *
     * @param groundDisplacment - the vector which represents the moving of the ground.
     */
    private PVector setFlyingPosition(final PVector groundDisplacment) {
        final float time = (pApplet.millis() - startFlyingTime) / 1000.0f * Main.WORM_FLYING_SPEED_FACTOR;
        final float x = originalPosition.x + groundDisplacment.x + flyingVelocity * (float) Math.cos(flyingAngle) * time;
        final float y = originalPosition.y - flyingVelocity * (float) Math.sin(flyingAngle) * time +
            Main.GRAVITY_CONSTANT * time * time / 2;
        // position.set(x, y);
        return new PVector(x, y);
    }

    public void setFlyingParameters(final PVector currentPosition, final float velocity, final double angle) {
        originalPosition = currentPosition;
        startFlyingTime = pApplet.millis();
        flyingVelocity = velocity * Main.WORM_VELOCITY_FACTOR;
        flyingAngle = angle;
        flying = true;
        aiming = false;
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
