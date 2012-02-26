package gui;

import java.awt.Color;

public class QuadShape extends PolygonShape {

    public QuadShape(Color color, PointD p1, PointD p2, PointD p3, PointD p4) {
        super(color);
        super.addVertex(p1);
        super.addVertex(p2);
        super.addVertex(p3);
        super.addVertex(p4);
    }

}
