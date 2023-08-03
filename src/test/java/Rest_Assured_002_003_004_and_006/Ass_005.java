package Rest_Assured_002_003_004_and_006;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;




public class Ass_005
{
    @Test
    public void validate_findByStatus()
    {
        findByStatus("available");
        findByStatus("pending");
        findByStatus("sold");
    }


    public void findByStatus(String txt_Status)
    {
try
{

          String endpoint = "https://petstore.swagger.io/v2/pet/findByStatus?status=" + txt_Status;
          Response response = RestAssured.get(endpoint);
          System.out.println(response.asString());
          int statusCode = response.getStatusCode();
          Assert.assertEquals(statusCode, 200);
    // Validate pet details for each pet in the response
    Pet[] pets = response.as(Pet[].class);
    for (Pet pet : pets) {
        System.out.println(pet.getStatus());
        Assert.assertEquals(pet.getStatus(), txt_Status);

    }

}
catch (Exception e)
{
    System.out.println(e);
}



    }
}
class Pet
{

    private String status;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
