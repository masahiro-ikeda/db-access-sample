package work;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.datasource.OracleDataSource;
import oracle.jdbc.replay.OracleDataSourceImpl;

public class MessageDao {

	// データベース接続に必要なデータ(接続文字列と呼ばれる)
	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:ORCL";

	// データベースの接続ユーザID
	private static final String USER_ID = "imuser";

	// データベースの接続パスワード
	private static final String USER_PASS = "impass";

	/**
	 * データベースにメッセージを保存する
	 *
	 * @param dto 投稿メッセージを格納したMessageDtoクラス
	 */
	public void insert(MessageDto dto) {

		// データベースへの接続管理を行うクラス
		OracleDataSource ds = null;

		// データベースとの接続状態を保持するクラス
		Connection con = null;

		// SQLの保持・実行を行うクラス
		PreparedStatement ps = null;

		// データベース接続処理は検査例外を伴うので例外処理を必ず実装する
		try {
			// データソースをインスタンス化
			ds = new OracleDataSourceImpl();

			// 接続文字列をデータソースにセットする
			ds.setURL(JDBC_URL);

			// ユーザIDとパスワードをセットして接続実施
			con = ds.getConnection(USER_ID, USER_PASS);

			// SQL文をStringBuilderクラスを使って用意する
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

			// ステートメントクラスにSQL文を格納
			ps = con.prepareStatement(builder.toString());

			// パラメータをセット("?"に値を入れる)
			ps.setInt(1, dto.getId());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getMessage());
			ps.setTimestamp(4, dto.getCreatedAt());

			// SQLを実行
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// con, psインスタンスが使っているメモリを開放
			try {
				con.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * データベースからメッセージを取得する
	 *
	 * @return メッセージを格納したリスト
	 */
	public ArrayList<MessageDto> select() {

		ArrayList<MessageDto> list = new ArrayList<>();

		// データベースへの接続管理を行うクラス
		OracleDataSource ds = null;

		// データベースとの接続状態を保持するクラス
		Connection con = null;

		// SQLの保持・実行を行うクラス
		PreparedStatement ps = null;

		// SELECT文の取得結果を受け取るクラス
		ResultSet rs = null;

		// データベース接続処理は検査例外を伴うので例外処理を必ず実装する
		try {
			// データソースをインスタンス化
			ds = new OracleDataSourceImpl();

			// 接続文字列をデータソースにセットする
			ds.setURL(JDBC_URL);

			// ユーザIDとパスワードをセットして接続実施
			con = ds.getConnection(USER_ID, USER_PASS);

			// SQL文をStringBuilderクラスを使って用意する
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

			// ステートメントクラスにSQL文を格納
			ps = con.prepareStatement(builder.toString());

			// SQLを実行して取得結果をリザルトセットに格納
			rs = ps.executeQuery();

			// リザルトセットから1レコードずつデータを取り出す
			while (rs.next()) {
				// 取得結果を格納するDtoをインスタンス化
				MessageDto dto = new MessageDto();
				// Dtoに取得結果を格納
				dto.setId(rs.getInt("id"));
				dto.setName(rs.getString("name"));
				dto.setMessage(rs.getString("message"));
				dto.setCreatedAt(rs.getTimestamp("created_at"));
				// Dtoに格納された1レコード分のデータをリストに詰める
				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// con, ps, rsインスタンスが使っているメモリを開放
				con.close();
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 呼び出し元に取得結果を返却
		return list;
	}
}
