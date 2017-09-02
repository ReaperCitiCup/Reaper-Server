package reaper.util;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by max on 2017/8/18.
 */
public class Code {

    public ArrayList<String> getStockCode() {

        File file = new File("manager.csv");
        ArrayList<String> codes = new ArrayList<String>();

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String s;
            while ((s = bufferedReader.readLine()) != null) {


                codes.add(s);
                //System.out.println(name[1]);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return codes;

    }



}
