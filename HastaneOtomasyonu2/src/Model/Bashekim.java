package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.Helper;

public class Bashekim extends User {
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Bashekim(int id, String tcno, String password, String name, String type) {
		super(id, tcno, password, name, type);
	}

	public Bashekim() {
	}

	public ArrayList<User> getDoctorList() throws SQLException {
		ArrayList<User> list = new ArrayList<>();
		User obj;
		Connection con = dbcon.connDb();

		try {

			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE type = 'Doktor'");
			while (rs.next()) {
				obj = new User(rs.getInt("id"), rs.getString("tcno"), rs.getString("password"), rs.getString("name"),
						rs.getString("type"));
				list.add(obj);
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			con.close();
		}
		return list;
	}

	public ArrayList<User> getWorkerList(int clinic_id) throws SQLException {
		ArrayList<User> list = new ArrayList<>();
		User obj;
		Connection con = dbcon.connDb();
		try {
			st = con.createStatement();
			rs = st.executeQuery(
					"SELECT u.id, u.tcno, u.password, u.name, u.type FROM worker w LEFT JOIN user u ON w.user_id = u.id WHERE clinic_id="
							+ clinic_id);
			while (rs.next()) {
				obj = new User(rs.getInt("u.id"), rs.getString("u.tcno"), rs.getString("u.password"),
						rs.getString("u.name"), rs.getString("u.type"));
				list.add(obj);
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			con.close();
		}
		return list;
	}

	public boolean addDoctor(String tcno, String password, String name) throws SQLException {
		String query = "INSERT INTO user (tcno, password, name, type) VALUES (?, ?, ?, ?)";
		boolean key = false;
		Connection con = dbcon.connDb();

		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, tcno);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, "Doktor");
			preparedStatement.executeUpdate();
			key = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		if (key)
			return true;
		else
			return false;
	}

	public boolean deleteDoctor(int id) throws SQLException {
		String query = "DELETE FROM user WHERE id = ?";
		boolean key = false;
		Connection con = dbcon.connDb();

		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		if (key)
			return true;
		else
			return false;
	}

	public boolean updateDoctor(int id, String tcno, String password, String name) throws SQLException {
		String query = "UPDATE user SET name = ?, tcno = ?, password = ? WHERE id = ?";
		boolean key = false;
		Connection con = dbcon.connDb();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, tcno);
			preparedStatement.setString(3, password);
			preparedStatement.setInt(4, id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		if (key)
			return true;
		else
			return false;
	}

	public boolean addWorker(int user_id, int clinic_id) throws SQLException {
		String query = "INSERT INTO worker (user_id, clinic_id) VALUES (?, ?)";
		boolean key = false;
		int count = 0;
		Connection con = dbcon.connDb();

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM worker WHERE clinic_id =" + clinic_id + " AND user_id =" + user_id);
			while (rs.next()) {
				count++;
				// FIX ME!!
				Helper.showMsg("Zaten EKli!");
			}
			if (count == 0) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, user_id);
				preparedStatement.setInt(2, clinic_id);
				preparedStatement.executeUpdate();
			}

			key = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		if (key)
			return true;
		else
			return false;
	}

	public boolean delWhourDoctor(int doctor_id) throws SQLException {
		String query = "DELETE FROM whour WHERE doctor_id = ? AND SET status = 'a'";
		boolean key = false;
		Connection con = dbcon.connDb();

		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, doctor_id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		if (key)
			return true;
		else
			return false;
	}

	public void deleteWorkers(int id) throws SQLException {
		String query = "DELETE FROM worker WHERE user_id = ?";

		Connection con = dbcon.connDb();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			con.close();
		}
	}
}
