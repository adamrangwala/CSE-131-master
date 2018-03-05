package lab10;

import java.awt.Color;

import sedgewick.StdDraw;

public class Player implements Draw_Interface {

	private double posX;
	private double posY;
	private int level;
	private int lives;
	private double height;
	private double width; 
	private double startPosX;
	private double startPosY;
	private int score;


	/**
	 * 
	 * @param x-starting x position of top left alien
	 * @param y-starting y position of top left alien
	 */
	public Player(double x, double y){
		this.startPosX = x;
		this.startPosY = y;
		this.posX = x;
		this.posY = y;
		this.level = 1;
		this.lives = 2;
		this.height = 0.025;
		this.width = 0.05;
		this.score = 0;
	}

	/**
	 * draws a bullet if up key is pressed
	 * @param a- bullet object
	 */
	public void shoot(Bullet a){
		if (a.getIsNotHit()==false){
			if (ArcadeKeys.isKeyPressed(0, ArcadeKeys.KEY_UP)){
				a.setNotHit(true);
				a.setPosX(this.getPosX());
				a.setPosY(this.getPosY()+0.05);
			}
		}
		if (a.getIsNotHit()==true){
			a.move();
			a.draw();
		}
		if (a.getPosY()>0.9){
			a.setNotHit(false);
		}
	}
	//move according to arrow keys
	/**
	 * moves player right or left if keys are pressed
	 */
	public void move() {
		if(ArcadeKeys.isKeyPressed(0, ArcadeKeys.KEY_RIGHT)){
			this.posX = this.posX + 0.075;
		}
		if(ArcadeKeys.isKeyPressed(0, ArcadeKeys.KEY_LEFT)){
			this.posX = this.posX - 0.075;
		}

		if (this.posX<.05){
			this.posX = 0.05;
		}
		if (this.posX>.95){
			this.posX = .95;
		}

	}
	/**
	 * adds 10 to score
	 */
	public void scoreUp(){
		this.score= this.score + 10;
	}
	/**
	 * 
	 * @return score
	 */
	public int getScore(){
		return this.score;
	}
	/**
	 * 
	 * @return player's x pos
	 */
	public double getPosX() {
		return posX;
	}
	/**
	 * 
	 * @return player's y pos
	 */
	public double getPosY() {
		return posY;
	}
	/**
	 * 
	 * @return height half the height of player
	 */
	public double getHeight() {
		return this.height;
	}
	/**
	 * 
	 * @return half the width of player
	 */
	public double getWidth() {
		return this.width;
	}
	/**
	 * subtracts player life by 1
	 */
	public void loseLife(){
		this.lives = this.lives-1;
	}

	/**
	 * 
	 * @return number of player's lives
	 */
	public int getLives(){
		return this.lives;
	}

	/**
	 * resets player to starting pos
	 */
	public void resetPlayer(){
		this.posX = this.startPosX;
		this.posY = this.startPosY;
	}
	/**
	 * gives game over screen
	 */
	public void gameOver(){
		StdDraw.clear(Color.BLACK);
		StdDraw.text(0.5, 0.5, "GAME OVER");
		StdDraw.text(0.5, 0.3, "Final Score: " + this.getScore());
		StdDraw.show(10000);
		SpaceInvaders game = new SpaceInvaders();
		game.playSpaceInvaders();
	}

	/**
	 * draws lives, score and player on screen
	 */
	public void draw() {
		StdDraw.text(0.9, 1.0, "Lives Left: " + (this.getLives()));
		StdDraw.text(0.05, 1.0, "Score: " + this.getScore());
		StdDraw.setPenColor(Color.GREEN);
		StdDraw.filledRectangle(this.posX, this.posY, this.width, this.height);
	}
}
