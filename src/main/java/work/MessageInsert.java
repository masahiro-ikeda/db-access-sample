package work;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/*
 * メッセージを投稿してデータベースに保存する。
 * コマンドライン引数で次の値を引き渡す。
 *  - 投稿ID
 *  - 投稿者名
 *  - 投稿内容
 *
 *  例：1 "ikeda" "Hello World!!"
 */
public class MessageInsert {

	// データベース接続に必要なデータ
	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:ORCL";
	private static final String USER_ID = "imuser";
	private static final String USER_PASS = "impass";

	public static void main(String[] args) {

		if (args.length != 3) {
			System.out.println("コマンドライン引数の数が不適切です。");
			System.exit(0);
		}

		int id = Integer.parseInt(args[0]);
		String name = args[1];
		String message = args[2];

		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			StringBuilder builder = new StringBuilder();
			builder.append("INSERT INTO message_board ( ");
			builder.append("    id,                     ");
			builder.append("    name,                   ");
			builder.append("    message,                ");
			builder.append("    created_at              ");
			builder.append(") VALUES (                  ");
			builder.append("    ?,                      ");
			builder.append("    ?,                      ");
			builder.append("    ?,                      ");
			builder.append("    ?                       ");
			builder.append(")                           ");

			ps = con.prepareStatement(builder.toString());
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, message);
			ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
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

		System.out.println("メッセージを投稿しました。");
	}
}
