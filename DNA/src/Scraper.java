//java.awt.geom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.Blog;
import com.tumblr.jumblr.types.Post;
import com.tumblr.jumblr.types.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Scraper {

	private static String		consumer_key		= "0bItUSuxTNYvg3aeMv9lXTVnOYME5kGRrzdGn0ba5ktWMTLLgL";
	private static String		consumer_secret		= "4zXYhAMODZDuWHm9cTUNN165JKoPohJGTdmxNCukg4mYSJyRHw";
	private static String		oauth_token			= "d4WwWT8lAUP67r2bBXNDM708JkZtpFg9EgBHEvVXs7u2EWd17j";
	private static String		oauth_token_secret	= "aqkfveFVuhnIVxxfLR3IohSgwgXup7jXHeMDMI2mvSeBD9qMfh";
	public  static String		tumblrName			= "wethinkwedream";
	public  static String 		filePath 			= new File("").getAbsolutePath();
	
	public Scraper()
	{
		//postList();
	}
	public static void postList()
	{
		String tumblrURL = tumblrName + ".tumblr.com";
		JumblrClient client = new JumblrClient(consumer_key, consumer_secret);
		client.setToken(oauth_token, oauth_token_secret);
		int count = 0;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("limit", 50);
		List<Post> posts = client.blogPosts(tumblrURL,params);
		
		// use 80 for 4000 --- 800 for 40k posts
		for (int i= 0; i < 8; i++)
		{
			count = count + 50;
			params.put("offset", count);
			posts.addAll(client.blogPosts(tumblrURL,params));
			System.out.println("I've done this loop " + i);
		}
		
		
		try {
			Vector<String> contentType = new Vector<String>(10);
			Vector<Long> contentTimestamp = new Vector<Long>(10); 
			Vector<String> contentDate = new Vector<String>(10);
			Vector<Long> contentId = new Vector<Long>(10);
			Vector<String> contentPostUrl = new Vector<String>(10);
			Vector<Long> contentNotes = new Vector<Long>(10);
			Vector<String> contentSourceUrl = new Vector<String>(10);
			Vector<List<String>> contentTags = new Vector<List<String>>(10);

			
			
			for (Post post : posts) 
			{	
				contentType.addElement(post.getType());
				contentTimestamp.addElement(post.getTimestamp());
				contentDate.addElement(post.getDateGMT());
				contentId.addElement(post.getId());
				contentPostUrl.addElement(post.getPostUrl());
				contentNotes.addElement(post.getNoteCount());
				contentSourceUrl.addElement(post.getSourceUrl());
				contentTags.addElement(post.getTags());
				
			}
			
			//(".").getAbsolutePath()
			//String filePath = new File("").getAbsolutePath();
			System.out.println(filePath);
			File file = new File(filePath + "/" + tumblrName + ".txt");
		
			if (!file.exists()){
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i =0; i < contentType.size(); ++i)
			{
				// Type
				bw.write(contentType.get(i) + "\n");
				// Timestamp
				bw.write(contentTimestamp.get(i) + "\n");
				// Date
				bw.write(contentDate.get(i) + "\n");
				// ID
				bw.write(contentId.get(i) + "\n");
				// Post URL
				bw.write(contentPostUrl.get(i) + "\n");
				// Source URL
				bw.write(contentSourceUrl.get(i) + "\n");
				// Notes
				bw.write(contentNotes.get(i) + "\n");
				// Tags
				bw.write(contentTags.get(i) + "\n");
				
			}
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		postList();
		/*
		JumblrClient client = new JumblrClient(consumer_key, consumer_secret);
		client.setToken(oauth_token, oauth_token_secret);
		
		
		int count = 0;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("limit", 50);
		List<Post> posts = client.blogPosts("alextheleon.tumblr.com",params);
		
		
		for (int i= 0; i < 80; i++)
		{
			count = count + 50;
			params.put("offset", count);
			posts.addAll(client.blogPosts("alextheleon.tumblr.com",params));
		}
		
		int count2 = 1;
		for (Post post : posts) 
		{	
			
			System.out.println( count2 + ". " + post.getType());
			count2++;
		}
		User user = client.user();
		Blog blog = client.blogInfo("alextheleon.tumblr.com");
		
		//System.out.println(user.getName());
		//System.out.println(blog.getPostCount());
		
		*/
	
		
	}
	
}
