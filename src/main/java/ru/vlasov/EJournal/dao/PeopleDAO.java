package ru.vlasov.EJournal.dao;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.springframework.stereotype.Component;
import ru.vlasov.EJournal.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class PeopleDAO {
    List<Participant> people;
    private static int PEOPLE_COUNT=0;
    private static final String URL="jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME="postgres";
    private static final String PASSWORD="PASSWORD";
    private static Connection connection;
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection= DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Participant> index(){
        ArrayList<Participant> people = new ArrayList<>();
        try {
            Statement statement= connection.createStatement();
            String CMND="SELECT MAX(id)as max FROM People";
            ResultSet resultSet = statement.executeQuery(CMND);
            resultSet.next();
            PEOPLE_COUNT=resultSet.getInt("max");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            Statement statement= connection.createStatement();
            String SQL="SELECT * FROM People";
            ResultSet resultSet = statement.executeQuery(SQL);
            while(resultSet.next()){
                switch(resultSet.getString("status")){
                    case "EMPLOYEE":
                        Employee newEmployee= new Employee();
                        newEmployee.setCardID(resultSet.getInt("cardid"));
                        newEmployee.setFullName(resultSet.getString("name"));
                        newEmployee.setPhone(resultSet.getString("phone"));
                        newEmployee.setPosition(resultSet.getString("position"));
                        people.add(newEmployee);
                        break;
                    case "LEARNER":
                        Parent firstParent= new Parent(resultSet.getString("first_parent_name"),
                                resultSet.getString("first_parent_phone"));
                        Parent secondParent = new Parent(resultSet.getString("second_parent_name"),
                                resultSet.getString("second_parent_phone"));
                        Learner newLearner= new Learner();
                        newLearner.setParents(new Parent[]{firstParent, secondParent});
                        newLearner.setCardID(resultSet.getInt("id"));
                        newLearner.setFullName(resultSet.getString("name"));
                        newLearner.setPhone(resultSet.getString("phone"));
                        people.add(newLearner);
                        break;
                    case "TEACHER":
                        Teacher newTeacher= new Teacher();
                        newTeacher.setCardID(resultSet.getInt("id"));
                        newTeacher.setFullName(resultSet.getString("name"));
                        newTeacher.setPhone(resultSet.getString("phone"));
                        newTeacher.setQualifications(resultSet.getString("qualification"));
                        people.add(newTeacher);
                        break;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return people;
    }
}
