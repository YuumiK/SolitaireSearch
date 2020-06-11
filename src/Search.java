import java.awt.Color;
import java.awt.Graphics2D;
import java.util.*;

public abstract class Search extends Thread implements Cloneable{
	List<Node> s;
	Node searching;
	int nodeNum;
	long timeStart, timeFinish;
	boolean isTimeFinish;
	Search(Status input)
	{
		searching = new Node(input);
		nodeNum = 0;
		isTimeFinish = false;
		//s = new LinkedList<Node>();
		//s.add(new Node(input));
	}
	public void run()
	{
		startSearch();
	}
	void startSearch()
	{
		
	}
	protected void printAnsinSysOut()
	{
		System.out.println("Answer by" + new Object(){}.getClass().getEnclosingClass().getName() + ":");
		for(boolean[][] b:searching.getOldStatus())
		{
			Status.printStatusFromBool(b);
		}
		System.out.println("-----------");
	}
	protected void paintStatus(Graphics2D g2, int x, int y, int w, int h)
	{
		g2.setColor(new Color(223, 132, 130));
		g2.fillOval(x + 20, y + 20, 240, 240);
		searching.getStatus().paintBoard(g2, x + w/2, y + h/2 - 20);
		g2.setColor(new Color(249, 249, 249));
		g2.drawString("SearchedNode: " + nodeNum, x + w/2, y + h - 45);
		if(isTimeFinish) g2.drawString("RequiredTime: " + (timeFinish - timeStart) + "ms", x + w/2, y + h - 25);
	}
	public int getnodeNum()
	{
		return nodeNum;
	}
	public Node getSearching()
	{
		return searching;
	}
}
