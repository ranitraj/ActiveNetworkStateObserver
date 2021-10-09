package com.android.ranit.activenetworkstatemonitor.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;

import com.android.ranit.activenetworkstatemonitor.R;
import com.android.ranit.activenetworkstatemonitor.databinding.ActivityMainBinding;
import com.android.ranit.activenetworkstatemonitor.model.NetworkStateManager;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private static final String INTERNET_CONNECTED = "internet_on.json";
    private static final String INTERNET_DISCONNECTED = "internet_off.json";

    private ActivityMainBinding mBinding;

    /**
     * Observer for internet connectivity status live-data
     */
    private final Observer<Boolean> activeNetworkStateObserver = new Observer<Boolean>() {
        @Override
        public void onChanged(Boolean isConnected) {
            prepareLottieAnimation(isConnected);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        NetworkStateManager.getInstance().getNetworkConnectivityStatus()
                .observe(this, activeNetworkStateObserver);
    }

    /**
     * Prepare lottie animations as per current Network-Status
     * @param isConnected - network connectivity status
     */
    private void prepareLottieAnimation(boolean isConnected) {
        Log.d(TAG, "prepareLottieAnimation() called with: isConnected = [" + isConnected + "]");
        if (isConnected) {
            mBinding.lottieNetworkConnection.setAnimation(INTERNET_CONNECTED);
            mBinding.tvMessage.setText(R.string.internet_connected);
        } else {
            mBinding.lottieNetworkConnection.setAnimation(INTERNET_DISCONNECTED);
            mBinding.tvMessage.setText(R.string.internet_disconnected);
        }
        mBinding.lottieNetworkConnection.playAnimation();
    }
}