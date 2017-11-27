#include <stdio.h>
#include <stdlib.h>
#include <jni.h>			///usr/lib/jvm/java-1.8.0-openjdk-amd64/include/
#include <android/log.h>

#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/ioctl.h>

static jint fd;

jint c_LEDOpen(JNIEnv *env, jobject cls)
{
	fd = open("/dev/leds", O_RDWR);
//	__android_log_print(ANDROID_LOG_DEBUG, "LEDDemo", "native led open: %d", fd);
	if(fd >= 0)
		return 0;
	else 
		return -1;
	return 0;
}

void c_LEDClose(JNIEnv *env, jobject cls)
{
//	__android_log_print(ANDROID_LOG_DEBUG, "LEDDemo", "native led close...");
	close(fd);
}

jint c_LEDControl(JNIEnv *env, jobject cls, jint which, jint Status)
{
	int ret = ioctl(fd, Status, which);
//	__android_log_print(ANDROID_LOG_DEBUG, "LEDDemo", "native led control %d %d", which, Status);
	return ret;
}

static const JNINativeMethod methods[] = 
{
	{"LEDOpen", "()I", (void *)c_LEDOpen},
	{"LEDClose", "()V", (void *)c_LEDClose},
	{"LEDControl", "(II)I", (void *)c_LEDControl},
};

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *jvm, void *reserved)
{
	JNIEnv *env = NULL;
	jclass cls;
	
	if((*jvm)->GetEnv(jvm, (void **)&env, JNI_VERSION_1_4))
	{
		return JNI_ERR;
	}
	
	cls = (*env)->FindClass(env, "com/example/shuimu/HardLib/HardControl");
	if(cls == NULL)
	{
		return JNI_ERR;
	}
	
	if((*env)->RegisterNatives(env, cls, methods, sizeof(methods) / sizeof(methods[0])) < 0)
		return JNI_ERR;
	
	return JNI_VERSION_1_4;
}

