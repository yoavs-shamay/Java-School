import java.sql.*;
import java.util.Vector;

public class ConnectToDatabase {
	private final static String DB_NAME = "jdbc:mysql://localhost:3306/myschool?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private final static String USERNAME = "yodi555";
	private final static String PASSWORD = "yodi654";
	private Connection c;
	private Statement st;
	private ResultSet rs;
	public Vector getStudents(String classroom) {
		Vector v = new Vector();
		try {
			String query = "SELECT name FROM " + classroom + ";";
			c = DriverManager.getConnection(DB_NAME, USERNAME, PASSWORD);
			st = c.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				v.add(rs.getString("name"));
			}
			rs.close();
			st.close();
			c.close();
			return v;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public int getPhoneNumber(String classroom,String studentName) {
		try {
			String query = "SELECT phone FROM " + classroom + " WHERE name = \"" + studentName + "\";";
			c = DriverManager.getConnection(DB_NAME,USERNAME,PASSWORD);
			st = c.createStatement();
			rs = st.executeQuery(query);
			rs.next();
			int phone = rs.getInt("phone");
			rs.close();
			st.close();
			c.close();
			return phone;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	public void addStudent(int id,String name,int phone,String classroom) {
		try {
			String query = "INSERT INTO " + classroom + " VALUES(" + id + ",\"" + name + "\"," + phone + ");";
			c = DriverManager.getConnection(DB_NAME, USERNAME, PASSWORD);
			st = c.createStatement();
			st.executeUpdate(query);
			st.close();
			c.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void removeStudent(String classroom,String name) {
		try {
			c = DriverManager.getConnection(DB_NAME, USERNAME, PASSWORD);
			st = c.createStatement();
			st.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
