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
	public ArrayList<String> refillChunk(ArrayList<String> chunk, int start)
	{
		ArrayList<String> newChunk = new ArrayList<String>();
		for(int i=0;i<chunk.size();i++)
		{
			newChunk.add(i,chunk.get(start));
			if(newChunk.get(i).equals(";"))
				break;
			start++;
		}
		return newChunk;
	}
	public ArrayList<PagePost> BetweenFrom(ArrayList<PagePost> page, ArrayList<String> chunk, boolean inverseFlag)
	{
		Parser parse = new Parser();
		ApplyTimes apply = new ApplyTimes();
		String chunkRoot=null;
		// check if valid <<(between/from) #NUMBER (and/to) #NUMBER>> string
		if((chunk.get(0).equals("between")&&chunk.get(2).equals("and")&&isNumeric(chunk.get(1))&&isNumeric(chunk.get(3)))||
				(chunk.get(0).equals("from")&&chunk.get(2).equals("to")&&isNumeric(chunk.get(1))&&isNumeric(chunk.get(3))))
		{
			// between 1/1/2011 and 1/1/2012
			if(chunk.get(1).contains("/")&&chunk.get(3).contains("/"))
			{
				String lowDate=chunk.get(1);
				String highDate=chunk.get(3);
		
				page=apply.betweenFullDateXandY(page, inverseFlag, lowDate, highDate);
				
				String nextWord=chunk.get(4);
				if(nextWord.equals(";"))
					return page;
				else if(nextWord.equals("except"))
				{
					inverseFlag=true;
					nextWord=chunk.get(5);
					chunk=refillChunk(chunk,5);
					chunkRoot = parse.getChunkRoot(chunk);
					page = parse.callRoot(page, chunk, inverseFlag, chunkRoot);
				}
				else if(nextWord.equals("and")&&(chunk.get(5).equals("between")||chunk.get(5).equals("from")))
				{
					inverseFlag=false;
					nextWord=chunk.get(5);
					chunk=refillChunk(chunk,5);
					chunkRoot = parse.getChunkRoot(chunk);
					page = parse.callRoot(page, chunk, inverseFlag, chunkRoot);
				}
				else
					return page;
			}
			// between 13:30 and 14:30
			if(chunk.get(1).contains(":")&&chunk.get(3).contains(":"))
			{
				String lowTime=chunk.get(1);
				String highTime=chunk.get(3);
		
				page=apply.betweenTimeXandY(page, inverseFlag, lowTime, highTime);
				
				String nextWord=chunk.get(4);
				if(nextWord.equals(";"))
					return page;
				else if(nextWord.equals("except"))
				{
					inverseFlag=true;
					nextWord=chunk.get(5);
					chunk=refillChunk(chunk,5);
					chunkRoot = parse.getChunkRoot(chunk);
					page = parse.callRoot(page, chunk, inverseFlag, chunkRoot);
				}
				else if(nextWord.equals("and")&&(chunk.get(5).equals("between")||chunk.get(5).equals("from")))
				{
					inverseFlag=false;
					nextWord=chunk.get(5);
					chunk=refillChunk(chunk,5);
					chunkRoot = parse.getChunkRoot(chunk);
					page = parse.callRoot(page, chunk, inverseFlag, chunkRoot);
				}
				else
					return page;
			}
			return page;
		}
		// between 13:30 1/1/2011 and 14:30 1/1/2012
		if((chunk.get(0).equals("between")&&isNumeric(chunk.get(1))&&isNumeric(chunk.get(2))&&chunk.get(3).equals("and")&&isNumeric(chunk.get(4))&&isNumeric(chunk.get(5)))||
				(chunk.get(0).equals("from")&&isNumeric(chunk.get(1))&&isNumeric(chunk.get(2))&&chunk.get(3).equals("to")&&isNumeric(chunk.get(4))&&isNumeric(chunk.get(5))))
		{
			String lowTime=chunk.get(1);
			String lowDate=chunk.get(2);
			String highTime=chunk.get(4);
			String highDate=chunk.get(5);
			
			page=apply.betweenFullTimeDateXandY(page, inverseFlag, lowTime, lowDate, highTime, highDate);
			
			String nextWord=chunk.get(6);
			if(nextWord.equals(";"))
				return page;
			else if(nextWord.equals("except"))
			{
				inverseFlag=true;
				nextWord=chunk.get(7);
				chunk=refillChunk(chunk,7);
				chunkRoot = parse.getChunkRoot(chunk);
				page = parse.callRoot(page, chunk, inverseFlag, chunkRoot);
			}
			else if(nextWord.equals("and")&&(chunk.get(7).equals("between")||chunk.get(7).equals("from")))
			{
				inverseFlag=false;
				nextWord=chunk.get(7);
				chunk=refillChunk(chunk,7);
				chunkRoot = parse.getChunkRoot(chunk);
				page = parse.callRoot(page, chunk, inverseFlag, chunkRoot);
			}
			else
				return page;
		}
		// invalid input
		else
			return page;
		return page;

	}
	public ArrayList<PagePost> Since(ArrayList<PagePost> page, ArrayList<String> chunk, boolean inverseFlag)
	{
		Parser parse = new Parser();
		ApplyTimes apply = new ApplyTimes();
		String chunkRoot=null;
		// since 1/1/2013
		if((chunk.get(0).equals("since")&&isNumeric(chunk.get(1))))
		{
			String lowDate=chunk.get(1);
			
			page=apply.sinceDate(page, inverseFlag, lowDate);
			
			String nextWord=chunk.get(2);
			if(nextWord.equals(";"))
				return page;
			else if(nextWord.equals("except"))
			{
				inverseFlag=true;
				nextWord=chunk.get(3);
				chunk=refillChunk(chunk,3);
				chunkRoot = parse.getChunkRoot(chunk);
				page = parse.callRoot(page, chunk, inverseFlag, chunkRoot);
			}
			else
				return page;
		}
		// since 3:30pm 1/1/2013
		else if((chunk.get(0).equals("since")&&isNumeric(chunk.get(1))&&isNumeric(chunk.get(2))))
		{
			String lowTime=chunk.get(1);
			String lowDate=chunk.get(2);
			
			page=apply.sinceTimeDate(page, inverseFlag, lowTime, lowDate);
			
			String nextWord=chunk.get(3);
			if(nextWord.equals(";"))
				return page;
			else if(nextWord.equals("except"))
			{
				inverseFlag=true;
				nextWord=chunk.get(4);
				chunk=refillChunk(chunk,4);
				chunkRoot = parse.getChunkRoot(chunk);
				page = parse.callRoot(page, chunk, inverseFlag, chunkRoot);
			}
			else
				return page;
		}
		return page;
	}
	public ArrayList<PagePost> Until(ArrayList<PagePost> page, ArrayList<String> chunk, boolean inverseFlag)
	{
		Parser parse = new Parser();
		ApplyTimes apply = new ApplyTimes();
		String chunkRoot=null;
		// since 1/1/2013
		if((chunk.get(0).equals("until")&&isNumeric(chunk.get(1))))
		{
			String highDate=chunk.get(1);
			
			page=apply.untilDate(page, inverseFlag, highDate);
			
			String nextWord=chunk.get(2);
			if(nextWord.equals(";"))
				return page;
			else if(nextWord.equals("except"))
			{
				inverseFlag=true;
				nextWord=chunk.get(3);
				chunk=refillChunk(chunk,3);
				chunkRoot = parse.getChunkRoot(chunk);
				page = parse.callRoot(page, chunk, inverseFlag, chunkRoot);
			}
			else
				return page;
		}
		// since 3:30pm 1/1/2013
		else if((chunk.get(0).equals("until")&&isNumeric(chunk.get(1))&&isNumeric(chunk.get(2))))
		{
			String highTime=chunk.get(1);
			String highDate=chunk.get(2);
			
			page=apply.untilTimeDate(page, inverseFlag, highTime, highDate);
			
			String nextWord=chunk.get(3);
			if(nextWord.equals(";"))
				return page;
			else if(nextWord.equals("except"))
			{
				inverseFlag=true;
				nextWord=chunk.get(4);
				chunk=refillChunk(chunk,4);
				chunkRoot = parse.getChunkRoot(chunk);
				page = parse.callRoot(page, chunk, inverseFlag, chunkRoot);
			}
			else
				return page;
		}
		return page;
	}
	public ArrayList<PagePost> For(ArrayList<PagePost> page, ArrayList<String> chunk, boolean inverseFlag)
	{
		return page;
	}
	public ArrayList<PagePost> ForContinuation(ArrayList<PagePost> page, ArrayList<String> chunk, boolean inverseFlag)
	{
		return page;
	}
	public ArrayList<PagePost> AllEveryOnly(ArrayList<PagePost> page, ArrayList<String> chunk, boolean inverseFlag)
	{
		return page;
	}
	public ArrayList<PagePost> InOnAt(ArrayList<PagePost> page, ArrayList<String> chunk, boolean inverseFlag)
	{
		return page;
	}
}
