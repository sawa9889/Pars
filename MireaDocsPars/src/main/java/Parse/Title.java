package Parse;

import Src.Discipline;
import Src.EducationPlan;
import Threads.ThreadParse;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class Title extends General
{

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
    Logger log = Logger.getLogger(ThreadParse.class);

    public Title(){}

    public EducationPlan parse(String fileName) throws SQLException {
        Logger log = Logger.getLogger(Plan.class);
        //инициализируем потоки
        String result = "";
        InputStream inputStream = null;
        HSSFWorkbook workBook = null;
        try {
            workBook = new HSSFWorkbook(new FileInputStream(fileName));
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
            throw new SQLException("Не найдено файла");
        }

        Sheet sheet = workBook.getSheetAt(0);


            bool1 = false;
            j=0;
            ArrayList<ArrayList<String>> indexes = new ArrayList();
            ArrayList<String> Uch_plan = new ArrayList<String>();
            ArrayList<String> Direct = new ArrayList<String>();
            ArrayList<String> Prof = new ArrayList<String>();
            ArrayList<String> Stand = new ArrayList<String>();

        ArrayList<String> NewPositions = new ArrayList<String>();

            ArrayList<ArrayList<String>> Sheet = new ArrayList();
            Stroka = new ArrayList<String>();
            Iterator<Row> it = sheet.iterator();
            while (it.hasNext()) {
                Row row = it.next();
                Iterator<Cell> cells = row.iterator();
                Stroka = new ArrayList<String>();
                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    Stroka.add(get(cell));
                    i++;
                }
                Sheet.add(Stroka);
            }

        Stand.add("Приказ "+Sheet.get(12).get(2));
        Direct.add(Sheet.get(15).get(1));
        String name = Sheet.get(16).get(1);
        Direct.add(name.substring(name.lastIndexOf(Direct.get(0))==-1? 0:name.lastIndexOf(Direct.get(0))+Direct.get(0).length()));
        name = Sheet.get(17).get(1);
        Prof.add(name.substring(name.indexOf(": ")+2));
        Uch_plan.add("Год начала обучения "+ Sheet.get(29).get(17));
        Uch_plan.add("Название плана "+ fileName.substring(fileName.lastIndexOf('\\')+1,fileName.indexOf("xls")));
        Stand.add("Номер стандарта "+Sheet.get(31).get(17));
        Stand.add("Дата "+Sheet.get(32).get(17));
        String str = Sheet.get(25).get(2);
        NewPositions.add(str.substring(0,str.indexOf("«")-1));
        NewPositions.add(str.substring(str.indexOf("«")+1,str.indexOf("»")));
        NewPositions.add(Sheet.get(29).get(1).substring(Sheet.get(29).get(1).indexOf(":")+1,Sheet.get(29).get(1).length()));
        NewPositions.add(Sheet.get(30).get(1).substring(Sheet.get(30).get(1).indexOf(":")+1,Sheet.get(30).get(1).length()));
        NewPositions.add(Sheet.get(31).get(1).substring(Sheet.get(31).get(1).indexOf(":")+1,Sheet.get(31).get(1).length()));
        NewPositions.add(Sheet.get(32).get(1).substring(Sheet.get(32).get(1).indexOf(":")+1,Sheet.get(32).get(1).length()));
        NewPositions.add(Sheet.get(33).get(1).substring(Sheet.get(33).get(1).indexOf(":")+1,Sheet.get(33).get(1).length()));


        indexes.add(Uch_plan);
        indexes.add(Prof);
        indexes.add(Direct);
        indexes.add(Stand);
        indexes.add(NewPositions);

        Display(indexes);
        //return params;
        return new EducationPlan(indexes);
    }

    public void Display(ArrayList<ArrayList<String>> params) {
        System.out.println();
        for (int i =0;i<params.size();i++){
            System.out.println(params.get(i)+" ");
        }
    }


}
