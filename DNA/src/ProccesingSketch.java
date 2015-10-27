import processing.core.*;
import processing.event.MouseEvent;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;



public class ProccesingSketch extends PApplet {
	
	/**
	 * The serialVersioUID = 1L was added because of error, just a quick fix
	 */
	private static final long serialVersionUID = 1L;
	private static int 			width				= 1200;
	private static int			height				= 600;
	int 		   centerX 							= 0;
	int 		   centerY 							= 0;
	int 		   offsetX 							= 0;
	int 		   offsetY 							= 0;
	float 		   zoom 							= (float) 1.0;
	int		       photoColor						= color(255,255,255);	//White
	int		       textColor						= color(0,128,0);		//Green
	int		       audioColor						= color(123,90,205);	//Light Violet
	int		       videoColor						= color(196,255,0);		//Yellow
	int		       answerColor						= color(255,0,0);		//Red
	int		       quoteColor						= color(0,255,255);		//Cyan
	int		       chatColor						= color(36,31,182); 	//Dark Blue
	int		       linkColor						= color(238,130,238); 	//Pink
	
	
	public void setup() {
		size(width,height);
		background(0);
		centerX = 0;
		centerY = 0;
		cursor(HAND);
		smooth();
	} 

	public void draw() {
		clear();
		setSize(width, height);
		background(0,0,0);
		if (mousePressed == true) {
			centerX = mouseX-offsetX;
			centerY = mouseY-offsetY;
		}
		translate(centerX,centerY);
		scale(zoom);
		
		if(MyGUI.getDrawingType() == 0)
		{
			drawClockFace();
		}
		else if(MyGUI.getDrawingType() == 1)
		{
			drawPieGraph();
		}
		else if(MyGUI.getDrawingType() == 2)
		{
			drawBarGraph();
		}
		else if(MyGUI.getDrawingType() == 3)
		{
			drawLineGraph();
		}
		else if(MyGUI.getDrawingType() == 4)
		{
			drawMosaic();
		}
	}
	
	public void mousePressed(){
		// Pressing mouse begins looping draw()
		loop();
		offsetX = mouseX-centerX;
		offsetY = mouseY-centerY;
	}
	
	public void mouseReleased() {
		// Releasing the mouse stops looping draw()
		noLoop();
	}
	
	public void keyPressed() {
		// zoom
		loop();
		if (keyCode == UP) centerY -= 5;
		if (keyCode == DOWN) centerY += 5;
		if (keyCode == LEFT) centerX -= 5;
		if (keyCode == RIGHT) centerX += 5;
	}
	
	public void mouseWheel(MouseEvent event)
	{
		float e = event.getCount();
		e = e/20;
		zoom -= e;
		redraw();
	}
	
	public void keyReleased() {
		// zoom
		noLoop();
	}
	
	public void drawClockFace()
	{
		//-----------------------
		//ARRAY CREATION
		//-----------------------
		
		ArrayList<PagePost> page = new ArrayList<PagePost>();
		page = MyGUI.getPage();
		
		// array list containing individual posts
		// page gets return from txtToArr(a filled up array list)
		
		//-----------------------
		//VARIABLES
		//-----------------------
		int epoch2007		= 1167652800;
		int finishTime		= page.get(0).getTimestamp();
		int startTime 		= page.get(page.size() - 2).getTimestamp();
		int currentTime		= 0;
		int opac		 	= 180;
		float ir 		 	= random(25) + 250;
		float or 		 	= 300;
		float thetaDate  	= 0;
		PVector cp 			= new PVector(width/2,height/2);
		strokeWeight(1);
		strokeCap(SQUARE);
		
		//----------------------
		//DRAWING THE KEY
		//----------------------
		
		drawKey(70, 200);
		
		//-----------------------
		//ARRAY ITERATION
		//-----------------------
		
		
		for(int i=0; i < page.size() - 1;i++)
		{
			if(page.get(i).getInclude() == true && page.get(i).getType()!=null)
			{	
			
				String type = page.get(i).getType();
				
				switch(type)
				{
					case "photo" :
						stroke(photoColor, opac);
						or = 300; 
						ir = 290;
						break;
					case "text" :
						stroke(textColor,opac);
						or = 280; 
						ir = 270;
						break;
					case "audio" :
						stroke(audioColor,opac);
						or = 260;
						ir = 250;
						break;
					case "video" :
						stroke(videoColor,opac);
						or = 240; 
						ir = 230;
						break;
					case "answer" :
						stroke(answerColor,opac);
						or = 220; 
						ir = 210;
						break;
					case "quote" :
						stroke(quoteColor,opac);
						or = 200; 
						ir = 190;
						break;
					case "chat" :
						stroke(chatColor,opac);
						or = 180; 
						ir = 170;
						break;
					case "link" :
						stroke(linkColor,opac);
						or = 160; 
						ir = 150;
						break;
				}
				
				currentTime = page.get(i).getTimestamp();
				thetaDate = map(currentTime, startTime, finishTime, 0, 2 * PI);
				renderCirc(cp, or, ir, thetaDate);
			}
			
		}
		
		stroke(255,255,255,opac);
		fill(255,255,255);
		thetaDate = map(startTime, startTime, finishTime, 0, 2 * PI);
		renderStringInCirc(cp, 350, 340, thetaDate, "Start Time");
		int year = 2007;
		
		for(int i = epoch2007; i < finishTime; i += 31556926)
		{
			thetaDate = map(i, startTime, finishTime, 0, 2 * PI);
			if(i > startTime)
			{
				renderStringInCirc(cp, 350, 340, thetaDate, "" + year);
			}
			year++;
		}
	}
	
	public void drawLineGraph()
	{
		
		//-----------------------
		//ARRAY CREATION
		//-----------------------
		
		ArrayList<PagePost> page = new ArrayList<PagePost>();
		page = MyGUI.getPage();
		
		//-----------------------
		//VARIABLES
		//-----------------------
		PVector startPoint 	= new PVector(0,height);
		PVector endPoint 	= new PVector(0, height);
		int opac		 	= 180;
		int x 				= 0;
		int y				= 0;
		int noteCap			= 0;
		int heightTenth 	= height/10;
		int divide10		= (page.size()  -1)/10;
		int noteTenth		= 0;
		int k 				= 0;
		strokeWeight(1);
		strokeCap(SQUARE);
		
		//----------------------
		//DRAWING THE KEY
		//----------------------
		
		drawKey(-350, 200);
		
		//-----------------------
		//ARRAY ITERATION
		//-----------------------
		for(int i=0; i < page.size() -1; i++)
		{
			int notes = page.get(i).getNotes();
			if(notes > noteCap)
			{
				noteCap = notes;
			}
		}
		
		noteTenth = noteCap/10;
		
		//Divides the horizontal graph by 10 pieces labeling the post #
		for(int i = 0; i <= page.size() - 1; i += divide10)
		{
			text("" + i + "", i, height + 20);
		}
		
		//Divides the vertical graph by 10 pieces labeling the note count
		for(int i = height; i >= 0; i -= heightTenth)
		{
			String num = NumberFormat.getNumberInstance(Locale.US).format(k);
			text("" + num + "", -100, i);
			k += noteTenth;
		}
		
		for(int i=0; i < page.size() - 1;i++)
		{
			if(page.get(i).getInclude() == true && page.get(i).getType()!=null)
			{	
			
				String type = page.get(i).getType();
				int notes = page.get(i).getNotes();
				
				switch(type)
				{
				case "photo" :
					stroke(photoColor,opac);
					break;
				case "text" :
					stroke(textColor,opac);
					break;
				case "audio" :
					stroke(audioColor,opac);
					break;
				case "video" :
					stroke(videoColor,opac);
					break;
				case "answer" :
					stroke(answerColor,opac);
					break;
				case "quote" :
					stroke(quoteColor,opac);
					break;
				case "chat" :
					stroke(quoteColor,opac);
					break;
				case "link" :
					stroke(linkColor,opac);
					break;
					
				}
				
				x = i;
				y = (int) map(notes, 0, noteCap,height, 0);
				endPoint.x = x;
				endPoint.y = y;
				line(startPoint.x,startPoint.y,endPoint.x,endPoint.y);
				startPoint.x = x;
				startPoint.y = y;
				
			}	
		}
	}
	
	public void drawBarGraph()
	{
		//-----------------------
		//ARRAY CREATION
		//-----------------------
		
		ArrayList<PagePost> page = new ArrayList<PagePost>();
		page = MyGUI.getPage();

		//-----------------------
		//VARIABLES
		//-----------------------
		
		int opac		 	= 180;
		int photo			= 0;
		int text			= 0;
		int audio			= 0;
		int video			= 0;
		int answer			= 0;
		int quote			= 0;
		int chat			= 0;
		int link			= 0;
		noStroke();
		
		//----------------------
		//DRAWING THE KEY
		//----------------------
		textSize(12);
		drawKey(-300,200);
		
		//-----------------------
		//ARRAY ITERATION
		//-----------------------
		
	
		
		for(int i=0; i < page.size() - 1;i++)
		{
			if(page.get(i).getInclude() == true && page.get(i).getType()!=null)
			{	
				
				String type = page.get(i).getType();
				
				switch(type)
				{
					case "photo" :
						stroke(photoColor,opac);
						photo++;
						break;
					case "text" :
						stroke(textColor,opac);
						text++;
						break;
					case "audio" :
						stroke(audioColor,opac);
						audio++;
						break;
					case "video" :
						stroke(videoColor,opac);
						video++;
						break;
					case "answer" :
						stroke(answerColor,opac);
						answer++;
						break;
					case "quote" :
						stroke(quoteColor,opac);
						quote++;
						break;
					case "chat" :
						stroke(chatColor,opac);
						chat++;
						break;
					case "link" :
						stroke(linkColor,opac);
						link++;
						break;
				}
			}
		}
		
		int[] heights = { photo, text, audio, video, answer, quote, chat, link};
		int maxHeight = 0;
		for(int i = 0; i < heights.length; i++)
		{
			if(maxHeight < heights[i])
			{
				maxHeight = heights[i];
			}
		}
		int size = 75;
		int size2 = 0;
		
		drawBar(size2, size, photo, "photo", maxHeight);
		size2 += size;
		
		drawBar(size2, size, text, "text", maxHeight);
		size2 += size;
		
		drawBar(size2, size, audio, "audio", maxHeight);
		size2 += size;
		
		drawBar(size2, size, video, "video", maxHeight);
		size2 += size;
		
		drawBar(size2, size, answer, "answer", maxHeight);
		size2 += size;
		
		drawBar(size2, size, quote, "quote", maxHeight);
		size2 += size;
		
		drawBar(size2, size, chat, "chat", maxHeight);
		size2 += size;
		
		drawBar(size2, size, link, "link", maxHeight);
		size2 += size;
	}
	
	public void drawPieGraph()
	{
		//-----------------------
		//ARRAY CREATION
		//-----------------------

		ArrayList<PagePost> page = new ArrayList<PagePost>();
		page = MyGUI.getPage();
		
		//-----------------------
		//VARIABLES
		//-----------------------
		int opac		 	= 180;
		int photo			= 0;
		int text			= 0;
		int audio			= 0;
		int video			= 0;
		int answer			= 0;
		int quote			= 0;
		int chat			= 0;
		int link			= 0;
		noStroke();

		
		//----------------------
		//DRAWING THE KEY
		//----------------------

		drawKey(70, 200);
		
		//-----------------------
		//ARRAY ITERATION
		//-----------------------
		
		for(int i=0; i < page.size() - 1;i++)
		{
			if(page.get(i).getInclude() == true && page.get(i).getType()!=null)
			{	
				String type = page.get(i).getType();
				switch(type)
				{
					case "photo" :
						stroke(photoColor,opac);
						photo++;
						break;
					case "text" :
						stroke(textColor,opac);
						text++;
						break;
					case "audio" :
						stroke(audioColor,opac);
						audio++;
						break;
					case "video" :
						stroke(videoColor,opac);
						video++;
						break;
					case "answer" :
						stroke(answerColor,opac);
						answer++;
						break;
					case "quote" :
						stroke(quoteColor,opac);
						quote++;
						break;
					case "chat" :
						stroke(chatColor,opac);
						chat++;
						break;
					case "link" :
						stroke(linkColor,opac);
						link++;
						break;
				}
			}
		}
		
		int[] angles = { photo, text, audio, video, answer, quote, chat, link};
		pieChart(600, 550, angles, page.size()-1);
	}
	
	public void drawMosaic()
	{
		//-----------------------
		//ARRAY CREATION
		//-----------------------
		
		ArrayList<PagePost> page = new ArrayList<PagePost>();
		page = MyGUI.getPage();
		
		//-----------------------
		//VARIABLES
		//-----------------------
		int opac = 180;
		int count3 = 0;
		int count4 = 0;
		int size = 20;
		fill(0,0,0,0);
		strokeWeight(1);
		noStroke();
		
		//----------------------
		//DRAWING THE KEY
		//----------------------
		
		drawKey(-200,200);
		
		//-----------------------
		//ARRAY ITERATION
		//-----------------------
		noStroke();
		
		for(int i=0; i < page.size() - 1;i++)
		{
			if(page.get(i).getInclude() == true && page.get(i).getType()!=null)
			{	
			
				String type = page.get(i).getType();
				
				switch(type)
				{
					case "photo" :
						ellipse(count3,count4,size,size);
						fill(photoColor,opac);
						break;
					case "text" :
						ellipse(count3,count4,size,size);
						fill(textColor,opac);
						break;
					case "audio" :
						ellipse(count3,count4,size,size);
						fill(audioColor,opac);
						break;
					case "video" :
						ellipse(count3,count4,size,size);
						fill(videoColor,opac);
						break;
					case "answer" :
						ellipse(count3,count4,size,size);
						fill(answerColor,opac);
						break;
					case "quote" :
						ellipse(count3,count4,size,size);
						fill(quoteColor,opac);
						break;
					case "chat" :
						ellipse(count3,count4,size,size);
						fill(chatColor,opac);
						break;
					case "link" :
						ellipse(count3,count4,size,size);
						fill(linkColor,opac);
						break;
				}
				
				if(page.size() > 20000)
				{
					count3 = count3 + 5;
				}
				else
				{
					count3 = count3 + 20;
				}
				
				if (count3 >= width)
				{
					count3 = 0;
					count4 = count4 + 5;
				}
			}
		}
	}
	public void drawKey(int keyX, int keyY)
	{
		int keyX2 = keyX + 5;
		stroke(255,255,255);
		noFill();
		rect(keyX,keyY,75, 170);

		fill(photoColor);
		text("Photo", keyX2, keyY +20);
		
		fill(textColor);
		text("Text", keyX2, keyY + 40);
		
		fill(audioColor);
		text("Audio", keyX2, keyY + 60);
		
		fill(videoColor);
		text("Video", keyX2, keyY + 80);
		
		fill(answerColor);
		text("Answer", keyX2, keyY + 100);
		
		fill(quoteColor);
		text("Quote", keyX2, keyY + 120);
		
		fill(chatColor);
		text("Chat", keyX2, keyY + 140);
		
		fill(linkColor);
		text("Link", keyX2, keyY + 160);
		
		fill(photoColor);
	}

	void renderCirc(PVector cp, float or, float ir, float thetaDate)
	{
		
		float x = (sin(thetaDate) * or) + cp.x;	//sets x value of the outer radius
		float y = (cos(thetaDate) * or) + cp.y;	//sets y value of the outer radius
		float xx = (sin(thetaDate) * ir) + cp.x; //sets x value of the inner radius
		float yy = (cos(thetaDate) * ir) + cp.y; //sets the y value of the inner radius
		
		line(xx,yy,x,y);	//draws the line from the outer radius point to the inner radius point
	}
	
	void renderStringInCirc(PVector cp, float or, float ir, float thetaDate, String s)
	{
		float x = (sin(thetaDate) * or) + cp.x;		//sets x value of the outer radius
		float y = (cos(thetaDate) * or) + cp.y;		//sets y value of the outer radius
		pushMatrix();
		translate(x,y);
		text(s, 0,0);
		popMatrix();
	}
	
	void pieChart(float outerDiameter, float innerDiameter, int data[],int size)
	{
		int opac = 255;
		float lastAngle = 0;
		for (int i = 0; i < data.length; i++) 
		{
			float angle = map(data[i], 0, size, 0, 2 * PI );
			switch(i)
			{
				case 0 :
					fill(photoColor,opac);
					break;
				case 1 :
					fill(textColor,opac);
					break;
				case 2 :
					fill(audioColor,opac);
					break;
				case 3 :
					fill(videoColor,opac);
					break;
				case 4 :
					fill(answerColor,opac);
					break;
				case 5 :
					fill(quoteColor,opac);
					break;
				case 6 :
					fill(chatColor,opac);
					break;
				case 7 :
					fill(linkColor,opac);
					break;
				
			}
			arc(width/2, height/2, outerDiameter, outerDiameter, lastAngle, lastAngle + angle);
			lastAngle += angle;
		}
	}
	
	void drawBar(float leftCorner,float barWidth, float barHeight, String color, int maxHeight)
	{
		int opac = 255;
		switch(color)
		{
			case "photo" :
				fill(photoColor,opac);
				break;
			case "text" :
				fill(textColor,opac);
				break;
			case "audio" :
				fill(audioColor,opac);
				break;
			case "video" :
				fill(videoColor,opac);
				break;
			case "answer" :
				fill(answerColor,opac);
				break;
			case "quote" :
				fill(quoteColor,opac);
				break;
			case "chat" :
				fill(chatColor,opac);
				break;
			case "link" :
				fill(linkColor,opac);
				break;
			
		}
		float downScaleBarHeight = map(barHeight, 0, maxHeight, 0, width -200);
		rect(110, leftCorner, downScaleBarHeight, barWidth);
		fill(255,255,255,opac);
		textSize(26);
		text("" + (int)barHeight + "", downScaleBarHeight + 25 + 100,(float) (leftCorner + barWidth/1.6));
		text("" + color + "s", -100, (float) (leftCorner + barWidth/1.6)); 
	}
}