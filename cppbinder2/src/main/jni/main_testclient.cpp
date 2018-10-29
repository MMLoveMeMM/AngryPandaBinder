#include<fcntl.h>
#include<sys/prctl.h>
#include<sys/wait.h>
#include<binder/IPCThreadState.h>
#include<binder/ProcessState.h>
#include<binder/IServiceManager.h>
#include<cutils/properties.h>
#include<utils/Log.h>
#include "ITestService.h"
#define LOG_TAG "TestClient"

using namespace android;

int main(int argc,char** argv){

    sp<ProcessState> proc(ProcessState::self());
    sp<IPServiceManager> sm = defaultServiceManager();
    sp<IBinder> binder = sm->getService(String16("testservice"));

    if(binder == 0){
        return -1;
    }

    sp<ITestService> proxy = interface_cast<ITestService>(binder);
    int result = proxy->addSum(11,22);
    printf("client calculate sum : 11+22 = %d",rersult)

    // 这边客户端可以不需要,获得结果以后就可以结束了
    // ProcessState::self()->startThreadPool();
    // IPCThreadState::self->joinThreadPool();
    return 0;

}

