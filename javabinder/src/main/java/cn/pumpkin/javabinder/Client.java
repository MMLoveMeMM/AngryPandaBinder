package cn.pumpkin.javabinder;

import android.os.IBinder;
import android.os.RemoteException;

import cn.pumpkin.javabinder.aidl.IJavaInterface;
import cn.pumpkin.javabinder.os.ServiceManager;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/10/24 17:42
 * @des:
 * @see {@link }
 */

public class Client {

    private final static String DESCRIBETOR =  "javaservice";
    private IJavaInterface mClientProxy;

    public Client(){

        IBinder binder = ServiceManager.getService(DESCRIBETOR);
        if(binder==null){
            return;
        }
        mClientProxy = IJavaInterface.Stub.asInterface(binder);

    }

    public int addSum(int a,int b){

        if(mClientProxy!=null){
            try {
                return mClientProxy.addSum(11,22);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

}
