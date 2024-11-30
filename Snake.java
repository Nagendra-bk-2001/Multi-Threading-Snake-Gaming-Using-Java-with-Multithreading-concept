package com.Snake;

import java.util.ArrayList;
import java.util.List;

public class Snake implements Runnable{
	private final int id;
	private final Grid grid;
	private final List<int[]> body;
	private volatile boolean isAlive;
	private char direction;
	

	public Snake(int id, int startRow, int startCol,Grid grid) {
		this.id = id;
		this.grid = grid;
		this.body = new ArrayList<>();
		this.body.add(new int[] {startRow,startCol});
		this.isAlive = true;
		this.direction = getRandomDirection();	
		
		grid.moveSnake(startRow,startCol,startRow,startCol);//initial position
	}
	
	public int getid()
	{
		return id;
	}
	
	public void terminate() {
		isAlive = false;
		
	}
	
//	@Override
	public void run()
	{
		while(isAlive)
		{
			move();
		}
		try {
			Thread.sleep(500);
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	private void move()
	{
		if(!isAlive)
		{
			return;
		}
		
		int[] head = body.get(0);
		int newRow = head[0];
		int newCol = head[1];
		
		switch(direction)
		{
		case 'U' : newRow--;//up
				break;
		case 'D' : newRow++;//Down
				break;
		case 'L' : newCol--;//left
				break;
		case 'R' : newCol++;//right
		}
		
		if(!grid.isCellEmplty(newRow,newCol))
		{
			System.out.println(" Snake " + id + " collieded stoping Thread. ");
			terminate();
			return;
		}
		
		boolean success = grid.moveSnake(head[0], head[1], newRow, newCol);//Add new Position
		if(success)
			{
				body.add(0,new int[] {newRow,newCol});
				int[] tail = body.remove(body.size()-1);//remove tail
				grid.removeSnake(tail[0], tail[1]);//clear old tail position
			}
		if(Math.random()< 0.2);
		{
			direction = getRandomDirection();
		}
		
		System.out.println(" Snake " + id + " Movedd to  (" + newRow + " , " + newCol + ").");
	}
	
	private char getRandomDirection()
	{
		char[] directions = {'U' , 'D' , 'L' , 'R' };
		return directions [(int) (Math.random() * 4)];
	}

}
