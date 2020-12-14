package SocialMedia.dao;

import SocialMedia.Profile;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProfileDAO {

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
        Profile profile = null;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM users WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            profile = new Profile();

            profile.setId(resultSet.getInt("id"));
            profile.setName(resultSet.getString("name"));
            profile.setAge(resultSet.getInt("age"));
            profile.setEmail(resultSet.getString("email"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return profile;
    }

    public void save(Profile profile) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT  INTO users SET name = ?, age =?, email=?");
            preparedStatement.setString(1, profile.getName());
            preparedStatement.setInt(2, profile.getAge());
            preparedStatement.setString(3, profile.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(int id, Profile updateProfile) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users Set name=?, age = ?, email=? WHERE id=?");
            preparedStatement.setString(1, updateProfile.getName());
            preparedStatement.setInt(2, updateProfile.getAge());
            preparedStatement.setString(3, updateProfile.getEmail());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void delete(int id) {

        try {
            PreparedStatement preparedStatement = preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}

