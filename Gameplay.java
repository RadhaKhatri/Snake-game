import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;
 
public class Gameplay extends JPanel implements ActionListener,KeyListener
{
	
	public class Tile
	{
	  int x;
	  int y;

	  Tile(int x, int y)
	  {
		  this.x=x;
		  this.y=y;
	  }
	  
	}
	int boardWidth;
	int boardHeight;
	int tileSize = 25;

	//Snake
	Tile snakeHead;
	ArrayList<Tile>snakeBody;
	
	//food
	Tile Food;
	
	Random random;
	
	//game Logic
	Timer gameLoop;
	int velocityX;
	int velocityY;
	boolean gameOver = false;
	
  public Gameplay(int boardWidth,int boardHeight)
  {
    this.boardWidth = boardWidth;
	this.boardHeight = boardHeight;
	setPreferredSize(new Dimension(this.boardWidth,this.boardHeight));
	addKeyListener(this);
	setFocusable(true);
	
	snakeHead = new Tile(10,10);
	snakeBody = new ArrayList<Tile>();
	
	Food = new Tile(20,20);
	random = new Random();
	placeFood();
	velocityX = 0;
    velocityY = 0;	
	
	gameLoop = new Timer(100, this);
	gameLoop.start();
  }
  public void paintComponent(Graphics g)
  {
	  super.paintComponent(g);	
	
	 //border of title gameplay
	g.setColor(Color.BLACK);
	g.fillRect(0,0,905,700);
	
	//snake
	  g.setColor(Color.green);
	 g.fill3DRect(snakeHead.x* tileSize,snakeHead.y* tileSize,tileSize,tileSize,true);
	   
	  //snake body
	  for(int i=0;i<snakeBody.size();i++)
	  {
		  Tile snakePart = snakeBody.get(i);
		  g.fill3DRect(snakePart.x*tileSize,snakePart.y*tileSize,tileSize,tileSize,true);
	  }
	 
	  //food
	  g.setColor(Color.red);
	  g.fill3DRect(Food.x* tileSize,Food.y* tileSize,tileSize,tileSize,true);
	  
	  //score
	  g.setFont(new Font("Arial",Font.PLAIN,16));
	  if(gameOver)
	  {
		  g.setColor(Color.red);
		  g.drawString("Game Over:"+String.valueOf(snakeBody.size()),770,40);
	  }
	  else
	  {
		  g.setColor(Color.green);
		  g.drawString("Score:"+String.valueOf(snakeBody.size()),780,40);
	  }
	 
  }
  public void placeFood()
  {
	 Food.x = random.nextInt(boardWidth/tileSize);
	 Food.y = random.nextInt(boardHeight/tileSize);   
  }
  public void move()
  {
	  if (collision(snakeHead,Food))
	  {
		  snakeBody.add(new Tile(Food.x,Food.y));
		  placeFood();
	  }
	  //snake body
	  for(int i = snakeBody.size()-1; i>=0;i--)
	  {
		  Tile snakePart= snakeBody.get(i);
		  if(i==0)
		  {
			  snakePart.x = snakeHead.x;
			  snakePart.y = snakeHead.y;
		  }
		  else
		  {
			  Tile prevSnakePart = snakeBody.get(i-1);
			  snakePart.x = prevSnakePart.x;
			  snakePart.y = prevSnakePart.y;
		  }
		 
	  }
	
	  //snake head
	 
  	  snakeHead.x += velocityX; 
	  snakeHead.y += velocityY;
	 
	 
	  //game Over cndition
	   for(int i=0; i<snakeBody.size();i++)
	   {
		   Tile snakePart = snakeBody.get(i);
		   
		   //collide with the snake head 
		   if (collision(snakeHead,snakePart))
		   {
			   gameOver=true;
		   }
	   }
			 
		if(snakeHead.x*tileSize<0 || snakeHead.x*tileSize>boardWidth||
		snakeHead.y*tileSize<0 || snakeHead.y*tileSize>boardHeight)
		{
			gameOver = true;
		}		
	
  }
  public boolean collision(Tile tile1,Tile tile2)
  {
	  return tile1.x == tile2.x && tile1.y == tile2.y;
  }
  
  @Override
  public void actionPerformed(ActionEvent e)
  {
	  move();
	  repaint();
	  if(gameOver)
	  {
		  gameLoop.stop();
	  }
	  	  
  }
  @Override
  public void keyPressed(KeyEvent e)
  {
	  if(e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1)
	  {	  
		  velocityX = 0;
		  velocityY = -1;
	  }
	  else if(e.getKeyCode() == KeyEvent.VK_DOWN && velocityY!= -1)
	  {
		  velocityX = 0;
		  velocityY = 1;
	  }
	  else if(e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1)
	  {
		  velocityX = -1;
		  velocityY = 0;
	  }
	  else if(e.getKeyCode()== KeyEvent.VK_RIGHT && velocityX != -1)
	  {
		  velocityX = 1;
		  velocityY = 0;
	  }
  }
  @Override
  public void keyTyped(KeyEvent e)
  {
  }
  @Override
  public void keyReleased(KeyEvent e)
  {  
  }
    
  }

