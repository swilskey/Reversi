public enum Cell {
	
		BLACK('B'),WHITE('W'),EMPTY('E');
		private char color;

		private Cell(char color) 
		{
			this.color = color;
			
		}

		protected char getColor()
		{
			return this.color;
		}
		
		public void flipColor(Cell c)
		{
			if (this.color == 'B')
				this.color = 'W';

			if (this.color == 'W')
				this.color = 'B';

		//	if (this.color == 'E')
		//		throw  EmptySpaceException();
		}
		
		public boolean equals(Cell c)
		{
			if ( this.color == c.color )
				return true;
			return false;
		}

		public String toString()
		{
			switch(this){
			case BLACK: return "B";
			case WHITE: return "W";
			case EMPTY: return " ";
			default: throw new IllegalArgumentException(); // had to have an exception to get this to compile. I will catch it later in the code.
			}		
		}
	
};