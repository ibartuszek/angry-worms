package hu.elte.angryworms.components.catapult;


import java.awt.Color;

import hu.elte.angryworms.Visualization;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class CatapultBody {


    private final PApplet pApplet;
    private final PShape shape;
    private final Color frontSideColor;
    private final Color backSideColor;
    private final PVector position;
    private final PVector leftTopPosition;
    private final PVector rightTopPosition;
    private final PVector bottomPosition;

    private final int width = Visualization.CATAPULT_WIDTH;
    private final int height = Visualization.CATAPULT_HEIGHT;


    public CatapultBody(PApplet pApplet, PVector position) {
        this.pApplet = pApplet;
        this.shape = pApplet.createShape();
        this.frontSideColor = Color.decode(Visualization.CATAPULT_BODY_COLOR_FRONTSIDE);
        this.backSideColor = Color.decode(Visualization.CATAPULT_BODY_COLOR_BACKSIDE);
        this.position = position;
        leftTopPosition = new PVector(position.x - (float)(height * Math.tan(Visualization.CATAPULT_ANGLE)), position.y - height);
        rightTopPosition = new PVector(position.x + (float)(height * Math.tan(Visualization.CATAPULT_ANGLE)), position.y - height + Visualization.CATAPULT_FORK_SHIFT);
        bottomPosition = new PVector(position.x, position.y + height);
    }

    public void drawBackSide() {
        pApplet.beginShape();

        pApplet.fill(backSideColor.getRed(), backSideColor.getGreen(), backSideColor.getBlue());
        pApplet.stroke(backSideColor.getRed(), backSideColor.getGreen(), backSideColor.getBlue());

        pApplet.curveVertex(bottomPosition.x - width/2, bottomPosition.y);
        pApplet.curveVertex(bottomPosition.x + width/2, bottomPosition.y);
        pApplet.curveVertex(position.x + width/2, position.y);
        pApplet.curveVertex(position.x, position.y - width);
        pApplet.curveVertex(leftTopPosition.x + width/2, leftTopPosition.y);
        pApplet.curveVertex(leftTopPosition.x - width/2, leftTopPosition.y);
        pApplet.curveVertex(position.x - width/2, position.y);
        pApplet.curveVertex(bottomPosition.x - width/2, bottomPosition.y);
        pApplet.curveVertex(bottomPosition.x + width/2, bottomPosition.y);

        pApplet.endShape(pApplet.CLOSE);
    }

    public void drawFrontSide() {
        pApplet.beginShape();

        pApplet.fill(frontSideColor.getRed(), frontSideColor.getGreen(), frontSideColor.getBlue());
        pApplet.stroke(frontSideColor.getRed(), frontSideColor.getGreen(), frontSideColor.getBlue());

        pApplet.curveVertex(bottomPosition.x - width/2, bottomPosition.y);
        pApplet.curveVertex(bottomPosition.x + width/2, bottomPosition.y);
        pApplet.curveVertex(position.x + width/2, position.y);
        pApplet.curveVertex(rightTopPosition.x + width/2, rightTopPosition.y);
        pApplet.curveVertex(rightTopPosition.x - width/2, rightTopPosition.y);
        pApplet.curveVertex(position.x, position.y - width);
        pApplet.curveVertex(position.x - width/2, position.y);
        pApplet.curveVertex(bottomPosition.x - width/2, bottomPosition.y);
        pApplet.curveVertex(bottomPosition.x + width/2, bottomPosition.y);

        pApplet.endShape(pApplet.CLOSE);
    }

    public PVector getPosition() {
        return position;
    }

    public PVector getLeftTopPosition() {
        return leftTopPosition;
    }

    public PVector getRightTopPosition() {
        return rightTopPosition;
    }

    public PVector getBottomPosition() {
        return bottomPosition;
    }
}
