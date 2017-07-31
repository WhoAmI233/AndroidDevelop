#include <jni.h>
#include <string>

extern "C"
jstring
Java_suprise_qiuguochao_com_charm_FortuneBigWheel_FortuneBigWheel_1Choose_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
