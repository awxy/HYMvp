package com.wxy.hyprodemo.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wxy.hyprodemo.bean.BaseInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;


public final class JsonConverterFactory extends Converter.Factory {

    private static final String ERROR_CODE = "Errorcode";
    private static final String ERROR_MSG = "Errormsg";

    private final String TAG = JsonConverterFactory.class.getSimpleName();

    public static JsonConverterFactory create() {
        return new JsonConverterFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
//        // 进行条件判断，如果传进来的Type不是class，则匹配失败
        if (!(type instanceof Class<?>)) {
            return null;
        }
        TypeToken<?> typeToken = TypeToken.get(type);
        Class<?> c = (Class<?>) type;

        return new JsonResponseBodyConverter<>(c);
    }

    final class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

        private final Class<?> c;


        public JsonResponseBodyConverter(Class<?> c) {
            this.c = c;
        }

        @Override
        public T convert(ResponseBody value) throws IOException {
            // 自己解析

            String jsonStr = value.string().trim();
            try {
                JSONObject jsonObject = new JSONObject(jsonStr);
                if (!jsonObject.isNull(ERROR_CODE)) {
                    Object obj;
                    Integer errorcode = (Integer) jsonObject.get(ERROR_CODE);
                    //请求成功 数据为空情况
                    if (errorcode == 200) {
                        Gson gson = new Gson();
                        obj = gson.fromJson(jsonStr, c);
                        if (!jsonObject.isNull("data")) {
                            JSONObject data = jsonObject.getJSONObject("data");
                            //空数据
                            if(data.toString().equals("{}")){
                                ((BaseInfo) obj).isHaveData =false;
                            }else{
                                ((BaseInfo) obj).isHaveData = true;
                            }
                            return  (T) obj;
                        }
                    }else{
                        obj = c.newInstance();
                        Field codeField = c.getField(ERROR_CODE);
                        Field msgField = c.getField(ERROR_MSG);
                        codeField.setInt(obj,errorcode);
                        msgField.set(obj,jsonObject.get(ERROR_MSG));
                    }
                    return (T)obj;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
