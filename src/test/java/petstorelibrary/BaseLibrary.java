package petstorelibrary;

import core.*;
import datamodel.Category;
import datamodel.Pet;
import datamodel.Tag;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseLibrary {

    private RestSession session;
    private static HashMap<String,Map> petData;

    @BeforeTest
    public void initTest() throws Exception {
        Environments.load();
        petData =  Data.getData();
    }

    @BeforeClass
    public void createPets() throws Exception {
        for (Map.Entry petdata : getPetData().entrySet()){
             Map data = (Map)(petdata.getValue());
             session = new RestSession();
             Response resp = session.sendRequest(APIMethods.CREATE_PET,getPetBody(data), Config.petStoreUserKey);
             Assert.assertEquals(resp.getStatusCode(),200);
        }
    }

    public HashMap<String, Map> getPetData(){
        return petData;
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

    public int getURLResponse(String URL) throws Exception {
        URL url = new URL(URL);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int code = conn.getResponseCode();
        return code;
    }



}
