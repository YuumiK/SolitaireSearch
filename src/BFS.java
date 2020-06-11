import java.awt.Graphics2D;
import java.util.*;

//breadth first search
public class BFS extends Search{
	BFS(Status input)//入力形式を(x,y) = (1,1),(1,2)から順に(3,3)までの値。0=空白{4,1,8,2,5,3,6,0,7}
	{
		super(input);
		s = new LinkedList<Node>();
		Node initial = new Node(input);
		s.add(initial);
	}

	public void startSearch()
	{
		timeStart = System.currentTimeMillis();

		while(true)
		{
			searching = ((LinkedList<Node>) s).poll();
			if(searching == null) break;
			
			nodeNum++;

			if(searching.IsGoalNode()) break;
			else
			{
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
