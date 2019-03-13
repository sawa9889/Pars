import Parse.Competence;
import Parse.General;
import Parse.Plan;

import java.sql.SQLException;

public class test {
    public static void main(String[] args) throws SQLException {
        General gen = new Plan();
        ((Plan) gen).parse("C:/Users/Alexandr/Downloads/Project_uni/Exel/09.03.02_ИТСАиБ_ИКБСП_2016.plm.xml.xls");
        gen.parse_base("C:/Users/Alexandr/Downloads/Project_uni/Exel/09.03.02_ИТСАиБ_ИКБСП_2016.plm.xml.xls", 3);
    }
}
