import processing.core.*;

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
	private static int 			width				= 1200;
	private static int			height				= 700;
	
	public void setup() {
	    size(width,height);
	    background(0);
	    if(!new File("C:/Users/alexm_000/Desktop/DNA/filename2.txt").exists())
	    {
	    	Scraper.postList();
	    }
	   }

	public void draw() {
		noLoop();
	    noStroke();
		
		int count3 = 10;
		int count4 = 10;
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
	    try {
	    	BufferedReader br = new BufferedReader(new FileReader("C:/Users/alexm_000/Desktop/DNA/filename2.txt"));
	    	
			while ((line = br.readLine()) != null) 
			{	
				//int count = 120;
				//float s = map(count, 120, 0, 0, TWO_PI*2);
				switch(line)
				{
				case "photo" :
					//stroke(255,255,255,opac);
					//arc(count3,count4, 20, 20, s, s + PI);
					ellipse(count3,count4,20,20);
					//white 255,255,255
					fill(255,255,255,opac);
					break;
				case "text" :
					//stroke(0,128,0,opac);
					//arc(count3,count4, 20, 20, s, s + PI);
					ellipse(count3,count4,20,20);
					//green 0 128 0
					fill(0,128,0,opac);
					break;
				case "audio" :
					//stroke(123,90,205,opac);
					//arc(count3,count4, 20, 20, s, s + PI);
					ellipse(count3,count4,20,20);
					// 123,90,205
					fill(123,90,205,opac);
					break;
				case "video" :
					//stroke(196,255,0,opac);
					//arc(count3,count4, 20, 20, s, s + PI);
					ellipse(count3,count4,20,20);
					//black 0,0,0 yellow
					fill(196,255,0,opac);
					break;
				case "answer" :
					//stroke(255,0,0,opac);
					//arc(count3,count4, 20, 20, s, s + PI);
					ellipse(count3,count4,20,20);
					// red
					fill(255,0,0,opac);
					break;
				case "quote" :
					//stroke(220,70,70,opac);
					//arc(count3,count4, 20, 20, s, s + PI);
					//pink i think
					ellipse(count3,count4,20,20);
					fill(220,70,70,opac);
					break;
				case "chat" :
					//stroke(36,31,182,opac);
					//arc(count3,count4, 20, 20, s, s + PI);
					ellipse(count3,count4,20,20);
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
				
				count3 = count3 + 20;
				if (count3 >= width)
				{
					count3 = 10;
					count4 = count4 + 5;
				}
				System.out.println(line);
				
				
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //save("candy" + random(2000) + ".jpg");
	}
}