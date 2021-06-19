package petstoretest;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import petstorelibrary.BaseLibrary;
import java.util.Map;

public class PetStoreTests extends BaseLibrary {

    @Test(priority = 1)
    public void verifyPetsCreated() throws Exception {
        createPets();
        Assert.assertEquals(getPet( Integer.parseInt((String) getPetData().get("monkey").get("id"))).statusCode(),200);
        Assert.assertEquals(getURLResponse((String) getPetData().get("monkey").get("photo")),200);
        Assert.assertEquals(getPet( Integer.parseInt((String) getPetData().get("cat").get("id"))).statusCode(),200);
        Assert.assertEquals(getURLResponse((String) getPetData().get("cat").get("photo")),200);
        Assert.assertEquals(getPet( Integer.parseInt((String) getPetData().get("dog").get("id"))).statusCode(),200);
        Assert.assertEquals(getURLResponse((String) getPetData().get("dog").get("photo")),200);
    }

    @Test(priority = 2)
    public void verifyPetsUpdated() throws Exception {
        Map dataDog = getPetData().get("dog");
        dataDog.put("name","Ronnie");
        updatePet(dataDog);
        Response resp = getPet(Integer.parseInt((String) getPetData().get("dog").get("id")));
        Assert.assertEquals(resp.body().jsonPath().get("name"),"Ronnie");
    }

    @Test(priority = 3)
    public void verifyPetsDeleted() throws Exception {
        deletePet(Integer.parseInt((String) getPetData().get("monkey").get("id")));
        Assert.assertEquals(getPet(Integer.parseInt((String) getPetData().get("monkey").get("id"))).statusCode(),404);
    }

}
