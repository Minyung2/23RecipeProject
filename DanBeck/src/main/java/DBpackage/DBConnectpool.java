package DBpackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnectpool {
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	public DBConnectpool() {
		try {
			Context initCtx = new InitialContext();
			Context ctx = (Context)initCtx.lookup("java:comp/env");
			DataSource source = (DataSource)ctx.lookup("dbcp_mysql");
			con=source.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB CP connect fail");
		} 
	}
	
	public void close() {
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(psmt!=null) psmt.close();
			if (con != null) con.close();
			System.out.println("DB CP connection 반납");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
