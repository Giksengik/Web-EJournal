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

    public List<Participant> getPeople() {
        return people;
    }

    public void setPeople(List<Participant> people) {
        this.people = people;
    }

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
            String CMND="SELECT MAX(cardid) FROM People";
            ResultSet resultSet = statement.executeQuery(CMND);
            resultSet.next();
            PEOPLE_COUNT=resultSet.getInt("cardid");
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
                        newEmployee.setStatus("EMPLOYEE");
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
                        newLearner.setStatus("LEARNER");
                        newLearner.setParents(new Parent[]{firstParent, secondParent});
                        newLearner.setCardID(resultSet.getInt("cardid"));
                        newLearner.setFullName(resultSet.getString("name"));
                        newLearner.setPhone(resultSet.getString("phone"));
                        people.add(newLearner);
                        break;
                    case "TEACHER":
                        Teacher newTeacher= new Teacher();
                        newTeacher.setStatus("TEACHER");
                        newTeacher.setCardID(resultSet.getInt("cardid"));
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
    public Teacher showTeacher(int id){
        Teacher teacher=null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM People WHERE cardid=? AND  status='TEACHER'");
            preparedStatement.setInt(1,id);
            ResultSet resultSet =preparedStatement.executeQuery();
            resultSet.next();
            teacher=new Teacher();
            teacher.setCardID(resultSet.getInt("cardid"));
            teacher.setQualifications(resultSet.getString("qualification"));
            teacher.setStatus("TEACHER");
            teacher.setFullName(resultSet.getString("name"));
            teacher.setPhone(resultSet.getString("phone"));
            teacher.setPosition(resultSet.getString("position"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return teacher;
    }
    public Learner showLearner(int id){
        Learner learner=null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM People WHERE cardid=? AND  status='LEARNER'");
            preparedStatement.setInt(1,id);
            ResultSet resultSet =preparedStatement.executeQuery();
            resultSet.next();
            learner=new Learner();
            learner.setCardID(resultSet.getInt("cardid"));
            learner.setStatus("LEARNER");
            learner.setFullName(resultSet.getString("name"));
            learner.setPhone(resultSet.getString("phone"));
            Parent firstParent= new Parent(resultSet.getString("first_parent_name"),
                    resultSet.getString("first_parent_phone"));
            Parent secondParent = new Parent(resultSet.getString("second_parent_name"),
                    resultSet.getString("second_parent_phone"));
            learner.setParents(new Parent[]{firstParent, secondParent});
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return learner;
    }
    public Employee showEmployee(int id){
        Employee employee=null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM People WHERE cardid=? AND  status='EMPLOYEE'");
            preparedStatement.setInt(1,id);
            ResultSet resultSet =preparedStatement.executeQuery();
            resultSet.next();
            employee=new Employee();
            employee.setCardID(resultSet.getInt("cardid"));
            employee.setStatus("EMPLOYEE");
            employee.setFullName(resultSet.getString("name"));
            employee.setPhone(resultSet.getString("phone"));
            employee.setPosition(resultSet.getString("position"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employee;
    }
}
