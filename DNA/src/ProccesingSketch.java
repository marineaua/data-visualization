import processing.core.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.Blog;
import com.tumblr.jumblr.types.Post;
import com.tumblr.jumblr.types.User;


public class ProccesingSketch extends PApplet {
	
	private static String		consumer_key		= "0bItUSuxTNYvg3aeMv9lXTVnOYME5kGRrzdGn0ba5ktWMTLLgL";
	private static String		consumer_secret		= "4zXYhAMODZDuWHm9cTUNN165JKoPohJGTdmxNCukg4mYSJyRHw";
	private static String		oauth_token			= "d4WwWT8lAUP67r2bBXNDM708JkZtpFg9EgBHEvVXs7u2EWd17j";
	private static String		oauth_token_secret	= "aqkfveFVuhnIVxxfLR3IohSgwgXup7jXHeMDMI2mvSeBD9qMfh";
	private static int 			width				= 1800;
	private static int			height				= 900;
	private static String		newFilePath 		= Scraper.filePath.substring(0, Scraper.filePath.length()-4);
	private static String		thePath				= "C:/Users/alexm_000/Documents/data-visualization/DNA/src/files/" + Scraper.tumblrName + ".txt";
	//private static String		tumblrName			= "alextheleon";
	//private static String		filePath			= new File("").getAbsolutePath();
	
	public void setup() {
	    size(width,height);
	    background(0);
	    //File file = new File(Scraper.tumblrName + ".txt").getAbsoluteFile();
	    //String newFilePath = Scraper.filePath.substring(0, Scraper.filePath.length()-4);
	    System.out.println(newFilePath);
	    File file = new File(thePath);
	    if(!file.exists())
	    {
	    	Scraper.postList();
	    }
	    
	   }

	public void draw() {
		noLoop();
		
	    drawFromArray();
		//drawFromBR();
	}
	
	public void drawFromArray()
	{
		//-----------------------
		//ARRAY CREATION
		//-----------------------
		ArrayList<PagePost> page = new ArrayList<PagePost>(); 	// array list containing individual posts
		page = RawToArr.txtToArr(page,thePath);					// page gets return from txtToArr(a filled up array list)
		//-----------------------
		//VARIABLES
		//-----------------------
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
		
		//-----------------------
		//ARRAY ITERATION
		//-----------------------
		
		int count = 0; //test code
		for(int i=0; i < page.size() - 1;i++)
		{
			String type = page.get(i).getType();
			
			if(type == null)
			{
				System.out.println(type);
				System.out.println(count);
			}
			else
			{
				count++;
			}
			
			switch(type)
			{
			case "photo" :
				stroke(255,255,255,opac);
				or = 400; 
				ir = 390;
				break;
			case "text" :
				stroke(0,128,0,opac);
				or = 380; 
				ir = 370;
				break;
			case "audio" :
				stroke(123,90,205,opac);
				or = 360;
				ir = 350;
				break;
			case "video" :
				stroke(196,255,0,opac);
				or = 340; 
				ir = 330;
				break;
			case "answer" :
				stroke(255,0,0,opac);
				or = 320; 
				ir = 310;
				break;
			case "quote" :
				stroke(220,70,70,opac);
				or = 300; 
				ir = 290;
				break;
			case "chat" :
				stroke(36,31,182,opac);
				or = 280; 
				ir = 270;
			case "link" :
				stroke(255,255,255,opac);
				or = 260; 
				ir = 250;
				break;
				
			
			}
			
			currentTime = page.get(i).getTimestamp();
			thetaDate = map(currentTime, startTime, finishTime, 0, 2 * PI);
			
			renderCirc(cp, or, ir, thetaDate);
		}
		System.out.println("done");
	}
	/*
	 * renderCirc() is a utility function for drawing a circle with given points in a circle 
	 */
	void renderCirc(PVector cp, float or, float ir, float thetaDate)
	{
		
		float x = (sin(thetaDate) * or) + cp.x;	//sets x value of the outer radius
		float y = (cos(thetaDate) * or) + cp.y;	//sets y calue of the outer radius
		float xx = (sin(thetaDate) * ir) + cp.x; //sets x value of the inner radius
		float yy = (cos(thetaDate) * ir) + cp.y; //sets the y value of the inner radius
		
		line(xx,yy,x,y);	//draws the line from the outer radius point to the inner radius point
	}
	
	public void drawFromBR()
	{
		int count3 = 200;
		int count4 = 200;
		int opac = 180;
		int size = 10;
		fill(0,0,0,0);
		
		//stroke(255, 180);
		
		/*
		//int count = 120;
	    for (int x = 50; x <= width-50; x +=20) {
	      for (int y = 50; y <= height-50; y+=20) {
	        float s = map(0, 120, 0, 0, TWO_PI*2);
	        arc(x, y, 14, 14, s, s + PI);
	        //count--;
	      }
	    }
	  	*/
		String line = null;
		String line2 = null;
	    try {
	    	
	    	int i = 0;
	    	int j = 0;
	    	
	    	//File file = new File(Scraper.filePath + Scraper.tumblrName + ".txt");
	    	File file = new File("C:/Users/alexm_000/Documents/data-visualization/DNA/src/files/" + Scraper.tumblrName + ".txt");
	    	BufferedReader br = new BufferedReader(new FileReader(file));	
	    	
	
	    	
	    	
	    	translate(width/2, height/2);
	    	
			while ((line = br.readLine()) != null) 
			{	
				
				if(i == 0){
					
						rotate(PI/360);
					//int count = 120;
					//float s = map(count, 120, 0, 0, TWO_PI*2);
					switch(line)
					{
					case "photo" :
						//stroke(255,255,255,opac);
						//arc(count3,count4, 20, 20, s, s + PI);
						ellipse(count3,count4,size,size);
						//white 255,255,255
						fill(255,255,255,opac);
						break;
					case "text" :
						//stroke(0,128,0,opac);
						//arc(count3,count4, 20, 20, s, s + PI);
						ellipse(170,170,size,size);
						//green 0 128 0
						fill(0,128,0,opac);
						break;
					case "audio" :
						//stroke(123,90,205,opac);
						//arc(count3,count4, 20, 20, s, s + PI);
						ellipse(140,140,size,size);
						// 123,90,205
						fill(123,90,205,opac);
						break;
					case "video" :
						//stroke(196,255,0,opac);
						//arc(count3,count4, 20, 20, s, s + PI);
						ellipse(110,110,size,size);
						//black 0,0,0 yellow
						fill(196,255,0,opac);
						break;
					case "answer" :
						//stroke(255,0,0,opac);
						//arc(count3,count4, 20, 20, s, s + PI);
						ellipse(80,80,size,size);
						// red
						fill(255,0,0,opac);
						break;
					case "quote" :
						//stroke(220,70,70,opac);
						//arc(count3,count4, 20, 20, s, s + PI);
						//pink i think
						ellipse(50,0,size,size);
						fill(220,70,70,opac);
						break;
					case "chat" :
						//stroke(36,31,182,opac);
						//arc(count3,count4, 20, 20, s, s + PI);
						ellipse(20,20,size,size);
						//70,30,180 blue
						fill(36,31,182,opac);
						break;
					
					}
					
					//count--;
					/*
					10
					10
					10
					interesting 
					*/
					/*
					count3 = count3 + 20;
					if (count3 >= width)
					{
						count3 = 10;
						count4 = count4 + 5;
					}
					System.out.println(line);
					*/
				
				}
				i++;
				if(i == 8)
				{
					i = 0;
				}
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //save("candy" + random(2000) + ".jpg");
	}
}