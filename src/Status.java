import java.awt.Graphics2D;
import java.util.ArrayList;

//ソリティア盤と玉の配置、動かせる玉の一覧を作る
public class Status implements Cloneable{
	private static final int TO = 0;
	private static final int FROM = 1;
	public static final int BOARD_SIZE = (7 + 4);
	public static final boolean[][] BOARD_INITIAL_STATUS = {
			{false, false, false, false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false, false, false, false},
			{false, false, false, false, true , true , true , false, false, false, false},
			{false, false, false, false, true , true , true , false, false, false, false},
			{false, false, true , true , true , true , true , true , true , false, false},
			{false, false, true , true , true , true , true , true , true , false, false},
			{false, false, true , true , true , true , true , true , true , false, false},
			{false, false, false, false, true , true , true , false, false, false, false},
			{false, false, false, false, true , true , true , false, false, false, false},
			{false, false, false, false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false, false, false, false}};//→x軸、↓y軸
	public static final boolean[][] GOAL_BOARD = {
			{false, false, false, false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false, false, false, false},
			{false, false, false, false, false, true , false, false, false, false, false},
			{false, false, false, false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false, false, false, false},
			{false, false, false, false, false, false, false, false, false, false, false}};
	private Square[][] board;
	private ArrayList<Integer[][]> move_list;
	private int quantity_hole;
	Status(boolean[][] board_initial)
	{
		move_list = new ArrayList<Integer[][]>();
		board = new Square[BOARD_SIZE][BOARD_SIZE];
		quantity_hole = 0;
		for(int i = 0; i < BOARD_SIZE; i++)
		{
			for(int j = 0; j < BOARD_SIZE; j++)
			{
				try
				{
					board[i][j] = new Square(BOARD_INITIAL_STATUS[i][j], board_initial[i][j]);
					if(board[i][j].getIsPuttable()) quantity_hole++;
				}
				catch(NullPointerException e)
				{
					board[i][j] = new Square(BOARD_INITIAL_STATUS[i][j], false);	
				}
			}
		}
		setMoveList();
	}
	void setMoveList()
	{
		move_list.clear();
		for(int i = 0; i < BOARD_SIZE; i++)
		{
			for(int j = 0; j < BOARD_SIZE; j++)
			{
				if(board[i][j].isAbleToMoveToHere())
				{
					Integer[][] movable = new Integer[2][2];
					//UP
					if(board[i][j - 1].getIsPut() && board[i][j - 2].isAbleToMoveFromHere())
					{
						movable[TO][0] = i;
						movable[TO][1] = j;//to
						movable[FROM][0] = i;
						movable[FROM][1] = j - 2;//from
						move_list.add(movable);
					}
					//LEFT
					if(board[i - 1][j].getIsPut() && board[i - 2][j].isAbleToMoveFromHere())
					{
						movable[TO][0] = i;
						movable[TO][1] = j;//to
						movable[FROM][0] = i - 2;
						movable[FROM][1] = j;//from
						move_list.add(movable);
					}
					//DOWN
					if(board[i][j + 1].getIsPut() && board[i][j + 2].isAbleToMoveFromHere())
					{
						movable[TO][0] = i;
						movable[TO][1] = j;//to
						movable[FROM][0] = i;
						movable[FROM][1] = j + 2;//from
						move_list.add(movable);
					}
					//RIGHT
					if(board[i + 1][j].getIsPut() && board[i + 2][j].isAbleToMoveFromHere())
					{
						movable[TO][0] = i;
						movable[TO][1] = j;//to
						movable[FROM][0] = i + 2;
						movable[FROM][1] = j;//from
						move_list.add(movable);
					}
				}
			}
		}
	}
	ArrayList<Integer[][]> getUndoList()
	{
		ArrayList<Integer[][]> ans = new ArrayList<Integer[][]>();
		for(int i = 0; i < BOARD_SIZE; i++)
		{
			for(int j = 0; j < BOARD_SIZE; j++)
			{
				if(board[i][j].getIsPut())
				{
					Integer[][] undoable = new Integer[2][2];
					//UP
					if(board[i][j - 1].isAbleToMoveToHere() && board[i][j - 2].isAbleToMoveToHere())
					{
						undoable[TO][0] = i;
						undoable[TO][1] = j;//to
						undoable[FROM][0] = i;
						undoable[FROM][1] = j - 2;//from
						ans.add(undoable);
					}
					//LEFT
					if(board[i - 1][j].isAbleToMoveToHere() && board[i - 2][j].isAbleToMoveToHere())
					{
						undoable[TO][0] = i;
						undoable[TO][1] = j;//to
						undoable[FROM][0] = i - 2;
						undoable[FROM][1] = j;//from
						ans.add(undoable);
					}
					//DOWN
					if(board[i][j + 1].isAbleToMoveToHere() && board[i][j + 2].isAbleToMoveToHere())
					{
						undoable[TO][0] = i;
						undoable[TO][1] = j;//to
						undoable[FROM][0] = i;
						undoable[FROM][1] = j + 2;//from
						ans.add(undoable);
					}
					//RIGHT
					if(board[i + 1][j].isAbleToMoveToHere() && board[i + 2][j].isAbleToMoveToHere())
					{
						undoable[TO][0] = i;
						undoable[TO][1] = j;//to
						undoable[FROM][0] = i + 2;
						undoable[FROM][1] = j;//from
						ans.add(undoable);
					}
				}
			}
		}
		return ans;
	}
	ArrayList<Integer[][]> getMoveList()
	{
		return move_list;
	}

	boolean isGoal()
	{
		boolean is_goal = true;
		for(int i = 0; i < BOARD_SIZE; i++)
		{
			for(int j = 0; j < BOARD_SIZE; j++)
			{
				if(board[i][j].getIsPut() != GOAL_BOARD[i][j])
				{
					is_goal = false;
				}
			}
		}
		return is_goal;
		//		int count = 0;
		//		for(int i = 0; i < BOARD_SIZE; i++)
		//		{
		//			for(int j = 0; j < BOARD_SIZE; j++)
		//			{
		//				if(board[i][j].getIsPut())
		//				{
		//					count++;
		//				}
		//			}
		//		}
		//		if(count == 1) return true;
		//		return false;
	}
	void printStatus()
	{
		System.out.println("-----------");
		for(Square[] tmp: board)
		{
			for(Square val:tmp)
			{
				if(!val.getIsPuttable())
				{
					System.out.print(".");
				}
				else if(!val.getIsPut())
				{
					System.out.print("*");
				}
				else 
				{
					System.out.print("0");
				}
			}
			System.out.println("");
		}
	}
	static void printStatusFromBool(boolean[][] board_bool)
	{
		System.out.println("-----------");
		for(int i = 0; i < BOARD_SIZE; i++)
		{
			for(int j = 0; j < BOARD_SIZE; j++)
			{
				if(!BOARD_INITIAL_STATUS[i][j])
				{
					System.out.print(".");
				}
				else if(!board_bool[i][j])
				{
					System.out.print("*");
				}
				else 
				{
					System.out.print("0");
				}
			}
			System.out.println("");
		}
	}
	boolean[][] getBoolofBoard()
	{
		boolean[][] bool_board = new boolean[BOARD_SIZE][BOARD_SIZE];
		for(int i = 0; i < BOARD_SIZE; i++)
		{
			for(int j = 0; j < BOARD_SIZE; j++)
			{
				bool_board[i][j] = board[i][j].getIsPut();
			}
		}
		return bool_board;
	}
	boolean movePiece(Integer[][] move)
	{
		if(board[move[FROM][0]][move[FROM][1]].moveFromHere())
		{
			board[move[TO][0]][move[TO][1]].moveToHere();
			board[(move[FROM][0] + move[TO][0])/2][(move[FROM][1] + move[TO][1])/2].remove();
			setMoveList();
			return true;
		}
		return false;
	}
	boolean undoPiece(Integer[][] undo)
	{
		if(board[undo[TO][0]][undo[TO][1]].moveFromHere())
		{
			board[undo[FROM][0]][undo[FROM][1]].moveToHere();
			board[(undo[FROM][0] + undo[TO][0])/2][(undo[FROM][1] + undo[TO][1])/2].moveToHere();
			setMoveList();
			return true;
		}
		return false;
	}
	int getQuantityHole()
	{
		return quantity_hole;
	}
	int calcHashVal(int quantity_hash)
	{
		int count = quantity_hole;
		long hashedVal = 0;
		for(Square[] tmp: board)
		{
			for(Square val:tmp)
			{
				if(val.getIsPuttable())
				{
					count--;
					hashedVal += (val.getIsPut() ? (long)Math.pow(2, count) : 0);
				}
			}
		}
		return (int)(hashedVal % quantity_hash);
	}
	long calcListVal()
	{
		int count = quantity_hole;
		long hashedVal = 0;
		for(Square[] tmp: board)
		{
			for(Square val:tmp)
			{
				if(val.getIsPuttable())
				{
					count--;
					hashedVal += (val.getIsPut() ? (long)Math.pow(2, count) : 0);
				}
			}
		}
		return hashedVal;
	}
	int h1()
	{
		int unmatch = 0;
		int center = (int) Math.ceil(BOARD_SIZE / 2.0) - 1;
		for(int i = 0; i < BOARD_SIZE; i++)
		{
			for(int j = 0; j < BOARD_SIZE; j++)
			{
				if(board[i][j].getIsPut())
				{
					unmatch += ((center - i) * (center - i) + (center - j) * (center - j));
				}
			}
		}
		return unmatch;
	}
	public void paintBoard(Graphics2D g2, int cx, int cy)
	{
		for(int i = 0; i < BOARD_SIZE; i++)
		{
			for(int j = 0; j < BOARD_SIZE; j++)
			{
				if(board[i][j].getIsPuttable())
				{
					board[i][j].paintPeg(g2, cx + Square.PEG_PIXEL_SIZE/2 * (i - BOARD_SIZE/2 - 1), cy + Square.PEG_PIXEL_SIZE/2 * (j - BOARD_SIZE/2 - 1));
				}
			}
		}
	}
}
