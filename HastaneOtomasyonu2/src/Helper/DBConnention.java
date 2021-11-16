package Helper;

import java.sql.*;

public class DBConnention {
	Connection c = null;
	
	public  DBConnention() {
		
	}
	public Connection connDb() {
		try {
			this.c = DriverManager.getConnection("jdbc:mariadb://localhost:3325/hospital?user=root&password=root");
			return c;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

}
