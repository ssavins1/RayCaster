package main;

import Math.Point;
import Math.Vector;
import Utils.RayCast;
import Utils.RayCastUtil;
import components.Camera;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class RayCaster extends JFrame implements KeyListener{
	
	JPanel jPanel = new JPanel();

	int mapWidth = 24;
	int mapHeight = 24;
	int cubeSize = 64;
	Camera camera;
	int screenWidth = 640;
	int screenHeight = 360;


	
	int[][] worldMap = 
		  { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 0, 0, 0, 0, 3, 0, 3, 0, 3, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 2, 2, 0, 2, 2, 0, 0, 0, 0, 3, 0, 3, 0, 3, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 4, 0, 4, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 4, 0, 0, 0, 0, 5, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 4, 0, 4, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 4, 0, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };
	
	
	public void init() {
		this.setTitle("My RayCaster");
		this.setSize(screenHeight, screenWidth);
		this.addKeyListener(this);
		camera = new Camera(new Point(300,300), new Vector(0,-1), 90);
		
		this.jPanel = new JPanel() {
			@Override
	        public void paintComponent(Graphics g) {
	            super.paintComponent(g);
	          
	            g.setColor(Color.GRAY);
	            g.fillRect(0, 0,(int) getSize().getWidth(), (int)getSize().getHeight());
	            drawMap3D(g);
	            drawMap2D(g);
	            drawCastRays(g);
	            //drawCharacter(g);
	        }
		};
		
//		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
//		int screenWidth = gd.getDisplayMode().getWidth();
//		int screenHeight = gd.getDisplayMode().getHeight();
		//this.scaleFactorHeight =  screenHeight / 360;
		//this.scaleFactorWidth =  screenWidth / 640;
		this.jPanel.addKeyListener(this);
		this.jPanel.setSize(screenWidth,screenHeight);
		this.add(jPanel);
		this.setSize(screenWidth, screenHeight);
	}
	
	public void drawCharacter(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect((int)camera.getLocation().getX()/16 - 4, (int)camera.getLocation().getY()/16 - 4, 8, 8);
	}
	
	public void drawMap2D(Graphics g) {
		
		for(int x = 0; x < worldMap.length; x++) {
			for(int y = 0; y < worldMap[x].length; y++) {
				if(worldMap[x][y] != 0) {
					g.setColor(Color.WHITE);
				}else {
					g.setColor(Color.BLACK);
				}
				g.fillRect(x * cubeSize/16 + 1, y * cubeSize/16 + 1 , cubeSize/16 -1, cubeSize/16 -1);
			
			}
		}
	}
	
	public void drawMap3D(Graphics g) {
		
		List<RayCast> castCameraRays = new RayCastUtil().calculateIntersections(camera, cubeSize, worldMap);
		for(int x = 0; x < castCameraRays.size(); x++) {
			int lineHeight = (int) (this.cubeSize / (castCameraRays.get(x).getDistance() / cubeSize)); //need to investigate why i need to divide by the cubeSize. I assume it has to do with the ray calculations
			//int lineHeight = (int) ((this.cubeSize) / (castCameraRays.get(x).getDistance() ));
			if(castCameraRays.get(x).getHitAxis() == 0) {
				g.setColor(Color.BLUE);
			}else {
				g.setColor(Color.CYAN);
			}
			g.drawLine(x, this.screenHeight/2 - lineHeight, x, this.screenHeight/2 + lineHeight);
			
//			g.drawLine((int)(x * this.scaleFactorWidth),
//					(int)((640 * this.scaleFactorHeight)/2 - (lineHeight /2)), 
//					(int)(x * this.scaleFactorWidth), 
//					(int)((640 * this.scaleFactorHeight)/2 + (lineHeight/2)));
		}
	
	}
	
	public void drawCastRays(Graphics g) {
		new RayCastUtil().calculateIntersections(camera, cubeSize, worldMap).forEach(e-> e.render(g));
		g.setColor(Color.GREEN);
		
		float cameraXScaled = camera.getLocation().getX()/16;
		float cameraYScaled = camera.getLocation().getY()/16;
		
		g.drawLine((int)cameraXScaled, (int)cameraYScaled, (int)(cameraXScaled + camera.getDirection().getX() * 10),(int) (cameraYScaled + camera.getDirection().getY() * 10));
	}
	
	public void run() {
		this.init();
		this.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_W) {
			camera.moveForward(5);
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			camera.moveBackward(5);
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			camera.rotateLeft(5);
		}
		if(e.getKeyCode() == KeyEvent.VK_A) {
			camera.rotateRight(5);
		}
		
		repaint();	
	}

	
	public static void main(String[] args) {
		new RayCaster().run();
	}

}
