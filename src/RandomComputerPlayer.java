import java.util.Random;

public class RandomComputerPlayer extends Player {

	Random r = new Random();
	
	public RandomComputerPlayer(Cell color) {
		super(color);
	}

	@Override
	int[] move(Board b){
		int[][] moves = b.getMoves(color);              
		int random = r.nextInt(moves.length);
		int[] returnArray = {moves[random][0], moves[random][1]};
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
