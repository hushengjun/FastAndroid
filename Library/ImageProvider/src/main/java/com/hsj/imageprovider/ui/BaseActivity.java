package com.hsj.imageprovider.ui;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * @Company:****息科技有限公司
 * @Author:HSJ
 * @E-mail:mr.ajun@foxmail.com
 * @Date:2017/4/7 10:51
 * @Version:FastAndroid V21.0
 * @Class:BaseActivity
 * @Description: 权限检测
 */
public class BaseActivity extends AppCompatActivity {

    private int PERMISSION_CODE = 0;

    /**
     * 查找View
     *
     * @param id  控件的id
     * @param <V> View类型
     * @return
     */
    protected <V extends View> V findView(@IdRes int id) {
        return (V) findViewById(id);
    }

    /**
     * 展示对话框
     */
    public void showDialog(){

    }

    /**
     * 取消对话框
     */
    public void dismissDilog(){

    }

    /**
     * Toast
     * @param msg
     */
    public void showToast(@NonNull String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 复写读取图片、调用camera方法，（子类复写该方法即可）
     */
    public void hadPermission(int permissionCode) {

    }

    /**
     * 需要检测的权限数组
     *
     * @param permission
     */
    public void checkPermission(@NonNull String[] permission, int permissionCode) {
        this.PERMISSION_CODE = permissionCode;
        boolean flag = true;
        for (int i = 0; i < permission.length; i++) {
            flag = flag && (ActivityCompat.checkSelfPermission(this, permission[i]) == 0);
        }

        if (flag) {//全都有权限
            hadPermission(PERMISSION_CODE);
        } else {//非全授权
            ActivityCompat.requestPermissions(this, permission, PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_CODE) {
            boolean flag = true;
            for (int i = 0; i < grantResults.length; i++) {
                flag = flag && (grantResults[i] == 0);
            }

            if (flag) {
                showToast("授权成功!");
                hadPermission(PERMISSION_CODE);
            } else {
                showToast("权限被拒绝，无法进行以上操作!");
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}