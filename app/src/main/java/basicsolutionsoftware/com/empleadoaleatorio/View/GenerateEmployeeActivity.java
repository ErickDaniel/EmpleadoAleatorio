package basicsolutionsoftware.com.empleadoaleatorio.View;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.crash.FirebaseCrash;

import basicsolutionsoftware.com.empleadoaleatorio.BuildConfig;
import basicsolutionsoftware.com.empleadoaleatorio.Commons.InfoAleatoria;
import basicsolutionsoftware.com.empleadoaleatorio.Commons.Utils;
import basicsolutionsoftware.com.empleadoaleatorio.Domain.Objects.Entidad;
import basicsolutionsoftware.com.empleadoaleatorio.Presenter.GenerateEmployeePresenter;
import basicsolutionsoftware.com.empleadoaleatorio.Presenter.Interface.GenerateEmployeePresenterInterface;
import basicsolutionsoftware.com.empleadoaleatorio.R;
import basicsolutionsoftware.com.empleadoaleatorio.View.Interface.GenerateEmployeeActivityInterface;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GenerateEmployeeActivity extends AppCompatActivity implements GenerateEmployeeActivityInterface {

    private GenerateEmployeePresenterInterface presenter;

    @BindView(R.id.nombre)              TextView    nombreEdt;
    @BindView(R.id.segundo_nombre)      TextView    segundoNombreEdt;
    @BindView(R.id.segundo_nombre_text_input_layout) TextInputLayout segundoNombreTil;
    @BindView(R.id.apellido_paterno)    TextView    apellidoPaternoEdt;
    @BindView(R.id.apellido_materno)    TextView    apellidoMaternoEdt;
    @BindView(R.id.edad)                TextView    edadEdt;
    @BindView(R.id.sexo)                TextView    sexoEdt;
    @BindView(R.id.curp)                TextView    curpEdt;
    @BindView(R.id.nss)                 TextView    nssEdt;
    @BindView(R.id.codigo_entidad)      TextView    codigoEntidadEdt;
    @BindView(R.id.entidad_nacimiento)  TextView    entidadNacimientoEdt;
    @BindView(R.id.fecha_nacimiento)    TextView    fechaNacimientoEdt;
    @BindView(R.id.cumplidos)           CheckBox    cumplidosCheckBox;
    @BindView(R.id.toolbar)             Toolbar     toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_employee);
        FirebaseCrash.log(this.getClass().toString().concat(getString(R.string.space)).concat(getString(R.string.created)));
        ButterKnife.bind(this);
        initToolBar();
        presenter = new GenerateEmployeePresenter(this, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_generate_employee_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.info:
                showInfo();
                break;
            default:

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showInfo(){
        Dialog dialogInfo = new Dialog(this);
        View viewInfo = getLayoutInflater().inflate(R.layout.dialog_info_app, null, false);
        TextView version = viewInfo.findViewById(R.id.version);
        version.setText(getString(R.string.version_).concat(BuildConfig.VERSION_NAME));
        dialogInfo.setContentView(viewInfo);
        dialogInfo.show();
    }

    private void initToolBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.aleatory_employee);
    }

    @OnClick(R.id.generar)
    public void generateAleatoryEmployee(Button button){
        nssEdt.setText(presenter.getNssAleatory());
        presenter.getSexoAleatorio();

    }

    @OnClick(R.id.generar_ine)
    public void generarINE(Button b){
        Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().setLayout(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        View viewDialog = getLayoutInflater().inflate(R.layout.ine_fragment, null);
        dialog.setContentView(viewDialog);
        dialog.show();
    }

    @Override
    public void setNombreAleatorio(String object) {
        nombreEdt.setText(object);
    }

    @Override
    public void setSegundoNombreAleatorio(String object) {
        segundoNombreEdt.setText(object);
    }

    @Override
    public void setApellidoPaternoAleatorio(String object) {
        apellidoPaternoEdt.setText(object);
    }

    @Override
    public void setApellidoMaternoAleatorio(String object) {
        apellidoMaternoEdt.setText(object);
    }

    @Override
    public void setSexo(int sexo) {
        if(sexo == InfoAleatoria.HOMBRE){
            sexoEdt.setText(R.string.hombre);
        } else {
            sexoEdt.setText(R.string.mujer);
        }
        presenter.getNombreAleatorio(sexo);
        if(Utils.getRandomInt(0,1) == 1) {
            segundoNombreTil.setVisibility(View.VISIBLE);
            presenter.getSegundoNombreAleatorio(sexo);
        } else {
            segundoNombreTil.setVisibility(View.GONE);
        }
        presenter.getApellidoPaternoAleatorio();
        presenter.getApellidoMaternoAleatorio();
        presenter.getEdadAleatorio();
        presenter.getEntidadAleatorio();
    }

    @Override
    public void setEntidadAleatoria(Entidad entidad) {
        codigoEntidadEdt.setText(entidad.getCodigo());
        entidadNacimientoEdt.setText(entidad.getEntidad());

        presenter.generateCURPAleatorio(nombreEdt.getText().toString(), apellidoPaternoEdt.getText().toString(),
                apellidoMaternoEdt.getText().toString(), fechaNacimientoEdt.getText().toString(),
                sexoEdt.getText().toString(), codigoEntidadEdt.getText().toString());
    }

    @Override
    public void setEdad(String fechaCumpleanios, int edad, boolean cumplidos) {
        edadEdt.setText(Integer.toString(edad));
        fechaNacimientoEdt.setText(fechaCumpleanios);
        cumplidosCheckBox.setChecked(cumplidos);
    }

    @Override
    public void setCurp(String curp) {
        curpEdt.setText(curp);
    }
}
