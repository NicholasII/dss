package cn.qdcares.rpc.common.utils;

/**
 * @program: dss-parent
 * @description: 字符串工具类
 * @author: dongqun
 * @date: 2018/06/22 10:25
 **/
public class StringUtil {
    /**
     * @description 判断字符串是否为空
     * @param content 带判断字符串
     * @return 如果为空返回true
     * @Author dongqun
     * @Date 2018/6/22 10:28
     */
    public static boolean isEmpty(String content){
        if (content==null || "".equals(content)){
            return true;
        }
        return false;
    }
}
