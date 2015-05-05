import java.util.ArrayList;

public class RootWords 
{
	public boolean isNumeric(String str)  
	{  
		str=str.replace("/", "");
		str=str.replace(":", "");
		str=str.replace(".", "");
		try  
		{  
			double d = Double.parseDouble(str);  
		}  
		catch(NumberFormatException nfe)  
		{  
			return false;  
		}  
		return true;  
	}
	public ArrayList<PagePost> BetweenFrom(ArrayList<PagePost> page, ArrayList<String> chunk, boolean inverseFlag)
	{
		// check if valid <<(between/from) #NUMBER (and/to) #NUMBER>> string
		if((chunk.get(0).equals("between")&&chunk.get(2).equals("and")&&isNumeric(chunk.get(1))&&isNumeric(chunk.get(3)))||
				(chunk.get(0).equals("from")&&chunk.get(2).equals("to")&&isNumeric(chunk.get(1))&&isNumeric(chunk.get(3))))
		{
			if(chunk.get(1).contains("/")&&chunk.get(3).contains("/"))
			{
				ApplyTimes apply = new ApplyTimes();
				String lowDate=chunk.get(1);
				String highDate=chunk.get(3);
		
				page=apply.betweenFullDateXandY(page, inverseFlag, lowDate, highDate);
				
				if(chunk.get(4).equals(";"))
					return page;
			}
			return page;
		}
		// invalid input
		else
			return page;

	}
}
