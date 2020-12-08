package sortDynamic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.border.StandardBorderPainter;
import org.jvnet.substance.button.ClassicButtonShaper;
import org.jvnet.substance.painter.StandardGradientPainter;
import org.jvnet.substance.skin.RavenSkin;
import org.jvnet.substance.theme.SubstanceTerracottaTheme;
import org.jvnet.substance.watermark.SubstanceBubblesWatermark;

@SuppressWarnings( "serial" )
public class mainInterface extends JFrame implements ActionListener {
	/* 定义需要图片 */
	public Image titleImage, timeBg;
	/* 定义需要的面板 */
	public JPanel jp_east, jp_under, jp_center_all;
	@SuppressWarnings( "rawtypes" )
	MyPanel jp_center;
	/* 定义需要的按钮 */
	public JButton reset_data, change_data, run_sort, insert_data;
	/* 定义单选框 */
	public JRadioButton	ascending_order, descending_order;
	public ButtonGroup	buttonGroup;
	/* 定义下拉列表 */
	public JComboBox<String> combox;
	/* 定义排序顺序,默认为升序 */
	public boolean ascend = true;
	/* 定义算法选择,默认为冒泡排序 */
	public int		sort_num;
	public String		sort_flag;
	public JTextField	jtf;
	public JLabel		jl;

	public mainInterface()
	{
		/* 导入标题图片 */
		try {
			titleImage = ImageIO.read( new File( "image//titleImage.jpg" ) );
		} catch ( IOException e ) {
			/* TODO Auto-generated catch block */
			e.printStackTrace();
		}
		/* 设置标题图片 */
		this.setIconImage( titleImage );
		this.setTitle( "SortDynamic" );
		/* 设置布局 */
		this.setLayout( new BorderLayout() );

		/* 定义中间的显示面板 */
		jp_center = new MyPanel<>();

		/* 定义右端面板 */
		jp_east = new JPanel();
		jp_east.setLayout( new GridLayout( 6, 1 ) );
		String[] data	= { "BubbleSort", "SelectSort", "HeapSort", "ShellSort", "InsertSort" };
		combox		= new JComboBox<String>( data );
		combox.addItemListener( new ItemListener()
					{
						public void itemStateChanged( ItemEvent e )
						{
		                                        /* 只处理选中的状态 */
							if ( e.getStateChange() == ItemEvent.SELECTED )
							{
								//...
							}
						}
					} );
		ascending_order = new JRadioButton( "Ascending" );
		ascending_order.addActionListener( this );
		ascending_order.setActionCommand( "ascending" );
		descending_order = new JRadioButton( "Descending" );
		descending_order.addActionListener( this );
		descending_order.setActionCommand( "descending" );
		buttonGroup = new ButtonGroup();
		buttonGroup.add( ascending_order );
		buttonGroup.add( descending_order );
		jl		= new JLabel( "Add integer" );
		jtf		= new JTextField( 6 );
		insert_data	= new JButton( "Add integer" );
		insert_data.addActionListener( this );
		insert_data.setActionCommand( "insert" );
		jp_east.add( combox );
		jp_east.add( ascending_order );
		jp_east.add( descending_order );
		jp_east.add( jl );
		jp_east.add( jtf );
		jp_east.add( insert_data );

		/* 定义底部按钮 */
		jp_under = new JPanel();
		jp_under.setLayout( new GridLayout( 1, 3 ) );
		reset_data = new JButton( "Reset Data" );
		reset_data.addActionListener( this );
		reset_data.setActionCommand( "reset" );
		change_data = new JButton( "Insert Data" );
		change_data.addActionListener( this );
		change_data.setActionCommand( "change" );
		run_sort = new JButton( "Start sorting" );
		run_sort.addActionListener( this );
		run_sort.setActionCommand( "start" );

		jp_under.add( reset_data );
		jp_under.add( change_data );
		jp_under.add( run_sort );

		/* 定义容器，将其他组件放入其中 */
		Container ct = this.getContentPane();
		ct.add( jp_east, "East" );
		ct.add( jp_center, "Center" );
		ct.add( jp_under, "South" );

		/* 设置窗口的大小 */
		this.setSize( 600, 500 );
		/* 确定JWindow的初始位置，使其处于屏幕的中间，使用toolkit获得窗口的大小 */
		int	width	= Toolkit.getDefaultToolkit().getScreenSize().width;
		int	height	= Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation( width / 2 - 300, height / 2 - 250 );
		/* 使窗口可见 */
		this.setVisible( true );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}


	@SuppressWarnings( { "unused" } )
	public static void main( String[] args )
	{
		/* TODO Auto-generated method stub */
		try {
			UIManager.setLookAndFeel( new SubstanceLookAndFeel() );
			JFrame.setDefaultLookAndFeelDecorated( true );
			JDialog.setDefaultLookAndFeelDecorated( true );
			/* 设置皮肤 */
			SubstanceLookAndFeel.setSkin( new RavenSkin() );
			/* 设置主题 */
			SubstanceLookAndFeel.setCurrentTheme( new SubstanceTerracottaTheme() );
			/* 设置按钮形状 */
			SubstanceLookAndFeel.setCurrentButtonShaper( new ClassicButtonShaper() );
			/* 设置水印 */
			SubstanceLookAndFeel.setCurrentWatermark( new SubstanceBubblesWatermark() );
			/* 设置渐变渲染 */
			SubstanceLookAndFeel.setCurrentGradientPainter( new StandardGradientPainter() );
			/* 设置边框渲染 */
			SubstanceLookAndFeel.setCurrentBorderPainter( new StandardBorderPainter() );
			mainInterface mainInterface = new mainInterface();
		} catch ( Exception e ) {
			/* TODO Auto-generated catch block */
			e.printStackTrace();
		}
	}


	@SuppressWarnings( "unchecked" )
	@Override
	public void actionPerformed( ActionEvent e )
	{
		/* TODO Auto-generated method stub */
		if ( e.getActionCommand().equals( "reset" ) )
		{
			this.jp_center.testdata.clear();
			this.jp_center.compa_a		= Integer.MIN_VALUE;
			this.jp_center.compa_b		= Integer.MIN_VALUE;
			this.jp_center.sorted_number	= 0;
			this.jp_center.paint_now();
		} else if ( e.getActionCommand().equals( "change" ) )
		{
			int a = (int) ( (Math.random() * 100) % 50) + 1;
			this.jp_center.testdata.add( a );
			this.jp_center.paint_now();
		} else if ( e.getActionCommand().equals( "start" ) )
		{
			/* 编写冒泡排序算法 */
			if ( combox.getSelectedIndex() == 0 )
			{
				if ( this.ascend == true )
				{
					this.jp_center.ascend = true;
					ascend_bubble( this.jp_center.testdata );
				} else {
					this.jp_center.ascend = false;
					this.descend_bubble( this.jp_center.testdata );
				}
			}
			/* 编写选择排序算法 */
			else if ( combox.getSelectedIndex() == 1 )
			{
				if ( this.ascend == true )
				{
					this.jp_center.ascend = false; /* debug */
					ascend_select( this.jp_center.testdata );
				} else {
					this.jp_center.ascend = false;
					this.descend_select( this.jp_center.testdata );
				}
			}
			/* 编写堆排序算法 */
			else if ( combox.getSelectedIndex() == 2 )
			{
				if ( this.ascend == true )
				{
					this.jp_center.ascend = true;
					ascend_heap( this.jp_center.testdata );
				} else {
					this.jp_center.ascend = false;
					this.descend_heap( this.jp_center.testdata );
				}
			}
			/* 编写希尔排序算法 */
			else if ( combox.getSelectedIndex() == 3 )
			{
				if ( this.ascend == true )
				{
					this.jp_center.ascend = true;
					ascend_shell( this.jp_center.testdata );
				} else {
					this.jp_center.ascend = false;
					this.descend_shell( this.jp_center.testdata );
				}
			}
			/* 编写插入排序算法 */
			else if ( combox.getSelectedIndex() == 4 )
			{
				if ( this.ascend == true )
				{
					this.jp_center.ascend = true;
					ascend_insert( this.jp_center.testdata );
				} else {
					this.jp_center.ascend = false;
					this.descend_insert( this.jp_center.testdata );
				}
			}
		} else if ( e.getActionCommand().equals( "ascending" ) )
		{
			this.ascend = true;
		} else if ( e.getActionCommand().equals( "descending" ) )
		{
			this.ascend = false;
		} else if ( e.getActionCommand().equals( "insert" ) )
		{
			String mydigit = this.jtf.getText();
			/* 判断输入的是否为数字，若为其他则报错 */
			int count = 0;
			for ( int k = 0; k < mydigit.length(); k++ )
			{
				if ( Character.isDigit( mydigit.charAt( k ) ) )
					count++;
			}
			if ( count != mydigit.length() )
			{
				JOptionPane.showMessageDialog( new JFrame().getContentPane(), "请输入合法的数字" );
			} else {
				int mydata = Integer.parseInt( mydigit );
				this.jp_center.testdata.add( mydata );
				this.jp_center.paint_now();
			}
		}
	}


	@SuppressWarnings( "unchecked" )
	public void ascend_shell( List<Integer> testdata )
	{
		int	j	= 0;
		int	temp	= 0;
		for ( int increment = testdata.size() / 2; increment > 0; increment /= 2 )
		{
			for ( int i = increment; i < testdata.size(); i++ )
			{
				temp = (int) testdata.get( i );
				for ( j = i - increment; j >= 0; j -= increment )
				{
					this.jp_center.setComa( i );
					this.jp_center.setComb( j );
					this.jp_center.paint_now();
					if ( temp < (int) testdata.get( j ) )
					{
						testdata.set( j + increment, (int) testdata.get( j ) );
					} else {
						break;
					}
				}
				this.jp_center.setComa( i );
				this.jp_center.setComb( j + increment );
				this.jp_center.paint_now();
				testdata.set( j + increment, temp );
			}
		}
		this.jp_center.sorted_number = this.jp_center.testdata.size();
		this.jp_center.paint_now();
	}


	@SuppressWarnings( "unchecked" )
	public void descend_shell( List<Integer> testdata )
	{
		int	j	= 0;
		int	temp	= 0;
		for ( int increment = testdata.size() / 2; increment > 0; increment /= 2 )
		{
			for ( int i = increment; i < testdata.size(); i++ )
			{
				temp = (int) testdata.get( i );
				for ( j = i - increment; j >= 0; j -= increment )
				{
					this.jp_center.setComa( i );
					this.jp_center.setComb( j );
					this.jp_center.paint_now();
					if ( temp > (int) testdata.get( j ) )
					{
						testdata.set( j + increment, (int) testdata.get( j ) );
					} else {
						break;
					}
				}
				this.jp_center.setComa( i );
				this.jp_center.setComb( j + increment );
				this.jp_center.paint_now();
				testdata.set( j + increment, temp );
			}
		}
		this.jp_center.sorted_number = this.jp_center.testdata.size();
		this.jp_center.paint_now();
	}


	@SuppressWarnings( "unchecked" )
	public void ascend_insert( List<Integer> testdata )
	{
		int mark;
		for ( int i = 1; i < testdata.size(); i++ )
		{
			this.jp_center.setComa( i );
			this.jp_center.setComb( i - 1 );
			this.jp_center.paint_now();
			if ( (int) testdata.get( i ) < (int) testdata.get( i - 1 ) )
			{
				mark = testdata.get( i );
				int j;
				for ( j = i; j > 0 && mark < (int) testdata.get( j - 1 ); j-- )
				{
					this.jp_center.setComa( i );
					this.jp_center.setComb( j - 1 );
					this.jp_center.paint_now();
					testdata.set( j, (int) testdata.get( j - 1 ) );
				}
				this.jp_center.setComa( i );
				this.jp_center.setComb( j );
				testdata.set( j, mark );
				this.jp_center.paint_now();
			}
		}
		this.jp_center.sorted_number = this.jp_center.testdata.size();
		this.jp_center.paint_now();
	}


	@SuppressWarnings( "unchecked" )
	public void descend_insert( List<Integer> testdata )
	{
		int mark;
		for ( int i = 1; i < testdata.size(); i++ )
		{
			this.jp_center.setComa( i );
			this.jp_center.setComb( i - 1 );
			this.jp_center.paint_now();
			if ( (int) testdata.get( i ) > (int) testdata.get( i - 1 ) )
			{
				mark = (int) testdata.get( i );
				int j;
				for ( j = i; j > 0 && mark > (int) testdata.get( j - 1 ); j-- )
				{
					this.jp_center.setComa( i );
					this.jp_center.setComb( j - 1 );
					this.jp_center.paint_now();
					testdata.set( j, (int) testdata.get( j - 1 ) );
				}
				this.jp_center.setComa( i );
				this.jp_center.setComb( j );
				testdata.set( j, mark );
				this.jp_center.paint_now();
			}
		}
		this.jp_center.sorted_number = this.jp_center.testdata.size();
		this.jp_center.paint_now();
	}


	@SuppressWarnings( "unchecked" )
	public void ascend_bubble( List<Integer> testdata )
	{
		/* TODO Auto-generated method stub */
		for ( int i = 0; i < testdata.size() - 1; i++ )
		{
			/*
			 * 可以在原始的冒泡排序中加入标志位，当没有元素继续交换时退出程序，
			 * 从而减少排序的趟数
			 */
			Boolean flag = true;
			for ( int j = 0; j < testdata.size() - i - 1; j++ )
			{
				int	a	= (int) testdata.get( j );
				int	b	= (int) testdata.get( j + 1 );
				this.jp_center.setComa( j );
				this.jp_center.setComb( j + 1 );
				this.jp_center.paint_now();
				if ( a > b )
				{
					testdata.set( j, b );
					testdata.set( j + 1, a );
					flag = false;
					this.jp_center.paint_now();
				}
			}
			this.jp_center.sorted_number = i + 1;
			this.jp_center.paint_now();
			if ( flag == true )
				break;
		}
		this.jp_center.sorted_number = this.jp_center.testdata.size();
		this.jp_center.paint_now();
	}


	@SuppressWarnings( "unchecked" )
	public void descend_bubble( List<Integer> array )
	{
		for ( int i = 0; i < array.size() - 1; i++ )
		{
			/*
			 * 可以在原始的冒泡排序中加入标志位，当没有元素继续交换时退出程序，
			 * 从而减少排序的趟数
			 */
			Boolean flag = true;
			for ( int j = array.size() - 1; j > i; j-- )
			{
				int	a	= (int) array.get( j );
				int	b	= (int) array.get( j - 1 );
				this.jp_center.setComa( j );
				this.jp_center.setComb( j - 1 );
				this.jp_center.paint_now();
				if ( a > b )
				{
					array.set( j, b );
					array.set( j - 1, a );
					flag = false;
					this.jp_center.paint_now();
				}
			}
			this.jp_center.sorted_number = i + 1;
			this.jp_center.paint_now();
			if ( flag == true )
				break;
		}
		this.jp_center.sorted_number = this.jp_center.testdata.size();
		this.jp_center.paint_now();
	}


	@SuppressWarnings( "unchecked" )
	public void descend_select( List<Integer> array )
	{
		for ( int i = 0; i < array.size() - 1; i++ )
		{
			int	mark = i;
			int	tmp;
			for ( int j = i + 1; j < array.size(); j++ )
			{
				this.jp_center.setComa( mark );
				this.jp_center.setComb( j );
				this.jp_center.paint_now();
				if ( (int) array.get( mark ) < (int) array.get( j ) )
				{
					mark = j;
				}
			}
			if ( mark != i )
			{
				tmp = (int) array.get( i );
				array.set( i, (int) array.get( mark ) );
				array.set( mark, tmp );
				this.jp_center.paint_now();
			}
			this.jp_center.sorted_number = i + 1;
			this.jp_center.paint_now();
		}
		this.jp_center.sorted_number = this.jp_center.testdata.size();
		this.jp_center.paint_now();
	}


	@SuppressWarnings( "unchecked" )
	public void ascend_select( List<Integer> array )
	{
		for ( int i = 0; i < array.size() - 1; i++ )
		{
			int	mark = i;
			int	tmp;
			for ( int j = array.size() - 1; j > i; j-- )
			{
				this.jp_center.setComa( mark );
				this.jp_center.setComb( j );
				this.jp_center.paint_now();
				if ( (int) array.get( mark ) > (int) array.get( j ) )
				{
					mark = j;
				}
			}
			if ( mark != i )
			{
				tmp = (int) array.get( i );
				array.set( i, (int) array.get( mark ) );
				array.set( mark, tmp );
				this.jp_center.paint_now();
			}
			this.jp_center.sorted_number = i + 1;
			this.jp_center.paint_now();
		}
		this.jp_center.sorted_number = this.jp_center.testdata.size();
		this.jp_center.paint_now();
	}


	@SuppressWarnings( "unchecked" )
	public void descend_heap( List<Integer> array )
	{
		for ( int i = array.size() / 2 - 1; i >= 0; i-- )
			heapAdjust2( array, i, array.size() );
		for ( int j = array.size() - 1; j > 0; j-- )
		{
			swap( array, 0, j );
			heapAdjust2( array, 0, j );
		}
	}


	@SuppressWarnings( "unchecked" )
	private void heapAdjust2( List<Integer> arr, int s, int m )
	{
		int temp = (int) arr.get( s );                                          /* 先取出当前元素i */
		for ( int k = s * 2 + 1; k < m; k = k * 2 + 1 )                         /* 从i结点的左子结点开始，也就是2i+1处开始 */
		{
			this.jp_center.setComa( k );
			this.jp_center.setComb( k + 1 );
			this.jp_center.paint_now();
			if ( k + 1 < m && (int) arr.get( k ) > (int) arr.get( k + 1 ) ) /* 如果左子结点小于右子结点，k指向右子结点 */
			{
				k++;
			}
			this.jp_center.setComa( k );
			this.jp_center.setComb( s );
			this.jp_center.paint_now();
			if ( (int) arr.get( k ) < temp )                                /* 如果子节点大于父节点，将子节点值赋给父节点（不用进行交换） */
			{
				arr.set( s, (int) arr.get( k ) );
				s = k;
			} else {
				break;
			}
			this.jp_center.paint_now();
		}
		arr.set( s, temp ); /* 将temp值放到最终的位置 */
		this.jp_center.paint_now();
	}


	public static void swap( List<Integer> arr, int a, int b )
	{
		int temp = (int) arr.get( a );
		arr.set( a, (int) arr.get( b ) );
		arr.set( b, temp );
	}


	@SuppressWarnings( "unchecked" )
	public void ascend_heap( List<Integer> array )
	{
		for ( int i = array.size() / 2 - 1; i >= 0; i-- )
			heapAdjust( array, i, array.size() );
		for ( int j = array.size() - 1; j > 0; j-- )
		{
			swap( array, 0, j );
			heapAdjust( array, 0, j );
		}
	}


	@SuppressWarnings( "unchecked" )
	private void heapAdjust( List<Integer> arr, int s, int m )
	{
		int temp = (int) arr.get( s );                                          /* 先取出当前元素i */
		for ( int k = s * 2 + 1; k < m; k = k * 2 + 1 )                         /* 从i结点的左子结点开始，也就是2i+1处开始 */
		{
			this.jp_center.setComa( k );
			this.jp_center.setComb( k + 1 );
			this.jp_center.paint_now();
			if ( k + 1 < m && (int) arr.get( k ) < (int) arr.get( k + 1 ) ) /* 如果左子结点小于右子结点，k指向右子结点 */
			{
				k++;
			}
			this.jp_center.setComa( k );
			this.jp_center.setComb( s );
			this.jp_center.paint_now();
			if ( (int) arr.get( k ) > temp )                                /* 如果子节点大于父节点，将子节点值赋给父节点（不用进行交换） */
			{
				arr.set( s, (int) arr.get( k ) );
				s = k;
			} else {
				break;
			}
			this.jp_center.paint_now();
		}
		arr.set( s, temp ); /* 将temp值放到最终的位置 */
		this.jp_center.paint_now();
	}
}
