import java.util.ArrayList;
import java.util.Calendar;

public class PagePost 
{
	boolean include=true;
	String type=null;
	int timestamp=0;
	long id=0;
	String postURL=null;
	String sourceURL=null;
	int notes=0;
	ArrayList tags=new ArrayList();
	
	Calendar calendar=null;
	
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
	
	public PagePost(boolean include, String type,int timestamp,int year,int month,int dayOfMonth,
					int dayOfWeek,int weekOfYear,int weekOfMonth,int hour,int hourOfDay,int minute,
					int second, long id, String postURL, String sourceURL, int notes, ArrayList tags, Calendar calendar)
	{	
		this.include=include;
		this.type=type;
		this.timestamp=timestamp;
		this.year=year;
		this.month=month;
		this.dayOfMonth=dayOfMonth;
		this.dayOfWeek=dayOfWeek;
		this.weekOfYear=weekOfYear;
		this.weekOfMonth=weekOfMonth;
		this.hour=hour;
		this.hourOfDay=hourOfDay;
		this.minute=minute;
		this.second=second;
		this.id=id;
		this.postURL=postURL;
		this.sourceURL=sourceURL;
		this.notes=notes;
		this.tags=tags;
		this.calendar=calendar;
	}
	public boolean getInclude()
	{
		return this.include;
	}
	public String getType()
	{
		return this.type;
	}
	public int getTimestamp()
	{
		return this.timestamp;
	}
	public int getYear()
	{
		return this.year;
	}
	public int getMonth()
	{
		return this.month;
	}
	public int getDayOfMonth()
	{
		return this.dayOfMonth;
	}
	public int getDayOfWeek()
	{
		return this.dayOfWeek;
	}
	public int getWeekOfYear()
	{
		return this.weekOfYear;
	}
	public int getWeekOfMonth()
	{
		return this.weekOfMonth;
	}
	public int getHour()
	{
		return this.hour;
	}
	public int getHourOfDay()
	{
		return this.hourOfDay;
	}
	public int getMinute()
	{
		return this.minute;
	}
	public int getSecond()
	{
		return this.second;
	}
	public long getID()
	{
		return this.id;
	}
	public String getPostURL()
	{
		return this.postURL;
	}
	public String getSourceURL()
	{
		return this.sourceURL;
	}
	public int getNotes()
	{
		return this.notes;
	}
	public ArrayList getTags()
	{
		return this.tags;
	}
	public Calendar getCalendar()
	{
		return this.calendar;
	}
	
	public void setInclude(boolean include)
	{
		this.include=include;
	}
}