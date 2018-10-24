package cn.pumpkin.angrypandabinder.proxy;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;

import cn.pumpkin.angrypandabinder.aidl.IBinderInterface;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/5/15 09:09
 * @des:主要用于对接播放业务层的接口
 * @see {@link }
 */

public class MediaServiceProxy implements ServiceConnection {

    private static final String TAG = MediaServiceProxy.class.getName();
    private final static String SERVICE_DEFUALT_CLASSNAME = "cn.pumpkin.angrypandabinder.core.MyService";

    private IBinderInterface mClientProxy;
    private static String gPackageName;
    private static String gClassName;
    public static MediaServiceProxy instance;
    private static Context gContext;

    public static void init(Context context, Looper looper, String packageName) {
        if (instance != null) {
            // TODO: Already initialized
            return;
        }
        gContext = context.getApplicationContext();
        gPackageName = (packageName == null ? context.getPackageName() : packageName);
        gClassName = SERVICE_DEFUALT_CLASSNAME;
        instance = new MediaServiceProxy();
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder binder) {
        Log.d(TAG,"onServiceConnected");
        /*
        * 打印这个binder地址,就会发现和MyService里面的binder地址是一样的,
        * 说明是同一个对象;
        * */
        Log.d(TAG,"IBinder srv : "+binder);
        /*
        * 这里返回的是IInterface的代理Proxy(这个代理implements了IBinderInterface),
        * 所以我们调用的都是代理,所以返回保存的变量取名mClientProxy更准确,
        * 提供给客户端调用发送数据的;
        * 主要:传入的binder作为Proxy代理类的构造函数中,赋值给mRemote(继承Binder类实现IBinder接口);
        * 这个mRemote是向binder发送通信的"手段"
        * */
        mClientProxy = IBinderInterface.Stub.asInterface(binder);// 断点进入这个方法;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.d(TAG,"onServiceDisconnected");
        if(mClientProxy!=null){
            mClientProxy=null;
        }
    }

    public void onBindService() {
        if (mClientProxy == null) {
            Intent iSrv = new Intent().setClassName(gPackageName, gClassName);
            Log.d(TAG,"onBindService start");
            if (!gContext.bindService(iSrv, instance, Service.BIND_AUTO_CREATE)) {
                Log.e(TAG,"remote mClientProxy bind failed");
            }
        }
    }

    public int addSum(int a,int b){
        if(mClientProxy!=null){
            try {
                return mClientProxy.addSum(a,b);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public void unBindService(){
        if (mClientProxy != null) {
            gContext.unbindService(instance);
            mClientProxy = null;
        }
    }


}
