package hu.elte.angryworms.components.ground;

import java.util.TreeSet;

import hu.elte.angryworms.Visualization;
import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

public class Ground extends PShape {

    public static final int STEP_SHIFT = 2;
    private final PApplet pApplet;
    private int width;
    private int height;
    private int detail;
    // private Quadtree groundElements;
    private TreeSet<GroundNode> groundElements;
    // private List<GroundNode> groundElements;

    public Ground(PApplet pApplet) {
        this.pApplet = pApplet;
        this.width = Visualization.WIDTH;
        this.height = Visualization.HEIGHT;
        this.detail = Visualization.DETAIL;
        this.groundElements = initGround();
    }

    public void draw() {
        groundElements.forEach(groundNode -> groundNode.draw());
    }

    private TreeSet<GroundNode> initGround() {
        TreeSet<GroundNode> groundElements = new TreeSet<>();
        int step = width / detail;
        int maxHeight = (int)(height * Visualization.SURFACE_RATIO);
        for (int y = height - step/ STEP_SHIFT; y > maxHeight; y -= step) {
            for (int x = step / STEP_SHIFT; x < width; x += step) {
                groundElements.add(new GroundNode(pApplet, new PVector(x, y), step));
            }
        }
        return groundElements;
    }
}
