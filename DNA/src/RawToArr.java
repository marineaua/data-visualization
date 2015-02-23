/*		
 * 		String path="E:/DNATESTING/justinBieberIsLife.txt"; // path to ripped file
		ArrayList<String[]> page = new ArrayList<String[]>(); // array list containing individual posts
		page=RawToArr.txtToArr(page,path); // page gets return from txtToArr(a filled up array list)
 */



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RawToArr 
{
	public static ArrayList<String[]> txtToArr(ArrayList<String[]> page, String path)
	{	
		try
		{
			BufferedReader br = null;
			br=new BufferedReader(new FileReader(path));
			while(true)
			{
				String line = null;
				while(true)
				{
					String[] post = new String[9];
					post[0]="1"; // sets first element in array to 1... this is the flag for include or ignore
					for(int i=1; i<9; i++)
					{
						 if((line = br.readLine()) != null)
						 {
							 post[i]=line;
						 }
					}
					page.add(post);
					if(line==null)
						break;
				}
				if(line==null)
					break;
			}
			br.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	
		return page;
	}
	
}