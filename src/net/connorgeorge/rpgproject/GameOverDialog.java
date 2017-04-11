package net.connorgeorge.rpgproject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOverDialog extends JDialog implements ActionListener
{
	public GameOverDialog(JFrame parent, String title, String message) {
		super(parent, title, true);
	    if (parent != null) {
	      Dimension parentSize = new Dimension(500, 500); 
	      Point p = parent.getLocation(); 
	      setLocation(400, 400);
	      setResizable(true);
	      setSize(parentSize);
	    }
	    JPanel messagePane = new JPanel();
	    messagePane.add(new JLabel(message));
	    getContentPane().add(messagePane);
	    JPanel buttonPane = new JPanel();
	    JButton button = new JButton("OK"); 
	    buttonPane.add(button); 
	    button.addActionListener(this);
	    getContentPane().add(buttonPane, BorderLayout.SOUTH);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    pack(); 
	    setVisible(true);
	  }
	  public void actionPerformed(ActionEvent e) {
	    setVisible(false); 
	    dispose(); 
	  }
}
