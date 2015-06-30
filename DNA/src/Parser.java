import java.util.ArrayList;

public class Parser 
{
	public String fixTime(String time, String apm)
	{
		if(apm==null)
		{
			if(time.contains("am"))
			{
				apm="am";
				time=time.replace("am", "");
			}
			else if(time.contains("pm"))
			{
				apm="pm";
				time=time.replace("pm", "");
			}
		}
		String delims = "[:]";
		String[] timeTokens = time.split(delims);
		if(apm.equals("am")&&timeTokens[0].equals("12"))
		{
			timeTokens[0]="00";
		}
			else if(apm.equals("pm"))
		{
			int hour=Integer.parseInt(timeTokens[0]);
			if(hour<12)
			{
				hour=hour+12;
				timeTokens[0]=Integer.toString(hour);
			}
		}
		time=timeTokens[0]+":"+timeTokens[1];

		return time;
	}
	public String getChunkRoot(ArrayList<String> chunk) throws BadFormatException, BadRootException
	{
		String chunkRoot=null;
		
		if(!chunk.isEmpty()&&(chunk.get(0).equals("between")||chunk.get(0).equals("from")))
			chunkRoot="between/from";
		else if(!chunk.isEmpty()&&(chunk.get(0).equals("since")))
			chunkRoot="since";
		else if(!chunk.isEmpty()&&(chunk.get(0).equals("until")))
			chunkRoot="until";
		else if(!chunk.isEmpty()&&(chunk.get(0).equals("all")||chunk.get(0).equals("every")||chunk.get(0).equals("only")))
			chunkRoot="all/every/only";
		else
		{
			if(chunk.size()<2)
				return null;
			String s = "";
			for(int i=0;i<chunk.size();i++)
				s = s + chunk.get(i)+" ";
			throw new BadRootException("invalid root word in chunk: \n"+s);
		}
		return chunkRoot;
	}
	public ArrayList<PagePost> callRoot(ArrayList<PagePost> page, ArrayList<String> chunk, boolean inverseFlag, String chunkRoot) throws BadFormatException, BadRootException
	{
		RootWords roots = new RootWords();
		
		if(chunkRoot!=null)
		{
			switch(chunkRoot)
			{
			case "between/from":
				page=roots.BetweenFrom(page,chunk,inverseFlag);
				break;
			case "since":
				page=roots.Since(page,chunk,inverseFlag);
				break;
			case "until":
				page=roots.Until(page, chunk, inverseFlag);
				break;
			case "all/every/only":
				boolean continuation = false;
				page=roots.AllEveryOnly(page, chunk, inverseFlag, continuation);
				break;
			default:
			{
				if(chunk.size()<2)
					return page;
				String s = "";
				for(int i=0;i<chunk.size();i++)
					s = s + chunk.get(i)+" ";
				throw new BadRootException("invalid root word in chunk: \n"+s);
			}
			}
		}
		else
			return page;
		return page;
	}
	public boolean fbType(ArrayList<PagePost> page, String[] types, int i)
	{
		boolean include=true;
		if(types[0]!=null)
		{
			int k=0;
			while(true)
			{
				if(types[k]==null)
					return include;
				else if(page.get(i).getType().equalsIgnoreCase(types[k]))
				{
					include=true;
					k++;
				}
				else
				{
					include=false;
					k++;
				}	
			}
		}
		else
			return include;
	}

	public ArrayList<PagePost> fbTime(ArrayList<PagePost> page, String timeParams) throws BadFormatException, BadRootException
	{
		timeParams=timeParams.toLowerCase();
		timeParams=timeParams.replace(",","");
		String chunkRoot=null;
		String delims="[, +]";
		String[] tokenParams=timeParams.split(delims);
		ArrayList<String> chunk=new ArrayList<String>();
		chunk.clear();
		
		boolean inverseFlag=false;
		int a=0,b=0,c=0;
		ApplyTimes apply = new ApplyTimes();
		
		
		while(true)
		{
			inverseFlag=false;
			apply.trueBools();
			chunk.clear();
			chunkRoot=null;
			b=0;
			//fill chunk to be analyzed
			for(int i=a;i<tokenParams.length;i++)
			{
				a=i;
				chunk.add(b,tokenParams[i]);
				
				if(chunk.get(b).equals("am")||chunk.get(b).equals("pm"))
				{
					chunk.set((b-1),(fixTime(chunk.get(b-1),chunk.get(b))));
					chunk.remove(b);
					b--;
				}
				else if(chunk.get(b).contains("am")||chunk.get(b).contains("pm"))
				{
					chunk.set(b,fixTime(chunk.get(b),null));
				}
				b++;
			}
			if(chunk.size()<2)
				return page;
			chunkRoot = getChunkRoot(chunk);
			if(chunkRoot==null)
			{
				System.out.println("breakout");
				return page;
			}
			page=callRoot(page,chunk,inverseFlag,chunkRoot);
		}
		
	
		//return page;
	}
	public boolean fbNotes(ArrayList<PagePost> page, int notes, int i)
	{
		if(notes>=0)
		{
			if(page.get(i).getNotes()>=notes)
				return true;
			else
				return false;
		}
		else
			return true;
	}
	public boolean fbTags(ArrayList<PagePost> page, String tags, int i)
	{
		if(tags!=null)
		{
			int j=0;
			
			while(true)
			{
				if(j<(page.get(i).getTags().size()))
				{
					if(page.get(i).getTags().get(j)==null)
						return false;
					else
					{
						String word=("(\\b"+page.get(i).getTags().get(j)+"\\b)");
						if(tags.matches(word))
							return true;
						else
							j++;
					}
				}
				else
					return false;
			}
		}
		else
			return true;
	}	
	public boolean fbSourceURL(ArrayList<PagePost> page, String sourceURL, int i)
	{
		if(sourceURL!=null)
		{
			if(page.get(i).getSourceURL().contains(sourceURL))
				return true;
			else
				return false;
		}
		else
			return true;
	}
	public ArrayList<PagePost> includeAll(ArrayList<PagePost> page)
	{
		int numPosts=page.size()-1;
		
		for(int i=0;i<numPosts;i++)
			page.get(i).setInclude(true);
		return page;
	}
	public ArrayList<PagePost> excludeAll(ArrayList<PagePost> page)
	{
		int numPosts=page.size()-1;
		
		for(int i=0;i<numPosts;i++)
			page.get(i).setInclude(false);
		return page;
	}
	
	public ArrayList<PagePost> runFilter(ArrayList<PagePost> page, String[] types, int notes, String sourceURL, String timeParams, String tags) throws BadFormatException, BadRootException
	{
		int numPosts=page.size()-1;
		page=excludeAll(page);
		if(timeParams!=null)
			page=fbTime(page, timeParams);
		
		for(int i=0;i<numPosts;i++)
		{
			if(timeParams!=null)
			{
				if(page.get(i).getInclude())
				{
					if(fbType(page,types,i)&&fbNotes(page,notes,i)&&fbTags(page,tags,i)&&fbSourceURL(page,sourceURL,i))
						page.get(i).setInclude(true);
					else
						page.get(i).setInclude(false);
				}
				else
					page.get(i).setInclude(false);
			}
			else
			{
				if(fbType(page,types,i)&&fbNotes(page,notes,i)&&fbTags(page,tags,i)&&fbSourceURL(page,sourceURL,i))
					page.get(i).setInclude(true);
				else
					page.get(i).setInclude(false);
			}
				
		}

		return page;
	}
}