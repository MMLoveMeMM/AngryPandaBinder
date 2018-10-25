#include "ITestService.h"
namespace android{

class BpTestService:public BpInterface<ITestService>{

    public:
    BpTestService(const sp<IBinder>& impl):BpInterface<ITestService>(impl){

    }

    int addSum(int a,int b){
        Parcel data,reply;
        data.writeInt32(a);
        data.writeInt32(b);
        remote()->transact(TESTSERVICE_ADDSUM,data,&reply);
        return reply.readInt32();
    }

};

}


