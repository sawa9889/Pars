package Parse;

import Src.EducationPlan;
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

    public Title(){}

    public EducationPlan parse(String fileName) throws SQLException {
        Logger log = Logger.getLogger(Plan.class);
        //инициализируем потоки
        Integer i = 0;
        Integer j = 0;
        boolean bool;
        ArrayList<ArrayList<String>> indexes = new ArrayList();
        ArrayList<String> Uch_plan = new ArrayList<String>();
        ArrayList<String> Direct = new ArrayList<String>();
        ArrayList<String> Prof = new ArrayList<String>();
        ArrayList<String> Stand = new ArrayList<String>();


        HSSFWorkbook workBook = null;
        File parsFile = new File(fileName);
        try {
            workBook = new HSSFWorkbook(new FileInputStream(fileName));
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
            throw new SQLException("Не найдено файла");
        }

        Sheet sheet = workBook.getSheetAt(0);
        Iterator<Row> it = sheet.iterator();
        ArrayList<ArrayList<String>> params= new ArrayList();

        while (it.hasNext()) {
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();
            i++;
            bool = false;

            j=0;
            while (cells.hasNext()) {

                Cell cell = cells.next();
                j++;

                switch (i) {
                    case 13:
                        if (j==3){
                            Stand.add("Приказ "+get(cell));
                        }
                        break;
                    case 16:
                        if (j==2){
                            Direct.add(get(cell));
                        }
                        break;
                    case 17:
                        if (j==2){
                            String name = get(cell);
                            Direct.add(name.substring(name.lastIndexOf(Direct.get(0))==-1? 0:name.lastIndexOf(Direct.get(0))+Direct.get(0).length()));
                            log.info(Direct.get(1));
                            indexes.add(Direct);
                        }
                        break;
                    case 18:
                        if (j==2){
                            String name = get(cell);
                            Prof.add(name.substring(name.indexOf(": ")+2));
                            indexes.add(Prof);
                        }
                        break;
                    case 30:
                        if (j==18){
                            Uch_plan.add("Год начала обучения "+get(cell));
                            Uch_plan.add("Название плана "+ parsFile.getName());
                        }
                        break;
                    case 32:
                        if (j==18){
                            Stand.add("Номер стандарта "+get(cell));
                        }
                        break;
                    case 33:
                        if (j==18){
                            Stand.add("Дата "+get(cell));
                        }
                        break;
                }

            }

        }
        params.add(Uch_plan);
        params.add(Prof);
        params.add(Direct);
        params.add(Stand);

     //   Display(params);
        //return params;
        return new EducationPlan(params);
    }

    public void Display(ArrayList<ArrayList<String>> params) {
        System.out.println();
        for (int i =0;i<params.size();i++){
            System.out.println(params.get(i)+" ");
        }
    }


}
