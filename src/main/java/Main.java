import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Timer;

import com.gui.GUI;
import com.steelseries.*;
import com.utilities.HttpRequester;
import com.utilities.Utility;
import org.json.JSONObject;
import org.json.JSONTokener;

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

                    final HttpRequester requester = new HttpRequester(address);
                    final String app = "STEELSERIES_COLORCHANGER";
                    final String event = "CHANGE_COLOR";

                    RegisterGame rg = new RegisterGame(app, "Color Changer", 5);
                    rg.send(requester);

                    JSONObject[] sced = new JSONObject[3];
                    sced[0] = new SingleColorEventDescriptor(1,1,Color.GREEN).toJSONObject();
                    sced[1] = new SingleColorEventDescriptor(2,2,Color.YELLOW).toJSONObject();
                    sced[2] = new SingleColorEventDescriptor(3,3,Color.RED).toJSONObject();

                    JSONObject[] fceh = new JSONObject[1];
                    fceh[0] = new FixedColorEventHandler("headset","color","earcups",sced).toJSONObject();

                    BindEvent re = new BindEvent(app,event, 1, 100, 6, fceh);
                    re.send(requester);

                    //generate the heartbeat event that keeps up the app on the Steelseries engine.
                    Timer timer = new Timer();
                    timer.schedule(new GameHeartBeat(app,requester), 0, 14000);

                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            try {
                                GUI window = new GUI(requester, app, event);
                                window.setVisible(true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

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
