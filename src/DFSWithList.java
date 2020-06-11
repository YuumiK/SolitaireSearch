import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Stack;

public class DFSWithList extends DFS{
	ArrayList<Long> List;
	DFSWithList(Status input)
	{
		super(input);
		List = new ArrayList<Long>();
	}
	public void run()
	{
		long listVal;
		Node init = ((Stack<Node>) s).pop();
		ArrayList<Node> next = null;
		//int cutoff = 0;
		timeStart = System.currentTimeMillis();

		while(true)
		{
			if(s.isEmpty())
			{
				//cutoff++;
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
				listVal = searching.getStatus().calcListVal();
				if(searching.IsGoalNode()) break;
				else if(!List.contains(listVal))
				{
					List.add(listVal);
					next = searching.getNextNode();
					for(Node n: next)
					{
							((Stack<Node>) s).push(n);
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
