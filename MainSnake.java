package com.Snake;

public class MainSnake {
	public static void main(String[] args)
	{
		int gridRows = 10;
		int gridCols = 10;
		int newSnakes = 3;
		
		GameManager gameManager = new GameManager(gridRows,gridCols,newSnakes);
		
		
		System.out.println("Strining the Multi-Treading Using Snake Game ........ ");
		
		gameManager.startGame();
		
		try
		{
			Thread.sleep(20000);
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Stoping the Game.... !!! ");
		gameManager.stopGame();
		
		System.out.println("Final grid state : ");
		gameManager.printGrid();
		
	}

}
