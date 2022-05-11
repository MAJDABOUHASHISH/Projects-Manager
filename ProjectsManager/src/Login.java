import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class Login {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label msg;


    public void signUp (ActionEvent event) throws Exception{ //go to signup page
        Parent root = FXMLLoader.load(getClass().getResource("Signup.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void login (ActionEvent event) throws Exception{

        String username = usernameField.getText(); //get the input of the username
        String password = passwordField.getText(); //get the input of the password

        Company.setUser_who_entered(username); //this is to know who logged in for other operations in other classes.

        Company company = new Json().get_company_info(); //get the info from JSON file.

        boolean check_if_username_existed=false;
        if(company!=null){ //if the json file is empty then the company is null, but if it is not then this will run:
            for(int x=0; x<company.getUsers().size(); x++){ //to check for the user if already existed, by going through the users
                if(company.getUsers().get(x).getUsername().equalsIgnoreCase(username)){ //if the username is exited then:
                    if(company.getUsers().get(x).getPassword().equalsIgnoreCase(password)) { //if the password is also correct then:
                        msg.setText("Login Successful!");
                        check_if_username_existed=true;

                        //go to project dashboard now:
                        Parent root = FXMLLoader.load(getClass().getResource("projectDashboard.fxml"));
                        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        stage.setScene(new Scene(root));
                        stage.show();
                        //new Project_Dashboard().DisplayProject();
                    } //end of the if
                    else{ //if the password not correct
                        msg.setText("Incorrect Password" + " (try again)");
                        check_if_username_existed=true;
                    } //end of the else
                } //end of the if
            } // end of the for loop
            if(check_if_username_existed==false) msg.setText("The username does not exist! Please Sign up");
        } //end of the if
        else {//go to sign up
            msg.setText("The username does not exist! Please Sign up");
        }
    }

}