import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RawToArr 
{
	public static ArrayList<PagePost> txtToArr(ArrayList<PagePost> page, String path)
	{	
		try
		{
			BufferedReader br = null;
			br=new BufferedReader(new FileReader(path));
			while(true)
			{
				String line = null;
				while(true)
				{
					boolean include=true;
					String type=null;
					int timestamp=0;
					int[] date=new int[3];
					int[] time=new int[3];
					long id=0;
					String postURL=null;
					String sourceURL=null;
					int notes=0;
					//String[] tags=new String[300];
					ArrayList tags=new ArrayList();
					
					// set type
					if((line = br.readLine()) != null) // line 1
					{
						type=line;
					}
					
					// move down one line without doing anything with the return
					if((line = br.readLine()) != null) // line 2
					{
						timestamp=Integer.parseInt(line);
					}
					
					// set date and time
					if((line = br.readLine()) != null) // line 3
					{
						String delims = "[ \\-\\:]";
						
						String[] tokens = line.split(delims);
						date[0]=Integer.parseInt(tokens[0]); // year
						date[1]=Integer.parseInt(tokens[1]); // month
						date[2]=Integer.parseInt(tokens[2]); // day
						time[0]=Integer.parseInt(tokens[3]); // hours
						time[1]=Integer.parseInt(tokens[4]); // minutes
						time[2]=Integer.parseInt(tokens[5]); // seconds
						
					}
					// set id
					if((line = br.readLine()) != null) // line 4
					{
						id=Long.parseLong(line);
					}
					// set postURL
					if((line = br.readLine()) != null) // line 5
					{
						postURL=line;
					}
					// set sourceURL
					if((line = br.readLine()) != null) // line 6
					{
						sourceURL=line;
					}
					// set notes
					if((line = br.readLine()) != null) // line 7
					{
						notes=Integer.parseInt(line);
					}
					// set tags
					if((line = br.readLine()) != null) // line 8
					{
						String delims = "[,(:) +]";
						String[] tokens;
						line = line.substring(1, line.length() - 1);
						tokens = line.split(delims);
						
						for(int i=0; i<tokens.length;i++)
						{
							tags.add(tokens[i]);
						}
					}

					PagePost post = new PagePost(include, type, timestamp, date, time, id, postURL, sourceURL, notes, tags);
					page.add(post);
					if(line==null)
						break;
				}
				if(line==null)
					break;
			}
			br.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	
		return page;
	}
}