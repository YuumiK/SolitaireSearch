import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

public class ASearchWithList extends ASearch{
	ArrayList<Long> List;
	ASearchWithList(Status input)
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
			if(searching == null || searching.IsGoalNode()) break;
			nodeNum++;
		
			listVal = searching.getStatus().calcListVal();
			if(!List.contains(listVal))
			{
				List.add(listVal);
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
