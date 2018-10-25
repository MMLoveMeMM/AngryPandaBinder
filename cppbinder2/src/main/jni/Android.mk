LOCAL_PATH:= $(call my-dir)

include $(CLEAR_VARS)

LOCAL_SRC_FILES:= \
    BnTestService.cpp \
    BpTestService.cpp \
    TestServer.cpp

LOCAL_SHARED_LIBRARIES := \
    libcutils \
    libutils \
    liblog \
    libbinder

LOCAL_MODULE:= test_server
LOCAL_32_BIT_ONLY := true

include $(BUILD_EXECUTABLE)

include $(CLEAR_VARS)

LOCAL_SRC_FILES:= \
    BpTestService.cpp \
    TestClient.cpp

LOCAL_SHARED_LIBRARIES := \
    libcutils \
    libutils \
    liblog \
    libbinder

LOCAL_MODULE:= test_client
LOCAL_32_BIT_ONLY := true

include $(BUILD_EXECUTABLE)