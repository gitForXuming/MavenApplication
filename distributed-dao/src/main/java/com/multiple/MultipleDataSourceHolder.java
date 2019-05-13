package com.multiple;

/**
 * Created by lenovo on 2019/4/19.
 * Title MultipleDataSourceHolder
 * Package  com.Multiple
 * Description
 *
 * @Version V1.0
 */
public class MultipleDataSourceHolder {
    private static ThreadLocal<String> holder = new ThreadLocal<String>();

    public static void setDataSourceTyep(String dataSourceType){
        holder.set(dataSourceType);
    }

    public static String getDataSourceType(){
        return holder.get();
    }
    public static void clear(){
        holder.remove();
    }
}
