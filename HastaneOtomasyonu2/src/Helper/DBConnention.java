package Helper;

import java.sql.*;

public class DBConnention {
	Connection c = null;
	
	public  DBConnention() {
		
	}
	public Connection connDb() {
		try {
			this.c = DriverManager.getConnection("jdbc:mariadb://localhost:****/hospital?user=****&password=****");
			return c;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

}
