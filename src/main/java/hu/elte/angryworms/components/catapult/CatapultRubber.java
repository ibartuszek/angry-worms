package hu.elte.angryworms.components.catapult;

import java.awt.Color;

import hu.elte.angryworms.Main;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class CatapultRubber extends PShape {
    private PApplet pApplet;
    private CatapultBody catapultBody;
    private Color color;
    private float horizontalShift;
    private float defaultHorizontalPosition;
    private float rubberWidth;
    private PVector catapultPosition;
    private PVector rubberPosition;
    private PVector relaxedPosition;
    private PVector leftTopPosition;
    private PVector rightTopPosition;

    private void drawRelaxed(PVector leftDirection, PVector rightDirection) {
        drawTwoVertexWithTwoVectors(leftDirection, leftTopPosition);
        pApplet.curveVertex(relaxedPosition.x, relaxedPosition.y - rubberWidth / 2);
        drawTwoVertexWithTwoVectors(rightDirection, rightTopPosition);
        pApplet.curveVertex(relaxedPosition.x, relaxedPosition.y - rubberWidth / 2);
        drawTwoVertexWithTwoVectors(leftDirection, leftTopPosition);
    }

    private void drawClicked(PVector sidePosition, PVector centerPosition, PVector direction) {
        drawTwoVertexWithTwoVectors(direction, sidePosition);
        pApplet.curveVertex(centerPosition.x, centerPosition.y - rubberWidth / 2);
        pApplet.curveVertex(centerPosition.x, centerPosition.y + rubberWidth / 2);
        drawTwoVertexWithTwoVectors(direction, sidePosition);
    }

    private void drawTwoVertexWithTwoVectors(PVector leftDirection, PVector leftTopPosition) {
        pApplet.curveVertex(leftTopPosition.x - leftDirection.x, leftTopPosition.y + leftDirection.y);
        pApplet.curveVertex(leftTopPosition.x + leftDirection.x, leftTopPosition.y - leftDirection.y);
    }

    private void draw(boolean clicked, boolean front) {
        PVector leftDirection = PVector.fromAngle((float)(Main.CATAPULT_ANGLE + Math.PI / 2))
            .normalize()
            .mult(rubberWidth / 2);
        PVector rightDirection = PVector.fromAngle((float)(- Main.CATAPULT_ANGLE - Math.PI / 2))
            .normalize()
            .mult(rubberWidth / 2);
        pApplet.beginShape();
        pApplet.fill(color.getRed(), color.getGreen(), color.getBlue());
        pApplet.stroke(color.getRed(), color.getGreen(), color.getBlue());
        if (clicked) {
            rubberPosition.x = pApplet.mouseX;
            rubberPosition.y = pApplet.mouseY;
            if (front) {
                drawClicked(leftTopPosition, rubberPosition, rightDirection);
            } else {
                drawClicked(rightTopPosition, rubberPosition, leftDirection);
            }
        } else {
            drawRelaxed(leftDirection, rightDirection);
        }
        pApplet.endShape(pApplet.CLOSE);
    }

    void drawBackSide(boolean clicked) {
        draw(clicked, false);
    }

    void drawFrontSide(boolean clicked) {
        draw(clicked, true);
    }

    void setPositions(PVector position) {
        this.rubberPosition.x = defaultHorizontalPosition + position.x;
        initPositions();
    }

    private void initPositions() {
        this.catapultPosition = this.catapultBody.getPosition();
        this.leftTopPosition = new PVector(
            this.catapultBody.getLeftTopPosition().x + (float) (rubberWidth * Math.sin(Main.CATAPULT_ANGLE)),
            this.catapultBody.getLeftTopPosition().y + (float) (rubberWidth * Math.cos(Main.CATAPULT_ANGLE)));
        this.rightTopPosition = new PVector(
            this.catapultBody.getRightTopPosition().x - (float) (rubberWidth * Math.sin(Main.CATAPULT_ANGLE)),
            this.catapultBody.getRightTopPosition().y + (float) (rubberWidth * Math.cos(Main.CATAPULT_ANGLE)));
        this.relaxedPosition = new PVector(
            this.catapultPosition.x + this.horizontalShift,
            this.catapultPosition.y + (float) this.catapultBody.getCatapultHeight() / 2
        );
    }

    static CatapultRubber createFirstCatapultRubber(PApplet pApplet, CatapultBody body) {
        CatapultRubber catapultRubber = createCatapultRubber(pApplet, body);
        catapultRubber.horizontalShift = catapultRubber.catapultBody.getCatapultWidth();
        catapultRubber.initPositions();
        catapultRubber.rubberPosition = catapultRubber.relaxedPosition.copy();
        catapultRubber.defaultHorizontalPosition = catapultRubber.relaxedPosition.x;
        return catapultRubber;
    }

    private static CatapultRubber createCatapultRubber(PApplet pApplet, CatapultBody body) {
        CatapultRubber catapultRubber = new CatapultRubber();
        catapultRubber.pApplet = pApplet;
        catapultRubber.catapultBody = body;
        catapultRubber.color = Color.decode(Main.CATAPULT_RUBBER_COLOR);
        catapultRubber.rubberWidth = Main.CATAPULT_RUBBER_WIDTH;
        return catapultRubber;
    }
}
