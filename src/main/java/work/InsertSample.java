package work;

import java.sql.Timestamp;

public class InsertSample {

    public static void main(String[] args) {

        SampleDao dao = new SampleDao();
        SampleDto dto = new SampleDto();

        dto.setId(Integer.parseInt(args[0]));
        dto.setName(args[1]);
        dto.setMessage(args[2]);
        dto.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        dao.insert(dto);
    }
}
