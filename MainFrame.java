
 import Games.Gameplay;
 import Games.SnakeG;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame 
{
	public static void main(String args[])
	{
	JFrame f2 =new JFrame();
	    f2.setTitle("First frame");
		f2.setSize(850,575);
		f2.setBackground(Color.BLACK);
		
		JButton b2 = new JButton("Start");
		b2.setBounds(200,200,100,40);
		f2.add(b2);
		b2.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new SnakeG();
			}
		}
		);
		
		f2.setVisible(true);
	}
}
