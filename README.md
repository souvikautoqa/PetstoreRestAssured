# **PetStoreRestassured** - API Testing Framework to test https://petstore.swagger.io/v2

> Tested APIs --
 - CreatePet
 - UpdatePet
 - DeletePet
 
> Tools Used for testing --
 - RestAssured Framework 
 - GSON
 - TestNG Framework
 
> How to execute -- 

This is a maven project and its enabled to handle configurations and data source for following environments
 - DEV
 - INT
 - PREPROD
 - PROD
 
The test can be triggered with the following command

`mvn clean test -Denv=[ENVIRONMENT]`

> Report Generation --

Reports are automatically generated as a part of TestNG framework and it can be found under

`/target/surefire-reports/index.html`

> Folder structure
 - API Test :   `/test/java/petstoretest`
 - Common Library : `/test/java/petstorelibrary`
 - POJO Classes : `/main/java/datamodel`
 - API Method List : `/main/java/core/APIMethods`
 - Data Source : `/test/java/data`