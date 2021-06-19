package core;

public class APIMethods {

    public static APIMethod CREATE_PET = new APIMethod(Environments.getPetStoreEndPoint(), "pet", HttpMethod.POST);
    public static APIMethod UPDATE_PET = new APIMethod(Environments.getPetStoreEndPoint(), "pet", HttpMethod.PUT);
    public static APIMethod GET_PET = new APIMethod(Environments.getPetStoreEndPoint(), "pet/@@param", HttpMethod.GET);
    public static APIMethod DELETE_PET = new APIMethod(Environments.getPetStoreEndPoint(), "pet/@@param", HttpMethod.DELETE);
    public static APIMethod CREATE_USER = new APIMethod(Environments.getPetStoreEndPoint(), "user", HttpMethod.POST);
    public static APIMethod LOGIN_USER = new APIMethod(Environments.getPetStoreEndPoint(), "user/login", HttpMethod.GET);
    public static APIMethod GET_PET_BY_STATUS = new APIMethod(Environments.getPetStoreEndPoint(), "pet/findByStatus", HttpMethod.GET);
    public static APIMethod BUY_PET = new APIMethod(Environments.getPetStoreEndPoint(), "store/order", HttpMethod.POST);
    public static APIMethod GET_ORDER_BY_ID = new APIMethod(Environments.getPetStoreEndPoint(), "store/order/@@param", HttpMethod.GET);

}
