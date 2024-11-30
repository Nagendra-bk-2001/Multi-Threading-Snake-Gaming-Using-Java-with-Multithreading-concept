package com.Snake;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
	
	private final Grid grid;
	private  final List<Snake> snakes;
	private final List<Thread> snakeThreads;
	private volatile boolean isGameRunning; 
	
	public GameManager(int rows, int cols,int numSnakes)
	{
		this.grid = new Grid(rows,cols);
		this.snakes = new ArrayList<>();
		this.snakeThreads = new ArrayList<>();
		
		for(int i =0;i<numSnakes;i++)
		{
			int startRow = (int) (Math.random() * rows);
			int startCol = (int) (Math.random() * cols);
			
			while(!grid.isCellEmplty(startRow,startCol))
			{
				startRow = (int)(Math.random() * rows);
				startCol = (int)(Math.random() * cols);
			}
			 Snake snake = new Snake(i + 1 , startRow,startCol,grid);
			 snakes.add(snake);
		}
		
	}
	public void startGame()
	{
		isGameRunning = true;
		
		for(Snake snake : snakes)
		{
			Thread  snakeThread = new Thread(snake);
			snakeThreads.add(snakeThread);
			snakeThread.start();
			
			System.out.println(" Snake " + snake.getid() +  " Has  Started ");
//			snakes.add(snake);
//			new Thread().start();
			
			
		}
	}
	
	public void stopGame()
	{
		isGameRunning = false;
		
		for(Snake snake : snakes)
		{
			snake.terminate();
		}
		
		for(Thread thread : snakeThreads)
		{
			try {
				thread.join();
			}catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		System.out.println("Game  has been Stoped :).....:)!!! ");
	}
	
	public void printGrid()
	{
		grid.printGrid();
	}
}
