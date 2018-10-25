#include<binder/IServiceManager.h>
#include<binder/IBinder.h>
#include<binder/Parcel.h>
#include<binder/ProcessState.h>
#include<binder/IPCThreadState.h>
using namespace android;
#ifdef LOG_TAG
#undef LOG_TAG
#endif
#define LOG_TAG "SERVICE"
#define SERVICE_DES "binder.test"
#define FUNC_CALLFUNCTION 1

class Service : public BBinder{

public:
    Service(){
        descriptor =String16(SERVICE_DES);
    }
    virtual ~Service(){

    }
    virtual const String16& getInterfaceDescriptor() const{
        return descriptor;
    }
protected:
    void callFunction(){
        LOGE("service callfunction ...");
    }
    virtual status_t onTransact(uint32_t code,const Parcel& data,
    Parcel* reply,uint32_t flags = 0){
        LOGD("service onTransact,code = %d",code);
        switch(code){
            case FUNC_CALLFUNCTION:
                callFunction();
                break;
            default:
                return BBinder::onTransact(code,data,reply,flags);
        }
        return 0;
    }
private:
    String16 descriptor;
};

int main(){

    sp<IServiceManager> sm = defaultServiceManager();
    Service* srv = new Service();
    status_t ret = sm->addService(String16(SERVICE_DES),srv);
    LOGD("service addservice return %d",ret);
    ProcessState::self()->startThreadPool();
    IPCThreadState::self()->joinThreadPool(true);

    return 0;

}

