# Project Name
Software Engineering Project - Group 23 - Spring 2018.

## Project Description
The application gathers data about individuals daily activity level represented through their number of steps walked. The information is stored in a remote database, and the application allows for filtering, comparison and presentation of the data through a GUI. The application is created for the Norwegian Directorate of Health ("Helsedirektoratet") with the purpose of enabling research and monitoring of Norwegians exercise- and activity level. 

The origin of the project was through the course [TDT4140 - Software Engineering](https://www.ntnu.edu/studies/courses/TDT4140#tab=omEmnet) at the Norwegian University of Science and Technology in 2018. 

## Requirements

The application was created through the [Eclipse IDE](http://www.eclipse.org/downloads/eclipse-packages/). The [Operating Instructions](#operating-instructions) below is created with the Eclipse IDE in mind. 

JavaFX is the preferred framework for graphics and GUI. It is standard as part of Java 7 and Java 8. Alternatively one can install the [e(fx)clipse](http://www.eclipse.org/efxclipse/install.html)-plugin to Eclipse. 

When creating and modifying the GUI with JavaFX, [Scene Builder](http://gluonhq.com/products/scene-builder/) was utilized to construct GUI-elements, such as Text Fields and Buttons, and more. After installing, open Eclipse, select the _Window_-tab --> _Preferences_ --> _JavaFX_ and locate the Scene Builder executable file. 

## Operating Instructions
The application runs through one core .java class located inn the **tdt4140-gr1823.app.ui**-subfolder **src/main"java**, called **FxApp.java**. With the help of the class **SceneNavigator.java**, the **FxApp.java** class manages to load the view and functionality of **MainScreen.fxml** and **FxFiltering.fxml** and their respective controllers as scenes in the primary stage set in **Fx.App.java**. By right-clicking on **FxApp.java** and selecting _Run As_ --> _Java Application_, the screen below will be presented (follow the link):

[MainScreen](https://github.com/torbognaes/PU/blob/master/MainScreenGUI.png) 
 
The application is connected to a remote database that includes; The recommended daily activity level (set by "Helsedirektoratet"), daily step-data for the app-users, other user-related data. The MainScreen-window shows the recommended daily activity level, which can be modified by setting a new value and pushing the **SET VALUE** button, the total number of users for the application as well as the national average daily steps among all the users.

By pushing the **FILTER STEPS** button, the following window will open:

[FilterScreen](https://github.com/torbognaes/PU/blob/master/FilterGUI.png)

This is the Filtering-window, which enables the user to analyze step-data from different user-segments based on the parameters **Gender** and/or **Age**. The Gender drop-down list includes three alternatives; _MALE_, _FEMALE_ as well as the default value (_blank_) which represents both male and female. _AGE_ is filtered through a _FROM_-field and a _TO_-field. If only TO is specified, all users up to this value will be presented, whereas if only the FROM field is specified all users from that particular value and up will be presented. It is also possible to leave both the TO and the FROM field blank, the application will then return all the data within the chosen GENDER-segment (e.g. if FEMALE is chosen and TO and FROM is left blank, the average of all the female users will be returned). 

## Tests
The system has integrated the Maven build- and dependency management tool. If you want to run Maven from the Command-prompt/Terminal, it can be installed from their official website (see link under [Build With](#build-with) below). 

To run Maven in Eclipse, select the top folder in the hierarchy **tdt4140-gr1823** and _Run as_, several Maven-related options then occur including _6 Maven Test_ which runs all the JUnit tests in the project. The same operation can be performed on specific sub-folders in the hierarchy, such as for the functionality in the core-folder "tdt4140-gr1823.app.core". 

Individual JUnit and JavaFX tests can be run from their respective .java-files. The tests are located in the respective **src/test/java"-subfolders**. 

A dependency for the JUnit test framework is defined in the pom.xml-file, this file is used by Maven and Eclipse. Furthermore, in all the sub-folders on the level below the root node (**tdt4140-gr1823**) there exists a **Maven Dependencies**-folder where one can find all the dependencies, including JUnit.

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

