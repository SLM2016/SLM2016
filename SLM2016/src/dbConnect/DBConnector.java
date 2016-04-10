package dbConnect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnector {
	public static Connection connectToMySQL(){
		Connection connection = null;
		String connectionURL = "";
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connectionURL = "jdbc:mysql://127.0.0.1:3306/slm2016?user=root&password=root";
			if(connection == null || connection.isClosed()){
				connection = DriverManager.getConnection(connectionURL);
			}
			
		}catch(ClassNotFoundException e){
				e.printStackTrace();
		}catch (SQLException e){
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
