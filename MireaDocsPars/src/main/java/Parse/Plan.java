package Parse;

import Src.Discipline;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
        import org.apache.poi.ss.usermodel.Cell;
        import org.apache.poi.ss.usermodel.Row;
        import org.apache.poi.ss.usermodel.Sheet;

        import java.io.FileInputStream;
        import java.io.IOException;
        import java.io.InputStream;
import java.util.ArrayList;
        import java.util.Iterator;

public class Plan extends General{

    public Plan(){}

    public ArrayList<Discipline> parse(String fileName) {
        //инициализируем потоки
        Integer i = 0;
        Integer j = 0;
        boolean bool = false;
        ArrayList<Discipline> discArrList = new ArrayList<>();

        String result = "";
        InputStream inputStream = null;
        HSSFWorkbook workBook = null;
        try {
            inputStream = new FileInputStream(fileName);
            workBook = new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sheet sheet = workBook.getSheetAt(3);
        Iterator<Row> it = sheet.iterator();

        while (it.hasNext()) {
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();
            i++;
            ArrayList<ArrayList<String>> indexes = new ArrayList();
            ArrayList<String> Name = new ArrayList<String>();
            ArrayList<String> Exams = new ArrayList<String>();
            ArrayList<String> Hours_total = new ArrayList<String>();
            ArrayList<String> Hours = new ArrayList<String>();
            ArrayList<String> Hours_temp;
            bool = false;
            j=0;
            while (cells.hasNext()) {
                Cell cell = cells.next();
                j++;
                try {
                    if (j == 2 && get(cell).equals("1")) {
                        bool = true;
                    }
                }catch(IllegalStateException e){

                }

                if (bool){
                    switch (j){
                        case 4:
                            Name.add(get(cell));
                            indexes.add(Name);
                            break;
                        case 7:
                            Exams.add("Экзамены :"+get(cell));
                            break;
                        case 8:Exams.add(" Зачеты :"+get(cell));
                            break;
                        case 9:Exams.add(" Зачеты с оценкой :"+get(cell));
                            break;
                        case 10:Exams.add(" Курсовые проекты :"+get(cell));
                            break;
                        case 11:Exams.add(" Курсовые работы :"+get(cell));
                            indexes.add(Exams);
                            break;
                        case 12:
                            Hours_total.add("По ЗЕТ "+get(cell));
                            break;
                        case 14:Hours_total.add(" Контакт. раб "+get(cell));
                            break;
                        case 19:Hours_total.add(" СРС "+get(cell));
                            break;
                        case 20:Hours_total.add(" Контроль "+get(cell));
                            break;
                        case 21:Hours_total.add(" Экспертное "+get(cell));
                            indexes.add(Hours_total);
                            break;
                        case 27:
                            get_arr(indexes,cell,cells,"1 семестр");
                            j+=7;
                            break;
                        case 35:
                            get_arr(indexes,cell,cells,"2 семестр");
                            j+=7;
                            break;
                        case 46:
                            get_arr(indexes,cell,cells,"3 семестр");
                            j+=7;
                            break;
                        case 54:
                            get_arr(indexes,cell,cells,"4 семестр");
                            j+=7;
                            break;
                        case 65:
                            get_arr(indexes,cell,cells,"5 семестр");
                            j+=7;
                            break;
                        case 73:
                            get_arr(indexes,cell,cells,"6 семестр");
                            j+=7;
                            break;
                        case 84:
                            get_arr(indexes,cell,cells,"7 семестр");
                            j+=7;
                            break;
                        case 92:
                            get_arr(indexes,cell,cells,"8 семестр");
                            j+=7;
                            break;
                    }
                }

            }
            if (bool) {
           //     Display(indexes);
                discArrList.add(new Discipline(indexes));
            }
            result += "\n";

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

    public void get_arr(ArrayList<ArrayList<String>> indexes,Cell cell,Iterator<Cell> cells,String name) {
        int cellType = cell.getCellType();
        ArrayList<String> Hours_temp = new ArrayList<String>();
        for (int i =0;i<indexes.get(1).size();i++){
            if (indexes.get(1).get(i).contains(name.substring(0,1))) name+=" "+indexes.get(1).get(i).substring(0,indexes.get(1).get(i).indexOf(':'));
                    else
                if (indexes.get(1).get(i).contains("-")){
                    int x = indexes.get(1).get(i).indexOf('-');
                    Integer res_1 = Integer.parseInt(indexes.get(1).get(i).substring(x-1,x) );
                    Integer res_2 = Integer.parseInt(indexes.get(1).get(i).substring(x+1,x+2) );
                    if (Integer.parseInt(name.substring(0,1))<res_2 && Integer.parseInt(name.substring(0,1))>res_1)
                        name+=" "+indexes.get(1).get(i).substring(0,indexes.get(1).get(i).indexOf(':'));
                }
        }
        Hours_temp.add(name);
        for (int x = 0; x < 7; x++) {
            Hours_temp.add(get(cell));
            cell = cells.next();
            cellType = cell.getCellType();
        }
        if (!Hours_temp.get(6).equals("0.0"))
            indexes.add(Hours_temp);
    }
}
