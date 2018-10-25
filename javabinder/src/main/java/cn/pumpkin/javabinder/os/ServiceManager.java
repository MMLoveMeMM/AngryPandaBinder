package cn.pumpkin.javabinder.os;

import android.os.IBinder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/10/24 18:00
 * @des:
 * @see {@link }
 */

public class ServiceManager {

    private static Map<String,IBinder> binderMap=new HashMap<>();
    public static void addService(String name, IBinder binder){
        binderMap.put(name,binder);
    }

    public static IBinder getService(String name){
        if(binderMap.size()>0){
            return binderMap.get(name);
        }
        return null;
    }
}
