#include <jni.h>
#include <string>
#include <stdio.h>
#include <stdlib.h>
#include <opencv2/opencv.hpp>
using namespace cv;
using namespace std;

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_opencvdemo_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jintArray JNICALL
Java_com_example_opencvdemo_jni_OpenCVUtil_grey(JNIEnv *env, jclass clazz, jintArray pixels_,
                                                jint width, jint height) {
    // implement grey()
    jint *cbuf = env->GetIntArrayElements(pixels_, JNI_FALSE );
    if (cbuf == NULL) {
        return 0;
    }
    Mat imgData(height, width, CV_8UC4, (unsigned char *) cbuf);
    uchar* ptr = imgData.ptr(0);
    for(int i =0; i < width*height; i ++){
        //计算公式：Y(亮度) = 0.299*R + 0.587*G + 0.114*B
        //对于一个int四字节，其彩色值存储方式为：BGRA
        int grayScale = (int)(ptr[4*i+2]*0.299 + ptr[4*i+1]*0.587 + ptr[4*i+0]*0.114);
        ptr[4*i+1] = grayScale;
        ptr[4*i+2] = grayScale;
        ptr[4*i+0] = grayScale;
    }
    int size = width * height;
    jintArray result = env->NewIntArray(size);
    env->SetIntArrayRegion(result, 0, size, cbuf);
    env->ReleaseIntArrayElements(pixels_, cbuf, 0);
    return result;
}