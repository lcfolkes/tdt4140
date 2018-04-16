app.core submodule  
======   
The app.core submodule is into three parts:  
- **app.core**  
- **app.db**   
- **app.HttpCommunication**   
 
A class diagram for the app.core-submodule is shown here: [Class diagram - App.core](https://github.com/torbognaes/PU/blob/master/UML%20Diagram%20AppCore.png).  
 
The **app.core**-submodule includes:  
- **SPUser**: A class that creates a new Service Provider-user (user for "Helsedirektoratet") that meets certain requirements.  
- **DailyActivity**: Enables the creation of daily activity data for a  on the format in which they are stored in the database.   
- **User**: Enables the creation of dataprovider-users on the format in which they are stored in the database.  
- **Gender**: An Enum-class (female/male).    
 
The **app.db**-submodule contains classes which handles the database-operations:  
- **ActivityManager**: The class handles all step-data-operations, for both individual users as well as on an aggregate level. Utilizes the **DBManager**-class in order to communicate with the database. The individual methods open- and close the database-connections and includes the relevant SQL-queries.   
- **DBManager**: Execute the database-queries specified in the **ActivityManager**-class.   
- **SPManager**: Handles the creation and modification of Service Provider users as well as the functionality related to recommended daily activity.  
- **UserManager**: Implements getters used for finding the numbers of users when using the analyze-functionality. Furthermore, the class handles the functionality for changing the dataproviders preference for data-sharing.   
 
The **app.HttpCommunication**-submodule handles web-server communcation for the data provider:  
- **HttpCommunication**: Handles all communication over http for the dataprovider-functionality.

