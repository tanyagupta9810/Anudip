package Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class StatementProvider {
	private static PreparedStatement prst;

	public static PreparedStatement getStatement(String s) {
		try {
				Connection con = ConnectionProvider.getConnection();
				prst = con.prepareStatement(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prst;

	}
}
