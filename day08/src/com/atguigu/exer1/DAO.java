package com.atguigu.exer1;

import java.util.*;

/**
 * 定义个泛型类 DAO<T>，在其中定义一个 Map 成员变量，Map 的键
 * 为 String 类型，值为 T 类型。
 * 分别创建以下方法：
 * public void save(String id,T entity)： 保存 T 类型的对象到 Map 成员
 * 变量中
 * public T get(String id)：从 map 中获取 id 对应的对象
 * public void update(String id,T entity)：替换 map 中 key 为 id 的内容,
 * 改为 entity 对象
 * public List<T> list()：返回 map 中存放的所有 T 对象
 * public void delete(String id)：删除指定 id 对象
 *
 *
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/7/26 22:10
 */
public class DAO<T> {
    //此处提前赋值，可以方便后面用，防止空指针异常
    private Map<String,T> map = new HashMap<>();
    // 保存 T 类型的对象到 Map 成员变量中
    public void save(String id,T entity){
        map.put(id, entity);
    }
    //从 map 中获取 id 对应的对象
    public T get(String id) {
        return map.get(id);
    }
    //替换 map 中 key 为 id 的内容,改为 entity 对象
    public void update(String id,T entity) {
        if (map.containsKey(id)){
            map.put(id, entity);
        }
    }
    //返回 map 中存放的所有 T 对象
    public List<T> list() {
        //错误的
//        Collection<T> values = map.values();
//        return (List<T>) values;
        //正确的
        ArrayList<T> list = new ArrayList<>();
        Collection<T> values = map.values();
        for (T t :
                values) {
            list.add(t);
        }
        return list;
    }
    //删除指定 id 对象
    public void delete(String id) {
        map.remove(id);
    }
}
