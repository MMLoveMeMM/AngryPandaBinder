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
/*
* 参考android源代码main_mediaserver.cpp
*/
int main(void){

    sp<ProcessState> proc(ProcessState::self());
    sp<IServiceManager> sm = defaultServiceManager();
    sm->addService(String16("testservice"),new BnTestService());

    // 需要循环去读写binder设备,不能够让server程序结束
    // 其实就是起到hold住Service端的生命周期.
    // 不然Service立即结束
    ProcessState::self()->startThreadPool(); // 从/dev/binder读取客户端的请求
    IPCThreadState::self->joinThreadPool();

    return 0;
}