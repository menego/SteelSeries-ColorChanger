import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.utilities.HttpRequester;
import com.utilities.Utility;
import org.json.JSONObject;
import org.json.JSONTokener;
import com.steelseries.RegisterGame;

public class Main {

    public static void main(String[] args) {

        try {
            if(Utility.isWindows()) {
                File jsonConfig = new File(System.getenv("PROGRAMDATA") + "/SteelSeries/SteelSeries Engine 3/coreProps.json");

                if (jsonConfig.exists()) {

                    InputStream is = new FileInputStream(jsonConfig);
                    JSONTokener tokener = new JSONTokener(is);
                    JSONObject jsonObject = new JSONObject(tokener);
                    String address = jsonObject.get("address").toString();

                    RegisterGame rg = new RegisterGame("STEELSERIES_COLORCHANGER", "Color Changer", 4);
                    HttpRequester requester = new HttpRequester(address);
                    requester.postRequest("/game_metadata", rg.toJSONObject());

                } else {
                    throw new Exception("SteelSeries configuration file not found.");
                }
            }
            //TODO: implement Mac support.
            else {
                throw new Exception("Operative System not supported");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
