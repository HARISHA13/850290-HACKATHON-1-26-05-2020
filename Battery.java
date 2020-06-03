import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.swing.text.AbstractDocument.BranchElement;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

public class Battery {

	String filepath = "Battery.txt";

	void Reader() {
		String lineText = null;
		String foreValve = null;
		String batteryDrainValue = null;
		int line = 0;
		double batteryPercentage = 0.0;

		try {
			BufferedReader lineReader = new BufferedReader(new FileReader(filepath));
			while ((lineText = lineReader.readLine()) != null) {
				if (lineText.startsWith("    Uid u0a202")) {
					batteryDrainValue = lineText.substring(16, 21);
					break;
				}
				line++;
			}
			while ((lineText = lineReader.readLine()) != null) {
				if (lineText.startsWith("    Foreground activities:")) {
					foreValve = lineText.substring(27, 59);
					break;
				}
				line++;
			}
			batteryPercentage = Double.parseDouble(batteryDrainValue) / 1000;
			DecimalFormat decimalFormat = new DecimalFormat("###.######");
			batteryPercentage = Double.parseDouble(decimalFormat.format(batteryPercentage));



		} catch (IOException ex) {
			System.err.println(ex);
		}

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("Foreground_Time", foreValve);
		jsonObject.put("Battery_Percentage", batteryPercentage);
		jsonObject.put("Battery_DrainValue", batteryDrainValue);
		System.out.println(jsonObject);
		try {
			FileWriter file = new FileWriter("BAtteryJson.json");
			file.write(jsonObject.toString(3).replace("=", ":"));
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Battery batteryDetails = new Battery();
		batteryDetails.Reader();

	}

}