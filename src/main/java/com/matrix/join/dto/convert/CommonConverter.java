package com.matrix.join.dto.convert;

import com.matrix.join.constant.BasicConstant;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * @ClassName CommonConverter
 * @Description 公共
 * @Author Administrator
 * @Date 2020/3/22 12:46
 * @Version 1.0
 */
public class CommonConverter {

    /**
     * dto po 值传递
     * @param clazz
     * @param obj
     * @param <T>
     * @return
     */
    public <T> T convert(Class<T> clazz, Object obj) {
        if (Objects.isNull(obj)) {
            return null;
        }
        T t = null;
        try {
            t = clazz.newInstance();
            Class<?> dtoClass = obj.getClass();
            Method[] methods = clazz.getMethods();
            T finalT = t;
            Arrays.stream(methods).filter(x -> x.getName().startsWith(BasicConstant.SET)).forEach(x -> {
                try {
                    if (x.getParameterTypes()[0].getSimpleName()
                            .equals(dtoClass.getMethod(BasicConstant.GET + x.getName()
                            .substring(BasicConstant.PARAMETER_START_INDEX)).getReturnType().getSimpleName())) {
                        try {
                            x.invoke(finalT, dtoClass.getMethod(BasicConstant.GET + x.getName()
                                .substring(BasicConstant.PARAMETER_START_INDEX)).invoke(obj));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            });
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * po -> dto
     * @param clazz
     * @param po
     * @param <T>
     * @return
     */
    public <T> T convertPOToDTO(Class<T> clazz, Object po) {
        return null;
    }
}
