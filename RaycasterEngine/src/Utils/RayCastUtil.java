package Utils;

import java.util.ArrayList;
import java.util.List;


import Math.Point;
import Math.Vector;
import components.Camera;

public class RayCastUtil {
	
	public  List<RayCast> calculateIntersections(Camera camera, int tileSize, int[][] map) {
	
		double cameraRayIncrement = camera.getFOV() / camera.getWidth();
		ArrayList<RayCast> returnList = new ArrayList<>();
		Vector farLeftVector = camera.getDirection().rotate(- (camera.getFOV() /2));
		
		for(int x = 0; x < camera.getWidth(); x++) {
			returnList.add(this.castRay(camera.getLocation(), farLeftVector.rotate((float)(cameraRayIncrement * x)), tileSize, map));
		}
		
		return returnList;
		
	}
	
	
	
	public RayCast castRay(Point origin, Vector direction, int tileSize, int[][] map) {
		Point destination = new Point(origin.getX() + (direction.getNormalized().getX() * Integer.MAX_VALUE), origin.getY() + (direction.getNormalized().getY() * Integer.MAX_VALUE));
		
		float dx = destination.getX() - origin.getX();
		float dy = destination.getY() - origin.getY();
		float rayLength = (float) Math.sqrt(dx *dx + dy * dy);
		
		//pretty sure this can be done simplier, but we'll go with it because the guide does it this way.
		//apparently this is step size in each direction (distance is in tiles)
		float stepSizeX = (float) Math.sqrt(1 + (direction.getY() / direction.getX()) * (direction.getY() / direction.getX()));
		float stepSizeY = (float) Math.sqrt(1 + (direction.getX() / direction.getY()) * (direction.getX() / direction.getY()));
		
		int mapCheckX = (int)origin.getX() / tileSize;
		int mapCheckY = (int)origin.getY() / tileSize;
		
		int stepDirectionX;
		float rayLengthX;
		
		if(direction.getX() < 0) {
			stepDirectionX = -1;
			rayLengthX = (origin.getX() - (mapCheckX * tileSize )) / tileSize * stepSizeX;
		}else {
			stepDirectionX = 1;
			rayLengthX = ((mapCheckX  + 1 ) * tileSize - origin.getX()) / tileSize * stepSizeX;
		}
		
		int stepDirectionY;
		float rayLengthY;
		if(direction.getY() < 0) {
			stepDirectionY = -1;
			rayLengthY = (origin.getY() - (mapCheckY * tileSize )) / tileSize * stepSizeY;
		}else {
			stepDirectionY = 1;
			rayLengthY = ((mapCheckY  + 1 ) * tileSize - origin.getY()) / tileSize * stepSizeY;
		}
		
		RayCast rayCast = new RayCast(new Vector(origin.getX(), origin.getY()));
		
		float currentDistance = 0;
		boolean tileFound = false;
		int hitAxis = 0;
		while(!tileFound) {
			if(rayLengthX < rayLengthY) {
				
				mapCheckX += stepDirectionX;
				currentDistance = rayLengthX;			
				rayLengthX += stepSizeX;
				hitAxis = 0;
			} else {
				mapCheckY += stepDirectionY;
				currentDistance = rayLengthY;			
				rayLengthY += stepSizeY;
				hitAxis = 1;
			}
			
			if(map[(int) mapCheckX][(int) mapCheckY] > 0) { //0 meaning something greater than a 0 on the map matrix we made (AKA a wall)
				tileFound = true;
			}
			
			if(currentDistance > rayLength / tileSize) {
				return rayCast;
			}
		}
		float endX = origin.getX() + (direction.getX() * (currentDistance * tileSize));
		float endY = origin.getY() + (direction.getY() * (currentDistance * tileSize));
		rayCast.addIntersection(new Vector(endX, endY));
		rayCast.setHitAxis(hitAxis);
		
		return rayCast;
	}

}
