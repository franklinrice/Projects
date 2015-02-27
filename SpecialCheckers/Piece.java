public class Piece {

	private String team;
	private String type;
	private int xpos;
	private int ypos;
	private Board board;
	private boolean captured = false;

	public Piece(boolean isFire, Board b, int x, int y, String type) {
		if (isFire==true) {
			team = "fire";
		} else {
			team = "water";
		}
		this.type = type;
		board = b;
		xpos = x;
		ypos = y;
	}

	public boolean isFire() {
		if (team == "fire") {
			return true;
		}
		return false;
	}

	public int side() {
		if (team == "fire") {
			return 0;
		}
		return 1;		
	}

	public boolean isKing() {
		if (this.type.contains("king")) {
			return true;
		}
		return false;
	}

	public boolean isBomb() {
		if (this.type.contains("bomb")) {
			return true;
		}
		return false;
	}

	public boolean isShield() {
		if (this.type.contains("shield")) {
			return true;
		}
		return false;
	}

	public void move(int x, int y) {
		if (x-xpos==2 || x-xpos == -2) {
			board.remove((int) ((x + xpos)/2), (int) ((y+ypos)/2));
			captured = true;

		}
		xpos = x;
		ypos = y;
		if (isFire() && y == 7) {
			type = "king " + type;
		} else if (!isFire() && y == 0) {
			type = "king " + type;
		}
	
}
	public boolean hasCaptured() {
		return captured;

	}

	public void doneCapturing() {
		captured=false;

	}
}



/** 
*Pieces move method needs considion that bomb cannot destroy shield.
*Boards place method: how to remove a piece from its old location if it exists even though that is private.
*
*
*
*/