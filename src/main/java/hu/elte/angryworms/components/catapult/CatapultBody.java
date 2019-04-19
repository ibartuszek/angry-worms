package hu.elte.angryworms.components.catapult;


import java.awt.Color;

import hu.elte.angryworms.Main;

import lombok.Data;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

@Data
public class CatapultBody extends PShape{


    private PApplet pApplet;
    private Color frontSideColor;
    private Color backSideColor;
    private int catapultWidth;
    private int catapultHeight;
    private boolean firstCatapult;

    private float defaultHorizontalPosition;
    private PVector position;
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

    private void draw(PVector branchPosition, Color color) {
        pApplet.beginShape();
        pApplet.fill(color.getRed(), color.getGreen(), color.getBlue());
        pApplet.stroke(color.getRed(), color.getGreen(), color.getBlue());
        drawBottomStartPart();
        drawBranchPart(pApplet, branchPosition, catapultWidth);
        drawBottomEndPart();
        pApplet.endShape(pApplet.CLOSE);
    }

    private void drawBranchPart(PApplet pApplet, PVector position, int width) {
        pApplet.curveVertex(position.x + (float)(width / 2 * Math.cos(Main.CATAPULT_ANGLE)),
            position.y - (float)(width / 2 * Math.sin(Main.CATAPULT_ANGLE)));
        pApplet.curveVertex(position.x - (float)(width / 2 * Math.cos(Main.CATAPULT_ANGLE))
            , position.y + (float)(width / 2 * Math.sin(Main.CATAPULT_ANGLE)));
    }

    private void drawBottomStartPart() {
        drawBottomEndPart();
        pApplet.curveVertex(position.x + (float)catapultWidth/2, bottomPosition.y);
        pApplet.curveVertex(position.x + (float)catapultWidth/2, position.y);
    }

    private void drawBottomEndPart() {
        pApplet.curveVertex(position.x - (float)catapultWidth/2, position.y);
        pApplet.curveVertex(position.x - (float)catapultWidth/2, bottomPosition.y);
    }

    void setPositions(PVector position) {
        this.position.x = defaultHorizontalPosition + position.x;
        initPositions();
    }

    private void initPositions() {
        this.bottomPosition = new PVector(this.position.x,this.position.y + this.catapultHeight);
        this.leftTopPosition =  new PVector(
            this.position.x - (float)(this.catapultHeight * Math.tan(Main.CATAPULT_ANGLE)),
            this.position.y - this.catapultHeight);
        this.rightTopPosition =  new PVector(
            this.position.x + (float)(this.catapultHeight * Math.tan(Main.CATAPULT_ANGLE)),
            this.position.y - this.catapultHeight);
        if (this.firstCatapult) {
            this.rightTopPosition.y += Main.CATAPULT_FORK_SHIFT;
        } else {
            this.leftTopPosition.y += Main.CATAPULT_FORK_SHIFT;
        }
    }

    static CatapultBody createFirstCatapultBody(PApplet pApplet, PVector catapultPosition) {
        CatapultBody catapultBody = createCatapultBody(pApplet, catapultPosition);
        catapultBody.firstCatapult = true;
        catapultBody.initPositions();
        return catapultBody;
    }

    static CatapultBody createSecondCatapultBody(PApplet pApplet, PVector catapultPosition) {
        CatapultBody catapultBody = createCatapultBody(pApplet, catapultPosition);
        catapultBody.firstCatapult = false;
        catapultBody.initPositions();
        return catapultBody;
    }

    private static CatapultBody createCatapultBody(PApplet pApplet, PVector catapultPosition) {
        CatapultBody catapultBody = new CatapultBody();
        catapultBody.pApplet = pApplet;
        catapultBody.catapultWidth = Main.CATAPULT_WIDTH;
        catapultBody.catapultHeight = Main.CATAPULT_HEIGHT;
        catapultBody.frontSideColor = Color.decode(Main.CATAPULT_BODY_COLOR_FRONTSIDE);
        catapultBody.backSideColor = Color.decode(Main.CATAPULT_BODY_COLOR_BACKSIDE);
        catapultBody.defaultHorizontalPosition = catapultPosition.x;
        catapultBody.position = catapultPosition;
        return catapultBody;
    }
}
