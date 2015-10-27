import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class MyGUI extends JFrame implements ActionListener
{
	private static ArrayList<PagePost> page = new ArrayList<PagePost>(); 
	private static int drawingType;
	private  ProccesingSketch sketch;
	private static int notes=-1;
	private static String sourceURL=null;
	private static String timeParams=null;
	private static String[] types=new String[8];
	private static String tags=null;
	private static String path=null;
	
	private javax.swing.JPanel sketchPanel;
	private JPanel buttonPanel;
	private JPanel titlePanel;
	private JPanel mainPanel;
	
	private JLabel titleLabel;
	
	private JButton lineButton;
	private JButton mosaicButton;
	private JButton pieButton;
	private JButton barButton;
	private JButton mosaicButton2;
	
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
	private Component horizontalStrut_3;
	private Component horizontalStrut_4;
	private Component horizontalStrut_5;
	private Component horizontalStrut_6;
	private Component horizontalStrut_7;
	
	
	
	public MyGUI()
	{ 
		setLocation(350,150);
		setSize(1200,900);
		setMinimumSize(new Dimension(1200,600));
		setPreferredSize(new Dimension(1300, 800));
		setTitle("Data Visualization");
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		
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
		//mainPanel.setSize(1200, 700);
		mainPanel.setBackground(new Color(45, 45, 48));
	    mainPanel.setBorder(BorderFactory.createMatteBorder(5,5,5,5, new Color(20, 20, 40, 200)));
		
		//-------------------------
		// BUTTONS
		//-------------------------
		
		lineButton = new JButton("Line");
		lineButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lineButton.addActionListener(this);
		lineButton.setPreferredSize(new Dimension(220,40));		
		mosaicButton = new JButton("Clockface");
		mosaicButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		mosaicButton.setPreferredSize(new Dimension(220,40));
		pieButton = new JButton("Pie");
		pieButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pieButton.setPreferredSize(new Dimension(220,40));
		
		barButton = new JButton("Bar");
		barButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		barButton.setPreferredSize(new Dimension(220,40));
		
		mosaicButton2 = new JButton("Mosaic");
		mosaicButton2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		mosaicButton2.setPreferredSize(new Dimension(220,40));
		
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		buttonPanel.add(mosaicButton);		
		buttonPanel.add(lineButton);
		buttonPanel.add(barButton);
		buttonPanel.add(pieButton);
		buttonPanel.add(mosaicButton2);
		
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
	    menuBar.setBackground(null);
		GridBagConstraints gbc_menuBar = new GridBagConstraints();
		gbc_menuBar.fill = GridBagConstraints.BOTH;
		gbc_menuBar.insets = new Insets(0, 0, 0, 5);
		gbc_menuBar.gridheight = 2;
		gbc_menuBar.gridx = 0;
		gbc_menuBar.gridy = 0;
		titlePanel.add(menuBar, gbc_menuBar);
	    
	    mnFile = new JMenu("File");
	    mnFile.getPopupMenu().setLightWeightPopupEnabled(false);
	    mnFile.setFont(new Font("Segoe UI", Font.PLAIN, 17));
	    menuBar.add(mnFile);
	    mnFile.setHorizontalAlignment(SwingConstants.LEFT);
	    mnFile.setForeground(Color.WHITE);
	    
	    mntmNewRip = new JMenuItem("New Rip");
	    mntmNewRip.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {//same as LOAD
	    		 final JFrame ripf = new JFrame();
	    		ripf.setPreferredSize(new Dimension(400,150));
	    		ripf.setLocation(750,400);
	    		ripf.setResizable(false);
	    		JPanel ripp = new JPanel();
	    		ripp.setPreferredSize(new Dimension(200,100));
	    		JLabel pathlabell = new JLabel("Enter the URL of the tumbler page in the area below");
	    		final JTextField  riptf = new JTextField("",30);
	    		
	    		JButton oK = new JButton("Proceed");	
	    		oK.addActionListener(new ActionListener() {
	    	    	public void actionPerformed(ActionEvent arg0) { 
	    	    		Scraper scrap = new Scraper();
	    	    		Scraper.postList(riptf.getText());
	    	    		titleLabel.setText("tumblr: " + scrap.gettumblrName());
	    	    		ripf.dispose();
	    	    	} 
	    	    	});
	    		
	    		
	    		
	    		
	    		
	    		ripf.add(ripp);
	    		ripp.add(pathlabell);
	    		ripp.add(riptf);
	    		ripp.add(oK);
	    		
	    		
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
	    		String pathname=null;
	    		JFileChooser chooser = new JFileChooser();
    		    int returnVal = chooser.showOpenDialog(loadf);
    		    if(returnVal == JFileChooser.APPROVE_OPTION) {
    		       pathname= chooser.getSelectedFile().getPath();
    		       titleLabel.setText("tumblr: " + chooser.getName(chooser.getSelectedFile()));
    		    }
	    	path=pathname;	
	    	page=RawToArr.txtToArr(page,path); 
	    		loadf.pack();
	    		loadf.setVisible(false);	    		
	    		
	    	}
	    });
	    mntmLoadRip.setFont(new Font("Segoe UI", Font.PLAIN, 17));
	    mnFile.add(mntmLoadRip);
	    
	    horizontalStrut = Box.createHorizontalStrut(20);
	    menuBar.add(horizontalStrut);
	    
	    mnFilter = new JMenu("Filter");
	    mnFilter.getPopupMenu().setLightWeightPopupEnabled(false);
	    mnFilter.setFont(new Font("Segoe UI", Font.PLAIN, 17));
	    menuBar.add(mnFilter);
	    mnFilter.setForeground(Color.WHITE);
	    
	    mntmAddFilter = new JMenuItem("Add Filter");
	    mntmAddFilter.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) { // Same as others
	    		final JFrame addf = new JFrame();
	    		JPanel addp = new JPanel();
	    		addf.setLocation(700,300);
	    		addf.setResizable(false);
	    		JPanel maincont = new JPanel(new FlowLayout(FlowLayout.LEFT));//main container
	    		FlowLayout flow = new FlowLayout();
	    		GridLayout grid = new GridLayout(2,4);
	    		final JCheckBox Type = new JCheckBox("Type");	    		
	    		
	    		addf.getContentPane().add(maincont);
	    		addp.setAlignmentY(JComponent.LEFT_ALIGNMENT);
	    		addp.add(Type);
	    		addp.add(horizontalStrut);
	    		maincont.add(addp);
	    		maincont.setPreferredSize(new Dimension(375,300));
	    		
	    		final JRadioButton Text = new JRadioButton("Text");
	            final JRadioButton Photo = new JRadioButton("Photo");	
	            final JRadioButton Quotes = new JRadioButton("Quotes");
	            final JRadioButton Video = new JRadioButton("Video");
	            final JRadioButton Audio = new JRadioButton("Audio");
	            final JRadioButton Link = new JRadioButton("Link");
	            final JRadioButton Chat = new JRadioButton("Chat");
	            final JRadioButton Answer = new JRadioButton("Answer");
	            
	            addp.add(Text);
	            addp.add(Photo);
	            addp.add(Quotes);
	            addp.add(Video);
	            addp.add(Audio);
	            addp.add(Link);
	            addp.add(Chat);
	            addp.add(Answer);
	            
	            
	            
	            JPanel radioPanel = new JPanel();
	            radioPanel.setLayout(grid);
	            radioPanel.add(Text);
	            radioPanel.add(Photo);
	            radioPanel.add(Quotes);
	            radioPanel.add(Video);
	            radioPanel.add(Audio);
	            radioPanel.add(Link);
	            radioPanel.add(Chat);
	            radioPanel.add(Answer);
	            
	            maincont.add(radioPanel, BorderLayout.LINE_START);
	    		
	    		addf.getContentPane().add(new JSeparator(),
	    		          BorderLayout.LINE_START);
	    		addf.pack();
	    		addf.setResizable(false);
	    		
	    		JPanel typep =new JPanel();
	    		
	    		JPanel notep = new JPanel();
	    		
	    		JPanel timep = new JPanel();
	    		
	    		JPanel sourcep = new JPanel();
	    		JPanel buttonp = new JPanel();
	    		JPanel labelp = new JPanel();
	    		
	    		JLabel tagslabel = new JLabel("Tags:",  SwingConstants.LEFT);
	    		final JLabel noteslabel = new JLabel("At least");
	    		JLabel notestail = new JLabel("notes.");
	    		JLabel sourcelabel = new JLabel("http://");
	    		final JLabel sourcetail = new JLabel(".tumblr.com");
	    		
	    		
	    		final JCheckBox Tags = new JCheckBox("Tags");
	    		final JCheckBox Notes = new JCheckBox("Notes");
	    		final JCheckBox Source = new JCheckBox("Source");
	    		final JCheckBox Time = new JCheckBox("Time");
	    		
	    		final JTextField say = new JTextField("whatever",20);
	    		final JTextField notef = new JTextField("notes",5);
	    		final JTextField sourcet = new JTextField("source url",13);
	    		final JTextField timef = new JTextField("Time",25);
	    		
	    		typep.setLayout(flow);
	    		typep.setAlignmentY(JComponent.LEFT_ALIGNMENT);
	    		notep.setLayout(flow);
	    		notep.setAlignmentY(JComponent.LEFT_ALIGNMENT);
	    		timep.setLayout(flow);
	    		timep.setAlignmentY(JComponent.LEFT_ALIGNMENT);
	    		sourcep.setLayout(flow);
	    		
	    		
	    		horizontalStrut_3 = Box.createHorizontalStrut(27);
	    		horizontalStrut_4 = Box.createHorizontalStrut(23);
	    		horizontalStrut_5 = Box.createHorizontalStrut(15);
	    		horizontalStrut_6 = Box.createHorizontalStrut(23);
	    		
	    		typep.add(Tags);
	    		typep.add(horizontalStrut_3);
	    		typep.add(tagslabel);
	    		typep.add(say);
	    		
	    		notep.add(Notes);
	    		notep.add(horizontalStrut_4);
	    		notep.add(noteslabel);
	    		notep.add(notef);
	    		notep.add(notestail);
	    		
	    		timep.add(Time);
	    		timep.add(horizontalStrut_6);
	    		timep.add(timef);
	    		
	    		sourcep.add(Source);
	    		sourcep.add(horizontalStrut_5);
	    		sourcep.add(sourcelabel);
	    		sourcep.add(sourcet);
	    		final JLabel errorlabel = new JLabel();
	    		sourcep.add(sourcetail);
	    		
	    		JButton Filter = new JButton("Filter");

	    		Filter.addActionListener(new ActionListener(){
	    			   public void actionPerformed(ActionEvent ae){
	    				   if (Notes.isSelected() == false && Time.isSelected() == false && Tags.isSelected() == false && Source.isSelected() == false && Type.isSelected() == false)
	   						{
	   							errorlabel.setText("***NO CHECK BOX SELECTED***");
	   						}
	   					    
	   						else 
	   						{   				   
	    				   if(Notes.isSelected() == true)
	    		    		{
	    		    			notes = Integer.parseInt(notef.getText());
	    		    			
	    		    		}
	    				   if(Time.isSelected() == true)
	    		    		{
	    		    			timeParams = timef.getText();
	    		    			
	    		    		}
	    				   
	    				   if(Tags.isSelected() == true)
	    		    		{
	    					    tags=say.getText();
	    		    		}
	    				   
	    				   if(Source.isSelected() == true)
	    		    		{
	    		    			sourceURL = sourcet.getText();
	    		    			sourceURL = sourceURL + sourcetail;
	    		    		}
	    				   
	    				   if(Type.isSelected() == true)
	    		    		{ 
	    					   int i=0;
	    					   
	    		    			if(Text.isSelected()==true)
	    		    			{
	    		    				types[i] = "text";
	    		    				i++;
	    		    			}
	    		    			if(Photo.isSelected()==true)
	    		    			{
	    		    				types[i] = "photo";
	    		    				i++;
	    		    			}
	    		    			if(Audio.isSelected()==true)
	    		    			{
	    		    				types[i] = "audio";
	    		    				i++;
	    		    			}
	    		    			if(Video.isSelected()==true)
	    		    			{
	    		    				types[i] = "video";
	    		    				i++;
	    		    			}
	    		    			if(Quotes.isSelected()==true)
	    		    			{
	    		    				types[i] = "quotes";
	    		    				i++;
	    		    			}
	    		    		}
	    				   Parser parse = new Parser();
	    				     				  
	    				  try {
	    					   page=parse.runFilter(page, types, notes, sourceURL, timeParams, tags);
	    					  } catch (BadFormatException e) {
	    						  JOptionPane pane = new JOptionPane();
	  							JOptionPane.showMessageDialog(pane, e.getError());
	    					   //System.out.println(e.getError());
	    					  } catch (BadRootException e) {
	    						  JOptionPane pane = new JOptionPane();
	  							JOptionPane.showMessageDialog(pane, e.getError());
	    					  // System.out.println(e.getError());
	    					  }
	    				  
	    				  
	    				  addf.dispose();
	    			   }}
	    			});
	    		
	    		horizontalStrut_6 = Box.createHorizontalStrut(145);
	    		buttonp.add(horizontalStrut_6);
	    		buttonp.add(Filter);
	    		
	    		horizontalStrut_7 = Box.createHorizontalStrut(90);
	    		labelp.add(horizontalStrut_7);
	    		labelp.add(errorlabel);
	    		
	    		maincont.add(typep);
	    		maincont.add(notep);
	    		maincont.add(timep);
	    		maincont.add(sourcep);
	    		maincont.add(buttonp);
	    		maincont.add(labelp);
	    		addf.setVisible(true);	    		
	    		
	    	}
	    });
	    mntmAddFilter.setFont(new Font("Segoe UI", Font.PLAIN, 17));
	    mnFilter.add(mntmAddFilter);
	    
	    mntmClearFilter = new JMenuItem("Clear Filter");
	    mntmClearFilter.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) { 
	    		Parser parse = new Parser();
	    		parse.includeAll(page);
	    		//same as others, not sure what this does, so i left blank
	    	}
	    });
	    mntmClearFilter.setFont(new Font("Segoe UI", Font.PLAIN, 17));
	    mnFilter.add(mntmClearFilter);
	    
	    horizontalStrut_1 = Box.createHorizontalStrut(20);
	    menuBar.add(horizontalStrut_1);
	    
	    mnHelp = new JMenu("Help");
	    mnHelp.getPopupMenu().setLightWeightPopupEnabled(false);
	    mnHelp.setFont(new Font("Segoe UI", Font.PLAIN, 17));
	    menuBar.add(mnHelp);
	    mnHelp.setForeground(Color.WHITE);
	    
	    mntmAbout = new JMenuItem("About");
	    mntmAbout.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent arg0) { //Add event for clicking
	    		JFrame about = new JFrame();
	    		about.setResizable(false);
	    		about.setPreferredSize(new Dimension(300,75));
	    		about.setLocation(850,450);
	    		JPanel aboutp = new JPanel();
	    		
	    		JScrollPane scroll = new JScrollPane(aboutp);
	    		JLabel aboutmes = new JLabel("Read the Read me");
	    		about.getContentPane().add(scroll);
	    		aboutp.add(aboutmes);
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
	    titleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	    
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
	    gbc_titleLabel.anchor = GridBagConstraints.EAST;
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
		//--------------------------
		// MOUSE LISTENERS
		//--------------------------
		
		mosaicButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {//Add methods for mouse events to trigger loading graphics
				drawingType = 0;
				sketch.init();
				sketch.start();
				sketchPanel.add(sketch);
			}
		});
		
		pieButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {//Add methods for mouse events to trigger loading graphics
				drawingType = 1;
				//sketch.clear();
				sketch.init();
				sketch.start();
				sketchPanel.add(sketch);
			}
		});
		
		barButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {//Add methods for mouse events to trigger loading graphics
				drawingType = 2;
				sketch.init();
				sketch.start();
				sketchPanel.add(sketch);
			}
		});
		
		lineButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {//Add methods for mouse events to trigger loading graphics
				drawingType = 3;
				sketch.init();
				sketch.start();
				sketchPanel.add(sketch);
			}
		});
		
		mosaicButton2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {//Add methods for mouse events to trigger loading graphics
				drawingType = 4;
				sketch.init();
				sketch.start();
				sketchPanel.add(sketch);
			}
		});
			
		//Adding other things to Frame
		mainPanel.setLayout(new BorderLayout(0, 0));
	    mainPanel.add(buttonPanel, BorderLayout.SOUTH);
	    mainPanel.add(titlePanel, BorderLayout.NORTH);
	    GridBagConstraints tc1 = new GridBagConstraints();
	    tc1.anchor = GridBagConstraints.NORTHWEST;
	    tc1.insets = new Insets(0, 0, 0, 5);
	    tc1.gridx = 6;
	    tc1.gridy = 1;
	    
	    //Adding Sketch to the Frame
	    mainPanel.add(sketchPanel, BorderLayout.CENTER);
		
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
			//Not sure what Anthony had this here for?
		}
	}

	static public ArrayList<PagePost> getPage()
	{
		return page;
	}
	
	static public int getDrawingType()
	{
		return drawingType;
	}
	static public String getPath()
	{
		return path;
	}
	
}