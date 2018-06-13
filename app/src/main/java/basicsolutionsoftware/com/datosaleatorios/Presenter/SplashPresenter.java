package basicsolutionsoftware.com.datosaleatorios.Presenter;

import android.os.CountDownTimer;

import basicsolutionsoftware.com.datosaleatorios.Presenter.Interface.SplashPresenterInterface;
import basicsolutionsoftware.com.datosaleatorios.View.Interface.SplashActivityInterface;
import basicsolutionsoftware.com.datosaleatorios.Commons.Constants;

public class SplashPresenter implements SplashPresenterInterface {

    private SplashActivityInterface callBack;

    public SplashPresenter(SplashActivityInterface callBack){
        this.callBack = callBack;
    }

    @Override
    public void initCountDownSplash() {
        new CountDownTimer(Constants.SPLASH_TIME, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                callBack.launchGenerateEmployee();
            }
        }.start();
    }
}
