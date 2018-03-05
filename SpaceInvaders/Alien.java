package lab10;

import java.awt.Color;

import sedgewick.StdDraw;

public class Alien implements Draw_Interface {
	//delete overrides to implement

	private int rows;
	private int columns;
	private boolean [][] aliens;
	private double [][] xPosArray;
	private double [][] yPosArray;
	int direction;
	private double speed;
	private double radius;
	private double startPosY;
	private double startPosX;
	private int level;

	/**
	 * Alien Constructor
	 * @param row-number of rows for alien array
	 * @param col-number of columns for alien array
	 * @param x- starting x position of first alien
	 * @param y- starting y position of first alien
	 * @param speed- speed at which aliens move horizotally
	 */
	public Alien(int row, int col, double x, double y, double speed){
		this.level = 1;
		this.radius = 0.025;
		this.direction = 0;
		this.rows = row;
		this.columns = col;
		this.aliens = new boolean[this.rows][this.columns];
		this.startPosX = x;
		this.startPosY = y;
		this.xPosArray = new double[this.rows][this.columns];
		this.yPosArray = new double[this.rows][this.columns];
		this.speed = speed;
		//set starting positions of Alien array
		double b = x;
		double c = y;
		for (int i=0; i<this.rows; ++i){
			for(int j=0; j<this.columns;++j){
				this.xPosArray[i][j] = b;
				this.yPosArray[i][j] = c;
				b=b+0.1;
			}
			c=c-0.1;
			b=x;
		}
		//set all aliens to be alive (true)
		for (int i=0; i<this.rows; ++i){
			for(int j=0; j<this.columns;++j){
				this.aliens[i][j] = true;
			}
		}
	} 						//constructor final bracket

	/**
	 * Detects if hit alien..if so, destroys alien, boosts player score
	 * @param a-player's bullet object
	 * @param b-player object
	 */
	public void alienHit(Bullet a, Player b){
		for (int i=0; i < this.getRows(); ++i){
			for(int j=0; j < this.getColumns();++j){
				//if bullet head spans y-pos of alien
				if (a.getPosY()+a.getHeight() > this.getyPos(i, j)-this.getRadius()){
					if(a.getPosY()+a.getHeight() < this.getyPos(i, j)+this.getRadius()){
						//if bullet head spans x-pos of alien
						if (a.getPosX()-a.getWidth() > this.getxPos(i, j)-this.getRadius()){
							if(a.getPosX()-a.getWidth() < this.getxPos(i, j)+this.getRadius()){
								this.destroyAlien(i, j);
								a.setNotHit(false);
								b.scoreUp();
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Detects if all aliens are killed. If so, resets aliens and players back to beginning
	 * @param a- player object
	 */
	public void allAlienHit(Player a){
		int count = 0; 
		for (int i=0; i < this.getRows(); ++i){
			for(int j=0; j < this.getColumns();++j){
				if(this.getAliens(i, j)==false){
					count=count+1;
				}		
			}
		}
		if (count==this.getRows()*this.getColumns()){
			this.resetAliens();
			a.resetPlayer();
		}
	}
/**
 * 
 * @return matrix of x positions of aliens
 */
	public double[][] getxPosArray() {
		return xPosArray;
	}
	/**
	 * 
	 * @param a-row index
	 * @param b-column index
	 * @return x position of indexed alien
	 */
	public double getxPos(int a, int b) {
		return xPosArray[a][b];
	}
	
	/**
	 * 
	 * @param a-row index
	 * @param b-column index
	 * @return y position of indexed alien
	 */
	public double getyPos(int a, int b) {
		return yPosArray[a][b];
	}

	/**
	 * Destroys alien
	 * @param a-row index
	 * @param b- column index
	 */
	public void destroyAlien(int a, int b){
		this.aliens[a][b]=false;
		this.xPosArray[a][b]=5.0;
		this.yPosArray[a][b]=5.0;

	}

	/**
	 * 
	 * @param a-row index
	 * @param b-column index
	 * @return if the indexed alien is alive
	 */
	public boolean getAliens(int a, int b) {
		return aliens[a][b];
	}

	/**
	 * 
	 * @return matrix of y positions of aliens
	 */
	public double[][] getyPosArray() {
		return yPosArray;
	}
/**
 * 
 * @return number of columns
 */
	public int getColumns() {
		return columns;
	}
/**
 * 
 * @param columns-sets columns
 */
	public void setColumns(int columns) {
		this.columns = columns;
	}
	/**
	 * 
	 * @return number of rows
	 */
	public int getRows() {
		return rows;
	}
	/**
	 * 
	 * @return radius of aliens
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * 
	 * @param rows-sets number of rows
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * Moves array of living aliens to the right
	 */
	public void moveRight(){
		for (int i=0; i < this.getRows(); ++i){
			for(int j=0; j < this.getColumns();++j){
				if(this.getAliens(i, j)==true){
					this.xPosArray[i][j] = this.xPosArray[i][j]+speed;
				}
			}
		}
	}

	/**
	 * Moves array of living aliens to the left
	 */
	public void moveLeft(){
		for (int i=0; i < this.getRows(); ++i){
			for(int j=0; j < this.getColumns();++j){
				if(this.getAliens(i, j)==true){
					this.xPosArray[i][j] = this.xPosArray[i][j]-speed;
				}
			}
		}
	}

	/**
	 * Move array of living aliens down
	 */
	public void moveDown(){
		for (int i=0; i < this.getRows(); ++i){
			for(int j=0; j < this.getColumns();++j){
				if(this.getAliens(i, j)==true){
					this.yPosArray[i][j] = this.yPosArray[i][j]-0.1;

				}
			}
		}
	}
/**
 * 
 * @return column index of left edge of alien cluster
 */
	public int getIndexLeftEdge(){
		for (int a=0;a<this.columns;a++){
			for (int i=0; i<this.rows; i++){
				if (this.getAliens(i, a)==true){
					return a;
				}
			}
		}
		return 0;
	}
	/**
	 * 
	 * @return column index of right edge of alien cluster
	 */
	public int getIndexRightEdge(){
		for (int a=this.columns-1;a>-1;--a){
			for (int i=0; i<this.rows; i++){
				if (this.getAliens(i, a)==true){
					return a;
				}
			}
		}
		return 0;
	}


	/**
	 * game play motion of living alien array
	 */
	public void move(){
		if (direction==0){
			moveRight();
			for (int i=0; i<this.rows;++i){
				if(this.xPosArray[i][getIndexRightEdge()]>0.96 && getAliens(i,getIndexRightEdge())==true){
					direction=1;
				}
			}
		}
		else if (direction==1){
			moveDown();
			for(int i=0; i<this.rows;++i){
				if(this.xPosArray[i][getIndexLeftEdge()]<0.05 && getAliens(i,getIndexLeftEdge())==true){
					direction=0;
				}
			}
			for(int i=0; i<this.rows;++i){
				if(this.xPosArray[i][getIndexRightEdge()]>0.96 && getAliens(i,getIndexRightEdge())==true){
					direction=2;
				}
			}
		}
		else if (direction==2){
			moveLeft();
			for (int i=0; i<this.rows;++i){
				if(this.xPosArray[i][getIndexLeftEdge()]<0.05 && getAliens(i,getIndexLeftEdge())==true){
					direction=1;
				}
			}

		}

	}


	
	/**
	 * 
	 * @param a player object
	 * @return- true if player is in contact with any alien
	 */
	public boolean isCollision(Player a){
		for (int i=0; i<this.rows; ++i){
			for(int j=0; j<this.columns;++j){
				if ((a.getPosX()-a.getWidth() < this.xPosArray[i][j]+radius) && (a.getPosY()+a.getHeight() > this.yPosArray[i][j]-radius)){
					return true;
				}
			}
		}
		return false;
	}

	//Resets board
	/**
	 * resets the board of  aliens
	 */
	public void resetAliens(){
		//resets positions
		double b = startPosX;
		double c = startPosY;
		for (int i=0; i<this.rows; ++i){
			for(int j=0; j<this.columns;++j){
				this.aliens[i][j] = true;
			}
		}
		for (int i=0; i<this.rows; ++i){
			for(int j=0; j<this.columns;++j){
				this.xPosArray[i][j] = b;
				this.yPosArray[i][j] = c;
				b=b+0.1;
			}
			c=c-0.1;
			b=startPosX;
		}
	}
	/**
	 * resets the board of living aliens
	 */
	public void resetLiveAliens(){
		//resets positions
		double b = startPosX;
		double c = startPosY;
		for (int i=0; i<this.rows; ++i){
			for(int j=0; j<this.columns;++j){
				if (this.getAliens(i, j)==true){
					this.xPosArray[i][j] = b;
					this.yPosArray[i][j] = c;
					b=b+0.1;
				}
			}
			c=c-0.1;
			b=startPosX;
		}
	}
	/**
	 * if alien makes contact with player, game over
	 * @param b - player1 the player that shoots
	 */
	public void collsion(Player b){
		if (isCollision(b)==true){
			b.loseLife();
			StdDraw.text(.5, .5, "Life Lost");
			StdDraw.pause(500);
			b.resetPlayer();
			this.resetLiveAliens();

		}
	}

	/**
	 * draws aliens
	 */
	public void draw() {
		StdDraw.setPenColor(Color.GREEN);
		for (int i=0; i<this.rows; ++i){
			for(int j=0; j<this.columns;++j){
				StdDraw.filledCircle(this.xPosArray[i][j],this.yPosArray[i][j] , radius);
			}
		}

	}

}
