package work;

import java.sql.Timestamp;

public class MessageInsert {

    /*
     * メッセージを投稿してデータベースに保存する。
     * コマンドライン引数で次の値を引き渡す。
     *  - 投稿ID
     *  - 投稿者名
     *  - 投稿内容
     *
     *  例：1 ikeda Hello Everyone!!
     */
    public static void main(String[] args) {

        MessageDao dao = new MessageDao();
        MessageDto dto = new MessageDto();

        dto.setId(Integer.parseInt(args[0]));
        dto.setName(args[1]);
        dto.setMessage(args[2]);
        dto.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        dao.insert(dto);
    }
}