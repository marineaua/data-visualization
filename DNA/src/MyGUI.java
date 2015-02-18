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


public class MyGUI extends JFrame implements ActionListener
{
	JPanel mypanel;
	JButton mybutton;
	JLabel mylabel;
	private JPanel panel_1;
	private JButton btnMosiac;
	private JButton btnOther;
	
	public MyGUI()
	{
		getContentPane().setForeground(Color.WHITE);
		mypanel = new JPanel();
		mylabel = new JLabel();
		mybutton = new JButton("line");
		mybutton.addActionListener(this);
		mypanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnMosiac = new JButton("mosiac");
		btnMosiac.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {//Add methods for mouse events to trigger loading graphics
				mylabel.setText("my shit got clicked again");
				panel_1.setBackground(Color.BLUE);
				//ProccesingSketch.setup();
			}
		});
		getContentPane().setLayout(new BorderLayout(0, 0));
		btnMosiac.setHorizontalAlignment(SwingConstants.LEFT);
		mypanel.add(btnMosiac);
		
		mypanel.add(mybutton);
		
		btnOther = new JButton("other");
		mypanel.add(btnOther);
		mypanel.add(mylabel);
		getContentPane().add(mypanel, BorderLayout.SOUTH);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(panel_1);
	}



public static void main(String[] args)
{
	MyGUI first = new MyGUI();
	first.setTitle("Data Viz");
	first.setSize(1200,700);;
	first.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	first.setVisible(true);
}

public void actionPerformed(ActionEvent event)
{
	if(event.getSource()==mybutton)
	{
		mylabel.setText("my shit got clicked");
	}
}
}