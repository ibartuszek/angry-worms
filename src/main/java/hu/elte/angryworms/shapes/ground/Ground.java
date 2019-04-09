package hu.elte.angryworms.shapes.ground;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import hu.elte.angryworms.Visualization;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class Ground extends PShape {

    private final PApplet pApplet;
    private int width;
    private int height;
    private int detail;
    private Color color;
    // private Quadtree groundElements;
    private TreeSet<GroundNode> groundElements;
    // private List<GroundNode> groundElements;

    public Ground(PApplet pApplet) {
        this.pApplet = pApplet;
        this.width = Visualization.WIDTH;
        this.height = Visualization.HEIGHT;
        this.detail = Visualization.DETAIL;
        this.color = Color.decode(Visualization.GROUND_COLOR);
        this.groundElements = initGround();
    }

    public void draw() {
        groundElements.forEach(groundNode -> groundNode.draw());
    }

    private TreeSet<GroundNode> initGround() {
        TreeSet<GroundNode> groundElements = new TreeSet<>();
        // LinkedList<GroundNode> groundElements = new LinkedList<>();
        int step = width / detail;
        int maxHeight = height * 2/3;
        for (int y = height - step/2; y > maxHeight; y -= step) {
            for (int x = step / 2; x < width; x += step) {
                groundElements.add(new GroundNode(pApplet, color, new PVector(x, y), step));
            }
        }
        return groundElements;
    }
}
