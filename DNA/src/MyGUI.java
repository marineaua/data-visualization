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
import java.awt.Font;


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
	private JButton barButton;
	private JButton maxButton;
	private JButton	miniButton;
	private JButton exitButton;
	
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmNewRip;
	private JMenuItem mntmLoadRip;
	private JMenu mnFilter;
	private JMenuItem mntmAddFilter;
	private JMenuItem mntmClearFilter;
	private JMenu mnHelp;
	private JMenuItem mntmAbout;
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	//private Form1 form1;
	
	
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
		sketchLabel = new JLabel();
		
		//-------------------------
		// BUTTONS
		//-------------------------
		
		lineButton = new JButton("Line");
		lineButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lineButton.addActionListener(this);
		lineButton.setPreferredSize(new Dimension(250,40));		
		mosaicButton = new JButton("Mosaic");
		mosaicButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		mosaicButton.setPreferredSize(new Dimension(250,40));
		pieButton = new JButton("Pie");
		pieButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pieButton.setPreferredSize(new Dimension(250,40));
		
		barButton = new JButton("Bar");
		barButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		barButton.setPreferredSize(new Dimension(250,40));
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		buttonPanel.add(mosaicButton);		
		buttonPanel.add(lineButton);
		buttonPanel.add(barButton);
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
	    GridBagLayout gbl_titlePanel = new GridBagLayout();
	    gbl_titlePanel.columnWidths = new int[]{412, 62, 542, 0, 0, 26, 27, -104, 0};
	    gbl_titlePanel.rowHeights = new int[]{12, 12, 0};
	    gbl_titlePanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	    gbl_titlePanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
	    titlePanel.setLayout(gbl_titlePanel);
	    
	    //-------------------------
	    // FILE MENU
	    //-------------------------
	    
	    menuBar = new JMenuBar();
	    
	    menuBar.setOpaque(true);
	    menuBar.setBorder(null);
	    menuBar.setBackground(new Color(0,0,0,0));
		GridBagConstraints gbc_menuBar = new GridBagConstraints();
		gbc_menuBar.fill = GridBagConstraints.BOTH;
		gbc_menuBar.insets = new Insets(0, 0, 0, 5);
		gbc_menuBar.gridheight = 2;
		gbc_menuBar.gridx = 0;
		gbc_menuBar.gridy = 0;
		titlePanel.add(menuBar, gbc_menuBar);
	    
	    mnFile = new JMenu("File");
	    mnFile.setFont(new Font("Segoe UI", Font.PLAIN, 17));
	    menuBar.add(mnFile);
	    mnFile.setHorizontalAlignment(SwingConstants.LEFT);
	    mnFile.setForeground(Color.WHITE);
	    
	    mntmNewRip = new JMenuItem("New Rip");
	    mntmNewRip.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {//same as LOAD
	    		JFrame ripf = new JFrame();
	    		JPanel ripp = new JPanel();
	    		ripf.add(ripp);
	    		ripp.setPreferredSize(new Dimension(300,300));
	    		ripf.pack();
	    		ripf.setVisible(true);
	    	}
	    });
	    mntmNewRip.setFont(new Font("Segoe UI", Font.PLAIN, 17));
	    mnFile.add(mntmNewRip);
	    
	    mntmLoadRip = new JMenuItem("Load Rip");
	    mntmLoadRip.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) { //trying to make a new window that takes a path to a file to load
	    		JFrame loadf = new JFrame();
	    		JPanel loadp = new JPanel();
	    		JTextField loadtf = new JTextField("Path",20);
	    		
	    		loadtf.setBackground(Color.WHITE);
	    		loadp.setBackground(Color.BLACK);
	    		loadf.add(loadp);
	    		loadp.add(loadtf);
	    		loadp.setPreferredSize(new Dimension(300,300));	    		
	    		loadf.pack();
	    		loadf.setVisible(true);	    		
	    		
	    	}
	    });
	    mntmLoadRip.setFont(new Font("Segoe UI", Font.PLAIN, 17));
	    mnFile.add(mntmLoadRip);
	    
	    horizontalStrut = Box.createHorizontalStrut(20);
	    menuBar.add(horizontalStrut);
	    
	    mnFilter = new JMenu("Filter");
	    mnFilter.setFont(new Font("Segoe UI", Font.PLAIN, 17));
	    menuBar.add(mnFilter);
	    mnFilter.setForeground(Color.WHITE);
	    
	    mntmAddFilter = new JMenuItem("Add Filter");
	    mntmAddFilter.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) { // Same as others
	    		JFrame addf = new JFrame();
	    		JPanel addp = new JPanel();
	    		addf.add(addp);
	    		addp.setPreferredSize(new Dimension(300,300));
	    		
	    		JRadioButton name1 = new JRadioButton("name1");
	            name1.setSelected(true);	     
	            JRadioButton name2 = new JRadioButton("name2");
	            //name2.setActionCommand(name2);	    		
	            JRadioButton name3 = new JRadioButton("name3");
	            //name2.setActionCommand(name2);
	            JRadioButton name4 = new JRadioButton("name4");
	            //name2.setActionCommand(name2);
	            JRadioButton name5 = new JRadioButton("name5");
	            //name2.setActionCommand(name2);
	            JRadioButton name6 = new JRadioButton("name6");
	            //name2.setActionCommand(name2);
	            JRadioButton name7 = new JRadioButton("name7");
	            //name2.setActionCommand(name2);
	            JRadioButton name8 = new JRadioButton("name8");
	            //name2.setActionCommand(name2);
	    		
	         /*   ButtonGroup group = new ButtonGroup();
	            group.add(name1);
	            group.add(name2);
	            group.add(name3);
	            group.add(name4);
	            group.add(name5);
	            group.add(name6);
	            group.add(name7);
	            group.add(name8); */
	            
	            addp.add(name1);
	            addp.add(name2);
	            addp.add(name3);
	            addp.add(name4);
	            addp.add(name5);
	            addp.add(name6);
	            addp.add(name7);
	            addp.add(name8);
	            
	            JPanel radioPanel = new JPanel(new GridLayout(0, 1));
	            radioPanel.add(name1);
	            radioPanel.add(name2);
	            radioPanel.add(name3);
	            radioPanel.add(name4);
	            radioPanel.add(name5);
	            radioPanel.add(name6);
	            radioPanel.add(name7);
	            radioPanel.add(name8);
	     
	            addp.add(radioPanel, BorderLayout.LINE_START);
	           // addp.add(radioPanel);
	    		addf.pack();
	    		addf.setVisible(true);	    		
	    		
	    	}
	    });
	    mntmAddFilter.setFont(new Font("Segoe UI", Font.PLAIN, 17));
	    mnFilter.add(mntmAddFilter);
	    
	    mntmClearFilter = new JMenuItem("Clear Filter");
	    mntmClearFilter.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) { //same as others, not sure what this does, so i left blank
	    	}
	    });
	    mntmClearFilter.setFont(new Font("Segoe UI", Font.PLAIN, 17));
	    mnFilter.add(mntmClearFilter);
	    
	    horizontalStrut_1 = Box.createHorizontalStrut(20);
	    menuBar.add(horizontalStrut_1);
	    
	    mnHelp = new JMenu("Help");
	    mnHelp.setFont(new Font("Segoe UI", Font.PLAIN, 17));
	    menuBar.add(mnHelp);
	    mnHelp.setForeground(Color.WHITE);
	    
	    mntmAbout = new JMenuItem("About");
	    mntmAbout.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent arg0) { //Add event for clicking
	    		JFrame about = new JFrame();
	    		JPanel aboutp = new JPanel();	    		
	    		JLabel aboutmes = new JLabel("About this, what it does, the end");
	    		about.add(aboutp);
	    		aboutp.add(aboutmes);
	    		aboutp.setPreferredSize(new Dimension(300,300));
	    		about.pack();
	    		about.setVisible(true);
	    		
	    	
	    	}
	    });
	    mntmAbout.setFont(new Font("Segoe UI", Font.PLAIN, 17));
	    mnHelp.add(mntmAbout);
	    
	    //-------------------------
	    // LABELS
	    //-------------------------
	    
	    titleLabel = new JLabel("Data Visulalization");
	    titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    titleLabel.setForeground(new Color(220,220,220));
	    
	    //--------------------------
	    // TOP PANEL ADJUSTMENTS
	    // adding the menu to top panel
	    //--------------------------
	    
	    GridBagConstraints tc = new GridBagConstraints();
	    tc.anchor = GridBagConstraints.WEST;
	    tc.gridheight = 2;
	    tc.gridx = 5;
	    tc.gridy = 0;
	    tc.weightx = 1.0;
	    tc.gridx = 1;
	    tc.gridy = 0;
	    tc.weightx = 1.0;
	    tc.insets = new Insets(0, 0, 0, 5);
	    GridBagConstraints gbc_titleLabel = new GridBagConstraints();
	    gbc_titleLabel.anchor = GridBagConstraints.WEST;
	    gbc_titleLabel.gridheight = 2;
	    gbc_titleLabel.fill = GridBagConstraints.VERTICAL;
	    gbc_titleLabel.gridx = 2;
	    gbc_titleLabel.gridy = 0;
	    gbc_titleLabel.insets = new Insets(0, 0, 0, 5);
	    titlePanel.add(titleLabel, gbc_titleLabel);
	    tc.gridx = 2;
	    tc.gridy = 0;
	    tc.weightx = 0.0;
	    tc.gridx = 3;
	    tc.gridy = 0;
	    tc.weightx = 0.0;
	    tc.gridx = 4;
	    tc.gridy = 0;
	    tc.gridx = 5;
	    tc.gridy = 0;
	    
	    
	    miniButton 		= new JButton();
	    miniButton.setHorizontalAlignment(SwingConstants.RIGHT);
	    miniButton.setIcon(new ImageIcon("media/min-icon34.png"));
	    miniButton.setRolloverIcon(new ImageIcon("media/min-icon34H.png"));
	    miniButton.setBorder(null);
	    miniButton.setBorderPainted(false);
	    miniButton.setContentAreaFilled(false);
	    miniButton.setRolloverEnabled(true);
	    GridBagConstraints tc2 = new GridBagConstraints();
	    tc2.gridheight = 2;
	    tc2.anchor = GridBagConstraints.SOUTHWEST;
	    tc2.insets = new Insets(0, 0, 5, 5);
	    tc2.gridx = 7;
	    tc2.gridy = 0;
	    GridBagConstraints gbc_miniButton = new GridBagConstraints();
	    gbc_miniButton.anchor = GridBagConstraints.EAST;
	    gbc_miniButton.gridx = 5;
	    gbc_miniButton.insets = new Insets(0, 0, 0, 5);
	    gbc_miniButton.gridheight = 2;
	    gbc_miniButton.gridy = 0;
	    titlePanel.add(miniButton, gbc_miniButton);
	    
	    maxButton = new JButton();
	    maxButton.setHorizontalAlignment(SwingConstants.RIGHT);
	    maxButton.setIcon(new ImageIcon("media/max-icon34.png"));
	    maxButton.setRolloverIcon(new ImageIcon("media/max-icon34H.png"));
	    maxButton.setBorder(null);
	    maxButton.setBorderPainted(false);
	    maxButton.setContentAreaFilled(false);
	    maxButton.setRolloverEnabled(true);
	    GridBagConstraints tc3 = new GridBagConstraints();
	    tc3.anchor = GridBagConstraints.SOUTHWEST;
	    tc3.insets = new Insets(0, 0, 5, 5);
	    tc3.gridx = 8;
	    tc3.gridy = 0;
	    GridBagConstraints gbc_maxButton = new GridBagConstraints();
	    gbc_maxButton.anchor = GridBagConstraints.EAST;
	    gbc_maxButton.gridx = 6;
	    gbc_maxButton.insets = new Insets(0, 0, 0, 5);
	    gbc_maxButton.gridheight = 2;
	    gbc_maxButton.gridy = 0;
	    titlePanel.add(maxButton, gbc_maxButton);
	    
	    exitButton 		= new JButton();
	    exitButton.setHorizontalAlignment(SwingConstants.RIGHT);
	    exitButton.setIcon(new ImageIcon("media/exit-icon34.png"));
	    exitButton.setRolloverIcon(new ImageIcon("media/exit-icon34H.png"));
	    exitButton.setBorder(null);
	    exitButton.setBorderPainted(false);
	    exitButton.setContentAreaFilled(false);
	    exitButton.setRolloverEnabled(true);
	    GridBagConstraints tc4 = new GridBagConstraints();
	    tc4.anchor = GridBagConstraints.EAST;
	    tc4.insets = new Insets(0, 0, 5, 0);
	    tc4.gridx = 5;
	    tc4.gridy = 0;
	    GridBagConstraints gbc_exitButton = new GridBagConstraints();
	    gbc_exitButton.anchor = GridBagConstraints.EAST;
	    gbc_exitButton.gridx = 7;
	    gbc_exitButton.gridheight = 2;
	    gbc_exitButton.gridy = 0;
	    titlePanel.add(exitButton, gbc_exitButton);
	    
	    exitButton.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    		System.exit(0);
	    	}
	    });
		
		//--------------------------
		// MOUSE LISTENERS
		//--------------------------
		
		mosaicButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {//Add methods for mouse events to trigger loading graphics
				sketchLabel.setText("Mosaic Sketch");
				sketchPanel.add(sketchLabel);
				//sketchPanel.add(sketch);
				//sketchPanel.add(sketchPanel);
				//panel_1.setBackground(Color.BLUE);
				//sketchPanel.add(sketch);
				//ProccesingSketch.setup();
				//sketch.init();
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
	    GridBagConstraints tc1 = new GridBagConstraints();
	    tc1.anchor = GridBagConstraints.NORTHWEST;
	    tc1.insets = new Insets(0, 0, 0, 5);
	    tc1.gridx = 6;
	    tc1.gridy = 1;
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
		sketch.init();
		sketchPanel.add(sketch);
	}
}
	
	
}