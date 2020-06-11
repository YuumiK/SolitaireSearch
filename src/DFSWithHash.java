import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class DFSWithHash extends DFS implements withHash{
	byte[] hash;
	DFSWithHash(Status input)
	{
		super(input);
		hash = new byte[QUANTITY_HASH];
	}
	
	public void startSearch()
	{
		int hashVal;
		Node init = ((Stack<Node>) s).pop();
		ArrayList<Node> next = null;
		int cutoff = 0;
		timeStart = System.currentTimeMillis();

		while(true)
		{
			if(s.isEmpty())
			{
				cutoff++;
				((Stack<Node>) s).push(init);
			}
			else
			{
				try
				{
					searching = ((Stack<Node>) s).pop();
				}
				catch(StackOverflowError e)//コンピューターの性能の都合上
				{
					break;
				}
				nodeNum++;
				hashVal = searching.getStatus().calcHashVal(QUANTITY_HASH);
				if(searching.IsGoalNode()) break;
				else if(hash[hashVal] == 0)
				{
					hash[hashVal] = 1;
					next = searching.getNextNode();
					for(Node n: next)
					{
						if(n.getDepth() < cutoff)
						{
							((Stack<Node>) s).push(n);
						}
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
