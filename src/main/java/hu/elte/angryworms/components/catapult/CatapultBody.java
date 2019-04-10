package hu.elte.angryworms.components.catapult;


import java.awt.Color;

import hu.elte.angryworms.Visualization;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class CatapultBody {

    public static final int CATAPULT_WIDTH_FACTOR = 2;
    private final PApplet pApplet;
    private final PShape shape;
    private final Color color;
    private final PVector position;
    private final int width = Visualization.CATAPULT_WIDTH;
    private final int height = Visualization.CATAPULT_HEIGHT;


    public CatapultBody(PApplet pApplet, PVector position) {
        this.pApplet = pApplet;
        this.shape = pApplet.createShape();
        this.color = Color.decode(Visualization.CATAPULT_BODY_COLOR);
        this.position = position;
    }

    public void draw() {
        PVector leftTopPosition = new PVector(position.x - CATAPULT_WIDTH_FACTOR * width, position.y - height);
        PVector rightTopPosition = new PVector(position.x + CATAPULT_WIDTH_FACTOR * width, position.y - height);
        PVector bottomPosition = new PVector(position.x, position.y + height);

        pApplet.beginShape();

        pApplet.fill(color.getRed(), color.getGreen(), color.getBlue());
        pApplet.stroke(color.getRed(), color.getGreen(), color.getBlue());

        pApplet.curveVertex(bottomPosition.x - width/2, bottomPosition.y);
        pApplet.curveVertex(bottomPosition.x + width/2, bottomPosition.y);
        pApplet.curveVertex(position.x + width/2, position.y);
        pApplet.curveVertex(rightTopPosition.x + width/2, position.y - CATAPULT_WIDTH_FACTOR * width);
        pApplet.curveVertex(rightTopPosition.x + width/2, rightTopPosition.y);
        pApplet.curveVertex(rightTopPosition.x - width/2, rightTopPosition.y);
        pApplet.curveVertex(rightTopPosition.x - width/2, position.y - CATAPULT_WIDTH_FACTOR * width);
        pApplet.curveVertex(position.x, position.y - width);
        pApplet.curveVertex(leftTopPosition.x + width/2, position.y - CATAPULT_WIDTH_FACTOR * width);
        pApplet.curveVertex(leftTopPosition.x + width/2, leftTopPosition.y);
        pApplet.curveVertex(leftTopPosition.x - width/2, leftTopPosition.y);
        pApplet.curveVertex(leftTopPosition.x - width/2, position.y - CATAPULT_WIDTH_FACTOR * width);
        pApplet.curveVertex(position.x - width/2, position.y);
        pApplet.curveVertex(bottomPosition.x - width/2, bottomPosition.y);
        pApplet.curveVertex(bottomPosition.x + width/2, bottomPosition.y);

        pApplet.endShape(pApplet.CLOSE);
    }

}
