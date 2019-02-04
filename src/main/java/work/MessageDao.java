package work;

import java.sql.*;
import java.util.ArrayList;

public class MessageDao {

    private final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private final String JDBC_URL = "jdbc:mysql://localhost/develop?useSSL=false";
    private final String USER_ID = "root";
    private final String USER_PASS = "pass";

    /**
     * データベースにメッセージを保存する
     *
     * @param dto 投稿メッセージを格納したMessageDtoクラス
     */
    public void insert(MessageDto dto) {

        // JDBCドライバのロード
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // コネクションクラスの宣言
        Connection con = null;
        // ステートメントクラスの宣言
        PreparedStatement ps = null;

        // データベースにアクセス
        try {
            // データベースとの接続を行う
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
            try {
                if (ps != null) {
                    // Memoryの解放
                    ps.close();
                }
                if (con != null) {
                    // 接続の解除
                    con.close();
                }
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

        // JDBCドライバのロード
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // コネクションクラスの宣言
        Connection con = null;
        // ステートメントクラスの宣言
        PreparedStatement ps = null;
        // リザルトセットクラスの宣言
        ResultSet rs = null;

        // データベースにアクセス
        try {
            // データベースとの接続を行う
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
                if (rs != null) {
                    // Memoryの解放
                    rs.close();
                }
                if (ps != null) {
                    // Memoryの解放
                    ps.close();
                }
                if (con != null) {
                    //接続の解除
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // 呼び出し元に取得結果を返却
        return list;
    }
}
