
public abstract class Player {
	
	protected Cell color;
	
	public Player(Cell color){
		this.color = color;
	}
	
	abstract int[] move(Board b); //Automatic move selection
	
	abstract int[] move(Board b, int x, int y); //Button-Click move selection.

}

