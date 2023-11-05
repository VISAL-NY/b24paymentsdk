# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-keep class com.bill24.b24paymentsdk.*.**{*;}
#-keep class com.bill24.b24paymentsdk.model.**{*;}
#-keep class com.bill24.b24paymentsdk.adapter.**{*;}
#-keep class com.bill24.b24paymentsdk.bottomsheetDialogFragment.**{*;}
#-keep class com.bill24.b24paymentsdk.core.**{*;}
#-keep class com.bill24.b24paymentsdk.customShapeDrawable.**{*;}
#-keep class com.bill24.b24paymentsdk.fragment.**{*;}
#-keep class com.bill24.b24paymentsdk.helper.**{*;}
#-keep class com.bill24.b24paymentsdk.model.**{*;}
#-keep class com.bill24.b24paymentsdk.socketIO.**{*;}
#-keep class com.bill24.b24paymentsdk.SuccessActivity{*;}
#
#-keep class com.bill24.b24paymentsdk.main.B24PaymentSdk{
#    <init>();
#    public static void initSdk(...);
#}

-dontwarn com.google.android.material.R$id

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile