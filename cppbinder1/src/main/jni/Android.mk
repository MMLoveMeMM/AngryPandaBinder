LOCAL_PATH: = $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS : = eng

LOCAL_SRC_FILES: = \
       binderClient.cpp

LOCAL_C_INCLUDES + = \
        $(LOCAL_PATH) \

LOCAL_SHARED_LIBRARIES : = \
    libcutils \
    libbinder \
    libutils \
    libhardware

LOCAL_CFLAGS : = -DRIL_SHLIB

LOCAL_MODULE: = binderClient

include $(BUILD_EXECUTABLE)

include $(CLEAR_VARS)

LOCAL_MODULE_TAGS : = eng

LOCAL_SRC_FILES: = \
           binderService.cpp

LOCAL_C_INCLUDES + = \
        $(LOCAL_PATH) \

LOCAL_SHARED_LIBRARIES : = \
    libcutils \
    libbinder \
    libutils \
    libhardware

LOCAL_CFLAGS : = -DRIL_SHLIB

LOCAL_MODULE: = binderService

include $(BUILD_EXECUTABLE)