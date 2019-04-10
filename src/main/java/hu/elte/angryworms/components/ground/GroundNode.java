package hu.elte.angryworms.components.ground;


import java.awt.Color;

import hu.elte.angryworms.Visualization;
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

    public GroundNode(PApplet pApplet, PVector position, int width) {
        this.pApplet = pApplet;
        this.shape = pApplet.createShape();
        this.color = Color.decode(Visualization.GROUND_COLOR);
        this.position = position;
        this.width = width;
        this.height = width;
    }

    public void draw() {
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

}
