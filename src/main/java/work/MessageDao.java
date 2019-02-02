package work;

import java.sql.*;
import java.util.ArrayList;

public class MessageDao {

    private final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private final String JDBC_URL  = "jdbc:mysql://localhost/develop?useSSL=false";
    private final String USER_ID   = "root";
    private final String USER_PASS = "pass";

    public void insert(MessageDto dto) {

        // JDBCドライバのロード
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = null;
        PreparedStatement ps = null;

        // データベースにアクセス
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
            ps.setInt(1, dto.getId());
            ps.setString(2, dto.getName());
            ps.setString(3, dto.getMessage());
            ps.setTimestamp(4, dto.getCreatedAt());

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

    public ArrayList<MessageDto> select() {

        ArrayList<MessageDto> list = new ArrayList<>();

        // JDBCドライバのロード
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // データベースにアクセス
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
                MessageDto dto = new MessageDto();
                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
                dto.setMessage(rs.getString("message"));
                dto.setCreatedAt(rs.getTimestamp("created_at"));
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
        return list;
    }
}
