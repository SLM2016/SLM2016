package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDao {
	private static AccountDao mInstance;
	
	public static AccountDao getInstance(){
		if(mInstance == null){
			mInstance = new AccountDao();
		}
		return mInstance;
	}
	
	public boolean createAccount (Account account){
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = DbConnector.connectToMySQL();
			pstm = con.prepareStatement("INSERT INTO account SET user_name=?, nick_name=?, mail=?, phone=?, company=?, department=?, position=?");
			pstm.setString(1, account.getUserName());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean updateAccount (Account account, int id){
		int updateCount = -1;
		boolean updateStatus = false;
		Connection con = null;
		PreparedStatement pstm = null;
		con = DbConnector.connectToMySQL();
		try {
			pstm = con.prepareStatement("UPDATE account SET  user_name=?, nick_name=?, mail=?, phone=?, company=?, department=?, position=?");
			pstm.setString(1, account.getUserName());
			pstm.setString(2, account.getNickName());
			pstm.setString(3, account.getEmail());
			pstm.setInt(4, account.getPhone());
			pstm.setString(5, account.getCompany());
			pstm.setString(6, account.getDepartment());
			pstm.setString(7, account.getPosition());
			updateCount = pstm.executeUpdate();
			
			if(updateCount > 0){
				updateStatus = true;
			}
			pstm.close();
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return updateStatus;
	}
	
	public Account getAccountByName(String name) {
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		Account account = null;
		try {
			con = DbConnector.connectToMySQL();
			stm = con.createStatement();
			rs = stm.executeQuery("SELECT * FROM account WHERE user_name=" + "'" + name + "'");
			if(rs.next()) {
				account = new Account(name);
				account.setId(rs.getInt("id"));
				account.setNickName(rs.getString("nick_name"));
				account.setEmail(rs.getString("mail"));
				account.setPhone(rs.getInt("phone"));
				account.setCompany(rs.getString("company"));
				account.setDepartment(rs.getString("deparetment"));
				account.setPosition(rs.getString("position"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(stm != null) {
					stm.close();
				}
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return account;
	}
	
	public boolean delete(int id) {
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		con = DbConnector.connectToMySQL();
		
		try {
			stm = con.createStatement();
			int count = stm.executeUpdate("DELETE FROM account WHERE id =" + "'" + id + "'");
			if (count == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if(stm != null) {
					stm.close();
				}
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
