package hu.elte.angryworms.components.catapult;

import java.awt.Color;

import hu.elte.angryworms.Visualization;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class CatapultRubber {
    private final PApplet pApplet;
    private final PShape shape;
    private final Color color;
    private final CatapultBody catapultBody;
    private final PVector catapultPosition;
    private final PVector rubberPosition;
    private final PVector leftTopPosition;
    private final PVector rightTopPosition;

    private final int catapultWidth = Visualization.CATAPULT_WIDTH;
    private final int catapultHeight = Visualization.CATAPULT_HEIGHT;
    private final double catapultAngle = Visualization.CATAPULT_ANGLE;
    private final int rubberWidth;
    private final int rubberHorizontalShiftValue;

    public CatapultRubber(PApplet pApplet, CatapultBody body) {
        this.pApplet = pApplet;
        this.shape = pApplet.createShape();
        this.color = Color.decode(Visualization.CATAPULT_RUBBER_COLOR);
        this.catapultBody = body;
        this.catapultPosition = this.catapultBody.getPosition();
        this.rubberWidth = catapultWidth;
        this.rubberHorizontalShiftValue = rubberWidth / 2 + Visualization.CATAPULT_RUBBER_SHIFT_VALUE;

        leftTopPosition = new PVector(this.catapultBody.getLeftTopPosition().x, this.catapultBody.getLeftTopPosition().y + rubberWidth);
        rightTopPosition = new PVector(this.catapultBody.getRightTopPosition().x, this.catapultBody.getRightTopPosition().y + rubberWidth);

        int x = (int) catapultPosition.x;
        int y = (int) catapultPosition.y + catapultHeight / 2;
        this.rubberPosition =  new PVector(x, y);
    }

    public void drawBackSide() {

        float relativeX = leftTopPosition.x + (float)(rubberHorizontalShiftValue / Math.cos(Visualization.CATAPULT_ANGLE)) - (float)(rubberWidth / 2 * Math.tan(Visualization.CATAPULT_ANGLE));
        float relativeY = leftTopPosition.y - rubberWidth * (float)Math.cos(catapultAngle) / 2;

        pApplet.beginShape();

        pApplet.fill(color.getRed(), color.getGreen(), color.getBlue());
        pApplet.stroke(color.getRed(), color.getGreen(), color.getBlue());

        pApplet.vertex(relativeX, relativeY);
        relativeX = leftTopPosition.x - (float)(rubberHorizontalShiftValue / Math.cos(Visualization.CATAPULT_ANGLE));
        // relativeY = leftTopPosition.y - rubberWidth * (float)Math.cos(catapultAngle) / 2;
        pApplet.vertex(relativeX, relativeY);
        relativeX = relativeX + rubberWidth * (float)Math.sin(catapultAngle);
        relativeY = relativeY + rubberWidth * (float)Math.cos(catapultAngle);
        // pApplet.vertex(relativeX, relativeY);
        relativeX = rubberPosition.x;
        relativeY = rubberPosition.y + rubberWidth / 2;
        // pApplet.vertex(relativeX, relativeY);
        relativeY = rubberPosition.y - rubberWidth / 2;
        // pApplet.vertex(relativeX, relativeY);
        relativeX = leftTopPosition.x - rubberHorizontalShiftValue;
        relativeY = leftTopPosition.y - rubberWidth * (float)Math.cos(catapultAngle) / 2;
        //pApplet.vertex(relativeX, relativeY);

        // pApplet.vertex(leftTopPosition.x - rubberHorizontalShiftValue + (float)(rubberWidth * Math.sin(catapultAngle)), leftTopPosition.y + rubberWidth * (1 + (float)Math.cos(catapultAngle)));
        // pApplet.vertex(leftTopPosition.x + (int)(rubberWidth * Math.sin(catapultAngle)) + rubberHorizontalShiftValue, leftTopPosition.y + (float)(rubberWidth * Math.cos(catapultAngle)));
        // pApplet.vertex(leftTopPosition.x + rubberHorizontalShiftValue, leftTopPosition.y - rubberWidth);
        // pApplet.vertex(leftTopPosition.x + rubberHorizontalShiftValue, leftTopPosition.y + rubberWidth);


        pApplet.endShape(pApplet.CLOSE);
    }

    public void drawFrontSide() {

    }
    /*
    int getAngleOfLeftCatapult() {

    }

 */
}
