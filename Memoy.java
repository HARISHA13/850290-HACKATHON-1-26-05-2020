import java.io.*;
import java.util.*;
import org.json.JSONObject;
import java.text.DecimalFormat;
public class Memoy {

	public static void main(String[] args) throws IOException{
		JSONObject obj = new JSONObject(); 
		JSONObject obj1 = new JSONObject();
		Scanner sc = new Scanner(System.in);
		FileInputStream f= new FileInputStream("Memory.txt");
		InputStreamReader is = new InputStreamReader(f);
		BufferedReader br = new BufferedReader(is);
		String Line;
		double max =0;
		int i = 0;
		int flag=0;
		double b =0;
		double sum =0;
		String d;
		while((Line = br.readLine()) != null)
		{
			if(i%2!=0)
			{
				flag++;
				StringTokenizer st =new StringTokenizer(Line,": ");
				ArrayList<String> token = new ArrayList<>();
				while(st.hasMoreTokens())
				{
					token.add(st.nextToken());
				}
				b= Double.parseDouble(token.get(1));
				if(b>max)
				{
					max = b;
				}
				System.out.println(b);
				b= b/1024;
				sum+=b;
			}
			i++;
		}
		max = max/1024;
		double avg=sum/flag;
		System.out.println(avg);
		System.out.println(max);
		try {
		  DecimalFormat df = new DecimalFormat("#.###");
          obj.put("AverageMemory(MB)", df.format(avg));
          obj.put("values: ", obj1);
          obj.put("MaximumMemory(MB)", df.format(max));
          System.out.println(obj);
          obj.put("Usecasename","Homepage");
		}
		catch(Exception e)
		{
			System.out.println("e");
		}
		sc.close();
		}
	}


