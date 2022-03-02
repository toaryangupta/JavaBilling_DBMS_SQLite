
import java.sql.*;
//import java.sql.Connection; 
import javax.swing.*;
public class sqliteConnection {
	Connection conn=null;
	public static Connection dbConnector()
	{
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\toary\\eclipse-workspace\\JavaSCE\\sqliteDatabase\\database.sqlite");
			JOptionPane.showMessageDialog(null, "Success");
			return conn;
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
