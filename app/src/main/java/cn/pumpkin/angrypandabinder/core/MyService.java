package cn.pumpkin.angrypandabinder.core;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import cn.pumpkin.angrypandabinder.aidl.IBinderInterface;

public class MyService extends Service {

    private final static String TAG = MyService.class.getName();
    private static final java.lang.String DESCRIPTOR = "cn.pumpkin.angrypandabinder.aidl.IBinderInterface";
    static final int TRANSACTION_addSum = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        // throw new UnsupportedOperationException("Not yet implemented");
        Log.d(TAG, "connect remote ...");
        /*
        * 打印binder的地址,与客户端那边进行比较
        * */
        Log.d(TAG, "binder obj : " + binder);
        /*
        * 这个binder对象是返回给客户端使用的
        * */
        return binder;
    }

    public IBinderInterface.Stub binder = new IBinderInterface.Stub() {
        @Override
        public int addSum(int a, int b) throws RemoteException {
            /*
            * Stub是抽象类,addSum方法必须要实现
            * */
            /*
            * 核心功能逻辑在服务端实现
            * */
            /*
            * 下面延时,只是为了证明AIDL接口默认是同步的.
            * 这个地方如果有延时,如果调用的接口是在客户端的UI主线程,那将会导致UI主线程卡顿,
            * 甚至导致ANR.所以注意分离.
            * 也就是说AIDL是同步的,不是异步的,这个很好理解,reply是阻塞式的等待结果的(算是死循环等待吧).
            * */
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return a + b;
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            /*
            * 必须是跨进程,这里才会调用,如果不是跨进程,即使用aidl,这里也不需要(也不会)调用
            * */
            Log.d(TAG, "onTransact code : " + code);

            switch (code) {
                case TRANSACTION_addSum: {
                    /*
                    * TRANSACTION_addSum 其实是从IBinderInterface.aidl自动build生成的TRANSACTION_addSum.java中
                    * 获取的.其中DESCRIPTOR也是;
                    * 另外一个INTERFACE_TRANSACTION : 这个是对应binder注册到驱动binder的code标记
                    * */
                    data.enforceInterface(DESCRIPTOR);// 没有这一句,下面的data值是非确定性的
                    int arg0 = data.readInt();
                    int arg1 = data.readInt();
                    // 相对的方法:attachInterface,它主要是将DESCRIPTOR和对应的Binder(或者其子类)生产一张map表放在binder里面
                    IBinderInterface inf = (IBinderInterface) this.queryLocalInterface(DESCRIPTOR);
                    int result = inf.addSum(arg0, arg1);
                    reply.writeNoException();
                    reply.writeInt(result);

                    return true;
                }
            }
            return super.onTransact(code, data, reply, flags);
        }
    };
}
