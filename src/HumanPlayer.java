import java.util.Scanner;


public class HumanPlayer extends Player{

	Scanner s = new Scanner(System.in);
	
	public HumanPlayer(Cell color){
		super(color);
	}

	@Override
	int[] move(Board b){
		//This function shouldn't ever really be called
		int[] returnArray = {-1, -1};
		return returnArray;
	}

	@Override
	int[] move(Board b, int x, int y){
		int[][] moves = b.getMoves(color);
		if(moves.length == 0){
			int[] returnArray = {-1, -1};
			return returnArray;
		}

		for(int[] value : moves){
			if(value[0] == x && value[1] == y){
				int[] returnArray = {x, y};
				return returnArray;
			}
		}
		int[] returnArray = {-1, -1};
		return returnArray;
	}

}
