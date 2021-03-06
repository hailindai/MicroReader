package name.caiyao.microreader.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import name.caiyao.microreader.R;
import name.caiyao.microreader.presenter.IWelcomePresenter;
import name.caiyao.microreader.presenter.impl.WelcomePresenterImpl;
import name.caiyao.microreader.ui.iView.IWelcome;
import name.caiyao.microreader.utils.SharePreferenceUtil;

public class WelcomeActivity extends BaseActivity implements IWelcome {

    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 1;
    private IWelcomePresenter mIWelcomePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mIWelcomePresenter = new WelcomePresenterImpl(this, this);

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            new AlertDialog.Builder(this).setMessage(getString(R.string.request_storage_permission)).setPositiveButton(R.string.common_i_know, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(WelcomeActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE_REQUEST_CODE);
                }
            }).setCancelable(false).show();
        } else {
            mIWelcomePresenter.getBackground();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mIWelcomePresenter.getBackground();
            } else {
                new AlertDialog.Builder(this).setMessage(getString(R.string.re_request_permission)).setPositiveButton(getString(R.string.common_i_know), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(WelcomeActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE_REQUEST_CODE);
                    }
                }).show();
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void hasGetBackground() {
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        finish();
    }
}
