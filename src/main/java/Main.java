import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;


public class Main {

    public static void main(String[] args) {

        try {

            File jsonConfig = new File("%PROGRAMDATA%/SteelSeries/SteelSeries Engine 3/coreProps.json");

            InputStream is = new FileInputStream(jsonConfig);

            JSONTokener tokener = new JSONTokener(is);

            JSONObject jsonObject = new JSONObject(tokener);

            

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
