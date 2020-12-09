package SocialMedia.dao;

import SocialMedia.Profile;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProfileDAO {
    private static int PEOPLE_COUNT;

    private static final String URL = "jdbc:mysql://localhost:3306/test?verifyServerCertificate=false&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String userName = "root";
    private static final String password = "1234";

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, userName, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Profile> index() {
        List<Profile> usersProfile = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                Profile profile1 = new Profile();
                profile1.setId(resultSet.getInt("id"));
                profile1.setName(resultSet.getString("name"));
                profile1.setAge(resultSet.getInt("age"));
                profile1.setEmail(resultSet.getString("email"));

                usersProfile.add(profile1);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return usersProfile;
    }

    public Profile show(int id) {
//        return usersProfile.stream().filter(profile -> profile.getId() == id).findAny().orElse(null);
        return null;
    }

    public void save(Profile profile) {

//        profile.setId(++COUNT);
//        usersProfile.add(profile);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO users set name ='" + profile.getName() + "'," + "age = " + profile.getAge() + ", email ='" + profile.getEmail() + "'");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(int id, Profile updateProfile) {
//        Profile profileToBeUpdated = show(id);
//        profileToBeUpdated.setName(updateProfile.getName());
//        profileToBeUpdated.setAge(updateProfile.getAge());
//        profileToBeUpdated.setEmail(updateProfile.getEmail());
//        profileToBeUpdated.setPassword(updateProfile.getPassword());
    }

    public void delete(int id) {
//        usersProfile.removeIf(p -> p.getId() == id);
    }
//    public boolean checkPassword(Profile profile, int id){
//        return profile.getPassword().equals(usersProfile.get(id).getPassword());
//    }

}
