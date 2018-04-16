app.web.server submodule
======   
A class diagram for the app.web.server-submodule is shown here: [Class diagram - App.web.server](https://github.com/torbognaes/PU/blob/master/UML%20Diagram%20WebServer.png).  
 
An overview of the classes:  
- **HttpCommunicationTestIT**: Tests all of the functionality in the class HttpCommunication.java that is located in app.core.  
- **dailyActivityServlet**: Servlet that makes it possible to fetch the daily activity from the database over http.   
- **getRecommendedDailyActivityServlet**: Servlet that makes it possible to fetch the recommended daily activity from the database over http.   
- **nationalAverageServlet**: Servlet that makes it possible to fetch the national average from the database over http.   
- **ShareDataServlet**: Servlet that makes it possible to set and get the share value for a data provider in the database over http.  
- **HttpServlet**: Abstract class to be subclassed by the other servlets (the four above), to create an HTTP servlet.

