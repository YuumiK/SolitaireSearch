import java.util.ArrayList;

public class Node{
	private Status s;
	private ArrayList<boolean[][]> old_status_list;
	private int depth;
	Node(Status s)
	{
		this.s = s;
		old_status_list = new ArrayList<boolean[][]>();
		old_status_list.add(s.getBoolofBoard());
		//setNextNode();
		depth = old_status_list.size();
	}
	public int getDepth()
	{
		return depth;
	}
	Node(Status s, ArrayList<boolean[][]> inherit_status)//2回目以降。最初のノードからの道筋を引き継ぐ
	{
		this.s = s;
		old_status_list = new ArrayList<boolean[][]>();
		if(inherit_status != null)
		{
			for(boolean[][] old_s: inherit_status)
			{
				old_status_list.add(old_s);
				//old_s.printStatus();
			}
		}
		if(s != null)
		{
			old_status_list.add(s.getBoolofBoard());
			//			s.printStatus();
			//			System.out.println("current");
			//setNextNode();
		}
		depth = old_status_list.size();
	}
	void printStatus()
	{
		s.printStatus();
	}
	boolean IsGoalNode()
	{
		return s.isGoal();
	}
	ArrayList<Node> getNextNode()//動作一回で到達可能な盤面全てのリストをセットし返す
	{
		Status new_status;
		ArrayList<Integer[][]> move_list = s.getMoveList();
		ArrayList<Node> next_status_list = new ArrayList<Node>();
		if(move_list != null)
		{
			for(Integer[][] move: move_list)
			{
				new_status = new Status(s.getBoolofBoard());
				if(new_status.movePiece(move)) next_status_list.add(new Node(new_status, old_status_list));
			}
		}
		return next_status_list;
	}
	ArrayList<boolean[][]> getOldStatus()
	{
		return old_status_list;
	}
	Status getStatus()
	{
		return this.s;
	}
}
