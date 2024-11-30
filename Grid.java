package com.Snake;

import java.util.concurrent.locks.ReentrantLock;

public class Grid
{
	private final int rows ;
	private final int cols;
	private final char[][] grid;
	private final ReentrantLock[][] locks;
	//constructor using a locks
	public Grid(int rows,int cols)
	{
		this.rows = rows;
		this.cols = cols;
		this.grid = new char[rows][cols];
		this.locks = new ReentrantLock[rows][cols];
	//initialize thee grid and locks
		for(int i =0;i<rows;i++)
		{
			for(int j =0;j<cols;j++)
			{
				grid[i][j] = '.';
				locks[i][j] = new ReentrantLock();	
			}
		}
	}

		public boolean isCellEmplty(int row, int col) {
			if(!isValidCell(row,col))
			{
				return false;
			}
			
			locks[row][col].lock();
			
			try
			{
				return grid[row][col] == '.';
			}finally
			{
				locks[row][col].unlock();
			}
		
		}
		
		public boolean moveSnake(int oldRow,int oldCol,int newRow , int newCol)
		{
			if(!isValidCell(oldRow,oldCol))
			{
				return false;
			}
			
			locks[oldRow][oldCol].lock();
			locks[newRow][newCol].lock();
			
			try
			{
				if(grid[newRow][newCol] == '.')
				{
					grid[oldRow][oldCol] = '.';
					grid[newRow][newCol] = 's';
					return true;
				}
				return false;
			}finally
			{
				locks[newRow][newCol].unlock();
				locks[oldRow][oldCol].unlock();
			}
		}
		
		public void removeSnake(int row,int col)
		{
			if(!isValidCell(row,col))
			{
				return;
			}
			locks[row][col].lock();
			
			try
			{
				grid[row][col]='.';
			}finally
			{
				locks[row][col].unlock();
			}
		}

		public void printGrid() {
			
			 System.out.print("\033[H\033[2J");
			 System.out.flush();
			System.out.println("Current Grid : 	");
			for(int i =0;i<rows ; i++)
			{
				for(int j =0;j<cols;j++)
				{
					System.out.print(grid[i][j] + " " );
				}
				System.out.println();
			}
			System.out.println();
		}
		
		private boolean isValidCell(int row,int col)
		{
			return row >= 0 && row < this.rows && col >= 0 && col < this.cols ;
		}
	
	

}	
