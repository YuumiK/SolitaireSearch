import java.util.ArrayList;

public class StatusShuffler {
	static Status Shuffle(int count, Status s)//count ..　手数
	{
		ArrayList<Integer[][]> undo_list;
		Integer[][] undo;
		for(int i = count; i > 0; i--)
		{
			undo_list = s.getUndoList();
			try {
				undo = undo_list.get((int)(Math.random() * undo_list.size() - 1)); 
				s.undoPiece(undo);
			}
			catch(IndexOutOfBoundsException e)
			{
				break;
			}
		}
		System.out.println("Problem");
		s.printStatus();
		System.out.println("-----------");
		System.out.println("");
		return s;
	}
}
