import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//コマが置かれているか、そもそもコマがおけるマス目なのかを配置
public class Square {
	private boolean is_puttable;//コマがおけるマス目なのか?
	private boolean is_put;//コマが置かれているのか?
	public static final int PEG_PIXEL_SIZE = 60;
	Square(boolean puttable, boolean put)
	{
		is_puttable = puttable;
		is_put = put;
	}
	boolean getIsPuttable()
	{
		return is_puttable;
	}
	boolean getIsPut()
	{
		return is_put;
	}
	boolean isAbleToMoveToHere()//コマをおけるならtrue、そうでなければfalse
	{
		return(getIsPuttable() && !getIsPut());
	}
	boolean isAbleToMoveFromHere()//コマを取れるならtrue、そうでなければfalse
	{
		return(getIsPuttable() && getIsPut());
	}
	boolean remove()
	{
		if(is_put)
		{
			is_put = false;
			return true;
		}
		return false;
	}
	boolean moveToHere()//コマをおくことに成功したらtrue、そうでなければfalse
	{
		if(getIsPuttable() && !getIsPut())
		{
			is_put = true;
			return true;
		}
		else
		{
			return false;
		}
	}
	boolean moveFromHere()//コマを取ることに成功したらtrue,そうでなければfalse
	{
		if(getIsPuttable() && getIsPut())
		{
			is_put = false;
			return true;
		}
		else
		{
			return false;
		}
	}
	public void paintPeg(Graphics2D g2, int x, int y)
	{
		g2.setColor((getIsPut() ? new Color(193, 224, 220): new Color(116, 109, 129)));
		g2.fillOval(x, y + 15, 20, 20);
	}
}
