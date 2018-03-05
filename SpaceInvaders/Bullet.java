package lab10;

import java.awt.Color;

import sedgewick.StdDraw;

public class Bullet {
	private double posX;
	private double posY;
	private double height;
	private double width;
	private boolean isNotHit;
	private double speed;
/**
 * 
 * @param posX-starting x position of a bullet
 * @param posY-starting y position of a bullet
 */
	public Bullet(double posX, double posY){
		this.posX = posX;
		this.posY = posY;
		this.height = 0.02;
		this.width = 0.005;
		this.isNotHit = false;
		this.speed = 0.1;
	}
	/**
	 * draws bullet
	 */
	public void draw(){
		StdDraw.setPenColor(Color.BLUE);
		StdDraw.filledRectangle(this.posX, this.posY, this.width, this.height);
	}

/**
 * 
 * @return half the height of a bullet
 */
	public double getHeight() {
		return height;
	}
	/**
	 * 
	 * @return half the width of a bullet
	 */
	public double getWidth() {
		return width;
	}
	/**
	 * 
	 * @return x position of bullet
	 */
	public double getPosX() {
		return posX;
	}
	/**
	 * 
	 * @param posX- x pos of bullet
	 */
	public void setPosX(double posX) {
		this.posX = posX;
	}
	/**
	 * 
	 * @return y pos of bullets
	 */
	public double getPosY() {
		return posY;
	}
	/**
	 * sets y pos of bullet
	 * @param posY- y pos of bullet
	 */
	public void setPosY(double posY) {
		this.posY = posY;
	}
	/**
	 * 
	 * @return true if bullet hasn't collided with anything
	 */
	public boolean getIsNotHit() {
		return isNotHit;
	}
	/**
	 * moves the bullet upwards at var. speed
	 */
	public void move(){
		if (this.isNotHit == true) {
			this.posY = this.posY + speed;
		}
	}
	/**
	 * 
	 * @param isNotHit- boolean true if bullet is not collided with anything
	 */
	public void setNotHit(boolean isNotHit) {
		this.isNotHit = isNotHit;
	}
}



