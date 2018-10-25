package cn.pumpkin.javabinder;

import android.os.RemoteException;

import cn.pumpkin.javabinder.aidl.IJavaInterface;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/10/24 17:49
 * @des:
 * @see {@link }
 */

public class BnJavaService extends IJavaInterface.Stub{
    @Override
    public int addSum(int a, int b) throws RemoteException {
        return a+b;
    }
}
