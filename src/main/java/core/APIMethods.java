package core;

public class APIMethods {

    public static APIMethod CREATE_PET = new APIMethod(Environments.getPetStoreEndPoint(), "pet", HttpMethod.POST);
    public static APIMethod UPDATE_PET = new APIMethod(Environments.getPetStoreEndPoint(), "pet", HttpMethod.PUT);
    public static APIMethod GET_PET = new APIMethod(Environments.getPetStoreEndPoint(), "pet/@@param", HttpMethod.GET);
    public static APIMethod DELETE_PET = new APIMethod(Environments.getPetStoreEndPoint(), "pet/@@param", HttpMethod.DELETE);

}
