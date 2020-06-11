import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;

public class BFSWithHash extends BFS implements withHash{
	
	byte[] hash;
	BFSWithHash(Status input)
	{
		super(input);
		hash = new byte[QUANTITY_HASH];
	}
	
	public void startSearch()
	{
		int hashVal;
		timeStart = System.currentTimeMillis();

		while(true)
		{
			searching = ((LinkedList<Node>) s).poll();
			if(searching == null) break;
			
			nodeNum++;

			hashVal = searching.getStatus().calcHashVal(QUANTITY_HASH);
			if(searching.IsGoalNode()) break;
			else if(hash[hashVal] == 0)
			{
				hash[hashVal] = 1;
				ArrayList<Node> next = searching.getNextNode();
				if(!next.isEmpty())
				{
					for(Node n: next)
					{
						s.add(n);
					}
				}
			}
		}
		timeFinish = System.currentTimeMillis();
		isTimeFinish = true;
		printAnsinSysOut();
	}
	public void paintStatus(Graphics2D g2, int x, int y, int w, int h)
	{
		super.paintStatus(g2, x, y, w, h);
		String className = new Object(){}.getClass().getEnclosingClass().getName();
		g2.drawString(className, x + 10, y + 20);
	}
}
