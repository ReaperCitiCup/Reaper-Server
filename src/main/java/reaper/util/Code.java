package reaper.util;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by max on 2017/8/18.
 */
public class Code {

    public ArrayList<String> getStockCode() {

        File file = new File("src/main/code.txt");
        ArrayList<String> codes = new ArrayList<String>();

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String s;
            while ((s = bufferedReader.readLine()) != null) {

                String name = s.substring(0,6);
                codes.add(name);
                //System.out.println(name);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return codes;

    }



}