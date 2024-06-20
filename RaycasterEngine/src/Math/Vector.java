package Math;

public class Vector {
	
	float x;
	float y;
	
	public Vector(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}


	public void setY(float y) {
		this.y = y;
	}


	public float getMagnitude() {
		return (float) Math.sqrt(x * x + y *y);
	}
	
	public Vector getNormalized() {
		float magnitude = this.getMagnitude();
		if(magnitude == 1) {
			return this;
		}
		return new Vector(x/magnitude, y/magnitude);
	}
	
	public Vector rotate(float degrees) {
		double degreesToRadians = Math.toRadians(degrees);
		float newVectorX = (float) (this.getX() * Math.cos(degreesToRadians) - this.getY() * Math.sin(degreesToRadians));
		float newVectorY = (float) (this.getX() * Math.sin(degreesToRadians) + this.getY() * Math.cos(degreesToRadians)); //computer coordinates -_-
		return new Vector (newVectorX, newVectorY);
		
	}

}
