#ifndef ANDROID_ITESTSERVICE_H
#define ANDROID_ITESTSERVICE_H


#include<utils/Errors.h>
#include<utils/KeyedVector.h>
#include<utils/RefBase.h>
#include<utils/String8.h>
#include<binder/IIterface.h>
#include<binder/Parcel.h>

#define TESTSERVICE_ADDSUM 0

namespace android {

class ITestService:public IInterface{
    public:
    DECLARE_META_INTERFACE(TestService);
    virtual int addSum(int a,int b);
};

class BnTestService:public BnInterface<ITestService>{
    public:
    virtual status_t onTransact(uint32_t code,const Parcel& data,Parcel* reply,uint32_t flags=0);
    virtual int addSum(int a,int b);
};

}

#endif




