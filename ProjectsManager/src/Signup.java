import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Signup  {
    @FXML private TextField newUsernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton, SignUpButton;
    @FXML private Label msg;


    public void logIn (ActionEvent event) throws Exception{ //go to login page
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void singup (ActionEvent event) throws Exception{

        //if the user does not exist, create a new user:
        Users user1 = new Users();
        String username = newUsernameField.getText(); //get the username
        user1.setUsername(username); //give user1 object the username entered
        String password = passwordField.getText();  //get the password
        user1.setPassword(password); //give user1 object the password entered
        //now the new user is created and user1 object is holding the info

        Company.setUser_who_entered(username); //this is to know who singed up for other operations in other classes.

        Company company = new Json().get_company_info(); //to get all the information from the JSON file

        if (company!=null){ //check if the JSON file is not empty
            user1.setUserid(company.getUsers().size()+1); //give user1 object id number
            boolean check_if_user_existed = false;
            for(int x=0; x<company.getUsers().size(); x++) { //to check if the user already existed
                if (company.getUsers().get(x).getUsername().equalsIgnoreCase(username)){
                    check_if_user_existed= true;
                    break;
                }
            }

            if(check_if_user_existed){ //if the user already existed
                msg.setText("Username exists! Please enter another username.");
            }
            else if(username.matches(".*\\s.*")|| username.isEmpty()||password.matches(".*\\s.*")||password.isEmpty()){
            /*
               - The first ".*" says that there can be zero or more instances of any character in front of the space.
               - The "\\s" says it must contain any whitespace character.
               - The last ".*" says there can be zero or more instances of any character after the space.
                */
                msg.setText("Enter correct username!");
            }

            else{//if the user not already existed:
                company.addUsers(user1);
                msg.setText("User added successful");
                new Json().update_company_info(company);//to write all the values in a json file

                //go to project dashboard now:
                Parent root = FXMLLoader.load(getClass().getResource("projectDashboard.fxml"));
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            }
        }

        else { //if the JSON file is empty then:
            if (username.matches(".*\\s.*") || username.isEmpty()|| password.matches(".*\\s.*")||password.isEmpty()) {
                msg.setText("Enter correct username!");
            }
            else{
                Company company1 = new Company(); //create a new object of the company
                user1.setUserid(1); //set the user id to 1, because there are no users before
                company1.addUsers(user1); //add the user to the arraylist of users in the company object
                msg.setText("User added successful");
                new Json().update_company_info(company1);//to write all the values in a json file

                //go to project dashboard now:
                Parent root = FXMLLoader.load(getClass().getResource("projectDashboard.fxml"));
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

            } //end of the else
        }
    }

}
