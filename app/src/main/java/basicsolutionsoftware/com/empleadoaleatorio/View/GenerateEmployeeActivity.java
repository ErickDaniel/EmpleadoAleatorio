package basicsolutionsoftware.com.empleadoaleatorio.View;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
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
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.crash.FirebaseCrash;
import basicsolutionsoftware.com.empleadoaleatorio.BuildConfig;
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

    private String nombre;
    private String segundoNombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String fechaNacimiento;
    private int edad;
    private boolean cumplidos;
    private boolean hasSegundoNombre;
    private int sexo;
    private String entidadNacimiento;
    private String codigoEntidad;
    private String curp;
    private String nss;

    @OnClick(R.id.generar)
    public void generateAleatoryEmployee(Button button){
        clearForm();
        nss = presenter.getNssAleatory();
        sexo = presenter.getSexoAleatorio();
        hasSegundoNombre = Utils.getRandomInt(0,1) == 0;
        presenter.getNombreAleatorio(sexo);
    }

    @OnClick(R.id.generar_ine)
    public void generateIne(Button button){
        Dialog dialogIne = new Dialog(this);
        View viewIne = getLayoutInflater().inflate(R.layout.ine_fragment, null, false);
        dialogIne.setContentView(viewIne);
        dialogIne.show();
    }

    @Override
    public void setNombreAleatorio(String nombreAleatorio) {
        nombre = nombreAleatorio;
        if(hasSegundoNombre){
            presenter.getSegundoNombreAleatorio(sexo);
        } else {
            presenter.getApellidoPaternoAleatorio();
        }
    }

    @Override
    public void setSegundoNombreAleatorio(String segundoNombreAleatorio) {
        segundoNombre = segundoNombreAleatorio;
        presenter.getApellidoPaternoAleatorio();
    }

    @Override
    public void setApellidoPaternoAleatorio(String apellidoPaternoAleatorio) {
        apellidoPaterno = apellidoPaternoAleatorio;
        presenter.getApellidoMaternoAleatorio();
    }

    @Override
    public void setApellidoMaternoAleatorio(String apellidoMaternoAleatorio) {
        apellidoMaterno = apellidoMaternoAleatorio;
        presenter.getEdadAleatorio();
    }

    @Override
    public void setEdad(String fechaCumpleanios, int edad, boolean cumplidos) {
        this.fechaNacimiento = fechaCumpleanios;
        this.edad = edad;
        this.cumplidos = cumplidos;
        presenter.getEntidadAleatorio();
    }

    @Override
    public void setEntidadAleatoria(Entidad entidad) {
        this.codigoEntidad = entidad.getCodigo();
        this.entidadNacimiento = entidad.getEntidad();
        presenter.generateCURPAleatorio(nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, sexo, codigoEntidad);
    }

    @Override
    public void setCurp(String curp) {
        this.curp = curp;
        fillInputs();
    }

    private void fillInputs(){
        nombreEdt.setText(nombre);
        if(hasSegundoNombre){
            segundoNombreEdt.setVisibility(View.VISIBLE);
            segundoNombreTil.setVisibility(View.VISIBLE);
        } else {
            segundoNombreTil.setVisibility(View.GONE);
            segundoNombreEdt.setVisibility(View.GONE);
        }
        segundoNombreEdt.setText(segundoNombre);
        apellidoPaternoEdt.setText(apellidoPaterno);
        apellidoMaternoEdt.setText(apellidoMaterno);
        fechaNacimientoEdt.setText(fechaNacimiento);
        edadEdt.setText(Integer.toString(edad));
        cumplidosCheckBox.setChecked(cumplidos);
        sexoEdt.setText(sexo==5?"Hombre":"Mujer");
        codigoEntidadEdt.setText(codigoEntidad);
        entidadNacimientoEdt.setText(entidadNacimiento);
        curpEdt.setText(curp);
        nssEdt.setText(nss);
    }

    private void clearForm(){
        nombreEdt.setText("");
        apellidoPaternoEdt.setText("");
        apellidoMaternoEdt.setText("");
        fechaNacimientoEdt.setText("");
        edadEdt.setText("");
        cumplidosCheckBox.setChecked(false);
        sexoEdt.setText("");
        codigoEntidadEdt.setText("");
        entidadNacimientoEdt.setText("");
        curpEdt.setText("");
        nssEdt.setText("");
    }



}
