package basicsolutionsoftware.com.empleadoaleatorio.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.crash.FirebaseCrash;

import basicsolutionsoftware.com.empleadoaleatorio.Presenter.Interface.SplashPresenterInterface;
import basicsolutionsoftware.com.empleadoaleatorio.Presenter.SplashPresenter;
import basicsolutionsoftware.com.empleadoaleatorio.R;
import basicsolutionsoftware.com.empleadoaleatorio.View.Interface.SplashActivityInterface;

public class SplashActivity extends AppCompatActivity implements SplashActivityInterface {

    private SplashPresenterInterface presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        FirebaseCrash.log(this.getClass().toString().concat(getString(R.string.space)).concat(getString(R.string.created)));
        presenter = new SplashPresenter(this);
        presenter.initCountDownSplash();
    }

    @Override
    public void launchGenerateEmployee() {
        if(this != null) {
            Intent intent = new Intent(this, GenerateEmployeeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
            startActivity(intent);
        }
    }



}
