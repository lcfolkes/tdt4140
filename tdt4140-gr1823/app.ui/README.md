app.ui submodule  
======   
 
A class diagram for the app.ui-submodule is shown here: [Class diagram - App.ui](https://github.com/torbognaes/PU/blob/master/UML%20Diagram%20AppUI%20ny.png).  
 
An overview of the classes:  
 
- **HomeScreen**: The homescreen-tab for the application.   
- **AnalyzeScreen**: The analyze-tab for the application.  
- **FxApp**: The runnable .java-file, which is used to start the application for "Helsedirektoratet".   
- **UserScreen**: The runnable.java-file, which is used to start the applicaton for data-providers.  
- **Application**: Application class from which JavaFX applications extend.  
- **SceneNavigator**: Handles the functionality for switching between tabs.  
- **FxAppController**: Initializes the stage for FxApp.  
 
- **FxFilteringController**: Controller for the filtering-functionality.  
- **ServiceProviderController**: Connects **FxSettingsController**, **FxAnalyzeController** and **HomeScreenController**.  
- **FxSettingsController**: Connects the functionality for the settings-tab with UI.  
- **FxAnalyzeController**: Connects the functionality for the analyze-tab with UI.  
- **HomeScreenController**: Connects the functionality for the HomeScreen-tab with UI.  
- **UserScreenController**: Connects the functionality for the UserScreen with UI (For the data-provider-application).  
- **LoginScreenController**: Switches scenes from login-window to home screen-window by using **SceneNavigator**.    
- **Initializable**: Controller initialization interface.  

