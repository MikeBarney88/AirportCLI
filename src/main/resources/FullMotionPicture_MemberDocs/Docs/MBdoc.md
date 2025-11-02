# Michael Barney's Prospective
#### Date: October 31, 2025

### How it went

The sprint was a learning experience in building a full-stack REST API. 
We successfully created a Spring Boot backend with four core entities (City, Airport, Passenger, Aircraft) and their relationships, 
along with a separate CLI client to work with the API. The project required balancing learning new concepts with meeting deadlines, 
which was challenging but rewarding.

The development process involved several iterations and refactoring cycles as I learned best practices and discovered more efficient ways
to structure the code. Working through the JPA relationships, understanding how Spring Boot handles REST
controllers, and implementing proper exception handling all contributed to a deeper understanding of Java development.

### What was good

I had a great time learning about HTTPRest and RESTClient there was a bit of a learning curve to get it down path. The GitHub branching and PR was something that is
very helpful in a group project, i kind of wish we had learned this early in the program. 

### what was bad

We ran into a few snags on mocking tests and using the HTTPRestCLI/RESTClient as well as some PR's having some conflicts, but ultimately we figured out the issues and were
able to move past and continue on. all in all it was a great learning experience.

### what can be improved

Writing tests alongside feature development rather than at the end would have caught issues sooner and made refactoring less risky. Test-driven development (TDD) 
should be considered for future projects.

Our API endpoints ended up with inconsistent patterns (e.g., /cities vs /city/{id}, /aircrafts vs /aircraft). Establishing naming conventions at the 
start would have helped.