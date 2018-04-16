Project Name
=======
Software Engineering Project - Group 23 - Spring 2018.
Project Description
=======
The application gathers data about individuals daily activity level represented through their number of steps walked. The information is stored in a remote database, and the application allows for filtering, comparison and presentation of the data through a GUI. The application is created for the Norwegian Directorate of Health ("Helsedirektoratet") with the purpose of enabling research and monitoring of Norwegians exercise- and activity levels. 
The origin of the project was through the course [TDT4140 - Software Engineering](https://www.ntnu.edu/studies/courses/TDT4140#tab=omEmnet) at the Norwegian University of Science and Technology in 2018. 
Login
=======
In order to log in and use the application, use the following;   
- Username: helsedirektoratet   
- Password: Helsedirektoratet1   

Requirements
=======
The application was created through the [Eclipse IDE](http://www.eclipse.org/downloads/eclipse-packages/). The [Operating Instructions](#operating-instructions) below is created with the Eclipse IDE in mind. 
JavaFX is the preferred framework for graphics and GUI. It is standard as part of Java 7 and Java 8. Alternatively one can install the [e(fx)clipse](http://www.eclipse.org/efxclipse/install.html)-plugin to Eclipse. 
When creating and modifying the GUI with JavaFX, [Scene Builder](http://gluonhq.com/products/scene-builder/) was utilized to construct GUI-elements, such as Text Fields and Buttons, and more. After installing, open Eclipse, select the _Window_-tab --> _Preferences_ --> _JavaFX_ and locate the Scene Builder executable file. 
Maven is used as build system for the project, installation link is found under [Build With](#build-with) below.
## Installation of the project
The project is hierarchically organized with a top-level-module **tdt4140-gr1823** and three sub-modules in accordance with the Maven build system. 
First, clone the repository from GitLab and then import the project into Eclipse. To import the project into Eclipse, select _File_ --> _Import..._ --> _Existing Projects into Workspace_. Then, select all sub-module projects by checking the box _nested projects_.
The submodules below will then be available. Follow the links to go their individual README-files which also include class-diagrams:
-  [tdt4140-gr1823.app.core](https://gitlab.stud.iie.ntnu.no/tdt4140-2018/23/tree/master/tdt4140-gr1823/app.core/README.md): Located in the app.core folder and contains the core functionaly, database-communication as well as web-server communication methods.
-  [tdt4140-gr1823.app.ui](https://gitlab.stud.iie.ntnu.no/tdt4140-2018/23/tree/master/tdt4140-gr1823/app.ui/README.md): Located in the app.ui folder. Includes the JavaFX app. 
-  [tdt4140-gr1823.app.web.server](https://gitlab.stud.iie.ntnu.no/tdt4140-2018/23/tree/master/tdt4140-gr1823/app.core/README.md): Located in the web.server. Includes the web-servlets.    

## Operating Instructions
The application runs through one core .java class located inn the **tdt4140-gr1823.app.ui**-subfolder **src/main/java**, called **FxApp.java**.  With the help of the class **SceneNavigator.java**, the **FxApp.java** class manages to switch between the login-view and the analyze-view. By right-clicking on **FxApp.java** and selecting _Run As_ --> _Java Application_, the following [login window](https://github.com/torbognaes/PU/blob/master/loginWindow.PNG) will be presented (follow the link).   

After logging in, the [overview-window](https://github.com/torbognaes/PU/blob/master/overviwWindow1.PNG) and [overview-window 2](https://github.com/torbognaes/PU/blob/master/overViewWindow2.PNG) will be presented. The application is connected to a remote database that includes: The recommended daily activity level (set by "Helsedirektoratet") and daily step-data for the app-users, both of which are present in the overview window, as well as other user-related data. 
By selecting the [analyze-window](https://github.com/torbognaes/PU/blob/master/analyzeWindow.PNG), the user can filter step-data by **Gender** and/or **Age group**. The Gender drop-down list includes three alternatives; _MALE_, _FEMALE_ as well as the default value (_BLANK_) which represents both male and female. _AGE_ is filtered through a _FROM_-field and a _TO_-field. If only _TO_ is specified, all users up to this value will be presented, whereas if only the _FROM_ field is specified all users from that particular value and up will be presented. It is also possible to leave both the _TO_ and the _FROM_ field blank, the application will then return all the data within the chosen GENDER-segment (e.g. if _FEMALE_ is chosen and _TO_ and _FROM_ is left blank, the average of all the female users will be returned). 
In the [settings-window](https://github.com/torbognaes/PU/blob/master/settingsWindow.PNG) the user can change their username and password, it is also possible to change the recommended daily activity level.   

There is also created an own window for the data-provider-users of the application (i.e. the users that are intended to provide the data which "Helsedirektoratet" can analyse). By selecting _Run as_ --> _1. Java application_ on **UserScreen.java** in the ui-folder **tdt4140-gr1823.app.ui**, the following window will occur: [Log-in window](https://github.com/torbognaes/PU/blob/master/Data-provider%20log-in.png).
When the data-provider has successfully logged in, they will be provided with the following window: [Logged-in window](https://github.com/torbognaes/PU/blob/master/Data-provider%20logged-in.png). Here they can set their data-sharing preference and see their own activity
 The functionality for the data-providers is created with the purpose of satisfying the project-requirements which states that the data-providers should be able to see their own data as well as be able to choose whether or not they want their data to be shared with "Helsedirektoratet".
## Tests
The system has integrated the Maven build- and dependency management tool. If you want to run Maven from the Command-prompt/Terminal, it can be installed from their official website. 
To run Maven in Eclipse, select the top folder in the hierarchy **tdt4140-gr1823** and _Run as_, several Maven-related options then occur including _6 Maven Test_ which runs all the JUnit tests in the project. The same operation can be performed on specific sub-folders in the hierarchy, such as for the functionality in the core-folder **tdt4140-gr1823.app.core**.   

Individual JUnit and JavaFX tests can be run from their respective .java-files. The tests are located in the respective **src/test/java-subfolders**. 
A dependency for the JUnit test framework is defined in the **pom.xml**-file, this file is used by Maven and Eclipse. Furthermore, in all the sub-folders on the level below the root node (**tdt4140-gr1823**) there exists a **Maven Dependencies**-folder where one can find all the dependencies, including JUnit.
In order to run all the tests in the web-server one need to run Maven-_integration-test_. This can be done by choosing _4. Maven build..._ and in the _Goal_-field typing **integration-test**.
 
Build with
-
[Maven](https://maven.apache.org/) - Dependency Management.
## Authors
* Amanda Krutnes
* Andreas Storhaug Bøstrand 
* Anders Blesvik Gandrud 
* Erlend Hjelle Strandkleiv
* Lars Christian Ek Folkestad
* Tor Erlend Bognæs 