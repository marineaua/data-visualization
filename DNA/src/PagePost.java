import java.util.ArrayList;

public class PagePost 
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
	ArrayList tags=new ArrayList();
	
	public PagePost(boolean _include, String _type,int _timestamp, int[] _date, int[] _time, long _id, String _postURL, String _sourceURL, int _notes, ArrayList _tags)
	{	
		include=_include;
		type=_type;
		timestamp=_timestamp;
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
	public int getTimestamp()
	{
		return timestamp;
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
	public ArrayList getTags()
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
	public void setTimestamp(int _timestamp)
	{
		timestamp=_timestamp;
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
	public void setTags(ArrayList _tags)
	{
		tags=_tags;
	}
}