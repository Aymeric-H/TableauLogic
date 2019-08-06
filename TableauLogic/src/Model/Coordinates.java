package Model;

public class Coordinates {

	private int x1;
	private int y1;
	private int x2;
	private int y2;
	
	public Coordinates(int x1, int y1, int x2, int y2){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}
	
	public boolean equals(Coordinates coords){
		if (this.x1 == coords.getX1() && this.x2 == coords.getX2() && this.y1 == coords.getY1() && this.y2 == coords.getY2()) {
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean isOverlapping(Coordinates coords){
		/*if (this.y1 < coords.getY2() || this.y2 > coords.getY1()) {
			return false;
		}
		if (this.x1 < coords.getX2() || this.x2 > coords.getX1()) {
			return false;
		}
		return true;*/
		return !(this.x2 <= coords.getX1() ||   // left
                this.y2 <= coords.getY1() ||   // bottom
                this.x1 >= coords.getX2() ||   // right
                this.y1 >= coords.getY2());	   // top
	}
	
	public String toString(){
		return this.x1 + " - " + this.y1 + " - " + this.x2 + " - " + this.y2;
	}
	
}
