package com.example.train.utils;

import com.alibaba.fastjson.JSONObject;
import com.example.train.common.Code;
import com.example.train.common.Consts;
import com.example.train.common.Status;

public class JSONUtil {

    public static String JSONSuccessToString(Object content1){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Consts.CODE, Code.class.getField("SUCCESS").get(null));
            jsonObject.put(Consts.STATUS, Status.class.getField("SUCCESS").get(null));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        jsonObject.put(Consts.DATA, content1);

        return jsonObject.toJSONString();
    }

    public static String JSONFailedToString(Object content){
        JSONObject json = new JSONObject();
        try {
            json.put(Consts.CODE, "600");
            json.put(Consts.STATUS, "600");
        } catch (Exception e) {
            e.printStackTrace();
        }
        json.put(Consts.DATA, content);

        return json.toJSONString();
    }

    public static String JSONToString(boolean flag,Object content1,Object content2,String attribute){

        JSONObject jsonObject = new JSONObject();

        if (flag){
            try {
                jsonObject.put(Consts.CODE, Code.class.getField("SUCCESS").get(null));
                jsonObject.put(Consts.STATUS, Status.class.getField("SUCCESS").get(null));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
            jsonObject.put(Consts.DATA, content1);
        }else {
            try {
                jsonObject.put(Consts.CODE, Code.class.getField(attribute).get(null));
                jsonObject.put(Consts.STATUS, Status.class.getField(attribute).get(null));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
            jsonObject.put(Consts.DATA, content2);
        }

        return jsonObject.toJSONString();
    }
}
