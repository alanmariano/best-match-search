# MealMapper
MealMapper is an app designed to help you decide where to eat.

## Stack

### Backend

The Backend was developed using Kotlin and Spring Boot. Gradle was used as a dependency manager.
Unit tests were implemented using JUnit and Mockk. They were implemented for business logic code, including Validators, Utils, Services, as well as for Controllers.

Code Quality was ensured through SonarLint extension on IntelliJ. No Bugs or Code Smells were created.

The Rest API implemented exposes the following endpoints (port 8080):

For Cuisines Names:
**GET** ``/api/cuisines/names``

For Restaurants Names:
**GET** ``/api/restautants/names``

For Restaurant Search
**GET** ``/api/restaurants/search``

The following parameters can be sent through the Restaurant Search Endpoint:
- ``name`` **Restaurant Name** (String)
- ``rating`` **Minimun Rating** (Numeric)
- ``distance`` **Maximum Distance** (Numeric)
- ``price`` **Maximum Price** (Numeric)
- ``cuisine`` **Cuisine Name** (String)

#### How to run Backend

- Run server: 
    ``.\gradlew bootRun``

- Run tests: 
    ``.\gradlew test``

#### Requirements

``JVM 17``

### Frontend

Even though it was not necessary, a simple web page was built to help demonstrate the use of the search functionality.
The Frontend was built in React 18 and E2E testing was implemented in Cypress. In order to accelerate development of some components, some libraries were used, like PrimeReact and Formik.

#### How to run Frontend

- Run app: 
    ``npm install``
    ``npm start``

- Run tests: 
    ``npm test``

#### Requirements

``npm 9.5.1``

## Premisses

- CSV files imported should follow the pattern provided, without any blank or null fields or lines
- Every Restaurant has to have at least one Cuisine
- Restaurant Name can not be null, empty or blank in a search.
- Cuisine Field does not need any validation

## Compromisses

- I18n was not implemented
- Unit tests on CSV Import were not implemented
- Error handling on FrontEnd shows only generic errors in most cases



