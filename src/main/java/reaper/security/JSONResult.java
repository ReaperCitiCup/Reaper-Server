package reaper.security;

import org.json.JSONObject;

/**
 * Created by Sorumi on 17/5/13.
 */
public class JSONResult{
    public static String fillResultString(Integer status, String message, Object result){
        JSONObject jsonObject = new JSONObject(){{
            put("status", status);
            put("message", message);
            put("result", result);
        }};

        return jsonObject.toString();
    }
}
