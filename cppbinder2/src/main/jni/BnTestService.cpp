#include "ITestService.h"
#define LOG_TAG "TestService"

namespace android{

status_t BnTestService::onTransact(uint32_t code,const Parcel& data,Parcel* reply,uint32_t flags){

    switch(code){
        case TESTSERVICE_ADDSUM:
            int32_t arg0 = data.readInt32();
            int32_t arg1 = data.readInt32();
            int result = addSum(arg0,arg1);
            reply.writeInt32(cnt);
            return NO_ERROR;
        default:
            return BBinder::onTransact(code,data,reply,flags);
    }

}

int BnTestService::addSum(int a,int b){
    return a+b;
}

}