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
	 *  例：1 "ikeda" "Hello World!!"
	 */
	public static void main(String[] args) {

		// コマンドラインで入力した値をDtoクラスに格納
		MessageDto dto = new MessageDto();
		dto.setId(Integer.parseInt(args[0]));
		dto.setName(args[1]);
		dto.setMessage(args[2]);
		dto.setCreatedAt(new Timestamp(System.currentTimeMillis()));

		// Daoクラスの呼び出し
		MessageDao dao = new MessageDao();
		dao.insert(dto);

		System.out.println("メッセージを投稿しました。");
	}
}
