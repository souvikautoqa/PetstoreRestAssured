package core;


import java.util.HashMap;
import java.util.Map;

public class Config {

    public static String ENV = System.getenv("env");
    public static HashMap petStoreUserKey = new HashMap() {{put("api_key", "special-key");}};


}
