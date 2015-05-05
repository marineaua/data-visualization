import java.util.ArrayList;

public class Parser 
{
	public ArrayList<PagePost> fbType(ArrayList<PagePost> page, String[] types)
	{
		int numPosts=page.size()-1;
		
		for(int i=0;i<numPosts;i++)
		{
			int k=0;
			while(true)
			{
				if(types[k]==null)
					break;
				else if(page.get(i).getType().equalsIgnoreCase(types[k]))
				{
					page.get(i).setInclude(true);
					k++;
				}
				else
					k++;
			}
		}
		
		return page;
	}

	public ArrayList<PagePost> fbTime(ArrayList<PagePost> page, String timeParams)
	{
		timeParams=timeParams.toLowerCase();
		String s=timeParams.replace(";", " ;");
		String chunkRoot=null;
		String delims="[, +]";
		String[] tokenParams=s.split(delims);
		ArrayList<String> chunk=new ArrayList<String>();
		chunk.clear();
		RootWords roots = new RootWords();
		boolean inverseFlag=false;
		int a=0,b=0,c=0;
		
		while(true)
		{
			chunk.clear();
			chunkRoot=null;
			b=0;
			//fill chunk to be analyzed
			for(int i=a;i<tokenParams.length;i++)
			{
				chunk.add(b,tokenParams[i]);
				
				if(chunk.get(b).equals(";"))
				{
					a=i+1;
					b++;
					break;
				}
				b++;
			}

			if(!chunk.isEmpty()&&(chunk.get(0).equals("between")||chunk.get(0).equals("from")))
				chunkRoot="between/from";
			
			if(chunkRoot!=null)
			{
				switch(chunkRoot)
				{
				case "between/from":
					page=roots.BetweenFrom(page,chunk, inverseFlag);
					break;
				default:
					return page;
				}
			}
			else
				return page;
		}
		
	
		//return page;
	}
	public ArrayList<PagePost> fbNotes(ArrayList<PagePost> page, int notes)
	{
		int numPosts=page.size()-1;
		
		for(int i=0;i<numPosts;i++)
			if(page.get(i).getNotes()>=notes)
				page.get(i).setInclude(true);
		return page;
	}
	public ArrayList<PagePost> fbTags(ArrayList<PagePost> page, String tags)
	{
		int numPosts=page.size()-1;
		
		for(int i=0;i<numPosts;i++)
		{
			int j=0;
			
			while(true)
			{
				if(j<(page.get(i).getTags().size()))
				{
					if(page.get(i).getTags().get(j)==null)
						break;
					else
					{
						String word=("(\\b"+page.get(i).getTags().get(j)+"\\b)");
						if(tags.matches(word))
						{
							page.get(i).setInclude(true);
							break;
						}
						else
							j++;
					}
				}
				else
					break;
			}
		}
		return page;
	}	
	public ArrayList<PagePost> fbSourceURL(ArrayList<PagePost> page, String sourceURL)
	{
		int numPosts=page.size()-1;
		
		for(int i=0;i<numPosts;i++)
			if(page.get(i).getSourceURL().contains(sourceURL))
				page.get(i).setInclude(true);
		return page;
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
	
	public ArrayList<PagePost> runFilter(ArrayList<PagePost> page, String[] types, int notes, String sourceURL, String timeParams, String tags)
	{
		page=excludeAll(page);
		if(types[0]!=null)
			page=fbType(page, types);
		if(timeParams!=null)
			page=fbTime(page, timeParams);
		if(notes>=0)
			page=fbNotes(page, notes);
		if(tags!=null)
			page=fbTags(page, tags);
		if(sourceURL!=null)
			page=fbSourceURL(page, sourceURL);
		return page;
	}
}

