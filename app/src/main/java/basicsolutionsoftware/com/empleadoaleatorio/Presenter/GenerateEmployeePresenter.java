package basicsolutionsoftware.com.empleadoaleatorio.Presenter;

import android.content.Context;
import android.util.Log;

import com.google.firebase.crash.FirebaseCrash;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.Calendar;
import java.util.Random;

import basicsolutionsoftware.com.empleadoaleatorio.Commons.Constants;
import basicsolutionsoftware.com.empleadoaleatorio.Commons.InfoAleatoria;
import basicsolutionsoftware.com.empleadoaleatorio.Commons.Utils;
import basicsolutionsoftware.com.empleadoaleatorio.Domain.Manager.TaskManager;
import basicsolutionsoftware.com.empleadoaleatorio.Domain.Objects.Entidad;
import basicsolutionsoftware.com.empleadoaleatorio.Domain.UsesCases.GetURLFotografia;
import basicsolutionsoftware.com.empleadoaleatorio.Domain.UsesCases.GetEntidadAleatoria;
import basicsolutionsoftware.com.empleadoaleatorio.Domain.UsesCases.GetInformacionAleatoria;
import basicsolutionsoftware.com.empleadoaleatorio.Presenter.Interface.GenerateEmployeePresenterInterface;
import basicsolutionsoftware.com.empleadoaleatorio.R;
import basicsolutionsoftware.com.empleadoaleatorio.View.Interface.GenerateEmployeeActivityInterface;

public class GenerateEmployeePresenter implements GenerateEmployeePresenterInterface {

    private TaskManager taskManager;
    private GenerateEmployeeActivityInterface callBack;
    private Context context;

    public GenerateEmployeePresenter(Context context, GenerateEmployeeActivityInterface callBack){
        this.context = context;
        this.callBack = callBack;
        this.taskManager = new TaskManager();
        FirebaseCrash.log(this.getClass().toString().concat(context.getString(R.string.space)).concat(context.getString(R.string.created)));
    }

    private final String FORMAT_VALUE = "%04d";
    @Override
    public String getNssAleatory() {
        subDelegacion = DICCIONARIO_SUB_DELEGACION[Utils.getRandomInt(0,7)];
        anioInscripcion = DICCIONARIO_ANIO_INSCRIPCION[Utils.getRandomInt(0, 29)];
        anioNacimiento = DICCIONARIO_ANIO_NACIMIENTO[Utils.getRandomInt(0, 29)];
        int randomNumeroConsecutivoInscripcion = Utils.getRandomInt(0, 1000);
        String consecutivoImss = String.format(FORMAT_VALUE, randomNumeroConsecutivoInscripcion);
        consecutivoImss = fixConsecutivoImss(consecutivoImss);
        String integerString = Integer.toString(subDelegacion).concat(Integer.toString(anioInscripcion)).concat(Integer.toString(anioNacimiento)).concat(consecutivoImss);
        int[] intArray = new int[integerString.length()];
        char[] integerArray = integerString.toCharArray();
        for (int i = 0; i < integerArray.length; i++) {
            intArray[i] = Character.getNumericValue(integerArray[i]);
        }
        int verificacionLuhn = verificacionluhn(intArray);
        String verificacionLuhnString = Integer.toString(verificacionLuhn);
        int decena = 0;
        if(verificacionLuhnString.length() > 1){
            decena = Character.getNumericValue(verificacionLuhnString.charAt(0));
            decena++;
            decena *= 10;
        }
        if((verificacionLuhn-decena == 10) || (verificacionLuhn-decena == -10)) {
            return integerString.concat(Integer.toString(-1));
        } else {
            return integerString.concat(Integer.toString(verificacionLuhn-decena));
        }
    }

    @Override
    public void getNombreAleatorio(int sexo) {
        taskManager.execute(new GetInformacionAleatoria(context,sexo, InfoAleatoria.NOMBRE, new GetInformacionAleatoria.GetGetInformacionAleatoriaCallBack() {
            @Override
            public void onSuccess(String object) {
                callBack.setNombreAleatorio(object.toString());
            }

            @Override
            public void onError(String message) {

            }
        }));
    }

    @Override
    public void getSegundoNombreAleatorio(int sexo) {
        taskManager.execute(new GetInformacionAleatoria(context,sexo, InfoAleatoria.SEGUNDO_NOMBRE, new GetInformacionAleatoria.GetGetInformacionAleatoriaCallBack() {
            @Override
            public void onSuccess(String object) {
                callBack.setSegundoNombreAleatorio(object.toString());
            }

            @Override
            public void onError(String message) {

            }
        }));
    }

    @Override
    public void getApellidoPaternoAleatorio() {
        taskManager.execute(new GetInformacionAleatoria(context,1, InfoAleatoria.APELLIDO_PATERNO, new GetInformacionAleatoria.GetGetInformacionAleatoriaCallBack() {
            @Override
            public void onSuccess(String object) {
                callBack.setApellidoPaternoAleatorio(object.toString());
            }

            @Override
            public void onError(String message) {

            }
        }));
    }

    @Override
    public void getApellidoMaternoAleatorio() {
        taskManager.execute(new GetInformacionAleatoria(context,1, InfoAleatoria.APELLIDO_MATERNO, new GetInformacionAleatoria.GetGetInformacionAleatoriaCallBack() {
            @Override
            public void onSuccess(String object) {
                callBack.setApellidoMaternoAleatorio(object.toString());
            }

            @Override
            public void onError(String message) {

            }
        }));
    }

    @Override
    public void getEntidadAleatorio() {
        taskManager.execute(new GetEntidadAleatoria(context, new GetEntidadAleatoria.GetEntidadAleatoriaCallBack() {
            @Override
            public void onSuccess(Entidad entidad) {
                callBack.setEntidadAleatoria(entidad);
            }

            @Override
            public void onError(String message) {

            }
        }));
    }

    @Override
    public void getEdadAleatorio() {
        int dia;
        int mes;
        int anio;
        int edad;
        dia = Utils.getRandomInt(1, 31);
        mes = Utils.getRandomInt(1, 12);
        edad = Utils.getRandomInt(18, 80);
        anio = Calendar.getInstance().get(Calendar.YEAR) - edad;
        String fechaCumpleanios = new String(String.format("%02d", dia)).concat("/").concat(String.format("%02d", mes)).concat("/").concat(Integer.toString(anio));
        if(Utils.validateDate(fechaCumpleanios)) {
            int days = Days.daysBetween(new DateTime(), new DateTime(Calendar.getInstance().get(Calendar.YEAR), mes, dia, 0, 0)).getDays();
            boolean cumplidos = false;
            if(days <=0){
                cumplidos = true;
            }
            callBack.setEdad(fechaCumpleanios, edad, cumplidos);
        } else {
            getEdadAleatorio();
        }
    }

    @Override
    public int getSexoAleatorio() {
        return Utils.getRandomInt(5,6);
    }

    @Override
    public void generateCURPAleatorio(String nombre, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento, int sexo, String codigoEntidad) {
        try {
            String primeraLetraApellidoPaterno = apellidoPaterno.substring(0, 1).toUpperCase();
            String primeraVocalApellidoPaterno = getPrimeraVocal(apellidoPaterno).toUpperCase();
            String primeraLetraSegundoApellidoMaterno = apellidoMaterno.substring(0, 1).toUpperCase();
            String primeraLetraNombre = nombre.substring(0, 1).toUpperCase();
            String fechaNacimientoClean = getFechaNacimientoClean(fechaNacimiento).toUpperCase();
            String sexoClean = sexo== 5 ? "H" : "M";
            String consonanteInternaA = getConsonanteInterna(apellidoPaterno).toUpperCase();
            String consonanteInternaB = getConsonanteInterna(apellidoMaterno).toUpperCase();
            String consonanteInternaC = getConsonanteInterna(nombre).toUpperCase();
            String curp = primeraLetraApellidoPaterno;
            curp = curp.concat(primeraVocalApellidoPaterno);
            curp = curp.concat(primeraLetraSegundoApellidoMaterno);
            curp = curp.concat(primeraLetraNombre);
            curp = curp.concat(fechaNacimientoClean);
            curp = curp.concat(sexoClean);
            curp = curp.concat(codigoEntidad);
            curp = curp.concat(consonanteInternaA);
            curp = curp.concat(consonanteInternaB);
            curp = curp.concat(consonanteInternaC);

            int codigoVerificador = calcularCodigoVerificador(curp);
            curp = curp.concat(new String(String.format("%02d", codigoVerificador)));
            callBack.setCurp(curp);
        } catch (StringIndexOutOfBoundsException e){
            FirebaseCrash.logcat(Log.ERROR, Constants.TAG_LOG, "Error Caught");
            FirebaseCrash.report(e);
        } catch (Exception e){
            FirebaseCrash.logcat(Log.ERROR, Constants.TAG_LOG, "Error Caught");
            FirebaseCrash.report(e);
        }
    }

    /**
     * Calcula el valor que debe tener el último dígito de la curp
     * @param curp17 CURP with 17 digits
     * @return last digit of CURP
     */
    private static int calcularCodigoVerificador(String curp17){
        String dictionary  = "0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        double lngSuma = 0.0;
        double lngDigit;
        for(int i = 0; i < 16; i++) {
            lngSuma = lngSuma + dictionary.indexOf(curp17.charAt(i)) * (18 - i);
        }
        lngDigit = 10 - lngSuma % 10;
        if (lngDigit == 10){
            return 0;
        } else {
            return (int) lngDigit;
        }
    }

    private String vocalRegex = "(?i)[^aeiou].*";
    private String getConsonanteInterna(String apellidoPaterno){
        String[] data = apellidoPaterno.split("");
        String consonante = "";
        boolean match = false;
        for (int i = 2; i < data.length-1; i++) {
            if(new String(data[i]).matches(vocalRegex)){
                match = true;
                consonante = data[i];
                break;
            }
        }

        if(!match){
            for (int i = 2; i < data.length; i++) {
                if(new String(data[i]).matches(vocalRegex)){
                    match = true;
                    consonante = data[i];
                    break;
                }
            }
        }
        if(!match){
            consonante = data[1];
        }
        return consonante;
    }

    private String getPrimeraVocal(String nombre){
        String[] data = nombre.split("");
        String vocal = "";
        boolean match = false;
        for (int i = 2; i < data.length; i++) {
            if (!new String(data[i]).matches(vocalRegex)){
                match = true;
                vocal = data[i];
                break;
            }
        }
        if(!match){
            vocal = data[1];
        }
        return vocal;
    }

    private String getFechaNacimientoClean(String fechaNacimiento){
        String fechaNacimientoArray[] = fechaNacimiento.split("/");
        fechaNacimiento = fechaNacimientoArray[2].substring(fechaNacimientoArray[2].length()-2, fechaNacimientoArray[2].length());
        fechaNacimiento = fechaNacimiento.concat(fechaNacimientoArray[1]);
        fechaNacimiento = fechaNacimiento.concat(fechaNacimientoArray[0]);
        return fechaNacimiento;
    }

    private String fixConsecutivoImss(String consecutivoImss){
        char[] consecutivoArray = consecutivoImss.toCharArray();
        final Random random = new Random();
        for (int i = 0; i < consecutivoArray.length; i++) {
            if(consecutivoArray[i] == '0'){
                consecutivoArray[i] = Character.forDigit(Utils.getRandomInt(1, 9), 10);
            }
        }
        return String.valueOf(consecutivoArray);
    }

    private int subDelegacion;
    private int anioInscripcion;
    private int anioNacimiento;

    public static int verificacionluhn(int[] digits){
        int sum = 0;
        int length = digits.length;
        for(int i = 0; i < length; i++) {
            int digit = digits[i];
            if(i % 2 == 1) {
                digit = digit * 2;
                String digitString = Integer.toString(digit);
                if(digitString.length() > 1){
                    digit = Character.getNumericValue(digitString.charAt(0))+Character.getNumericValue(digitString.charAt(1));
                }
            }
            sum = sum + digit;
        }
        return sum;
    }

    @Override
    public String getAnioRegistro(String fechaNacimiento) {
        if(fechaNacimiento != null && !fechaNacimiento.isEmpty()) {
            String arrayFechaNacimiento[] = fechaNacimiento.split("/");
            anioInscripcion = Integer.parseInt(arrayFechaNacimiento[2]) + 18;
            return Integer.toString(anioInscripcion).concat(" 01");
        }
        return "";
    }

    @Override
    public String getLocalidadAleatoria() {
        int localidadRandom = Utils.getRandomInt(0, 100);
        return String.format("%04d", localidadRandom);
    }

    @Override
    public String getMunicipioAleatorio() {
        return String.format("%03d", Utils.getRandomInt(0, 99));
    }

    @Override
    public String getSeccionAleatoria() {
        int localidadRandom = Utils.getRandomInt(0, 800);
        return String.format("%04d", localidadRandom);
    }

    @Override
    public String getEmision(String anioRegistro) {
        return Integer.toString(Utils.getRandomInt(2000, new DateTime().getYear()));
    }

    @Override
    public String getURLFotografia(int sexo) {
        taskManager.execute(new GetURLFotografia(context, sexo, new GetURLFotografia.GetURLFotografiaCallBack(){

            @Override
            public void onSuccess(String url) {
                callBack.setURLImage(url);
            }

            @Override
            public void onError(String message) {
                callBack.showError(message);
            }
        }));
        return null;
    }

    @Override
    public void getCalle() {
        taskManager.execute(new GetInformacionAleatoria(context, 0, InfoAleatoria.CALLE, new GetInformacionAleatoria.GetGetInformacionAleatoriaCallBack() {
            @Override
            public void onSuccess(String object) {
                callBack.setCalle(object);
            }

            @Override
            public void onError(String message) {

            }
        }));
    }

    @Override
    public void getColonia() {
        taskManager.execute(new GetInformacionAleatoria(context, 0, InfoAleatoria.COLONIA, new GetInformacionAleatoria.GetGetInformacionAleatoriaCallBack() {
            @Override
            public void onSuccess(String object) {
                callBack.setColonia(object);
            }

            @Override
            public void onError(String message) {

            }
        }));
    }

    @Override
    public String getDomicilio(String colonia, String calle, String entidadNacimiento) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(calle);
        stringBuilder.append(context.getString(R.string.space));
        stringBuilder.append(context.getString(R.string.numero_simbolo));
        stringBuilder.append(Integer.toString(Utils.getRandomInt(0, 500)));
        stringBuilder.append(context.getString(R.string.space));
        stringBuilder.append(colonia);
        stringBuilder.append(context.getString(R.string.space));
        stringBuilder.append(entidadNacimiento);
        stringBuilder.append(context.getString(R.string.coma));
        stringBuilder.append(context.getString(R.string.space));
        stringBuilder.append(context.getString(R.string.mexico));
        return stringBuilder.toString();
    }

    private final int[] DICCIONARIO_SUB_DELEGACION = {90, 91, 92, 93, 94, 95, 96, 97};
    private final int[] DICCIONARIO_ANIO_INSCRIPCION = {70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99};
    private final int[] DICCIONARIO_ANIO_NACIMIENTO = {70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,91,91,92,93,94,95,96,97,98,99};

}
