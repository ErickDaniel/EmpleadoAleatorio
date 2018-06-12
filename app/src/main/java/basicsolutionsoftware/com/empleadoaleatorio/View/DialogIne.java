package basicsolutionsoftware.com.empleadoaleatorio.View;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import basicsolutionsoftware.com.empleadoaleatorio.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogIne extends Dialog {

    @BindView((R.id.fecha_nacimiento_identificacion_tv))TextView fechaNacimientoTV;
    @BindView(R.id.sexo_identificacion_tv)              TextView sexoTV;
    @BindView(R.id.nombres_identificacion_tv)           TextView nombresTV;
    @BindView(R.id.apellidos_identificacion_tv)         TextView apellidosTV;
    @BindView(R.id.domicilio_identificacion_tv)         TextView domicilioTV;
    @BindView(R.id.clave_elector_identificacion_tv)     TextView claveElectorTV;
    @BindView(R.id.curp_identificacion_tv)              TextView curpTV;
    @BindView(R.id.anio_registro_identificacion_tv)     TextView anioRegistroTV;
    @BindView(R.id.estado_identificacion_tv)            TextView estadoTV;
    @BindView(R.id.localidad_identificacion_tv)         TextView localidadTV;
    @BindView(R.id.municipio_identificacion_tv)         TextView municipioTV;
    @BindView(R.id.emision_identificacion_tv)           TextView emisionTV;
    @BindView(R.id.seccion_identificacion_tv)           TextView seccionTV;
    @BindView(R.id.vigencia)                            TextView vigenciaTV;
    @BindView(R.id.image_person_tv)                     ImageView imagePerson;
    @BindView(R.id.image_person_small_iv)               ImageView imagePersonSmall;
    @BindView(R.id.name_person_tv)                      TextView namePersonTV;
    private DialogIneData dialogIneData;

    public DialogIne(@NonNull Context context, DialogIneData dialogIneData) {
        super(context);
        this.dialogIneData = dialogIneData;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View viewIne = getLayoutInflater().inflate(R.layout.ine_fragment, null, false);
        ButterKnife.bind(this, viewIne);
        setContentView(viewIne);
        if(getContext().getResources().getBoolean(R.bool.isTablet)) {
            getWindow().setLayout(1500, 1000);
        }
        fillData();
    }

    private void fillData(){
        if(dialogIneData != null & !dialogIneData.getNombres().trim().isEmpty()) {
            fechaNacimientoTV.setText(dialogIneData.getFechaNacimiento());
            sexoTV.setText(dialogIneData.getSexo());
            nombresTV.setText(dialogIneData.getNombres());
            apellidosTV.setText(dialogIneData.getApellidos());
            domicilioTV.setText(dialogIneData.getDomicilio());
            claveElectorTV.setText(dialogIneData.getClaveElector());
            curpTV.setText(dialogIneData.getCurp());
            anioRegistroTV.setText(dialogIneData.getAnioRegistro());
            estadoTV.setText(dialogIneData.getEstado());
            localidadTV.setText(dialogIneData.getLocalidad());
            municipioTV.setText(dialogIneData.getMunicipio());
            emisionTV.setText(dialogIneData.getEmision());
            seccionTV.setText(dialogIneData.getSeccion());
            vigenciaTV.setText(dialogIneData.getVigencia());
        }
    }

    public void refreshImage(String url) {
        if(dialogIneData != null & !dialogIneData.getNombres().trim().isEmpty()) {
            Glide.with(getContext()).load(url).into(imagePerson);
            Glide.with(getContext()).load(url).into(imagePersonSmall);
            if (dialogIneData != null) {
                if (dialogIneData.getNombres() != null && !dialogIneData.getNombres().isEmpty()) {
                    StringBuilder name = new StringBuilder();
                    for (int i = 0; i < 80; i++) {
                        name.append(dialogIneData.getNombres().concat(dialogIneData.getApellidos()).replaceAll(" ", "").toUpperCase());
                    }
                    namePersonTV.setText(name.toString());
                }
            }
        }
    }

    public static class DialogIneData{

        private String fechaNacimiento;
        private String sexo;
        private String nombres;
        private String apellidos;
        private String domicilio;
        private String claveElector;
        private String curp;
        private String anioRegistro;
        private String estado;
        private String localidad;
        private String municipio;
        private String emision;
        private String seccion;
        private String vigencia;
        private String URLFotografia;

        public String getFechaNacimiento() {
            return fechaNacimiento;
        }

        public void setFechaNacimiento(String fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
        }

        public String getSexo() {
            return sexo;
        }

        public void setSexo(String sexo) {
            this.sexo = sexo.toUpperCase();
        }

        public String getNombres() {
            return nombres;
        }

        public void setNombres(String nombres) {
            this.nombres = nombres.toUpperCase();
        }

        public String getApellidos() {
            return apellidos;
        }

        public void setApellidos(String apellidos) {
            this.apellidos = apellidos.toUpperCase();
        }

        public String getDomicilio() {
            return domicilio;
        }

        public void setDomicilio(String domicilio) {
            this.domicilio = domicilio.toUpperCase();
        }

        public String getClaveElector() {
            return claveElector;
        }

        public void setClaveElector(String claveElector) {
            this.claveElector = claveElector.toUpperCase();
        }

        public String getCurp() {
            return curp;
        }

        public void setCurp(String curp) {
            this.curp = curp.toUpperCase();
        }

        public String getAnioRegistro() {
            return anioRegistro;
        }

        public void setAnioRegistro(String anioRegistro) {
            this.anioRegistro = anioRegistro;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado.toUpperCase();
        }

        public String getLocalidad() {
            return localidad;
        }

        public void setLocalidad(String localidad) {
            this.localidad = localidad.toUpperCase();
        }

        public String getMunicipio() {
            return municipio;
        }

        public void setMunicipio(String municipio) {
            this.municipio = municipio.toUpperCase();
        }

        public String getEmision() {
            return emision;
        }

        public void setEmision(String emision) {
            this.emision = emision.toUpperCase();
        }

        public String getSeccion() {
            return seccion;
        }

        public void setSeccion(String seccion) {
            this.seccion = seccion.toUpperCase();
        }

        public String getVigencia() {
            return vigencia;
        }

        public void setVigencia(String vigencia) {
            this.vigencia = vigencia.toUpperCase();
        }

        public String getURLFotografia() {
            return URLFotografia;
        }

        public void setURLFotografia(String URLFotografia) {
            this.URLFotografia = URLFotografia;
        }
    }

}