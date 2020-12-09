import java.sql.*;

public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String userName = "root";
        String password = "1234";
        String connectionURL = "jdbc:mysql://localhost:3306/test?verifyServerCertificate=false&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(connectionURL, userName, password)) {
            Statement statement = connection.createStatement();
            {

                connection.setAutoCommit(false);
                statement.addBatch("drop table if exists users");
                statement.addBatch("create table if not exists users(\n" +
                        "    id int primary key auto_increment,\n" +
                        "    age int,\n" +
                        "    name varchar(100),\n" +
                        "    email varchar(100)\n" +
                        ")");

                statement.addBatch("insert into users  set age = 15, name = 'Dan', email = 'dan@dan'");
                statement.addBatch("insert into users  set age = 12, name = 'faf', email = 'faf@dfaf'");
                statement.addBatch("insert into users  set age = 25, name = 'Kirill', email = 'dan@dan'");
                statement.addBatch("insert into users  set age = 34, name = 'Jeff', email = 'GAGa@dfaf'");
                statement.addBatch("insert into users  set age = 45, name = 'Man', email = 'Man@dan'");
                statement.addBatch("insert into users  set age = 34, name = 'John', email = 'John@dfaf'");
                Savepoint savepoint = connection.setSavepoint();
                statement.addBatch("insert into users  set age = 44, name = 'Maksim', email = 'Maksim@dan'");
                statement.addBatch("insert into users  set age = 23, name = 'Nick', email = 'Nick@dfaf'");

              if(statement.executeBatch().length == 10){
                  connection.commit();
              } else {
                  connection.rollback();
              }

            }
        }
    }
}

