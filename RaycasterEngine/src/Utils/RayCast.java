package Utils;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import Math.Vector;

public class RayCast {
	
	Vector origin;
	Vector collision;
	List<Vector> intersections;
	int hitAxis = 0;
	
	public RayCast(Vector origin) {
		this.origin = origin;
		this.intersections = new ArrayList<Vector>();
	}
	
	public void addIntersection(Vector intersection) {
		this.intersections.add(intersection);
	}
	
	public void setCollision(Vector collision) {
		this.collision = collision;
	}
	
	
	public int getHitAxis() {
		return hitAxis;
	}

	public void setHitAxis(int hitAxis) {
		this.hitAxis = hitAxis;
	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)origin.getX(), (int)origin.getY(), 5, 5);
		intersections.forEach(e-> g.drawLine((int)origin.getX()/16, (int)origin.getY()/16, (int)e.getX()/16, (int)e.getY()/16));
	}
	
	
	public float getDistance() {
		Vector lastIntersection = intersections.get(intersections.size() -1);
		return new Vector(lastIntersection.getX() - origin.getX(), lastIntersection.getY() - origin.getY()).getMagnitude();
	}

}
