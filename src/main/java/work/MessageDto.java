package work;

import java.sql.Timestamp;

/*
 * DTO (Data Trandfer Object)
 *
 * データの保持またはクラス間での受け渡しを目的に作られるクラスです。
 * フィールド変数とgetter() / setter()から構成されます。
 *
 * データベース接続の際などに頻繁に用いられます。
 */
public class MessageDto {
	private int id;
	private String name;
	private String message;
	private Timestamp createdAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}
