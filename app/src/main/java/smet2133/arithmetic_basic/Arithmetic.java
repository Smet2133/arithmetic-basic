package smet2133.arithmetic_basic;

import android.app.Application;
import android.content.Context;

import com.jakewharton.threetenabp.AndroidThreeTen;

public class Arithmetic extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
        Arithmetic.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return Arithmetic.context;
    }

}
