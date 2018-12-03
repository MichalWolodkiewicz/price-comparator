# price-comparator
Demo application presents performance difference between classic and reactive spring web approaches.

### Application infrastructure
![Application infrastructure](res/infra.png)

The following modules create the whole environment:
 - price-provider-registry - service discovery server using netflix eureka implementation
 - price-provider - eureka client with single rest endpoint that returns given product price
 - price-comparator - uses all registered in eureka services to collect prices for a given product. Application is based on classic spring mvc.
 - price-comparator - uses all registered in eureka services to collect prices for a given product. Application is based on new spring reactive support.

### Run the application
To start the applicaton type the following command:

<code>docker-compose up --build</code>

### Scale the number of price providers
Using docker compose it is very easy to increase number of active price providers. For example
to create 4 instances of price-provider in runtime, type the following command:

<code>docker-compose scale price-provider=4</code>

### Endpoints

localhost:8081 - eureka monitoring panel
localhost:8082/api/prices/product/{productName} - GET rest endpoint which returns all available prices. Uses spring classic mvc. 
localhost:8083/api/prices/product/{productName} - GET rest endpoint which returns all available prices. Uses react spring approach.

Product name are defined in price-provider/src/main/resources/application.properties file.