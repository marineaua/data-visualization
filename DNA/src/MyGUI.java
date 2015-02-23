import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.CardLayout;


@SuppressWarnings("serial")
public class MyGUI extends javax.swing.JFrame implements ActionListener
{
	JPanel mypanel;
	JButton mybutton;
	JLabel mylabel;
	private JPanel panel_1;
	private JButton btnMosiac;
	private JButton btnOther;
	private ProccesingSketch sketch;
	private javax.swing.JPanel sketchPanel;
	
	
	public MyGUI()
	{
		this.setSize(1200,700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Data Visualization");
		this.setForeground(Color.WHITE);
		
		mypanel = new JPanel();
		mylabel = new JLabel();
		mybutton = new JButton("line");
		mybutton.addActionListener(this);
		mypanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		sketchPanel = new JPanel();
		sketch = new ProccesingSketch();
		sketchPanel.add(sketch);
		//sketchPanel = new JPanel();
		//sketchPanel.add(sketchPanel);
		
		
		//add a new proccessing sketch
		//javax.swing.JPanel sketchPanel = new javax.swing.JPanel();
		//sketchPanel.setBounds(0, 0, 1200, 700);
		//ProccesingSketch sketch = new ProccesingSketch();
		//sketchPanel.add(sketch);
		
		
		btnMosiac = new JButton("mosiac");
		btnMosiac.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {//Add methods for mouse events to trigger loading graphics
				mylabel.setText("my shit got clicked again");
				
				//sketchPanel.add(sketch);
				//sketchPanel.add(sketchPanel);
				//panel_1.setBackground(Color.BLUE);
				//sketchPanel.add(sketch);
				//ProccesingSketch.setup();
				//sketch.init();
			}
		});
		
		this.setLayout(new BorderLayout());
		
		
		btnMosiac.setHorizontalAlignment(SwingConstants.LEFT);
		mypanel.add(btnMosiac);
		
		mypanel.add(mybutton);
		
		btnOther = new JButton("other");
		mypanel.add(btnOther);
		mypanel.add(mylabel);
		this.add(mypanel, BorderLayout.SOUTH);
		
		
		//panel_1 = new JPanel();
		//panel_1.setBackground(Color.LIGHT_GRAY);
		//this.add(panel_1);
		
		//Adding Sketch to the Frame
		this.add(sketchPanel);
		sketch.init();
		sketch.frame = this;
	}



public static void main(String[] args)
{
	new MyGUI().setVisible(true);
	
}

public void actionPerformed(ActionEvent event)
{
	if(event.getSource()==mybutton)
	{
		mylabel.setText("my shit got clicked");
	}
}
}