#include<fcntl.h>
#include<sys/prctl.h>
#include<sys/wait.h>
#include<binder/IPCThreadState.h>
#include<binder/ProcessState.h>
#include<binder/IServiceManager.h>
#include<cutils/properties.h>
#include<utils/Log.h>
#include "ITestService.h"
using namespace android;

int main(void){

    sp<ProcessState> proc(ProcessState::self());
    sp<IServiceManager> sm = defaultServiceManager();
    sm->addService(String16("testservice"),new BnTestService());

    ProcessState::self()->startThreadPool();
    IPCThreadState::self->joinThreadPool();

    return 0;
}