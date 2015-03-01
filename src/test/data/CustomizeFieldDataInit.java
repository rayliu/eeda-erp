package test.data;

import java.sql.Statement;

public class CustomizeFieldDataInit {
    public static void initCustomizeField(Statement stmt) {
        try {
            // 运输单
        	String instr="insert into customize_field(order_type, office_id, field_code, field_name, field_desc, is_hidden, customize_name, remark)";
            stmt.executeUpdate(instr+" values('TO', 1, 'EX_CARGO','贵重单品','贵重货品的名称', 0, null, null);");
            stmt.executeUpdate(instr+" values('TO', 1, 'EXE_OFFICE','执行办事处','分公司的名称', 0, null, null);");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
