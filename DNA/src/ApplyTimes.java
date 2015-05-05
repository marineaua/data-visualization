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
	
	// flags for DAY of the month (1-31)
	static boolean dom1=false,dom2=false,dom3=false,dom4=false,dom5=false,dom6=false,dom7=false,dom8=false
					,dom9=false,dom10=false,dom11=false,dom12=false,dom13=false,dom14=false,dom15=false,dom16=false
					,dom17=false,dom18=false,dom19=false,dom20=false,dom21=false,dom22=false,dom23=false,dom24=false
					,dom25=false,dom26=false,dom27=false,dom28=false,dom29=false,dom30=false,dom31=false;
	// flags for DAY of the week (sunday-saturday)
	static boolean dow1=false,dow2=false,dow3=false,dow4=false,dow5=false,dow6=false,dow7=false;
	
	// flags for WEEK of the year (1-52)
	static boolean woy1=false,woy2=false,woy3=false,woy4=false,woy5=false,woy6=false,woy7=false,woy8=false
					,woy9=false,woy10=false,woy11=false,woy12=false,woy13=false,woy14=false,woy15=false,woy16=false
					,woy17=false,woy18=false,woy19=false,woy20=false,woy21=false,woy22=false,woy23=false,woy24=false
					,woy25=false,woy26=false,woy27=false,woy28=false,woy29=false,woy30=false,woy31=false,woy32=false
					,woy33=false,woy34=false,woy35=false,woy36=false,woy37=false,woy38=false,woy39=false,woy40=false
					,woy41=false,woy42=false,woy43=false,woy44=false,woy45=false,woy46=false,woy47=false,woy48=false
					,woy49=false,woy50=false,woy51=false,woy52=false;
	// flags for WEEK of the month (1-5)
	static boolean wom1=false,wom2=false,wom3=false,wom4=false,wom5=false;
	
	public ArrayList<PagePost> betweenFullDateXandY(ArrayList<PagePost> page, boolean inverseFlag, String lowDate, String highDate)
	{
		String delims = "[/ \\-\\:]";
		String[] lowDateTokens = lowDate.split(delims);
		String[] highDateTokens = highDate.split(delims);          //   [year]                         //   [month]                      //  [day]
		Calendar lowDateCal = new GregorianCalendar(Integer.parseInt(lowDateTokens[2]),Integer.parseInt(lowDateTokens[0]),Integer.parseInt(lowDateTokens[1]));
		Calendar highDateCal = new GregorianCalendar(Integer.parseInt(highDateTokens[2]),Integer.parseInt(highDateTokens[0]),Integer.parseInt(highDateTokens[1]));
		
		year1=lowDateCal.get(Calendar.YEAR);
		month1=lowDateCal.get(Calendar.MONTH);
		day1=lowDateCal.get(Calendar.DAY_OF_MONTH);
		
		year4=highDateCal.get(Calendar.YEAR);
		month4=highDateCal.get(Calendar.MONTH);
		day4=highDateCal.get(Calendar.DAY_OF_MONTH);
		
		int numPosts=page.size()-1;
		
		for(int i=0;i<numPosts;i++)
			if(page.get(i).getCalendar().after(lowDateCal)&&page.get(i).getCalendar().before(highDateCal))
				page.get(i).setInclude(true);
		
		return page;
	}
	/*
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
	*/
}