package hu.elte.angryworms.shapes.ground;


import java.awt.Color;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class GroundNode implements Comparable<GroundNode> {
    private final PApplet pApplet;
    private final PShape shape;
    private final Color color;
    private final PVector position;
    private final int width;
    private final int height;

    public GroundNode(PApplet pApplet, Color color, PVector position, int width) {
        this.pApplet = pApplet;
        this.shape = pApplet.createShape();
        this.color = color;
        this.position = position;
        this.width = width;
        this.height = width;
    }

    public void draw() {

        /*
        shape.beginShape();
        shape.vertex(position.x-width/2, position.y+height/2);
        shape.vertex(position.x+width/2, position.y+height/2);
        shape.vertex(position.x+width/2, position.y-height/2);
        shape.vertex(position.x-width/2, position.y-height/2);
        shape.endShape(shape.CLOSE);
         */

        pApplet.beginShape();
        pApplet.fill(color.getRed(), color.getGreen(), color.getBlue());
        pApplet.stroke(color.getRed(), color.getGreen(), color.getBlue());
        pApplet.vertex(position.x-width/2, position.y+height/2);
        pApplet.vertex(position.x+width/2, position.y+height/2);
        pApplet.vertex(position.x+width/2, position.y-height/2);
        pApplet.vertex(position.x-width/2, position.y-height/2);
        pApplet.endShape(pApplet.CLOSE);
    }

    @Override public int compareTo(GroundNode o) {
        int result = 0;

        if (this.position.x < o.position.x ) {
            result = -1;
        } else if(this.position.x > o.position.x ) {
            result = 1;
        } else {
            if (this.position.y < o.position.y ) {
                result = -1;
            } else if (this.position.y > o.position.y ) {
                result = 1;
            }
        }

        return result;
    }

    // TODO

}
