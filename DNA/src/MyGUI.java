import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import processing.core.PApplet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.CardLayout;
import java.awt.Component;


@SuppressWarnings("serial")
public class MyGUI extends JFrame implements ActionListener
{
	private ProccesingSketch sketch;
	
	private javax.swing.JPanel sketchPanel;
	private JPanel buttonPanel;
	private JPanel titlePanel;
	private JPanel mainPanel;
	
	private JLabel titleLabel;
	private JLabel sketchLabel;
	
	private JButton lineButton;
	private JButton mosaicButton;
	private JButton pieButton;
	private JButton otherButton;
	private JButton maxButton;
	private JButton	miniButton;
	private JButton exitButton;
	
	private JMenuBar menuBar;
	
	private JMenu fileMenu;
	
	private JMenuItem openItem;
	
	
	
	public MyGUI()
	{
		setUndecorated(true);
		setSize(1200,700);
		setPreferredSize(new Dimension(1200, 700));
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		
		//this.setTitle("Data Visualization");
		//this.setForeground(Color.WHITE);
		//this.setLayout(new BorderLayout());
		
		//-------------------------
		// PANELS
		//-------------------------
		
		buttonPanel = new JPanel();
		buttonPanel.setBackground(null);
		
		sketchPanel = new JPanel();
		sketchPanel.setBackground(null);
		
		titlePanel = new JPanel();
		titlePanel.setBackground(null);
		
		mainPanel = new JPanel();
		mainPanel.setSize(1200, 700);
		mainPanel.setBackground(new Color(45, 45, 48));
	    mainPanel.setBorder(BorderFactory.createMatteBorder(5,5,5,5, new Color(20, 20, 40, 200)));
		
		//-------------------------
		// LABELS
		//-------------------------
		
		titleLabel = new JLabel("Data Visulalization");
		titleLabel.setForeground(new Color(220,220,220));
		sketchLabel = new JLabel();
		
		//-------------------------
		// BUTTONS
		//-------------------------
		
		lineButton = new JButton("line");
		lineButton.addActionListener(this);
		lineButton.setPreferredSize(new Dimension(250,40));		
		mosaicButton = new JButton("mosaic");
		mosaicButton.setPreferredSize(new Dimension(250,40));
		pieButton = new JButton("Pie");
		pieButton.setPreferredSize(new Dimension(250,40));
		
		otherButton = new JButton("other");
		otherButton.setPreferredSize(new Dimension(250,40));
		
		maxButton = new JButton();
	    maxButton.setIcon(new ImageIcon("media/max-icon34.png"));
	    maxButton.setRolloverIcon(new ImageIcon("media/max-icon34H.png"));
	    maxButton.setBorder(null);
	    maxButton.setBorderPainted(false);
	    maxButton.setContentAreaFilled(false);
	    maxButton.setRolloverEnabled(true);
	    
	    
	    miniButton 		= new JButton();
	    miniButton.setIcon(new ImageIcon("media/min-icon34.png"));
	    miniButton.setRolloverIcon(new ImageIcon("media/min-icon34H.png"));
	    miniButton.setBorder(null);
	    miniButton.setBorderPainted(false);
	    miniButton.setContentAreaFilled(false);
	    miniButton.setRolloverEnabled(true);
	    
	    exitButton 		= new JButton();
	    exitButton.setIcon(new ImageIcon("media/exit-icon34.png"));
	    exitButton.setRolloverIcon(new ImageIcon("media/exit-icon34H.png"));
	    exitButton.setBorder(null);
	    exitButton.setBorderPainted(false);
	    exitButton.setContentAreaFilled(false);
	    exitButton.setRolloverEnabled(true);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		buttonPanel.add(mosaicButton);		
		buttonPanel.add(lineButton);
		buttonPanel.add(otherButton);
		buttonPanel.add(pieButton);
		/*GridBagConstraints a = new GridBagConstraints();
	    a.gridx 	= 0;
	    a.gridy 	= 0;
	    a.weightx 	= 1.0;
	    buttonPanel.add(mosaicButton, a);
	    GridBagConstraints a1 = new GridBagConstraints();
	    a.gridx 	= 1;
	    a.gridy 	= 0;
	    a.weightx 	= 1.0;
	    buttonPanel.add(lineButton, a1);
	    GridBagConstraints a2 = new GridBagConstraints();
	    a.gridx 	= 2;
	    a.gridy 	= 0;
	    a.weightx	= 1.0;
	    buttonPanel.add(pieButton, a2);
	    GridBagConstraints a3 = new GridBagConstraints();
	    a.gridx 	= 3;
	    a.gridy 	= 0;
	    a.weightx	= 1.0; */
	    //buttonPanel.add(otherButton, a3);
		
		//-------------------------
		//SKETCH
		//-------------------------
		
		sketch = new ProccesingSketch();
		sketchPanel.setSize(1200, 700);
		sketchPanel.setLayout(new BorderLayout(0, 0));
		sketchPanel.add(sketch);
		
		//-------------------------
		// FILE MENU
		//-------------------------
		
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		openItem = new JMenuItem("Open");
		
		menuBar.setOpaque(true);
	    menuBar.setBorder(null);
	    menuBar.setBackground(new Color(0,0,0,0));
		
	    fileMenu.setForeground(new Color(220,220,220,220));
	    
		
		menuBar.add(fileMenu);
		fileMenu.add(openItem);
		
		//--------------------------
		// TOP PANEL ADJUSTMENTS
		// adding the menu to top panel
		//--------------------------
		
		GridBagConstraints tc = new GridBagConstraints();
	    tc.gridx = 0;
	    tc.gridy = 0;
	    tc.weightx = 1.0;
	    titlePanel.add(menuBar);
	    tc.gridx = 1;
	    tc.gridy = 0;
	    tc.weightx = 1.0;
	    tc.insets = new Insets(5, 10, 0, 0);
	    titlePanel.add(titleLabel, tc);
	    GridBagConstraints tc1 = new GridBagConstraints();
	    tc.gridx = 2;
	    tc.gridy = 0;
	    tc.weightx = 0.0;
	    titlePanel.add(new JLabel(""), tc1);
	    GridBagConstraints tc2 = new GridBagConstraints();
	    tc.gridx = 3;
	    tc.gridy = 0;
	    tc.weightx = 0.0;
	    titlePanel.add(miniButton, tc2);
	    GridBagConstraints tc3 = new GridBagConstraints();
	    tc.gridx = 4;
	    tc.gridy = 0;
	    titlePanel.add(maxButton, tc3);
	    GridBagConstraints tc4 = new GridBagConstraints();
	    tc.gridx = 5;
	    tc.gridy = 0;
	    titlePanel.add(exitButton, tc4);
		
		//--------------------------
		// MOUSE LISTENERS
		//--------------------------
		
		mosaicButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {//Add methods for mouse events to trigger loading graphics
				sketch.init();
				sketchPanel.add(sketch);
				//sketchPanel.add(sketch);
				//sketchPanel.add(sketchPanel);
				//panel_1.setBackground(Color.BLUE);
				//sketchPanel.add(sketch);
				//ProccesingSketch.setup();
				//sketch.init();
			}
		});
		
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
			
		//Adding other things to Frame
		
		/*GridBagConstraints c = new GridBagConstraints();
	    c.gridx 	= 0;
	    c.gridy 	= 0;
	    c.weightx 	= 1.0;
	    c.fill  	= GridBagConstraints.BOTH;
	    mainPanel.add(titlePanel, c);
	    GridBagConstraints c1 = new GridBagConstraints();
	    c1.gridx 	= 0;
	    c1.gridy 	= 1;
	    c1.weightx 	= 1.0;
	    c1.fill  	= GridBagConstraints.BOTH;
	    mainPanel.add(sketchPanel, c1);
	    GridBagConstraints c2 = new GridBagConstraints();
	    c2.anchor = GridBagConstraints.SOUTH;
	    c2.gridx 	= 0;
	    c2.gridy 	= 2;
	    c2.weightx	= 1.0;
	    c2.weighty  = 2.0;
	    c2.fill  	= GridBagConstraints.HORIZONTAL;
	    */mainPanel.setLayout(new BorderLayout(0, 0));
mainPanel.add(buttonPanel, BorderLayout.SOUTH); 
	    mainPanel.add(titlePanel, BorderLayout.NORTH);
	    mainPanel.add(sketchPanel, BorderLayout.CENTER);
		//Adding Sketch to the Frame
		
		//this.add(sketchPanel);
		/*sketch.init();
		sketch.frame = this;*/
		
		this.setContentPane(mainPanel);
		pack();
		
	}



public static void main(String[] args)
{
	new MyGUI().setVisible(true);
	
}

public void actionPerformed(ActionEvent event)
{
	if(event.getSource()==lineButton)
	{
		//sketch.init();
		//sketchPanel.add(sketch);
	}
}
}