# Sarah Depew's Computer Science Senior Project - Visualizing Algorithms in Android

# Instructions on How to Set Up the Running Environment: 
  - Download and set up Android Studio: 
	  * https://developer.android.com/studio/index.html
  - Create a virtual device in Android Studio (I used the Nexus 6 and 5x) or obtain hardware (I used an LG Rebel 3): 
    * https://developer.android.com/studio/run/managing-avds
  - Fork/download the GitHub repo (please use the main branch for your interactions with the application)
    * https://github.com/sdepew97/SeniorProject   

# Instructions on How to Run the Project: 
  - Build and run the code and interact with the application on the virtual device/hardware
  - Please note you may need to select and launch a virtual device (this oftentimes takes a lot of time…)
  - You can run the app by pressing the green arrow to the left of the lightning bolt on the upper bar of the Android Studio IDE 
  - If this gives you trouble, try running from the Run top menu selection
  - If this fails, then Google any errors to troubleshoot

# Instructions on How to Run the Tests: 
  - Please first follow the above instructions on setting up the repository and building the code
  - To Run Unit Tests: 
    * Right click on the com.example.apphomepages (test) package 
    * Select the Run 'Tests in 'apphompages'' option  
  - To Run Espresso UI Tests: 
    * Right click on com.example.apphomepages (androidTest) package
    * Select the Run 'Tests in 'com.exampl...' option  
    * Select the deployment target (hardware or virtual) 
    * Click on the "OK" button
 
 # Known Bugs/Oddities 
   - If at any point when you are running the tests you get a series of errors thrown saying "ClassNotFoundException" or "NoClassDefFoundError", go to File > Invalidate Caches/Restart... and select this option. Then, after the cache invalidation and restart process is done, attempt running the tests again.
   - Sometimes the nodes in the depth-first and breadth-first search visualizations have edges that overlap or nodes overlap on the screen. To fix this issue, simply click "Generate" until there is a suitable, clear graph. 
   - If the automated tests get stuck for longer than three to five minutes, then simply rerun them. 
