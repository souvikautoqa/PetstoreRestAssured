package core;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static core.Config.ENV;

public class Data {

    private static final String path = "src/main/java/data";
    private static Reader reader;
    private static Gson gson;

    public static HashMap<String,Map> getData() throws Exception {
        Type type = new TypeToken<HashMap<String, HashMap<String, Map<String,String>>>>(){}.getType();
        reader = new FileReader(path+"/data."+ENV.toLowerCase()+".json");
        gson = new Gson();
        Map data = gson.fromJson(reader,type);
        return (HashMap) data.get("pet");
    }
}
