import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
					long id=0;
					String postURL=null;
					String sourceURL=null;
					int notes=0;

					Calendar calendar = null;
					
					int year=0;
					int month=0;
					int dayOfMonth=0;
					int dayOfWeek=0;
					int weekOfYear=0;
					int weekOfMonth=0;
				 
					int hour=0;
					int hourOfDay=0;
					int minute=0;
					int second=0;
					
					//String[] tags=new String[300];
					ArrayList tags=new ArrayList();
					
					// set type
					if((line = br.readLine()) != null) // line 1
					{
						type=line;
					}
					
					// set timestamp
					if((line = br.readLine()) != null) // line 2
					{
						timestamp=Integer.parseInt(line);
					}
					
					// set date and time
					if((line = br.readLine()) != null) // line 3
					{
						String delims = "[ \\-\\:]";
						
						String[] tokens = line.split(delims);
						/* date[0]=Integer.parseInt(tokens[0]); // year
						date[1]=Integer.parseInt(tokens[1]); // month
						date[2]=Integer.parseInt(tokens[2]); // day
						time[0]=Integer.parseInt(tokens[3]); // hours
						time[1]=Integer.parseInt(tokens[4]); // minutes
						time[2]=Integer.parseInt(tokens[5]); // seconds */

						//SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");	// keep this 
						calendar = new GregorianCalendar(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]),
																	Integer.parseInt(tokens[3]),Integer.parseInt(tokens[4]),Integer.parseInt(tokens[5]));
					 
						year       = calendar.get(Calendar.YEAR);
						month      = calendar.get(Calendar.MONTH); // Jan = 0, dec = 11
						dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH); // 23 = the 23rd
						dayOfWeek  = calendar.get(Calendar.DAY_OF_WEEK); // 1 = sunday, 7 = saturday
						weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR); // 1 = first week, 52 = last week
						weekOfMonth= calendar.get(Calendar.WEEK_OF_MONTH); // 1 = first week, 5 = last possible week
					 
						hour       = calendar.get(Calendar.HOUR);        // 12 hour clock
						hourOfDay  = calendar.get(Calendar.HOUR_OF_DAY); // 24 hour clock
						minute     = calendar.get(Calendar.MINUTE);
						second     = calendar.get(Calendar.SECOND);
						// year, month, dayOfMonth, dayOfWeek, weekOfYear, weekOfMonth, hour, hourOfDay, minute, second
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

					PagePost post = new PagePost(include, type, timestamp, year, month, dayOfMonth, dayOfWeek, weekOfYear, 
													weekOfMonth, hour, hourOfDay, minute, second, id, postURL, sourceURL, notes, tags, calendar);
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