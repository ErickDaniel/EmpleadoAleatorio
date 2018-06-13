package basicsolutionsoftware.com.datosaleatorios.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import basicsolutionsoftware.com.datosaleatorios.Presenter.Interface.SplashPresenterInterface;
import basicsolutionsoftware.com.datosaleatorios.Presenter.SplashPresenter;
import basicsolutionsoftware.com.datosaleatorios.View.Interface.SplashActivityInterface;
import basicsolutionsoftware.com.datosaleatorios.R;

public class SplashActivity extends AppCompatActivity implements SplashActivityInterface {

    private SplashPresenterInterface presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
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
