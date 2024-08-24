# Add project specific ProGuard rules here.

# Keep Room entities and DAOs
-keep class androidx.room.** { *; }
-keepclassmembers class * extends androidx.room.RoomDatabase {
    @androidx.room.Dao *;
}

# Keep Kotlin serialization classes
-keep class kotlinx.serialization.** { *; }
-keep class kotlinx.serialization.internal.** { *; }
-keep class kotlinx.serialization.descriptors.** { *; }
-keep class kotlinx.serialization.modules.** { *; }

# Keep Dagger Hilt components and modules
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-keep class * extends dagger.hilt.android.HiltAndroidApp { *; }
-keep class * extends dagger.hilt.android.components.ActivityComponent { *; }
-keep class * extends dagger.hilt.android.components.FragmentComponent { *; }
-keep class * extends dagger.hilt.android.components.ViewComponent { *; }

# Keep data classes with @Serializable annotation
-keep @kotlinx.serialization.Serializable class * { *; }

# Keep classes with annotations used by Hilt
-keep class * {
    @dagger.hilt.android.scopes.ActivityScoped *;
    @dagger.hilt.android.scopes.ViewModelScoped *;
    @dagger.hilt.android.scopes.FragmentScoped *;
}

# Keep ViewModel classes
-keep class androidx.lifecycle.ViewModel { *; }
-keep class androidx.lifecycle.SavedStateHandle { *; }
-keep class androidx.lifecycle.LifecycleObserver { *; }
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

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile