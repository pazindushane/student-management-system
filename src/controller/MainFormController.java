package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import model.Student;
import util.crudUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {

    public AnchorPane context;
    public JFXTextField studentId;
    public JFXTextField studentName;
    public JFXTextField studentMail;
    public JFXTextField studentContact;
    public JFXTextField studentAddress;
    public JFXTextField studentNIC;
    public JFXButton addBtn;
    public JFXButton updateBtn;
    public JFXButton deleteBtn;
    public JFXTextField searchStudentId;
    public JFXButton searchBtn;


    public void updateBtn(ActionEvent actionEvent) {
        String newId = studentId.getText();
        String newName = studentName.getText();
        String newMail = studentMail.getText();
        String newContact = studentContact.getText();
        String newAddress = studentAddress.getText();
        String newNic = studentNIC.getText();

        Student student = new Student(newId,newName,newMail,newContact,newAddress,newNic);

        try{
            if(crudUtil.execute("UPDATE student SET studentName=?,email=?,contact=?,address=?,nic=? WHERE studentId=?",student.getStudentName(),student.getStudentEmail(),student.getStudentContact(),student.getStudentAddress(),student.getStudentNic(),student.getStudentId())){
                new Alert(Alert.AlertType.CONFIRMATION, "Updated Student Details!..").show();
            }
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING, "Something went Wrong!..").show();
        }
    }

    public void deleteBtn(ActionEvent actionEvent) {
        String Id = studentId.getText();

        try{
            if (crudUtil.execute("DELETE FROM student WHERE studentId=? ",Id)){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted Student!..").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Something went Wrong!..").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void addBtn(ActionEvent actionEvent) {
        String id = studentId.getText();
        String name = studentName.getText();
        String mail = studentMail.getText();
        String contact = studentContact.getText();
        String address = studentAddress.getText();
        String nic = studentNIC.getText();

        Student student = new Student(id,name,mail,contact,address,nic);

        try{
            if(crudUtil.execute("INSERT INTO student VALUES (?,?,?,?,?,?)",student.getStudentId(),student.getStudentName(),student.getStudentEmail(),student.getStudentContact(),student.getStudentAddress(),student.getStudentNic())){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved Student!..").show();
            }
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING, "Something went Wrong!..").show();
        }

    }

    public void searchBtn(ActionEvent actionEvent) {
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
