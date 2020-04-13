import java.util.Timer;
import java.util.TimerTask;

public class MineModel {
	int mines=12;
	int flagsLeft=mines;
	int numRows=9;
	int numCols=9;
	boolean mineSet=false;
	
	
	Timer timer;
	TimerTask task;
    static int seconds = 0;
	
	int [][] numbersArray;
	
	public MineModel()
	{
		
	}
	
	public static void main (String[]args)
	{
		
	}
	
	
	
	public int[][] populateGrid(int rowClicked, int colClicked)
	{
		numbersArray=new int[numRows][numCols];
		for (int x=0;x<mines;x++)
		{
			int random1=(int)(Math.random()*(numRows-1))+0;
			int random2=(int)(Math.random()*(numCols-1))+0;
			
			
			if (Math.abs(rowClicked-random1)<=1 && Math.abs(colClicked-random2)<=1)
			{
				x--;
				//continue;
			}
			
			else
				numbersArray[random1][random2]=9;
		}
			
	
		
		
		for (int x=0;x<numbersArray.length;x++)
		{
			for (int y=0;y<numbersArray[x].length;y++)
			{
				int counterForPopulateGrid=0;
				if (numbersArray[x][y]==9)
				{
					continue;
				}
				else
				{
					if (x-1>=0 && numbersArray[x-1][y]==9)
					{
						counterForPopulateGrid++;
					}
					if (x+1<numbersArray.length-1 && numbersArray[x+1][y]==9)
					{
						counterForPopulateGrid++;
					}
					if (y-1>=0 && numbersArray[x][y-1]==9)
					{
						counterForPopulateGrid++;
					}
					if (y+1<numbersArray[0].length-1 && numbersArray[x][y+1]==9)
					{
						counterForPopulateGrid++;
					}
					if (y+1<numbersArray[0].length-1 && x+1<numbersArray.length-1 && numbersArray[x+1][y+1]==9)
					{
						counterForPopulateGrid++;
					}
					if (y-1>=0 && x-1>=0 && numbersArray[x-1][y-1]==9)
					{
						counterForPopulateGrid++;
					}
					if (x<numbersArray.length-1 && y-1>=0 && numbersArray[x+1][y-1]==9)
					{
						counterForPopulateGrid++;
					}
					if (x-1>=0 && y+1<numbersArray[0].length-1 && numbersArray[x-1][y+1]==9)
					{
						counterForPopulateGrid++;
					}
				}
					
					numbersArray[x][y]=counterForPopulateGrid;
					
					
						
						
				}
			}
		
		for (int x=0;x<numbersArray.length;x++)
		{
			for (int y=0;y<numbersArray[x].length;y++)
			{
				System.out.print(numbersArray[x][y]+", ");
				
			}
			System.out.println();
		}
		
		return numbersArray;
	}
}
