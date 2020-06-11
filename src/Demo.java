import java.util.ArrayList;

public class Demo {
	public static void main(String[] args)
	{
		final int Num = 1;
		Status[] initial = new Status[1];
		int i;
		for(i = 0; i < Num; i++)
		{
			initial[i] = new Status(SolitaireDemo.board);	
		}
		long timeStart, timeEnd;
		int nodeAv = 0;
		Search s;
//		searchInitial.add(new DFS(initial));
//		searchInitial.add(new DFSWithList(initial));
//		searchInitial.add(new DFSWithHash(initial));
//		searchInitial.add(new BFS(initial));
//		searchInitial.add(new BFSWithList(initial));
//		searchInitial.add(new BFSWithHash(initial));
//		searchInitial.add(new ASearch(initial));
//		searchInitial.add(new ASearchWithList(initial));
//		searchInitial.add(new ASearchWithHash(initial));
//		timeStart = System.currentTimeMillis();
//		nodeAv = 0;
//		for(i = 0; i < Num; i++)
//		{
//			s = new DFS(initial[i]);
//			s.startSearch();
//			nodeAv += s.getnodeNum();
//		}
//		timeEnd = System.currentTimeMillis();
//		System.out.println("node:"+(nodeAv/Num)+"timeAv:"+((timeEnd-timeStart)/Num));
//		
//		timeStart = System.currentTimeMillis();
//		nodeAv = 0;
//		for(i = 0; i < Num; i++)
//		{
//			s = new DFSWithList(initial[i]);
//			s.startSearch();
//			nodeAv += s.getnodeNum();
//		}
//		timeEnd = System.currentTimeMillis();
//		System.out.println("node:"+(nodeAv/Num)+"timeAv:"+((timeEnd-timeStart)/Num));
//		
//		timeStart = System.currentTimeMillis();
//		nodeAv = 0;
//		for(i = 0; i < Num; i++)
//		{
//			s = new DFSWithHash(initial[i]);
//			s.startSearch();
//			nodeAv += s.getnodeNum();
//		}
//		timeEnd = System.currentTimeMillis();
//		System.out.println("node:"+(nodeAv/Num)+"timeAv:"+((timeEnd-timeStart)/Num));

//		timeStart = System.currentTimeMillis();
//		nodeAv = 0;
//		for(i = 0; i < Num; i++)
//		{
//			s = new BFS(initial[i]);
//			s.startSearch();
//			nodeAv += s.getnodeNum();
//		}
//		timeEnd = System.currentTimeMillis();
//		System.out.println("node:"+(nodeAv/Num)+"timeAv:"+((timeEnd-timeStart)/Num));

//		timeStart = System.currentTimeMillis();
//		nodeAv = 0;
//		for(i = 0; i < Num; i++)
//		{
//			s = new BFSWithList(initial[i]);
//			s.startSearch();
//			nodeAv += s.getnodeNum();
//		}
//		timeEnd = System.currentTimeMillis();
//		System.out.println("node:"+(nodeAv/Num)+"timeAv:"+((timeEnd-timeStart)/Num));
//
//		timeStart = System.currentTimeMillis();
//		nodeAv = 0;
//		for(i = 0; i < Num; i++)
//		{
//			s = new BFSWithHash(initial[i]);
//			s.startSearch();
//			nodeAv += s.getnodeNum();
//		}
//		timeEnd = System.currentTimeMillis();
//		System.out.println("node:"+(nodeAv/Num)+"timeAv:"+((timeEnd-timeStart)/Num));
//
//		timeStart = System.currentTimeMillis();
//		nodeAv = 0;
//		for(i = 0; i < Num; i++)
//		{
//			s = new ASearch(initial[i]);
//			s.startSearch();
//			nodeAv += s.getnodeNum();
//		}
//		timeEnd = System.currentTimeMillis();
//		System.out.println("node:"+(nodeAv/Num)+"timeAv:"+((timeEnd-timeStart)/Num));

//		timeStart = System.currentTimeMillis();
//		nodeAv = 0;
//		for(i = 0; i < Num; i++)
//		{
//			s = new ASearchWithList(initial[i]);
//			s.startSearch();
//			nodeAv += s.getnodeNum();
//		}
//		timeEnd = System.currentTimeMillis();
//		System.out.println("node:"+(nodeAv/Num)+"timeAv:"+((timeEnd-timeStart)/Num));

		timeStart = System.currentTimeMillis();
		nodeAv = 0;
		for(i = 0; i < Num; i++)
		{
			s = new ASearchWithHash(initial[i]);
			s.startSearch();
			nodeAv += s.getnodeNum();
		}
		timeEnd = System.currentTimeMillis();
		System.out.println("node:"+(nodeAv/Num)+"timeAv:"+((timeEnd-timeStart)/Num));
	}
}
