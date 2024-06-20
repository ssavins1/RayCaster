package components;

import Math.Point;
import Math.Vector;

public class Camera {
	
	Point location;
	Vector direction;
	float FOV = 90;
	int width = 640;
	int height = 480;
	int viewDistance = 90;
	
	public Camera(Point location, Vector direction, float fOV) {
		super();
		this.location = location;
		this.direction = direction;
		FOV = fOV;
	}
	
	public Point getLocation() {
		return location;
	}
	
	public void setLocation(Point location) {
		this.location = location;
	}
	
	public Vector getDirection() {
		return direction;
	}
	
	public void setDirection(Vector direction) {
		this.direction = direction;
	}
	
	public float getFOV() {
		return FOV;
	}
	
	public void setFOV(float fOV) {
		FOV = fOV;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void moveForward(int speed) {
		Vector unitDirection = this.getDirection().getNormalized();
		this.getLocation().setX(this.getLocation().getX() + unitDirection.getX() * speed);
		this.getLocation().setY(this.getLocation().getY() + unitDirection.getY() * speed);
	}
	
	public void moveBackward(int speed) {
		Vector unitDirection = this.getDirection().getNormalized();
		this.getLocation().setX(this.getLocation().getX() - unitDirection.getX() * speed);
		this.getLocation().setY(this.getLocation().getY() - unitDirection.getY() * speed);
	}
	
	public void rotateLeft(int speed) {
		this.setDirection(this.getDirection().rotate(1));
	}
	
	public void rotateRight(int speed) {
		this.setDirection(this.getDirection().rotate(-1));
	}
	
	
}
