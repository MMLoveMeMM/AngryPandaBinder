这个module工程注意:<br>
<1> : 代码要放到Android源码编译系统里面进行编译,具体操作步骤如下:<br>
1. 把 IJavaInterface.aidl 放入 frameworks/base/core/java/android/os<br>
2. 修改 frameworks/base/Android.mk 添加一行<br>
core/java/android/os/IVibratorService.aidl \<br>
+ core/java/android/os/IJavaInterface.aidl \<br>
3. mmm frameworks/base (源码已经完整编译过一次了)<br>
<2> : android.os.ServerManager是系统级别的类,普通APP无法调用,<br>
工程中的ServiceManager在放到系统里面编译时需要删除,用系统的android.os.ServerManager<br>

<3> : Android.mk文件<br>
# Copyright 2008 The Android Open Source Project<br>
#<br>
LOCAL_PATH:= $(call my-dir)<br>

include $(CLEAR_VARS)<br>
LOCAL_SRC_FILES := JavaService.java IJavaInterface.java Server.java<br>
LOCAL_MODULE := TestServer<br>
include $(BUILD_JAVA_LIBRARY)<br>
<br>
include $(CLEAR_VARS)<br>
LOCAL_SRC_FILES := JavaService.java IJavaInterface.java Client.java<br>
LOCAL_MODULE := TestClient<br>
include $(BUILD_JAVA_LIBRARY)<br>











