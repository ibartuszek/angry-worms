package hu.elte.angryworms.components.catapult;

import java.awt.Color;

import hu.elte.angryworms.Main;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

class CatapultRubber extends PShape {
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

    private void drawRelaxed(final PVector leftDirection, final PVector rightDirection) {
        drawTwoVertexWithTwoVectors(leftDirection, leftTopPosition);
        pApplet.curveVertex(relaxedPosition.x, relaxedPosition.y - rubberWidth / 2);
        drawTwoVertexWithTwoVectors(rightDirection, rightTopPosition);
        pApplet.curveVertex(relaxedPosition.x, relaxedPosition.y - rubberWidth / 2);
        drawTwoVertexWithTwoVectors(leftDirection, leftTopPosition);
    }

    private void drawClicked(final PVector sidePosition, final PVector centerPosition, final PVector direction) {
        drawTwoVertexWithTwoVectors(direction, sidePosition);
        pApplet.curveVertex(centerPosition.x, centerPosition.y - rubberWidth / 2);
        pApplet.curveVertex(centerPosition.x, centerPosition.y + rubberWidth / 2);
        drawTwoVertexWithTwoVectors(direction, sidePosition);
    }

    private void drawTwoVertexWithTwoVectors(final PVector leftDirection, final PVector leftTopPosition) {
        pApplet.curveVertex(leftTopPosition.x - leftDirection.x, leftTopPosition.y + leftDirection.y);
        pApplet.curveVertex(leftTopPosition.x + leftDirection.x, leftTopPosition.y - leftDirection.y);
    }

    private void draw(final boolean clicked, final boolean front) {
        final PVector leftDirection = PVector.fromAngle((float) (Main.CATAPULT_ANGLE + Math.PI / 2))
            .normalize()
            .mult(rubberWidth / 2);
        final PVector rightDirection = PVector.fromAngle((float) (-Main.CATAPULT_ANGLE - Math.PI / 2))
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

    void drawBackSide(final boolean clicked) {
        draw(clicked, false);
    }

    void drawFrontSide(final boolean clicked) {
        draw(clicked, true);
    }

    void setPositions(final PVector position) {
        this.rubberPosition.x = defaultHorizontalPosition + position.x;
        initPositions();
    }

    private void initPositions() {
        catapultPosition = catapultBody.getBasePosition();
        leftTopPosition = new PVector(
            catapultBody.getLeftTopPosition().x + (float) (rubberWidth * Math.sin(Main.CATAPULT_ANGLE)),
            catapultBody.getLeftTopPosition().y + (float) (rubberWidth * Math.cos(Main.CATAPULT_ANGLE)));
        rightTopPosition = new PVector(
            catapultBody.getRightTopPosition().x - (float) (rubberWidth * Math.sin(Main.CATAPULT_ANGLE)),
            catapultBody.getRightTopPosition().y + (float) (rubberWidth * Math.cos(Main.CATAPULT_ANGLE)));
        relaxedPosition = new PVector(
            catapultPosition.x + this.horizontalShift,
            catapultPosition.y + this.catapultBody.getCatapultHeight() / 2
        );
    }

    static CatapultRubber createFirstCatapultRubber(final PApplet pApplet, final CatapultBody body) {
        final CatapultRubber catapultRubber = CatapultRubber.createCatapultRubber(pApplet, body);
        catapultRubber.horizontalShift = catapultRubber.catapultBody.getCatapultWidth();
        catapultRubber.initPositions();
        catapultRubber.rubberPosition = catapultRubber.relaxedPosition.copy();
        catapultRubber.defaultHorizontalPosition = catapultRubber.relaxedPosition.x;
        return catapultRubber;
    }

    private static CatapultRubber createCatapultRubber(final PApplet pApplet, final CatapultBody body) {
        final CatapultRubber catapultRubber = new CatapultRubber();
        catapultRubber.pApplet = pApplet;
        catapultRubber.catapultBody = body;
        catapultRubber.color = Color.decode(Main.CATAPULT_RUBBER_COLOR);
        catapultRubber.rubberWidth = Main.CATAPULT_RUBBER_WIDTH;
        return catapultRubber;
    }
}
