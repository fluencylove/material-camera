package com.afollestad.materialcamera;

import android.app.Fragment;
import android.support.annotation.NonNull;
import com.afollestad.materialcamera.internal.BaseCaptureActivity;
import com.afollestad.materialcamera.internal.CarCameraFragment;

public class CarCaptureActivity extends BaseCaptureActivity {

    @Override
    @NonNull
    public Fragment getFragment() {
        return CarCameraFragment.newInstance();
    }
}