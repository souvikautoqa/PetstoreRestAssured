package core;



import static core.Config.ENV;

public class Environments {

    private static String petStoreEndPoint;

    public static void load() {
        if (ENV.equalsIgnoreCase("DEV")) {
            petStoreEndPoint = "https://petstore.swagger.io/v2";
        }

        if (ENV.equalsIgnoreCase("INT")) {
            petStoreEndPoint = "https://petstore.swagger.io/v2";
        }

        if (ENV.equalsIgnoreCase("PREPROD")) {
            petStoreEndPoint = "https://petstore.swagger.io/v2";
        }

        if (ENV.equalsIgnoreCase("PROD")) {
            petStoreEndPoint = "https://petstore.swagger.io/v2";
        }
    }

    public static String getPetStoreEndPoint() {
        return petStoreEndPoint;
    }


}
