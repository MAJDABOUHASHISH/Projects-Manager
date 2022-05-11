import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProjectDashboard implements Initializable {

    @FXML private TableView<CustomProjects> tableView;
    @FXML private TableColumn<CustomProjects, Integer> id;
    @FXML private TableColumn<CustomProjects, String> name;
    @FXML private TableColumn<CustomProjects, Integer> issues;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Company company = new Json().get_company_info();

        ArrayList<CustomProjects> projects = new ArrayList<>(); //this list will hold each project with values ID, name, number of issues

        for (Projects p : company.getProjects()) //assign projects list from database to project list as CustomProjects class templete
            projects.add(new CustomProjects(p.getId(),p.getName(),p.getIssues().size()));


        //connect columns to CustomProjects class variables
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        issues.setCellValueFactory(new PropertyValueFactory<>("issues"));

        ObservableList<CustomProjects> projectsList = FXCollections.observableArrayList(projects);

        tableView.setItems(projectsList);

    }





}
