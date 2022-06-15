package com.sungp.database.beans;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sungp
 * @description: bean转换工具类
 * @date 2022年06月13日 21:16
 */
@Slf4j
public class MyBeanUtils extends BeanUtils {

    /**
     * 通过反射获取bean对象
     * @description: 通过反射获取bean对象
     * @author sungp
     * @date 2022年06月13日 21:47
     * @param target 目标类型
     * @return T 对象
     */
    private static <T> T newInstance(Class<T> target) {
        try {
            return target.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 实体类转换
     * @description: 实体类转换
     * @author sungp
     * @date 2022年06月13日 21:20
     * @param source 数据源
     * @param target 目标
     * @param ignoreProperties 忽略字段
     * @return T
     */
    public static <S , T> T convert(S source , Class<T> target , String... ignoreProperties){
        T t = newInstance(target);
        copyProperties(source , target , ignoreProperties);
        return t;
    }

    /**
     * 实体类转换
     * @description: 实体类转换
     * @author sungp
     * @date 2022年06月13日 21:56
     * @param sList 数据源
     * @param target 目标
     * @param ignoreProperties 忽略字段
     * @return List<T>
     */
    public static <S , T> List<T> convert(List<S> sList , Class<T> target , String... ignoreProperties){
        return sList.stream().map(source -> convert(source , target)).collect(Collectors.toList());
    }

    /**
     * 实体类转换
     * @description: 实体类转换
     * @author sungp
     * @date 2022年06月13日 22:08
     * @param sPage 数据源
     * @param target 目标
     * @param ignoreProperties 忽略字段
     * @return Page<T>
     */
    public static <S , T> Page<T> convert(Page<S> sPage , Class<T> target , String... ignoreProperties){
        Page<T> tPage = new Page<>();
        copyProperties(sPage , tPage , "records");
        tPage.setRecords(convert(sPage.getRecords() , target));
        return tPage;
    }

}
