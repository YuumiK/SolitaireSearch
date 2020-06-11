
public class SolitaireDemo{
	static boolean[][] board = {
			{false, false, false, false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false, false, false, false},
			{false, false, false, false, true , true , true , false, false, false, false},
			{false, false, false, false, true , true , true , false, false, false, false},
			{false, false, true , true , true , true , true , true , true , false, false},
			{false, false, true , true , true , false, true , true , true , false, false},
			{false, false, true , true , true , true , true , true , true , false, false},
			{false, false, false, false, true , true , true , false, false, false, false},
			{false, false, false, false, true , true , true , false, false, false, false},
			{false, false, false, false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false, false, false, false}};

	//	static boolean[][] board = {
	//			{false, false, false, false, false, false, false, false, false, false, false},
	//			{false, false, false, false, false, false, false, false, false, false, false},
	//			{false, false, false, false, false, false, false, false, false, false, false},
	//			{false, false, false, false, false, false, false, false, false, false, false},
	//			{false, false, false, true , true , true , true , true , false, false, false},
	//			{false, false, false, false, false, true , false, false, false, false, false},
	//			{false, false, false, true , true , false, true , true , false, false, false},
	//			{false, false, false, false, false, true , false, false, false, false, false},
	//			{false, false, false, false, false, false, false, false, false, false, false},
	//			{false, false, false, false, false, false, false, false, false, false, false},
	//			{false, false, false, false, false, false, false, false, false, false, false}};
	//	
	public static void main(String[] args) {

		//Status initial = new Status(board);
		//Status initial = StatusShuffler.Shuffle(Integer.parseInt(args[0]), new Status(Status.GOAL_BOARD));
		Status initial = StatusShuffler.Shuffle(12, new Status(Status.GOAL_BOARD));	
		SearchWindow subWindow = new SearchWindow("Search", initial);
		subWindow.setVisible(true);
	}
	
}

