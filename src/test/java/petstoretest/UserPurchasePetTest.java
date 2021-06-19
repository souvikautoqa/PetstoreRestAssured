package petstoretest;

import org.testng.annotations.Test;
import petstorelibrary.BaseLibrary;

public class UserPurchasePetTest extends BaseLibrary {

    @Test
    public void verifyUsersPurchasingPets() throws Exception {
        createUsers();
        loginUsers();
        getPugDogs();
    }



}
