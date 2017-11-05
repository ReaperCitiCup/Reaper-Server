package reaper.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PythonUser {
    /**
     * 调用python代码
     * @param pyName python脚本名（带.py）
     * @param argvs 参数
     * @return 所有命令行输出
     */
    public static String usePy(String pyName, String argvs){
        String res = "";
        try {
            Process process = Runtime.getRuntime().exec("python2 "+pyName+" "+argvs);

            InputStreamReader ins = new InputStreamReader(process.getInputStream());
            BufferedReader br = new BufferedReader(ins);
            String s;
            while ((s = br.readLine())!=null){
                res+=s+"\n";
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;

    }
}
