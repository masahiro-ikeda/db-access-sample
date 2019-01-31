package work;

import java.util.ArrayList;

public class SelectSample {
    public static void main(String[] args) {

        SampleDao dao = new SampleDao();
        ArrayList<SampleDto> list = dao.select();

        for (SampleDto dto: list){
            System.out.println("====================================");
            System.out.println("Id         : " + dto.getId());
            System.out.println("Name       : " + dto.getName());
            System.out.println("Message    : " + dto.getMessage());
            System.out.println("created_at : " + dto.getCreatedAt());
        }
        System.out.println("====================================");
    }
}
