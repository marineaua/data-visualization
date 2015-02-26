/**************** example of main code to create an empty page ready to be filled by calling RawToArr.txtToArr(page,path);
 * 
 * 		int notes=-1;
		String sourceURL=null;
		int lowDate[]=new int[3];
		int highDate[]=new int[3];
		int lowTime[]=new int[3];
		int highTime[]=new int[3];
		String[] types=new String[8];
		String[] tags=new String[10]; // is there a hashtag limit?
		
		for(int i=0;i<=2;i++)
		{
			lowDate[i]=-1;
			highDate[i]=-1;
			lowTime[i]=-1;
			highTime[i]=-1;
		}
 * 
 * 
 * 		String path="E:/DNATESTING/testing123.txt";
		ArrayList<Post> page = new ArrayList<Post>();
 */


package pretentiousSocialMediaGarbage;

public class Post 
{
	boolean include=true;
	String type=null;
	int[] date=new int[3];
	int[] time=new int[3];
	long id=0;
	String postURL=null;
	String sourceURL=null;
	int notes=0;
	String[] tags=null;
	
	public Post(boolean _include, String _type, int[] _date, int[] _time, long _id, String _postURL, String _sourceURL, int _notes, String[] _tags)
	{	
		include=_include;
		type=_type;
		date=_date;
		time=_time;
		id=_id;
		postURL=_postURL;
		sourceURL=_sourceURL;
		notes=_notes;
		tags=_tags;
	}
	public boolean getInclude()
	{
		return include;
	}
	public String getType()
	{
		return type;
	}
	public int[] getDate()
	{
		return date;
	}
	public int[] getTime()
	{
		return time;
	}
	public long getID()
	{
		return id;
	}
	public String getPostURL()
	{
		return postURL;
	}
	public String getSourceURL()
	{
		return sourceURL;
	}
	public int getNotes()
	{
		return notes;
	}
	public String[] getTags()
	{
		return tags;
	}
	
	public void setInclude(boolean _include)
	{
		include=_include;
	}
	public void setType(String _type)
	{
		type=_type;
	}
	public void setDate(int[] _date)
	{
		date=_date;
	}
	public void setTime(int[] _time)
	{
		time=_time;
	}
	public void setID(long _id)
	{
		id=_id;
	}
	public void setPostURL(String _postURL)
	{
		postURL=_postURL;
	}
	public void setSourceURL(String _sourceURL)
	{
		sourceURL=_sourceURL;
	}
	public void setNotes(int _notes)
	{
		notes=_notes;
	}
	public void setTags(String[] _tags)
	{
		tags=_tags;
	}
	
}