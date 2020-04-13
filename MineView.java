import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.*;
public class MineView {
	
	JButton resetButton;
	static JLabel timerLabel;
	JLabel numFlagsLeft;
	JPanel topestPanel;
	int mines=12;
	int flagsLeft=mines;
	JFrame frame;
	JPanel scrollPanel,topPanel, bigGridPanel;
	JMenuBar topBar;
	JMenu game;
	JMenu icons;
	JMenu controls;
	int numRows=9;
	int numCols=9;
	
	JToggleButton [][]array;
	//int [][]numbersArray;
	
	JMenuItem beginner;
	JMenuItem intermediate;
	JMenuItem expert;
	
	JMenuItem redFlagItem;
	JMenuItem orangeFlagItem;
	JMenuItem blueFlagItem;
	
	
	JMenuItem explanation;

	
	ImageIcon empty;
	ImageIcon flagged;
	ImageIcon orangeFlag;
	ImageIcon blueFlag;
	ImageIcon block;
	ImageIcon mine;
	ImageIcon one;
	ImageIcon two;
	ImageIcon three;
	ImageIcon four;
	ImageIcon five;
	ImageIcon six;
	ImageIcon seven;
	ImageIcon eight;
	
	ImageIcon flagIconChange;
	
	
	public MineView()
	{
		scrollPanel=new JPanel();
		scrollPanel.setLayout(new GridLayout(5,1));
		
		timerLabel=new JLabel("TIMER");
		numFlagsLeft=new JLabel("# Flags Left");
		topestPanel=new JPanel();
		
		topBar=new JMenuBar();
		game=new JMenu("GAME");
		icons=new JMenu("ICONS");
		controls=new JMenu("CONTROLS");
		
		redFlagItem=new JMenuItem("RED flag");
		redFlagItem.setForeground(Color.RED);
		//redFlagItem.addActionListener(this);
		
		orangeFlagItem=new JMenuItem("ORANGE flag");
		orangeFlagItem.setForeground(Color.ORANGE);
		//orangeFlagItem.addActionListener(this);
		
		blueFlagItem=new JMenuItem("BLUE flag");
		blueFlagItem.setForeground(Color.BLUE);
		//blueFlagItem.addActionListener(this);
		
		
		
		beginner=new JMenuItem("beginner");
		//beginner.addActionListener(this);
		intermediate=new JMenuItem("intermediate");
		//intermediate.addActionListener(this);
		expert=new JMenuItem("expert");
		//expert.addActionListener(this);
		
		explanation=new JMenuItem("Left-click an empty square to reveal it.\n" + 
				"Right-click (or Ctrl+click) an empty square to flag it.\n" + 
				"Midde-click (or left+right click) a number to reveal\n" + 
				"its adjacent squares.\n" + 
				"Press space bar while hovering over a square to flag\n" + 
				"it or reveal its adjacent squares.\n" + 
				"Press F2 to start a new game.");
		

		flagged=new ImageIcon(getClass().getResource("/resources/flagged.png"));
		flagged = new ImageIcon(
				flagged.getImage().getScaledInstance(
				40,
				40,
				Image.SCALE_SMOOTH));
		
		orangeFlag=new ImageIcon(getClass().getResource("/resources/orangeFlag.png"));
		orangeFlag = new ImageIcon(
				orangeFlag.getImage().getScaledInstance(
				40,
				40,
				Image.SCALE_SMOOTH));
		
		blueFlag=new ImageIcon(getClass().getResource("/resources/blueflag.png"));
		blueFlag = new ImageIcon(
				blueFlag.getImage().getScaledInstance(
				40,
				40,
				Image.SCALE_SMOOTH));
		
		flagIconChange=flagged;
		
		
		mine=new ImageIcon(getClass().getResource("/resources/mine.png"));
		mine = new ImageIcon(
				mine.getImage().getScaledInstance(
				40,
				40,
				Image.SCALE_SMOOTH));
		
	
		topBar.add(game);
		topBar.add(icons);
		topBar.add(controls);
		
		
		game.add(beginner);
		game.add(intermediate);
		game.add(expert);
		
		icons.add(redFlagItem);
		icons.add(orangeFlagItem);
		icons.add(blueFlagItem);
		
		controls.add(explanation);
		
		topestPanel.add(timerLabel);
		topestPanel.add(Box.createHorizontalStrut(30));
		topestPanel.add(topBar);
		topestPanel.add(Box.createHorizontalStrut(30));
		topestPanel.add(numFlagsLeft);
		
		//frame.add(this);
		//frame.add(topPanel,BorderLayout.NORTH);
		
		
		
		/////////setBoard();
		
	}
	public void setBoard(MineController mineControl)
	{
		///mineModel.gameStarted=false;
		frame=new JFrame("Minesweeper Program");
		if(bigGridPanel!=null)
			frame.remove(bigGridPanel);
		bigGridPanel=new JPanel();
		bigGridPanel.setLayout(new GridLayout(numRows,numCols));		
		array=new JToggleButton[numRows][numCols];
		//numbersArray=new int[numRows][numCols];
		
		for (int x=0;x<array.length;x++)
		{
			for (int y=0;y<array[x].length;y++)
			{
				array[x][y]=new JToggleButton();
				array[x][y].putClientProperty("row",x);
				array[x][y].putClientProperty("col", y);
				//array[x][y].putClientProperty("state", whichState);
				array[x][y].addMouseListener(mineControl);
				//array[x][y].setIcon(block);
				bigGridPanel.add(array[x][y]);
			}
		}
		//frame.add(topBar,BorderLayout.NORTH);
		frame.add(topestPanel,BorderLayout.NORTH);
		resetButton = new JButton();
		resetButton.setText("RESET");
		resetButton.addActionListener(mineControl);
		resetButton.addMouseListener(mineControl);
		frame.add(resetButton,BorderLayout.SOUTH);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(bigGridPanel,BorderLayout.CENTER);
		if (mines==12)
			frame.setSize(70*numCols, 70*numRows);
		else if (mines==42)
			frame.setSize(50*numCols, 50*numRows);
		else
			frame.setSize(40*numCols, 55*numRows);
		frame.revalidate();

	}
	public void methodForPlacingFlags(int xPro, int yPro)
	{
		flagsLeft-=1;
		if (flagsLeft>-1)
			updateFlags(flagsLeft);
		//array[xProp][yProp].setText("FLAG");
		array[xPro][yPro].setText("");
		if (flagsLeft>=0)
		{
			array[xPro][yPro].setIcon(flagIconChange);
		}
		else
		{
			flagsLeft++;
			System.out.println("YOU'RE OUT OF FLAGS");
		}
	}
	public void writeText(int r,int c, int state)
	{
		switch(state)
		{
			case 1:array[r][c].setForeground(Color.BLACK);
				break;
			case 2:array[r][c].setForeground(Color.GREEN);
				break;
			case 3:array[r][c].setForeground(Color.RED);
				break;
			case 4:array[r][c].setForeground(new Color(0,40,0));
				break;
			case 5:array[r][c].setForeground(new Color(0,0,120));
				break;
			case 6:array[r][c].setForeground(Color.CYAN);
				break;
			case 7:array[r][c].setForeground(Color.BLACK);
				break;
			case 8:array[r][c].setForeground(Color.BLACK);
				break;
			case 9:array[r][c].setIcon(mine);
					array[r][c].setText("");
				break;	
		}
		if (state!=9)
		{
			System.out.println("in WRITE TEXT"+r+","+c+","+state);
			array[r][c].setText(""+state);
		}
	}
	public boolean isMineAround(int r, int c, int[][]numbersArray)
	{
		for (int i=r-1;i<=r+1;i++)
		{
			for (int j=c-1;j<=c+1;j++)
			{
				try {
					//System.out.println(i+","+j+","+numbersArray[i][j]);
					if (numbersArray[i][j]==9)
					{
	
						//System.out.println("returning true from isminearound");
						return true;
						
					}
					
				}catch(IndexOutOfBoundsException e)
				{}
			}
		}
		return false;
	}
	public void expand(int r, int c, int[][] numbersArray)
	{
		if (array[r][c].isSelected())
			return;
		System.out.println(array[r][c].isSelected()+" "+r+","+c);
		
		boolean isMine = isMineAround(r,c, numbersArray);
		if (isMine==true)
		{
			writeText(r,c,numbersArray[r][c]);
			array[r][c].setSelected(true);
			array[r][c].setEnabled(false);   ///////////////////////////////////////

		}
		else
		{
			array[r][c].setSelected(true);
			for (int i=r-1;i<=r+1;i++)
			{
				for (int j=c-1;j<=c+1;j++)
				{
					
					try {
						
						if( i==r && j==c)
							continue;
						if (array[i][j].isSelected()==false )
						{
							
							expand(i,j, numbersArray);
						}
						
						
					}catch(IndexOutOfBoundsException e)
					{}
				}
			}
		}
	}
	public void updateFlags(int flagsLeftt)
	{
		numFlagsLeft.setText(flagsLeftt+" ");
	}
	public void allVisualFlagChangingStuff(int xPro, int yPro)
	{
		if (array[xPro][yPro].getIcon()==flagIconChange)
		{
			flagsLeft++;
			updateFlags(flagsLeft);
			array[xPro][yPro].setIcon(null);
		}
	}
	public static void updateTimer(int secondss)
	{
		timerLabel.setText(secondss+"");
	}
	public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
	
	public void disable0Buttons(int [][] numbersArray)
	{
		for (int x=0;x<numbersArray.length;x++)
		{
			for (int y=0;y<numbersArray[x].length;y++)
			{
				if (numbersArray[x][y]==0)
					array[x][y].setEnabled(false);
			}
		}
	}
	public void actionChangeToRed(int [][] numbersArray)
	{
		flagIconChange=flagged;
		changeTheScreenFlags(numbersArray);
	}
	public void actionChangeToOrange(int [][] numbersArray)
	{
		flagIconChange=orangeFlag;
		changeTheScreenFlags(numbersArray);
	}
	public void actionChangeToBlue(int [][] numbersArray)
	{
		flagIconChange=blueFlag;
		changeTheScreenFlags(numbersArray);
	}
	
	public void changeTheScreenFlags(int [][] numbersArray)
	{
		for (int a=0;a<numbersArray.length;a++)
		{
			for (int b=0;b<numbersArray[a].length;b++)
			{
				if (array[a][b].getIcon()==flagged || array[a][b].getIcon()==orangeFlag || array[a][b].getIcon()==blueFlag)
				{
					array[a][b].setIcon(flagIconChange);
				}
			}
		}
	}
	
	public static void main(String[]args)
	{
		
	}
	
}
