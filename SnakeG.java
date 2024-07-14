package Games;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SnakeG extends JPanel
{
 public SnakeG()
  {
	 int boardWidth=850;
	int boardHeight=575;
	
	setPreferredSize(new Dimension(boardWidth,boardHeight));
    JFrame f = new JFrame();
	f.setTitle("Snake Game");
	f.setBounds(0,0,905,700);
    f.setResizable(false);
    f.setVisible(true);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.setBackground(Color.DARK_GRAY);
	
	 Gameplay game = new Gameplay(boardWidth,boardHeight);
	f.add(game);
	game.requestFocus();  
	
 }
}