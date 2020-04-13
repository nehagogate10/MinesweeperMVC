import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JToggleButton;

public class MineController implements MouseListener,ActionListener{
	MineView mineView=new MineView();
	MineModel mineModel=new MineModel();
	Timer timer;
	TimerTask task;
    static int seconds = 0;
    boolean isGameOver=true;
    boolean gameStarted=false;
    boolean isEverythingCorrect=false;
	public MineController()
	{
		
		mineView.redFlagItem.addActionListener(this);
		mineView.orangeFlagItem.addActionListener(this);
		mineView.blueFlagItem.addActionListener(this);
		
		mineView.beginner.addActionListener(this);
		mineView.intermediate.addActionListener(this);
		mineView.expert.addActionListener(this);
		
		mineView.setBoard(this);
		gameStarted=false;
	}
	public static void main (String []args)
	{
		MineController app = new MineController();
	}
	public void checkingIfEverythingIsCorrect()
	{
		isEverythingCorrect=true;
		for (int a=0;a<mineModel.numbersArray.length;a++)
		{
			for (int b=0;b<mineModel.numbersArray[a].length;b++)
			{
				System.out.println("mouseclicked :" + mineView.array[a][b].getText()+","+mineModel.numbersArray[a][b]);
				if (mineModel.numbersArray[a][b]==9 && mineView.array[a][b].getIcon()!=mineView.flagged)
				{
					isEverythingCorrect=false;
					break;
				}
			
			}
		}
		if (isEverythingCorrect==true) 
		{
			mineView.infoBox("WINNER", "YOU WIN !!!!");
			isGameOver=true;
		}
	}
	 public void MyTimer() 
	 {
		 System.out.println("creating a timer");
		 	timer = new Timer();
	        task = new TimerTask() {
	            private final int MAX_SECONDS = 3000;

	            @Override
	            public void run() { 
	            
	                if (seconds < MAX_SECONDS) {
	                    System.out.println("Seconds = " + seconds);
	                    seconds++;
	                    updateTimerController(seconds);
	                } else {
	                    // stop the timer
	                	mineView.infoBox("GAME OVER, TIME RAN OUT!", "GAME OVER");
	                    cancel();
	                }
	            }
	            
	        };
	         timer.schedule(task, 0, 1000);
	       

	   }
	 public static void updateTimerController(int secondss)
		{
			MineView.updateTimer(secondss);
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==mineView.redFlagItem)
		{
			//in MineView 
			mineView.actionChangeToRed(mineModel.numbersArray);
			
		
		}
		if (e.getSource()==mineView.orangeFlagItem)
		{
			mineView.actionChangeToOrange(mineModel.numbersArray);
		
		}
		if (e.getSource()==mineView.blueFlagItem)
		{
			mineView.actionChangeToBlue(mineModel.numbersArray);
		
		}
		if (e.getSource()==mineView.resetButton)
		{
			System.out.println("reset is working");
			mineModel.mineSet=false;
			mineView.frame.dispose();
			gameStarted=false;
			if (mineModel.timer!=null)
				mineModel.timer.cancel();
			mineModel.flagsLeft=mineModel.mines;
			//timer.purge();
			mineModel.seconds=0;
			
			mineView.setBoard(this);
			
		}
		if (e.getSource()==mineView.beginner)
		{
			mineModel.mines=12;
			mineModel.flagsLeft=mineModel.mines;
			mineModel.mineSet=false;
			mineModel.numRows=9;
			mineModel.numCols=9;
			mineView.frame.dispose();
			gameStarted=false;
			if (mineModel.timer!=null)
				mineModel.timer.cancel();
			
			//timer.purge();
			mineModel.seconds=0;
			mineView.setBoard(this);
		}
		else if (e.getSource()==mineView.intermediate)
		{
			mineModel.mines=42;
			mineModel.flagsLeft=mineModel.mines;
			mineModel.mineSet=false;
			mineModel.numRows=16;
			mineModel.numCols=16;
			mineView.frame.dispose();
			gameStarted=false;
			if (mineModel.timer!=null)
				mineModel.timer.cancel();
			
			//timer.purge();
			mineModel.seconds=0;
			mineView.setBoard(this);
		
		}
		else if (e.getSource()==mineView.expert)
		{
			mineModel.mines=78;
			mineModel.flagsLeft=mineModel.mines;
			mineModel.mineSet=false;
			mineModel.numRows=16;
			mineModel.numCols=30;
			mineView.frame.dispose();
			gameStarted=false;
			if (mineModel.timer!=null)
				mineModel.timer.cancel();
			
			//timer.purge();
			mineModel.seconds=0;
			mineView.setBoard(this);
			
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("In clicked");
		if (e.getButton()==MouseEvent.BUTTON3)
		{
			return;
		}
	
		
		JToggleButton source = (JToggleButton)e.getSource();
		int xProp = (int)source.getClientProperty("row");
		int yProp = (int)source.getClientProperty("col");
		
		mineView.allVisualFlagChangingStuff(xProp, yProp);
		
		
		if (mineView.array[xProp][yProp].isEnabled()==false)
			return;
		if (e.getButton()== MouseEvent.BUTTON1)
		{
			System.out.print("IS THIS NORMAL CLICK??");
		}
		
		
		// TODO Auto-generated method stub
		if (gameStarted==false)
		{
			MyTimer();
			gameStarted=true;
			isGameOver=false;
			
			int [][]numArr= mineModel.populateGrid(xProp,yProp);
			mineView.disable0Buttons(mineModel.numbersArray);
			mineView.array[xProp][yProp].setSelected(false);
			mineView.expand(xProp,yProp,mineModel.numbersArray);
		}
		else
		{
			if (mineModel.numbersArray[xProp][yProp]==9)
			{
				for (int x=0;x<mineModel.numbersArray.length;x++)
				{
					for (int y=0;y<mineModel.numbersArray[x].length;y++)
					{
						if(mineModel.numbersArray[x][y]==9)
						{
							mineView.array[x][y].setText("");
							mineView.array[x][y].setIcon(mineView.mine);
							
						}
						
					}
				}
				////// DISPLAY GAME OVER MESSAGE
				mineView.infoBox("GAME OVER, YOU LOSE", "GAME OVER");
				isGameOver=true;
				
			}
			else if (mineModel.numbersArray[xProp][yProp]==0)
			{
				//array[xProp][yProp].setSelected(true);
				mineView.expand(xProp,yProp, mineModel.numbersArray);
			}
			else
			{
				mineView.array[xProp][yProp].setText(""+mineModel.numbersArray[xProp][yProp]);
			}
			
			checkingIfEverythingIsCorrect();
			
			
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
		JToggleButton source = (JToggleButton)e.getSource();
		int xProp = (int)source.getClientProperty("row");
		int yProp = (int)source.getClientProperty("col");
		
		if (mineView.array[xProp][yProp].isEnabled()==false)
			return;
		
		if (e.getButton()==MouseEvent.BUTTON3) //// FLAGS
		{
			System.out.println("RIGHT CLICK ACTUALLY WORKS ");
			
			mineView.methodForPlacingFlags(xProp,yProp);
			
			
			
			checkingIfEverythingIsCorrect();
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
