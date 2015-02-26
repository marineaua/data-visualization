/********* how to call in main
 * 
 * 		Parser parse = new Parser();
		page=parse.runFilter(page, types, notes, sourceURL, lowDate, highDate, lowTime, highTime, tags);
 */

package pretentiousSocialMediaGarbage;

import java.util.ArrayList;

import pretentiousSocialMediaGarbage.Post;

public class Parser 
{
	public ArrayList<Post> fbType(ArrayList<Post> page, String[] types)
	{
		int numPosts=page.size()-1;
		
		for(int i=0;i<numPosts;i++)
		{
			int k=0;
			while(true)
			{
				if(types[k]==null)
					break;
				else if(page.get(i).getType().equals(types[k]))
				{
					page.get(i).setInclude(false);
					k++;
				}
				else
					k++;
			}
		}
		
		return page;
	}
	public ArrayList<Post> fbTime(ArrayList<Post> page, int[] lowDate, int[] highDate, int[] lowTime, int[] highTime)
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
				page.get(i).setInclude(false);
			// >=month
			if(lowDate[1]==-1){}
			else if(page.get(i).getDate()[1]>=lowDate[1]&&noLow==false)
				page.get(i).setInclude(false);
			// >=day
			if(lowDate[2]==-1){}
			else if(page.get(i).getDate()[2]>=lowDate[2]&&noLow==false)
				page.get(i).setInclude(false);
			/**************  low time    ****************/
			// >=hour
			if(lowTime[0]==-1){}
			if(page.get(i).getTime()[0]>=lowTime[0]&&noLow==false)
				page.get(i).setInclude(false);
			// >=minute
			if(lowTime[1]==-1){}
			else if(page.get(i).getTime()[1]>=lowTime[1]&&noLow==false)
				page.get(i).setInclude(false);
			// >=second
			if(lowTime[2]==-1){}
			else if(page.get(i).getTime()[2]>=lowTime[2]&&noLow==false)
				page.get(i).setInclude(false);
			

			/**************  high date    ****************/
			if(highDate[0]==-1)
				continue;
			
			// <year
			if(page.get(i).getDate()[0]<highDate[0])
				page.get(i).setInclude(false);
			// <month
			if(lowDate[1]==-1){}
			else if(page.get(i).getDate()[1]<highDate[1])
				page.get(i).setInclude(false);
			// <day
			if(lowDate[2]==-1){}
			else if(page.get(i).getDate()[2]<highDate[2])
				page.get(i).setInclude(false);
			/**************  high time    ****************/
			// <hour
			if(lowTime[0]==-1){}
			else if(page.get(i).getTime()[0]<highTime[0])
				page.get(i).setInclude(false);
			// <minute
			if(lowTime[1]==-1){}
			else if(page.get(i).getTime()[1]<highTime[1])
				page.get(i).setInclude(false);
			// <second
			if(lowTime[2]==-1){}
			else if(page.get(i).getTime()[2]<highTime[2])
				page.get(i).setInclude(false);
		}
		return page;
	}
	public ArrayList<Post> fbNotes(ArrayList<Post> page, int notes)
	{
		return page;
	}
	public ArrayList<Post> fbTags(ArrayList<Post> page, String[] tags)
	{
		return page;
	}	
	public ArrayList<Post> fbSourceURL(ArrayList<Post> page, String sourceURL)
	{
		return page;
	}
	public ArrayList<Post> includeAll(ArrayList<Post> page)
	{
		return page;
	}
	public ArrayList<Post> excludeAll(ArrayList<Post> page)
	{
		return page;
	}
	
	public ArrayList<Post> runFilter(ArrayList<Post> page, String[] types, int notes, String sourceURL, int[] lowDate, int[] highDate, int[] lowTime, int[] highTime, String[] tags)
	{
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
			fbNotes(page, notes);
		}
		if(tags[0]!=null)
		{
			fbTags(page, tags);
		}
		if(sourceURL!=null)
		{
			fbSourceURL(page, sourceURL);
		}
		return page;
	}
}
