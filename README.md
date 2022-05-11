# Projects-Manager
A desktop application to maintain the reported software bugs by the users with an interactive environment for the users to comment, react, etc.


## How to set up the Project
1- Download JAVAFX package: https://openjfx.io/
  - Add to the project libraries the JavaFX library, e.g. C:\Program Files\Java\javafx-sdk-17.0.2\lib.
  - Add to the project as a VM Options the following:
  <p>
  --module-path
  "C:\Program Files\Java\javafx-sdk-17.0.2\lib"
  --add-modules
  javafx.controls,javafx.fxml
  </p>

<br>

2- Download the GSON package: https://search.maven.org/artifact/com.google.code.gson/gson/2.9.0/jar
  - Add the path of the GSON jar file that you have donwloaded to the modules of the project structure.
