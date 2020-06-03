import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

import org.json.JSONObject;
import org.json.simple.parser.ParseException;

public class CPU1 {

	String filepath = "CPU.txt";
	ArrayList<Double> cpuArray = new ArrayList<Double>();
	LinkedHashMap<String, Double> CpuMap = new LinkedHashMap<String, Double>();
	void Reader() throws ParseException {
			String lineText = null;
			int line = 0;
			double OverAllMemorySize = 0;
			double AverageMemorySize = 0.0;
			double MaxMemorySize = 0;
			int count = 0;
			int keyvalue=1;
			try {
				BufferedReader lineReader = new BufferedReader(new FileReader(filepath));
				while ((lineText = lineReader.readLine()) != null) {
						String Memory = lineText.substring(41, 46);
						OverAllMemorySize = OverAllMemorySize + Double.parseDouble(Memory);
						String key= keyvalue+"s";
						CpuMap.put(key, (Double.parseDouble(Memory)));
						cpuArray.add((Double.parseDouble(Memory)));
						count++;
						AverageMemorySize = OverAllMemorySize / count;
						
						keyvalue++;
					line++;
				}
				DecimalFormat decimalFormat =new  DecimalFormat("###.##");
				String formater=decimalFormat.format(AverageMemorySize);
				AverageMemorySize=Double.parseDouble(formater);
				
				MaxMemorySize=(Collections.max(CpuMap.values()));
				lineReader.close();
			} catch (IOException ex) {
				System.err.println(ex);
			}

			JSONObject jsonObj = new JSONObject();
			
			jsonObj.put("values",CpuMap.toString() );
			jsonObj.put("avgcpu", String.valueOf(AverageMemorySize));
			jsonObj.put("", MaxMemorySize);
			
			
			JSONObject jsonobject= new JSONObject();
			
			jsonobject.put("sampletranscation", jsonObj);
			System.out.println(jsonobject);
			 try
			 {
				 	FileWriter file = new FileWriter("CPUJson.json");
			        file.write(jsonobject.toString(3).replace("=", ":"));
			        file.flush();
			        }
			        catch(IOException e)
			        {
			        e.printStackTrace();
			        }
		}

	public static void main(String[] args) throws ParseException {
		CPU1 obj = new CPU1();
		obj.Reader();
	}

}