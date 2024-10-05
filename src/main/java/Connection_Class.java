import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection_Class {
  public static void main(String[] args) {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jwd57","root","root");
      System.out.println("Success");
    } catch (ClassNotFoundException e) {
      System.out.println("Driver error : "+e.getMessage());
    } catch (SQLException e) {
      System.out.println("connection error : "+e.getMessage());
    }
  }
}