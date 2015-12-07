LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE := libYvImSdk
LOCAL_SRC_FILES := android/libs/$(TARGET_ARCH_ABI)/libYvImSdk.so
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/include
include $(PREBUILT_SHARED_LIBRARY)