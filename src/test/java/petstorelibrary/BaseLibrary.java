package petstorelibrary;

import core.*;
import datamodel.Category;
import datamodel.Pet;
import datamodel.Tag;
import datamodel.User;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class BaseLibrary {

    private RestSession session;
    private static HashMap<String,Map> testData;

    @BeforeTest
    public void initTest() throws Exception {
        Environments.load();
        testData =  Data.getData();
    }

    public void createPets() throws Exception {
        for (Map.Entry petdata : getPetData().entrySet()){   
             Map data = (Map)(petdata.getValue());
             session = new RestSession();
             Response resp = session.sendRequest(APIMethods.CREATE_PET,getPetBody(data), Config.petStoreUserKey);
             Assert.assertEquals(resp.getStatusCode(),200);
        }
    }

    public void createUsers() throws Exception {
        for (Map.Entry userdata : getUsersData().entrySet()){
            Map data = (Map)(userdata.getValue());
            session = new RestSession();
            Response resp = session.sendRequest(APIMethods.CREATE_USER,getUserBody(data), Config.petStoreUserKey);
            Assert.assertEquals(resp.getStatusCode(),200);
            Assert.assertEquals(resp.body().jsonPath().get("message"), data.get("id"));
        }
    }

    public void loginUsers() throws Exception {
        for (Map.Entry userdata : getUsersData().entrySet()){
            Map data = (Map)(userdata.getValue());
            session = new RestSession();
            Map<String,String> userCreds = new HashMap<>();
            userCreds.put("username",(String) data.get("username"));
            userCreds.put("password",(String) data.get("password"));
            Response resp = session.sendRequest(APIMethods.LOGIN_USER,userCreds, Config.petStoreUserKey);
            Assert.assertEquals(resp.getStatusCode(),200);
            Assert.assertEquals(resp.body().jsonPath().get("message").toString().contains("logged in user session"), true);
        }
    }

    public List<Integer> getPugDogs() throws Exception {
        List<Integer> dogIds = new ArrayList<>();
        Map<String,String> status = new HashMap<>();
        status.put("status","available");
        session = new RestSession();
        Response resp = session.sendRequest(APIMethods.GET_PET_BY_STATUS,status, Config.petStoreUserKey);

        JsonPath jsonPath =  resp.getBody().jsonPath();
        List<Object> pugs = jsonPath.getList("", Object.class);

        System.out.println();
        return  null;
    }

    public HashMap<String, Map> getPetData(){
        return (HashMap) testData.get("pet");
    }

    public HashMap<String, Map> getUsersData(){
        return (HashMap) testData.get("users");
    }

    public Response getPet(int id) throws Exception {
        APIMethods.GET_PET.addMethodParam(String.valueOf(id));
        session = new RestSession();
        Response resp = session.sendRequest(APIMethods.GET_PET, Config.petStoreUserKey);
        return resp;
    }

    public Response updatePet(Map petData) throws Exception {
        session = new RestSession();
        Response resp = session.sendRequest(APIMethods.UPDATE_PET,getPetBody(petData), Config.petStoreUserKey);
        Assert.assertEquals(resp.getStatusCode(),200);
        return resp;
    }

    public void deletePet(int id) throws Exception {
        APIMethods.DELETE_PET.addMethodParam(String.valueOf(id));
        session = new RestSession();
        Response resp = session.sendRequest(APIMethods.DELETE_PET, Config.petStoreUserKey);
        Assert.assertEquals(resp.getStatusCode(),200);
    }

    private Pet getPetBody(Map data){
        Pet pet = new Pet();
        Category cat = new Category();
        Tag tag = new Tag();
        List<Tag> tagList = new ArrayList<>();
        List<String> photoUrls = new ArrayList<>();
        cat.setId(Integer.parseInt((String) data.get("categoryId")));
        cat.setName((String) data.get("categoryName"));
        tag.setId(Integer.parseInt((String) data.get("tagId")));
        tag.setName((String) data.get("tagName"));
        tagList.add(tag);
        photoUrls.add((String) data.get("photo"));
        pet.setId(Integer.parseInt((String) data.get("id")));
        pet.setName((String) data.get("name"));
        pet.setCategory(cat);
        pet.setTags(tagList);
        pet.setPhotoUrls(photoUrls);
        pet.setStatus((String) data.get("status"));
        return pet;
    }

    private User getUserBody(Map data){
        User user = new User();
        user.setId(Integer.parseInt((String) data.get("id")));
        user.setUsername((String) data.get("username"));
        user.setFirstName((String) data.get("firstName"));
        user.setLastName((String) data.get("lastName"));
        user.setEmail((String) data.get("email"));
        user.setPassword((String) data.get("password"));
        user.setPhone((String) data.get("phone"));
        user.setUserStatus(Integer.parseInt((String) data.get("userStatus")));

        return user;
    }

    public int getURLResponse(String URL) throws Exception {
        URL url = new URL(URL);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int code = conn.getResponseCode();
        return code;
    }

}
