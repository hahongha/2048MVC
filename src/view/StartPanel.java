package view;

import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartPanel extends JFrame {
    public StartPanel() {
        // Create the first panel
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.RED);

        // Create the second panel
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.BLUE);

        // Use a BorderLayout to arrange the panels
        setLayout(new BorderLayout());
        add(panel1, BorderLayout.CENTER);

        // Add a KeyListener to panel2
        panel2.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
            	 if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            	remove(panel2);
                add(panel1, BorderLayout.CENTER);
                panel2.requestFocusInWindow();
                validate();
            	 }
            }
            public void keyReleased(KeyEvent e) {}
            public void keyTyped(KeyEvent e) {}
        });
        JButton next = new JButton("next");
        next.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		if(e.getClickCount()==1) {
        			setTitle("1");
        		}
        	}
        	public  void mouseDragged(MouseEvent e) {
        		
        	}
        	public  void mouseEntered(MouseEvent e) {
        		
        	}

        	public void mouseExited(MouseEvent e) {
        		
        	}

        	public void mouseMoved(MouseEvent e) {
        		
        	}

        	public void mousePressed(MouseEvent e) {
        		
        	}

        	public  void mouseReleased(MouseEvent e) {
        		
        	}
		});
        panel2.add(next);

        // Switch to the second panel when the spacebar is pressed
        panel1.setFocusable(true);
        panel1.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    remove(panel1);
                    add(panel2, BorderLayout.CENTER);
                    panel2.requestFocusInWindow();
                    validate();
                }
            }
        });

        // Set the JFrame properties
        setTitle("Panel Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new StartPanel();
    }
}

