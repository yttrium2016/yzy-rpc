package cn.com.yangzhenyu;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author yzy
 */
public class ClassHandle {
    public static ConcurrentMap<String,Class> map = new ConcurrentHashMap<>();
}
