import java.awt.Graphics2D;
import java.util.*;

//A search
public class ASearch extends Search{	
	ASearch(Status input)
	{
		super(input);
		s = new LinkedList<Node>();
		s.add(new Node(input));
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
				for(Node n: next)
				{
					s.add(n);
				}
				s.sort(new Comparator<Node>(){
					@Override
					public int compare(Node a, Node b){
						return (a.getDepth() + a.getStatus().h1()) - (b.getDepth() + b.getStatus().h1());
					}
				});
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

