package Parse;

import Src.Discipline;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Plan extends General{

    private HashMap<Integer,String> HOURS_TOTAL_INDEXES= new HashMap<Integer, String>() {{
        put(10,"По ЗЕТ ");
        put(12," Часов в з.е. ");
        put(13," Итог: экспертное  ");
        put(15," Контакт часы ");
        put(16," СР ");
        put(17," Контроль ");
    }};

    private HashMap<Integer,String>  EXAMS_INDEXES = new HashMap<Integer, String>() {{
        put(4,"Экзамены :");
        put(5,"Зачеты :");
        put(6,"Зачеты с оценкой :");
        put(7,"Курсовые проекты :");
        put(8,"Курсовые работы :");
        put(9,"Реферат :");
    }};

    private HashMap<Integer,String>  HOURS_PLAN_INDEXES = new HashMap<Integer, String>() {{
        put(26,"1 семестр");
        put(34,"2 семестр");
        put(45,"3 семестр");
        put(53,"4 семестр");
        put(64,"5 семестр");
        put(72,"6 семестр");
        put(83,"7 семестр");
        put(91,"8 семестр");
    }};
    private Integer HOURS_PLAN_SIZE = 7;

    private Integer i = 0;
    private Integer j = 0;
    private boolean bool1 = false;
    private boolean bool2 = false;

    ArrayList<ArrayList<String>> indexes = new ArrayList();
    ArrayList<String> Name = new ArrayList<String>();
    ArrayList<String> Exams = new ArrayList<String>();
    ArrayList<String> Hours_total = new ArrayList<String>();
    ArrayList<String> Hours = new ArrayList<String>();
    ArrayList<String> Kaf = new ArrayList<String>();
    ArrayList<String> Stroka;

    public Plan(){}

    public ArrayList<Discipline> parse(String fileName) throws SQLException {
        Logger log = Logger.getLogger(Plan.class);
        //инициализируем потоки
        ArrayList<Discipline> discArrList = new ArrayList<>();

        String result = "";
        InputStream inputStream = null;
        HSSFWorkbook workBook = null;
        try {
            workBook = new HSSFWorkbook(new FileInputStream(fileName));
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
            throw new SQLException("Не найдено файла");
        }

        Sheet sheet = workBook.getSheetAt(3);
        Iterator<Row> it = sheet.iterator();

        while (it.hasNext()) {
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();
            bool1 = false;
            j=0;
            indexes = new ArrayList();
            Name = new ArrayList<String>();
            Exams = new ArrayList<String>();
            Hours_total = new ArrayList<String>();
            Hours = new ArrayList<String>();
            Kaf = new ArrayList<String>();

            Stroka = new ArrayList<String>();
            int j = 0;
            while (cells.hasNext()) {
                Cell cell = cells.next();
                Stroka.add(get(cell));
            }
            if (Stroka.size()>5) {
                if (Stroka.get(1).equals("1")) {
                    bool1 = true;
                }
                if (bool1) {

                    Name.add(Stroka.get(2));
                    Name.add(Stroka.get(3));
                    indexes.add(Name);
                    for (Integer i : EXAMS_INDEXES.keySet())
                        Exams.add(EXAMS_INDEXES.get(i) + Stroka.get(i));
                    indexes.add(Exams);
                    for (Integer i : HOURS_TOTAL_INDEXES.keySet())
                        Hours_total.add(HOURS_TOTAL_INDEXES.get(i) + Stroka.get(i));
                    indexes.add(Hours_total);

                    for (Integer i : HOURS_PLAN_INDEXES.keySet())
                        get_arr(indexes, Stroka, i, i + HOURS_PLAN_SIZE, HOURS_PLAN_INDEXES.get(i));

                    Kaf.add(" Номер кафедры " + Stroka.get(Stroka.size() - 3));
                    Kaf.add(" Кафедра " + Stroka.get(Stroka.size() - 2));
                    indexes.add(Kaf);

                    if (bool1 && !(Kaf.get(0).contains(" 0.0") && Kaf.get(1).contains(" 0.0"))) {
                        //Display(indexes);
                        discArrList.add(new Discipline(indexes));
//                        System.out.println('b');
                    }
                }
            }

        }
        return discArrList;
    }


    public void Display(ArrayList<ArrayList<String>> indexes) {
        System.out.println();
        for (int i =0;i<indexes.size();i++){
            for (int j =0;j<indexes.get(i).size();j++){
                System.out.print(indexes.get(i).get(j)+" ");
            }
            System.out.println();
        }
    }

    public void get_arr(ArrayList<ArrayList<String>> indexes, ArrayList<String> Stroka, Integer beginIndex, Integer EndIndex,String name) {

        ArrayList<String> Hours_temp = new ArrayList<String>();
        for (int i =0;i<indexes.get(1).size();i++){
            String FormOFControl = indexes.get(1).get(i);
            //System.out.println(FormOFControl);
            if (FormOFControl.contains(name.substring(0,1))) name+=" "+FormOFControl.substring(0,FormOFControl.indexOf(':'));
            else
            if (FormOFControl.contains("-")){
                int x = FormOFControl.indexOf('-');

                Integer res_1 = Integer.parseInt(FormOFControl.substring(x-1,x) );
                Integer res_2 = Integer.parseInt(FormOFControl.substring(x+1,x+2) );
                if (Integer.parseInt(name.substring(0,1))<res_2 && Integer.parseInt(name.substring(0,1))>res_1)
                    name+=" "+FormOFControl.substring(0,FormOFControl.indexOf(':'));
            }
        }

        Hours_temp.add(name);
        for (int x = beginIndex; x < EndIndex; x++) {
            Hours_temp.add(Stroka.get(x));
        }
        if (!Hours_temp.get(6).equals("0.0"))
            indexes.add(Hours_temp);
    }
}
