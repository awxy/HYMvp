package com.wxy.hyprodemo.http;

import com.google.gson.reflect.TypeToken;
import com.wxy.hyprodemo.bean.PMInfo;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * 自定义解析JSON
 * <p>
 * Created by Chang.Xiao on 2016/7/29.
 *
 * @version 1.0
 */
public final class JsonConverterFactory extends Converter.Factory {

    private final String TAG = JsonConverterFactory.class.getSimpleName();

    public static JsonConverterFactory create() {
        return new JsonConverterFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        // 进行条件判断，如果传进来的Type不是class，则匹配失败
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
            PMInfo pmInfo = new PMInfo();
            pmInfo.setErrormsg("1");

            return (T)pmInfo;


        }
    }

}
