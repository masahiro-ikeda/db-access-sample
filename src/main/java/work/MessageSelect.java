package work;

import java.util.ArrayList;

public class MessageSelect {

    /*
     * データベースから投稿内容を取得して出力する
     */
    public static void main(String[] args) {

        MessageDao dao = new MessageDao();
        ArrayList<MessageDto> list = dao.select();

        for (MessageDto dto: list){
            System.out.println("====================================");
            System.out.println("Id         : " + dto.getId());
            System.out.println("Name       : " + dto.getName());
            System.out.println("Message    : " + dto.getMessage());
            System.out.println("created_at : " + dto.getCreatedAt());
        }
        System.out.println("====================================");
    }
}
