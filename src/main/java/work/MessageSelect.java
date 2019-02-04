package work;

import java.util.ArrayList;

public class MessageSelect {

    /*
     * データベースから投稿メッセージを取得して出力する
     */
    public static void main(String[] args) {

        // Daoクラスの呼び出し
        MessageDao dao = new MessageDao();
        ArrayList<MessageDto> list = dao.select();

        // 拡張for文で1行ずつ取り出して出力
        for (MessageDto dto: list){
            System.out.println("====================================");
            System.out.println("Id         : " + dto.getId());
            System.out.println("Name       : " + dto.getName());
            System.out.println("Message    : " + dto.getMessage());
            System.out.println("created_at : " + dto.getCreatedAt());
            System.out.println("====================================");
        }
    }
}
