package work;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * データベースから投稿メッセージを取得して出力する
 */
public class MessageSelect {

	// データベース接続に必要なデータ
	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:ORCL";
	private static final String USER_ID = "imuser";
	private static final String USER_PASS = "impass";

	public static void main(String[] args) {

		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			StringBuilder builder = new StringBuilder();
			builder.append("SELECT          ");
			builder.append("  id,           ");
			builder.append("  name,         ");
			builder.append("  message,      ");
			builder.append("  created_at    ");
			builder.append("FROM            ");
			builder.append("  message_board ");
			builder.append("ORDER BY        ");
			builder.append("  id            ");

			ps = con.prepareStatement(builder.toString());
			rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("=====================================");
				System.out.println("id         : " + rs.getInt("id"));
				System.out.println("name       : " + rs.getString("name"));
				System.out.println("message    : " + rs.getString("message"));
				System.out.println("created_at : " + rs.getTimestamp("created_at"));
				System.out.println("=====================================");
				System.out.println();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
