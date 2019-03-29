import Parse.Competence;
import Parse.General;
import Parse.Title;

import java.sql.SQLException;

public class test {
    public static void main(String[] args) throws SQLException {
        General gen = new Title();
        ((Title) gen).parse("C:/Users/Alexandr/Downloads/Project_uni/Exel/09.04.02_ИИСиТ_ИКБСП_2016.plm.xml.xls");
        gen.parse_base("C:/Users/Alexandr/Downloads/Project_uni/Exel/09.04.02_ИИСиТ_ИКБСП_2016.plm.xml.xls", 0);
    }
}
