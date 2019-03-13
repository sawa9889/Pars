package New_Parse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class Title extends General
{

    public Title(){}

    public String parse(String fileName) {
        //инициализируем потоки
        Integer i = 0;
        Integer j = 0;
        boolean bool;
        ArrayList<ArrayList<String>> indexes = new ArrayList();
        ArrayList<String> Uch_plan = new ArrayList<String>();
        ArrayList<String> Direct = new ArrayList<String>();
        ArrayList<String> Prof = new ArrayList<String>();
        ArrayList<String> Stand = new ArrayList<String>();


        String result = "";
        InputStream inputStream = null;
        HSSFWorkbook workBook = null;
        try {
            inputStream = new FileInputStream(fileName);
            workBook = new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
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
                        if (j==1){
                            Stand.add("Приказ "+get(cell));
                        }
                        break;
                    case 16:
                        if (j==2){
                            Direct.add(get(cell));
                        }
                        break;
                    case 18:
                        if (j==2){
                            String name = get(cell);
                            Direct.add(name.substring(name.lastIndexOf(Direct.get(0))==-1? 0:name.lastIndexOf(Direct.get(0))));
                            indexes.add(Direct);
                        }
                        break;
                    case 19:
                        if (j==2){
                            String name = get(cell);
                            Prof.add(name.substring(name.indexOf(": ")+1));
                            indexes.add(Prof);
                        }
                        break;
                    case 29:
                        if (j==20){
                            Uch_plan.add("Год начала обучения "+get(cell));
                            Uch_plan.add("Название плана "+fileName.substring(fileName.lastIndexOf('\\')+1,fileName.indexOf("xls")));
                        }
                        break;
                    case 30:
                        if (j==18){
                            Stand.add("Дата "+get(cell));
                        }
                        break;
                    case 31:
                        if (j==18){
                            Stand.add("Номер стандарта "+get(cell));
                        }
                        break;

                }

            }

        }
        params.add(Uch_plan);
        params.add(Prof);
        params.add(Direct);
        params.add(Stand);

        Display(params);
        return result;
    }

    public void Display(ArrayList<ArrayList<String>> params) {
        System.out.println();
        for (int i =0;i<params.size();i++){
            System.out.println(params.get(i)+" ");
        }
    }


}
