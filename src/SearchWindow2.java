import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SearchWindow2 extends JFrame implements KeyListener{
	protected static final int WINDOW_SIZE = 320;
	protected static final int SEARCH_NUM = 1;
	MainPanel2 MP;
	private boolean isStart;
	SearchWindow2(String title, Status initial)
	{
		isStart = false;
		setTitle(title);
		setBounds(0, 0, WINDOW_SIZE * SEARCH_NUM, WINDOW_SIZE + 20);
		//setLocationRelativeTo(null);//初期画面表示位置を中央に
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//CLOSEでプログラム終了
		setResizable(false);

		Container CP = getContentPane();//getContentPane()はJFrameクラスに定義されている
		//CP.setLayout(null);//レイアウトマネージャを停止

		//上部の背景色を橙に設定する
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		CP.add(panel, BorderLayout.NORTH);

		//Mainパネルの作成、フレームへのセット
		MP = new MainPanel2(initial);
		CP.add(MP);
		addKeyListener(this);//キーを
		//CP.remove(MP);//フレームを外す
		//CP.removeKeyListener(MP);//KeyListenerを外す
	}
	void StartSearch()
	{
		MP.Search();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_ENTER:
			if(!isStart)
			{
				StartSearch();
				isStart = true;
			}
			break;
		case KeyEvent.VK_ESCAPE:
				System.exit(0);
			break;
		default:
			break;
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

class MainPanel2 extends JPanel implements Runnable{
	ArrayList<Search> search;
	Status initial;
	int x = 100, y = 100;
	boolean state = true;
	Thread t1;

	MainPanel2(Status initial){
		//setLayout(null);
		setBackground(new Color(116, 109, 129));//背景色を青に
		t1 = new Thread(this);//Thread instance	
		this.initial = initial;
		initialSearch();
		t1.start();//Thread Start
		//search = new ASearchWithHash(initial);
	}
	public void initialSearch()
	{
		search = new ArrayList<Search>();
		//search.add(new DFS(this.initial));
		//search.add(new DFSWithList(this.initial));
		//search.add(new DFSWithHash(this.initial));
		//search.add(new BFS(this.initial));
		//search.add(new BFSWithList(this.initial));
		//search.add(new BFSWithHash(this.initial));
		//search.add(new ASearch(this.initial));
		//search.add(new ASearchWithList(this.initial));
		search.add(new ASearchWithHash(this.initial));
	}
	public void Search()
	{
		for(Search s: search)
		{
			s.start();
		}
		//		if(complete == null)
		//		{
		//			System.out.println("No Answer");
		//		}
		//		else
		//		{
		//			for(boolean[][] s: complete.getOldStatus())
		//			{
		//				Status.printStatusFromBool(s);
		//			}
		//			System.out.println("-----------");
		//			System.out.println("completed; move:"+ (complete.getDepth() - 1) +"times");
		//		}
	}
	//Runnableによるrun() method
	public void run(){
		//無限ループでThreadが終了しないようにする
		while(true){
			try{
				t1.sleep(100);//100ms毎に実施
			}catch(InterruptedException e){
			}
			repaint();
		}
	}

	//JComponentによるpaintComponent method
	//JPanel は JComponent を継承している
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(new Font("Gotham", Font.BOLD, 12));
		for(int i = 0; i < search.size(); i++)
		{
			if(search.get(i).getSearching() != null)
				search.get(i).paintStatus(g2, 0 + SearchWindow.WINDOW_SIZE * i, 0, SearchWindow.WINDOW_SIZE, SearchWindow.WINDOW_SIZE);
		}
	}
}
