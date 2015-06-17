
public class HardComputerPlayer extends Player {
	
	 int[][] boardWeights = { {3, 2, 2, 2, 3}, {2, 1, 1, 1, 1, 2}, {2, 1, 1, 1, 1, 1, 2},  {2, 1, 1, 0, 0, 1, 1, 2}, {2, 1, 1, 0, -5, 0, 1, 1, 2},
			 {2, 1, 1, 0, 0, 1, 1, 2}, {2, 1, 1, 1, 1, 1, 2}, {2, 1, 1, 1, 1, 2}, {3, 2, 2, 2, 3} };

	   public HardComputerPlayer(Cell color)
	   {
		  super(color);
	   }


	@Override
	int[] move(Board b) {
		int[] returnArray = {-1, -1};
		int[][] moves = b.getMoves(color);
		if(moves.length == 0){
			return returnArray;
		}
		returnArray[0] = moves[0][0];
		returnArray[1] = moves[0][1];
		for(int[] value : moves){ //I might be calling the wrong cell in playRating. Hopefully not, though.
			if(b.playRating(color, value[0], value[1])*boardWeights[value[0]][value[1]] > b.playRating(color, returnArray[0], returnArray[1])*boardWeights[returnArray[0]][returnArray[1]]){
				returnArray[0] = value[0];
				returnArray[1] = value[1];
			}
		}
		return returnArray;
	}

	@Override
	int[] move(Board b, int x, int y) {{
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

}
