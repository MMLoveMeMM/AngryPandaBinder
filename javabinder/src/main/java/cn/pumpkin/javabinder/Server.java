package cn.pumpkin.javabinder;
// import android.os.ServiceManager;

import cn.pumpkin.javabinder.os.ServiceManager;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/10/24 17:42
 * @des:
 * @see {@link }
 */

public class Server {

    private final static String DESCRIBETOR =  "javaservice";

    public void activeService(){

        /*
        * 将服务注册到系统(映射到binder上)
        * */
        ServiceManager.addService(DESCRIBETOR,new BnJavaService());

        /*
        * 维持活动状态
        * */
        while(true){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
