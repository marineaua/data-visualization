import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ApplyTimes 
{
	// lowest year
	static int year1=0, month1=0, day1=0, hour1=0, minute1=0;
	// lower mid year, usually used for "except" filter
	static int year2=0, month2=0, day2=0, hour2=0, minute2=0;
	// upper mid year, usually used for "except" filter
	static int year3=0, month3=0, day3=0, hour3=0, minute3=0;
	// highest year
	static int year4=0, month4=0, day4=0, hour4=0, minute4=0;
	
	// EXCLUDE = TRUE; flags for DAY of the month (1-31)
	static boolean[] dom = new boolean[31];
	static boolean[] compDOM = new boolean[31];

	// EXCLUDE = TRUE; flags for DAY of the week (sunday-saturday)
	static boolean[] dow = new boolean[7];
	static boolean[] compDOW = new boolean[7];
	
	// EXCLUDE = TRUE; flags for WEEK of the year (1-52)
	static boolean[] woy = new boolean[52];
	static boolean[] compWOY = new boolean[52];
	
	// EXCLUDE = TRUE; flags for WEEK of the month (1-5)
	static boolean[] wom = new boolean[5];
	static boolean[] compWOM = new boolean[5];
	
	public void clearBools()
	{
		// lowest year
		year1=0; month1=0; day1=0; hour1=0; minute1=0;
		// lower mid year, usually used for "except" filter
		year2=0; month2=0; day2=0; hour2=0; minute2=0;
		// upper mid year, usually used for "except" filter
		year3=0; month3=0; day3=0; hour3=0; minute3=0;
		// highest year
		year4=0; month4=0; day4=0; hour4=0; minute4=0;
		
		for(int i=0;i<dom.length;i++)
			dom[i]=true;
		for(int i=0;i<dow.length;i++)
			dow[i]=true;
		for(int i=0;i<woy.length;i++)
			woy[i]=true;
		for(int i=0;i<wom.length;i++)
			wom[i]=true;
		for(int i=0;i<compDOM.length;i++)
			dom[i]=true;
		for(int i=0;i<compDOW.length;i++)
			dow[i]=true;
		for(int i=0;i<compWOY.length;i++)
			woy[i]=true;
		for(int i=0;i<compWOM.length;i++)
			wom[i]=true;

	}
	public ArrayList<PagePost> setIncludes(ArrayList<PagePost> page, int i, boolean inverseFlag)
	{
		if(dom[page.get(i).getDayOfMonth()-1]==true&&dow[page.get(i).getDayOfWeek()-1]==true
				&&woy[page.get(i).getWeekOfYear()-1]==true&&wom[page.get(i).getWeekOfMonth()-1]==true)
		{
			if(!inverseFlag)
				page.get(i).setInclude(true);
			else
				page.get(i).setInclude(false);
		}
		if(compDOM[page.get(i).getDayOfMonth()-1]==true&&compDOW[page.get(i).getDayOfWeek()-1]==true
				&&compWOY[page.get(i).getWeekOfYear()-1]==true&&compWOM[page.get(i).getWeekOfMonth()-1]==true)
		{
			if(!inverseFlag)
				page.get(i).setInclude(true);
			else
				page.get(i).setInclude(false);
		}
		return page;
	}
	public ArrayList<PagePost> untilDate(ArrayList<PagePost> page, boolean inverseFlag, String highDate)
	{
		String delims="[/ \\-\\:]";
		String[] highTokens = highDate.split(delims);
		Calendar highCal = new GregorianCalendar(Integer.parseInt(highTokens[2]),Integer.parseInt(highTokens[0]),Integer.parseInt(highTokens[1]));
		
		year1=highCal.get(Calendar.YEAR);
		month1=highCal.get(Calendar.MONTH);
		day1=highCal.get(Calendar.DAY_OF_MONTH);
		
		int numPosts=page.size()-1;
		for(int i=0;i<numPosts;i++)
		{
			if(page.get(i).getCalendar().before(highCal))
			{
				page = setIncludes(page,i,inverseFlag);
			}
		}
		
		return page;
	}
	public ArrayList<PagePost> untilTimeDate(ArrayList<PagePost> page, boolean inverseFlag, String highTime, String highDate)
	{
		String delims = "[/ \\-\\:]";
		String[] highTimeTokens = highTime.split(delims);
		String[] highDateTokens = highDate.split(delims);
		Calendar highCal = new GregorianCalendar(Integer.parseInt(highDateTokens[2]),Integer.parseInt(highDateTokens[0]),Integer.parseInt(highDateTokens[1])
				,Integer.parseInt(highTimeTokens[0]),Integer.parseInt(highTimeTokens[1]));
		
		year1=highCal.get(Calendar.YEAR);
		month1=highCal.get(Calendar.MONTH);
		day1=highCal.get(Calendar.DAY_OF_MONTH);
		hour1=highCal.get(Calendar.HOUR_OF_DAY);
		minute1=highCal.get(Calendar.MINUTE);
		
		int numPosts=page.size()-1;
		for(int i=0;i<numPosts;i++)
		{
			if(page.get(i).getCalendar().before(highCal))
			{
				page = setIncludes(page,i,inverseFlag);
			}
		}
		
		return page;
	}
	public ArrayList<PagePost> sinceDate(ArrayList<PagePost> page, boolean inverseFlag, String lowDate)
	{
		String delims="[/ \\-\\:]";
		String[] lowTokens = lowDate.split(delims);
		Calendar lowCal = new GregorianCalendar(Integer.parseInt(lowTokens[2]),Integer.parseInt(lowTokens[0]),Integer.parseInt(lowTokens[1]));
		
		year1=lowCal.get(Calendar.YEAR);
		month1=lowCal.get(Calendar.MONTH);
		day1=lowCal.get(Calendar.DAY_OF_MONTH);
		
		int numPosts=page.size()-1;
		for(int i=0;i<numPosts;i++)
		{
			if(page.get(i).getCalendar().after(lowCal))
			{
				page = setIncludes(page,i,inverseFlag);
			}
		}
		
		return page;
	}
	public ArrayList<PagePost> sinceTimeDate(ArrayList<PagePost> page, boolean inverseFlag, String lowTime, String lowDate)
	{
		String delims = "[/ \\-\\:]";
		String[] lowTimeTokens = lowTime.split(delims);
		String[] lowDateTokens = lowDate.split(delims);
		Calendar lowCal = new GregorianCalendar(Integer.parseInt(lowDateTokens[2]),Integer.parseInt(lowDateTokens[0]),Integer.parseInt(lowDateTokens[1])
				,Integer.parseInt(lowTimeTokens[0]),Integer.parseInt(lowTimeTokens[1]));
		
		year1=lowCal.get(Calendar.YEAR);
		month1=lowCal.get(Calendar.MONTH);
		day1=lowCal.get(Calendar.DAY_OF_MONTH);
		hour1=lowCal.get(Calendar.HOUR_OF_DAY);
		minute1=lowCal.get(Calendar.MINUTE);
		
		int numPosts=page.size()-1;
		for(int i=0;i<numPosts;i++)
		{
			if(page.get(i).getCalendar().after(lowCal))
			{
				page = setIncludes(page,i,inverseFlag);
			}
		}
		
		return page;
	}
	public ArrayList<PagePost> betweenTimeXandY(ArrayList<PagePost> page, boolean inverseFlag, String lowTime, String highTime)
	{
		String delims="[/ \\-\\:]";
		String[]lowTokens=lowTime.split(delims);
		String[]highTokens=highTime.split(delims);
		
		int numPosts=page.size()-1;
		for(int i=0;i<numPosts;i++)
		{
			if(page.get(i).getHourOfDay()>=Integer.parseInt(lowTokens[0])&&page.get(i).getHourOfDay()<=Integer.parseInt(highTokens[0]))
			{
				// if same hour, but minutes to low
				if(page.get(i).getHourOfDay()==Integer.parseInt(lowTokens[0])&&page.get(i).getMinute()<Integer.parseInt(lowTokens[1]))
					continue;
				// if same hour, but minutes to high
				else if(page.get(i).getHourOfDay()==Integer.parseInt(highTokens[0])&&page.get(i).getMinute()>=Integer.parseInt(highTokens[1]))
					continue;
				else
				{
					page = setIncludes(page,i,inverseFlag);
				}
			}
		}
		
		return page;
	}
	public ArrayList<PagePost> betweenFullTimeDateXandY(ArrayList<PagePost> page, boolean inverseFlag, String lowTime, String lowDate, String highTime, String highDate)
	{
		String delims = "[/ \\-\\:]";
		String[] lowTimeTokens = lowTime.split(delims);
		String[] highTimeTokens = highTime.split(delims);
		String[] lowDateTokens = lowDate.split(delims);
		String[] highDateTokens = highDate.split(delims); // [year] [month] [day] / [hour] [minute]
		Calendar lowCal = new GregorianCalendar(Integer.parseInt(lowDateTokens[2]),Integer.parseInt(lowDateTokens[0]),Integer.parseInt(lowDateTokens[1])
												,Integer.parseInt(lowTimeTokens[0]),Integer.parseInt(lowTimeTokens[1]));
		Calendar highCal = new GregorianCalendar(Integer.parseInt(highDateTokens[2]),Integer.parseInt(highDateTokens[0]),Integer.parseInt(highDateTokens[1])
												,Integer.parseInt(highTimeTokens[0]),Integer.parseInt(highTimeTokens[1]));
		
		year1=lowCal.get(Calendar.YEAR);
		month1=lowCal.get(Calendar.MONTH);
		day1=lowCal.get(Calendar.DAY_OF_MONTH);
		hour1=lowCal.get(Calendar.HOUR_OF_DAY);
		minute1=lowCal.get(Calendar.MINUTE);
		
		year4=highCal.get(Calendar.YEAR);
		month4=highCal.get(Calendar.MONTH);
		day4=highCal.get(Calendar.DAY_OF_MONTH);
		hour4=highCal.get(Calendar.HOUR_OF_DAY);
		minute4=highCal.get(Calendar.MINUTE);
		
		int numPosts=page.size()-1;
		
		for(int i=0;i<numPosts;i++)
		{
			if(page.get(i).getCalendar().after(lowCal)&&page.get(i).getCalendar().before(highCal))
			{
				page = setIncludes(page,i,inverseFlag);
			}
		}
		
		return page;
	}
	public ArrayList<PagePost> betweenFullDateXandY(ArrayList<PagePost> page, boolean inverseFlag, String lowDate, String highDate)
	{
		String delims = "[/ \\-\\:]";
		String[] lowTokens = lowDate.split(delims);
		String[] highTokens = highDate.split(delims);          //   [year]                 //   [month]                      //  [day]
		Calendar lowCal = new GregorianCalendar(Integer.parseInt(lowTokens[2]),Integer.parseInt(lowTokens[0]),Integer.parseInt(lowTokens[1]));
		Calendar highCal = new GregorianCalendar(Integer.parseInt(highTokens[2]),Integer.parseInt(highTokens[0]),Integer.parseInt(highTokens[1]));
		
		year1=lowCal.get(Calendar.YEAR);
		month1=lowCal.get(Calendar.MONTH);
		day1=lowCal.get(Calendar.DAY_OF_MONTH);
		
		year4=highCal.get(Calendar.YEAR);
		month4=highCal.get(Calendar.MONTH);
		day4=highCal.get(Calendar.DAY_OF_MONTH);
		
		int numPosts=page.size()-1;
		
		for(int i=0;i<numPosts;i++)
		{
			if(page.get(i).getCalendar().after(lowCal)&&page.get(i).getCalendar().before(highCal))
			{
				page = setIncludes(page,i,inverseFlag);
			}
		}
		
		return page;
	}
}