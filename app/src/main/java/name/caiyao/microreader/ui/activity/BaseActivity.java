package name.caiyao.microreader.ui.activity;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;

import name.caiyao.microreader.config.Config;
import name.caiyao.microreader.utils.SharePreferenceUtil;
import name.caiyao.microreader.utils.StatusBarUtil;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    public int setToolBar(FloatingActionButton floatingActionButton, Toolbar toolbar, boolean isChangeToolbar, boolean isChangeStatusBar, DrawerLayout drawerLayout) {

        int vibrantColor = getSharedPreferences(SharePreferenceUtil.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE).getInt(SharePreferenceUtil.VIBRANT, 0);
        int mutedColor = getSharedPreferences(SharePreferenceUtil.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE).getInt(SharePreferenceUtil.MUTED, 0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (SharePreferenceUtil.isChangeNavColor(this))
                getWindow().setNavigationBarColor(vibrantColor);
            else
                getWindow().setNavigationBarColor(Color.BLACK);
        }
        if (floatingActionButton != null)
            floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(mutedColor));
        if (isChangeToolbar)
            toolbar.setBackgroundColor(vibrantColor);
        if (isChangeStatusBar) {
            if (SharePreferenceUtil.isImmersiveMode(this))
                StatusBarUtil.setColorNoTranslucent(this, vibrantColor);
            else
                StatusBarUtil.setColor(this, vibrantColor);
        }
        if (drawerLayout != null) {
            if (SharePreferenceUtil.isImmersiveMode(this))
                StatusBarUtil.setColorNoTranslucentForDrawerLayout(this, drawerLayout, vibrantColor);
            else
                StatusBarUtil.setColorForDrawerLayout(this, drawerLayout, vibrantColor);
        }
        return vibrantColor;
    }
}
