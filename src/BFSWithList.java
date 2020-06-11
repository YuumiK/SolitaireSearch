import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;

public class BFSWithList extends BFS{
	ArrayList<Long> List;
	BFSWithList(Status input)
	{
		super(input);
		List = new ArrayList<Long>();
	}
	public void startSearch()
	{
		long listVal;
		timeStart = System.currentTimeMillis();

		while(true)
		{
			searching = ((LinkedList<Node>) s).poll();
			if(searching == null) break;
			
			nodeNum++;

			listVal = searching.getStatus().calcListVal();
			if(searching.IsGoalNode()) break;
			else if(!List.contains(listVal))
			{
				List.add(listVal);
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
