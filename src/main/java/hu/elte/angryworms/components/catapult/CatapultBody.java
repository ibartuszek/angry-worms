package hu.elte.angryworms.components.catapult;

import java.awt.Color;

import hu.elte.angryworms.Main;

import lombok.Data;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

@Data
class CatapultBody extends PShape {

    private PApplet pApplet;
    private Color frontSideColor;
    private Color backSideColor;
    private float catapultWidth;
    private float catapultHeight;
    private boolean firstCatapult;

    private float defaultHorizontalPosition;
    private PVector basePosition;
    private PVector leftTopPosition;
    private PVector rightTopPosition;
    private PVector bottomPosition;

    private CatapultBody() {
        super();
    }

    void drawBackSide() {
        if (firstCatapult) {
            draw(leftTopPosition, backSideColor);
        } else {
            draw(rightTopPosition, backSideColor);
        }
    }

    void drawFrontSide() {
        if (firstCatapult) {
            draw(rightTopPosition, frontSideColor);
        } else {
            draw(leftTopPosition, frontSideColor);
        }
    }

    private void draw(final PVector branchPosition, final Color color) {
        pApplet.beginShape();
        pApplet.fill(color.getRed(), color.getGreen(), color.getBlue());
        pApplet.stroke(color.getRed(), color.getGreen(), color.getBlue());
        drawBottomStartPart();
        drawBranchPart(pApplet, branchPosition, catapultWidth);
        drawBottomEndPart();
        pApplet.endShape(pApplet.CLOSE);
    }

    private void drawBranchPart(final PApplet pApplet, final PVector position, final float width) {
        pApplet.curveVertex(position.x + (float) (width / 2 * Math.cos(Main.CATAPULT_ANGLE)),
            position.y - (float) (width / 2 * Math.sin(Main.CATAPULT_ANGLE)));
        pApplet.curveVertex(position.x - (float) (width / 2 * Math.cos(Main.CATAPULT_ANGLE))
            , position.y + (float) (width / 2 * Math.sin(Main.CATAPULT_ANGLE)));
    }

    private void drawBottomStartPart() {
        drawBottomEndPart();
        pApplet.curveVertex(basePosition.x + catapultWidth / 2, bottomPosition.y);
        pApplet.curveVertex(basePosition.x + catapultWidth / 2, basePosition.y);
    }

    private void drawBottomEndPart() {
        pApplet.curveVertex(basePosition.x - catapultWidth / 2, basePosition.y);
        pApplet.curveVertex(basePosition.x - catapultWidth / 2, bottomPosition.y);
    }

    void setPositions(final PVector newPosition) {
        basePosition.x = defaultHorizontalPosition + newPosition.x;
        initPositions();
    }

    PVector getTopPosition() {
        return new PVector(basePosition.x, basePosition.y - catapultHeight);
    }

    private void initPositions() {
        bottomPosition = new PVector(basePosition.x, basePosition.y + catapultHeight);
        leftTopPosition = new PVector(
            basePosition.x - (float) (catapultHeight * Math.tan(Main.CATAPULT_ANGLE)),
            basePosition.y - catapultHeight);
        rightTopPosition = new PVector(
            basePosition.x + (float) (catapultHeight * Math.tan(Main.CATAPULT_ANGLE)),
            basePosition.y - catapultHeight);
        if (firstCatapult) {
            rightTopPosition.y += Main.CATAPULT_FORK_SHIFT;
        } else {
            leftTopPosition.y += Main.CATAPULT_FORK_SHIFT;
        }
    }

    static CatapultBody createFirstCatapultBody(final PApplet pApplet, final PVector catapultPosition) {
        final CatapultBody catapultBody = CatapultBody.createCatapultBody(pApplet, catapultPosition);
        catapultBody.firstCatapult = true;
        catapultBody.initPositions();
        return catapultBody;
    }

    static CatapultBody createSecondCatapultBody(final PApplet pApplet, final PVector catapultPosition) {
        final CatapultBody catapultBody = CatapultBody.createCatapultBody(pApplet, catapultPosition);
        catapultBody.firstCatapult = false;
        catapultBody.initPositions();
        return catapultBody;
    }

    private static CatapultBody createCatapultBody(final PApplet pApplet, final PVector catapultPosition) {
        final CatapultBody catapultBody = new CatapultBody();
        catapultBody.pApplet = pApplet;
        catapultBody.catapultWidth = Main.CATAPULT_WIDTH;
        catapultBody.catapultHeight = Main.CATAPULT_HEIGHT;
        catapultBody.frontSideColor = Color.decode(Main.CATAPULT_BODY_COLOR_FRONTSIDE);
        catapultBody.backSideColor = Color.decode(Main.CATAPULT_BODY_COLOR_BACKSIDE);
        catapultBody.defaultHorizontalPosition = catapultPosition.x;
        catapultBody.basePosition = catapultPosition;
        return catapultBody;
    }
}
