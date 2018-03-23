**Simple Stock REST Application**
====================================

This application exposes four REST endpoint by which we can manage the stock items. All operation are happening in memory.

**Simple Front End:** 

      -To see the list of available stock, just run the application and open following link http://127.0.0.1:8080/ in your browser.

**Stack Details:**

      - Maven as a build tool.
      - Spring Boot as a framework.
      - H2 as the in memory database.

**Minimum System Requirements to run the project**

      - Java 8
      
**How to run application ?**

    1. Use Precompiled Jar
      - Download from here(https://drive.google.com/open?id=1Jmz-cnuDp3wI9WOvbmnov4YSrhpcu4jY) & simply run with java -jar <filename>
      
    2. Build from source
      - Install & Setup Maven.
      - Clone this project.
      - Go to root folder.
      - Fire command "mvn clean package"
      - It will run all the unit tests,compile the code & generate the final jar under the target folder there.
      - Run generated jar from the last step. java -jar target/SimpleStockRESTApp-1.0-SNAPSHOT.jar
  

**API Documentation via Swagger**
  
    To see the API documentation & for trying out the api call. Please run the application and go to the following page.
      - Swagger UI : http://127.0.0.1:8080/swagger-ui.html
    
    
    
**REST endpoints details:**

  1. Get All stocks.
  
         Method : GET 
         URL    : http://127.0.0.1:8080/api/stocks
         
  2. Get a stock by id.
    
         Method : GET 
         URL    : http://127.0.0.1:8080/api/stocks/{id}
   
  3. Create a stock.
      
         Method : POST
         URL    : http://127.0.0.1:8080/api/stocks
         Body   : { "name" : "APPLE", "currentPrice": "USD 182.14"}
  
  4. Update an existing stock.
        
         Method : PUT
         URL    : http://127.0.0.1:8080/api/stocks/{id}
         Body   : { "currentPrice": "EUR 153.47"}
                          
     