package lab10;

import java.awt.Color;

import sedgewick.StdDraw;
public class SpaceInvaders {
	private Player player1;
	private Alien newAlien;
	private Bullet playerBullet;
	private Bullet playerBullet2;
	/**
	 * Constructor for game object
	 */
	public SpaceInvaders(){
		newAlien = new Alien(3, 6, 0.1, 0.9, 0.025);
		player1 = new Player(0.05, 0.05);
		playerBullet = new Bullet(player1.getPosX(), player1.getPosY());
		playerBullet2 = new Bullet(player1.getPosX(), player1.getPosY());

	}
	/**
	 * game play codes
	 */
	public void playSpaceInvaders(){
		while (player1.getLives() > 0){
			StdDraw.clear(Color.BLACK);
			newAlien.move();
			player1.move();
			newAlien.draw();
			player1.draw();
			player1.shoot(playerBullet);
			player1.shoot(playerBullet2);
			//Alien gets shot
			newAlien.alienHit(playerBullet, player1);
			newAlien.alienHit(playerBullet2, player1);
			//checks and acts if all aliens shot
			newAlien.allAlienHit(player1);
			//collision between alien and player
			newAlien.collsion(player1);
			StdDraw.show(50);
		}
		player1.gameOver();
	}
//MAIN
 public static void main(String[] args){
 
		SpaceInvaders game = new SpaceInvaders();
		game.playSpaceInvaders();
	}
}
