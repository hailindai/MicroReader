package name.caiyao.microreader;

import android.app.Application;
import android.content.Context;

/**
 * Created by 蔡小木 on 2016/3/9 0009.
 * Updated by hailin on 2017/05/26.
 */
public class MicroApplication extends Application {

    public static MicroApplication microApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        microApplication = this;
    }

    public static Context getContext(){
        return microApplication;
    }
}
