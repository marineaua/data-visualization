import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	public ArrayList<PagePost> fbTime(ArrayList<PagePost> page, int[] lowDate, int[] highDate, int[] lowTime, int[] highTime)
	{
		int numPosts=page.size()-1;
		boolean noLow=false;
		
		if(lowDate[0]==-1&&highDate[0]!=-1)
			noLow=true;
		else if(lowDate[0]==-1&&highDate[0]==-1)
			return page;
		
		for(int i=0;i<numPosts;i++)
		{
			/**************  low date    ****************/
			// >=year
			if(page.get(i).getDate()[0]>=lowDate[0]&&noLow==false)
				page.get(i).setInclude(true);
			// >=month
			if(lowDate[1]==-1){}
			else if(page.get(i).getDate()[1]>=lowDate[1]&&noLow==false)
				page.get(i).setInclude(true);
			// >=day
			if(lowDate[2]==-1){}
			else if(page.get(i).getDate()[2]>=lowDate[2]&&noLow==false)
				page.get(i).setInclude(true);
			/**************  low time    ****************/
			// >=hour
			if(lowTime[0]==-1){}
			if(page.get(i).getTime()[0]>=lowTime[0]&&noLow==false)
				page.get(i).setInclude(true);
			// >=minute
			if(lowTime[1]==-1){}
			else if(page.get(i).getTime()[1]>=lowTime[1]&&noLow==false)
				page.get(i).setInclude(true);
			// >=second
			if(lowTime[2]==-1){}
			else if(page.get(i).getTime()[2]>=lowTime[2]&&noLow==false)
				page.get(i).setInclude(true);
			

			/**************  high date    ****************/
			if(highDate[0]==-1)
				continue;
			
			// <year
			if(page.get(i).getDate()[0]<highDate[0])
				page.get(i).setInclude(true);
			// <month
			if(lowDate[1]==-1){}
			else if(page.get(i).getDate()[1]<highDate[1])
				page.get(i).setInclude(true);
			// <day
			if(lowDate[2]==-1){}
			else if(page.get(i).getDate()[2]<highDate[2])
				page.get(i).setInclude(true);
			/**************  high time    ****************/
			// <hour
			if(lowTime[0]==-1){}
			else if(page.get(i).getTime()[0]<highTime[0])
				page.get(i).setInclude(true);
			// <minute
			if(lowTime[1]==-1){}
			else if(page.get(i).getTime()[1]<highTime[1])
				page.get(i).setInclude(true);
			// <second
			if(lowTime[2]==-1){}
			else if(page.get(i).getTime()[2]<highTime[2])
				page.get(i).setInclude(true);
		}
		return page;
	}
	public ArrayList<PagePost> fbNotes(ArrayList<PagePost> page, int notes)
	{
		int numPosts=page.size()-1;
		
		for(int i=0;i<numPosts;i++)
		{
			if(page.get(i).getNotes()>=notes)
				page.get(i).setInclude(true);
		}
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
		{
			if(page.get(i).getSourceURL().contains(sourceURL))
				page.get(i).setInclude(true);
		}
		return page;
	}
	public ArrayList<PagePost> includeAll(ArrayList<PagePost> page)
	{
		int numPosts=page.size()-1;
		
		for(int i=0;i<numPosts;i++)
		{
			page.get(i).setInclude(true);
		}
		return page;
	}
	public ArrayList<PagePost> excludeAll(ArrayList<PagePost> page)
	{
		int numPosts=page.size()-1;
		
		for(int i=0;i<numPosts;i++)
		{
			page.get(i).setInclude(false);
		}
		return page;
	}
	
	public ArrayList<PagePost> runFilter(ArrayList<PagePost> page, String[] types, int notes, String sourceURL, int[] lowDate, int[] highDate, int[] lowTime, int[] highTime, String tags)
	{
		page=excludeAll(page);
		if(types[0]!=null)
		{
			page=fbType(page, types);
		}
		if(lowDate[0]!=-1||highDate[0]!=-1)
		{
			page=fbTime(page, lowDate, highDate, lowTime, highTime);
		}
		if(notes>=0)
		{
			page=fbNotes(page, notes);
		}
		if(tags!=null)
		{
			page=fbTags(page, tags);
		}
		if(sourceURL!=null)
		{
			page=fbSourceURL(page, sourceURL);
		}
		return page;
	}
}
