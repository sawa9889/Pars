package Parse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
        import org.apache.poi.ss.usermodel.Cell;
        import org.apache.poi.ss.usermodel.Row;
        import org.apache.poi.ss.usermodel.Sheet;

        import java.io.FileInputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.lang.reflect.Array;
        import java.util.ArrayList;
        import java.util.Iterator;

public class Plan extends General{

    public Plan(){}

    public String parse(String fileName) {
        //инициализируем потоки
        Integer i = 0;
        Integer j = 0;
        boolean bool1 = false;
        boolean bool2 = false;

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
            ArrayList<String> Kaf = new ArrayList<String>();
            bool1 = false;
            bool2 = false;
            j=0;
            while (cells.hasNext()) {
                Cell cell = cells.next();
                j++;
                try {
                    if (j == 1 && (get(cell).equals("+")||get(cell).equals("-"))) {
                        bool1 = true;
                    }
                }catch(IllegalStateException e){
                    System.out.println(get(cell));
                }

                if (bool1){
                    switch (j){
                        case 2:
                            Name.add(get(cell));
                            break;
                        case 3:
                            Name.add(get(cell));
                            indexes.add(Name);
                            break;
                        case 4:
                            Exams.add("Экзамены :"+get(cell));
                            cell = cells.next();
                            Exams.add(" Зачеты :"+get(cell));
                            cell = cells.next();
                            Exams.add(" Зачеты с оценкой :"+get(cell));
                            cell = cells.next();
                            Exams.add(" Курсовые проекты :"+get(cell));
                            cell = cells.next();
                            Exams.add(" Курсовые работы :"+get(cell));
                            cell = cells.next();
                            Exams.add(" Реферат :"+get(cell));
                            indexes.add(Exams);
                            j+=5;
                            break;
                        case 10:
                            Hours_total.add("По ЗЕТ "+get(cell));
                            break;
                        case 12:Hours_total.add(" Часов в з.е. "+get(cell));
                            break;
                        case 13:Hours_total.add(" Итог: экспертное  "+get(cell));
                            break;
                        case 15:Hours_total.add("  Контакт часы "+get(cell));
                            break;
                        case 16:Hours_total.add(" СР "+get(cell));
                            break;
                        case 17:Hours_total.add(" Контроль "+get(cell));
                            indexes.add(Hours_total);
                            break;
                        case 18:
                            get_arr(indexes,cell,cells,"1 семестр");
                            j+=7;
                            break;
                        case 26:
                            get_arr(indexes,cell,cells,"2 семестр");
                            j+=7;
                            break;
                        case 34:
                            get_arr(indexes,cell,cells,"3 семестр");
                            j+=7;
                            break;
                        case 42:
                            get_arr(indexes,cell,cells,"4 семестр");
                            j+=7;
                            break;
                        case 50:
                            get_arr(indexes,cell,cells,"5 семестр");
                            j+=7;
                            break;
                        case 58:
                            get_arr(indexes,cell,cells,"6 семестр");
                            j+=7;
                            break;
                        case 66:
                            get_arr(indexes,cell,cells,"7 семестр");
                            j+=7;
                            break;
                        case 74:
                            get_arr(indexes,cell,cells,"8 семестр");
                            j+=7;
                            break;
                        case 82:
                            if (!get(cell).equals("0.0")) {
                                Kaf.add(" Номер кафедры " + get(cell));
                            }
                            break;
                        case 83:
                            Kaf.add(" Кафедра "+get(cell));
                            if (!get(cell).equals("0.0")) {
                                bool2=true;
                                indexes.add(Kaf);
                            }

                    }
                }

            }
            if (bool1 && bool2)
                Display(indexes);
            result += "\n";
        }

        return result;
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
        Hours_temp.add(get(cell));
        if (!Hours_temp.get(1).equals("0.0"))
            indexes.add(Hours_temp);
    }
}
