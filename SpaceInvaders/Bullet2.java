package lab10;

import java.awt.Color;

import sedgewick.StdDraw;

public class Bullet2 {

		private double posX;
		private double posY;
		private double height;
		private double width;
		private boolean isNotHit;

		public double getHeight() {
			return height;
		}
		public double getWidth() {
			return width;
		}
		public Bullet2(double posX, double posY){
			this.posX = posX;
			this.posY = posY;
			this.height = 0.02;
			this.width = 0.005;
			this.isNotHit = false;
		}
		public void draw(){
			StdDraw.setPenColor(Color.RED);
			StdDraw.filledRectangle(this.posX, this.posY, this.width, this.height);
		}

		public double getPosX() {
			return posX;
		}
		public void setPosX(double posX) {
			this.posX = posX;
		}
		public double getPosY() {
			return posY;
		}
		public void setPosY(double posY) {
			this.posY = posY;
		}
		public boolean getIsNotHit() {
			return isNotHit;
		}
		public void move(){
			if (this.isNotHit == true) {
				this.posY = this.posY -0.1;
			}
		}
		public void setNotHit(boolean isNotHit) {
			this.isNotHit = isNotHit;
		}




	}



