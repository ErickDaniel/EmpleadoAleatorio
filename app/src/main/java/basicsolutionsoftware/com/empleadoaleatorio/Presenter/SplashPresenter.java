package basicsolutionsoftware.com.empleadoaleatorio.Presenter;

import android.os.CountDownTimer;

import basicsolutionsoftware.com.empleadoaleatorio.Commons.Constants;
import basicsolutionsoftware.com.empleadoaleatorio.Presenter.Interface.SplashPresenterInterface;
import basicsolutionsoftware.com.empleadoaleatorio.View.Interface.SplashActivityInterface;

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
