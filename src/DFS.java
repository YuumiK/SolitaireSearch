import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Stack;
//DFS = Depth first search -> IDDFS(iterative deepening depth-first search)
public class DFS extends Search{
	DFS(Status input)
	{
		super(input);
		s = new Stack<Node>();
		s.add(new Node(input));
	}

	public void startSearch()
	{
		Node init = ((Stack<Node>) s).pop();
		ArrayList<Node> next = null;
		int cutoff = 0;
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
				if(searching.IsGoalNode()) break;
				else
				{
					next = searching.getNextNode();
					for(Node n: next)
					{
//						if(n.getDepth() < cutoff)
//						{
//							((Stack<Node>) s).push(n);
//						}
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
