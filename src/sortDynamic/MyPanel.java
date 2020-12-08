package sortDynamic;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

@SuppressWarnings( "serial" )
public class MyPanel<E> extends JPanel {
	/* testdata用来存放需要绘制的数据，sortedData用来绘制排好序的数据（不同算法保存的数据不同） */
	public List<Integer>	testdata, sortedData;
	public int		width, height, number;
	public int		max, min;
	public double		distance;
	public int		location_digit_Y	= 420;
	public int		location_digit_X	= 10;
	int			index			= 0;
	Color			select_color, sorted_color, create_color;
	public int		compa_a = Integer.MIN_VALUE, compa_b = Integer.MIN_VALUE;
	/* 冒泡排序的以排好数据个数，这里只适用与冒泡，选择，直接插入等排好序列规律的情况 */
	public int sorted_number = 0;
	/* 定义升序或者降序，默认升序 */
	public Boolean ascend = true;

	public MyPanel()
	{
		super();
		this.testdata	= new ArrayList<Integer>();
		this.sortedData = new ArrayList<Integer>();
		select_color	= Color.RED;
		sorted_color	= Color.GREEN;
		create_color	= Color.BLUE;
	}


	public void paint( Graphics g )
	{
		/*  */
		super.paint( g );
		/* 绘制出背景板 */
		g.setColor( Color.WHITE );
		this.width	= (int) this.getSize().getWidth();
		this.height	= (int) this.getSize().getHeight();
		g.fillRect( 0, 0, this.width, this.height );
		if ( !this.testdata.isEmpty() )
		{
			number		= this.testdata.size();
			distance	= (this.width - location_digit_X) / number;
			if ( distance > 100 )
				distance = 100;
			int max = this.find_max( this.testdata );
			/*
			 * 用来生成随机颜色
			 * int r = (int)((Math.random()*1000)%256);
			 * int gr = (int)((Math.random()*1000)%256);
			 * int b = (int)((Math.random()*1000)%256);
			 * g.setColor(new Color(r,gr,b));
			 */
			g.setColor( create_color );
			for ( int i = 0; i < this.testdata.size(); i++ )
			{
				/* 对不同的情况设置不同的画笔颜色 */
				if ( i == compa_a || i == compa_b )
					g.setColor( select_color );
				else
					g.setColor( create_color );
				/* 计算数据的相对大小，转换成柱状图的高度 */
				int	digit		= this.testdata.get( i );
				int	showheight	= (int) (location_digit_Y / max);
				g.drawString( String.valueOf( digit ), location_digit_X + (int) (distance * i) + (int) (distance / 2),
					      location_digit_Y + 10 );
				digit *= showheight;
				g.fill3DRect( location_digit_X + (int) (distance * i), location_digit_Y - digit, (int) distance, digit,
					      true );
				/* 进程休眠，造成动态效果 */
				try {
					Thread.sleep( 1 );
				} catch ( InterruptedException e ) {
					/* TODO Auto-generated catch block */
					e.printStackTrace();
				}
			}
		}
		/* 对已排序数据进行着色 */
		if ( sorted_number > 0 )
		{
			if ( this.ascend == true )
			{
				for ( int i = 0; i < this.testdata.size(); i++ )
				{
					if ( i >= this.testdata.size() - sorted_number )
					{
						g.setColor( sorted_color );
						max = this.find_max( this.testdata );
						int	digit		= this.testdata.get( i );
						int	showheight	= (int) (location_digit_Y / max);
						g.drawString( String.valueOf( digit ),
							      location_digit_X + (int) (distance * i) + (int) (distance / 2), location_digit_Y + 10 );
						digit *= showheight;
						g.fill3DRect( location_digit_X + (int) (distance * i), location_digit_Y - digit, (int) distance,
							      digit, true );
					}
				}
			} else {
				for ( int i = 0; i < this.sorted_number; i++ )
				{
					g.setColor( sorted_color );
					max = this.find_max( this.testdata );
					int	digit		= this.testdata.get( i );
					int	showheight	= (int) (location_digit_Y / max);
					g.drawString( String.valueOf( digit ), location_digit_X + (int) (distance * i) + (int) (distance / 2),
						      location_digit_Y + 10 );
					digit *= showheight;
					g.fill3DRect( location_digit_X + (int) (distance * i), location_digit_Y - digit, (int) distance,
						      digit, true );
				}
			}
		}
		/*
		 * 这里是对已排序的数据进行着色
		 * if(!sortedData.isEmpty())
		 * {
		 * g.setColor(sorted_color);
		 * for(int i=0;i<sortedData.size();i++)
		 * {
		 *
		 * }
		 * }
		 */
	}


	public int find_max( List<Integer> list )
	{
		int max = Integer.MIN_VALUE;
		for ( int i = 0; i < list.size(); i++ )
		{
			if ( list.get( i ) > max )
				max = list.get( i );
		}
		return(max);
	}


	/* 自定义重绘方法，解决repaint()异步刷新的问题 */
	public void paint_now()
	{
		this.paintImmediately( getVisibleRect() );
	}


	public void setComa( int a )
	{
		this.compa_a = a;
	}


	public void setComb( int b )
	{
		this.compa_b = b;
	}
}