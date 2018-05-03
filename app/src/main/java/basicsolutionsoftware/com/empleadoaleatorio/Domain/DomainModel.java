package basicsolutionsoftware.com.empleadoaleatorio.Domain;

import android.content.Context;
import java.io.IOException;

import basicsolutionsoftware.com.empleadoaleatorio.Commons.FileSystem;
import basicsolutionsoftware.com.empleadoaleatorio.Commons.InfoAleatoria;
import basicsolutionsoftware.com.empleadoaleatorio.Commons.Utils;

public class DomainModel {

    private Context context;
    private static DomainModel domainModelInstance;

    public DomainModel(Context context){
        this.context = context;
    }

    /**
     * Factory de Request
     * @return requestInstance
     */
    public static DomainModel getDomainModelInstance(Context context) {
        if (domainModelInstance == null) {
            domainModelInstance = new DomainModel(context);
        }
        return domainModelInstance;
    }

    public void getInformacionAleatoriaDiccionario(int sexo, int info, final DomainModelCallBack callback) {
        String fileName = "";
        switch (info){
            case InfoAleatoria.NOMBRE:
                if(sexo == InfoAleatoria.HOMBRE) {
                    fileName = "nombres_masculinos.json";
                } else {
                    fileName = "nombres_femeninos.json";
                }
                break;
            case InfoAleatoria.SEGUNDO_NOMBRE:
                if(sexo == InfoAleatoria.HOMBRE) {
                    fileName = "nombres_masculinos.json";
                } else {
                    fileName = "nombres_femeninos.json";
                }
                break;
            case InfoAleatoria.APELLIDO_PATERNO:
                fileName = "apellidos.json";
                break;
            case InfoAleatoria.APELLIDO_MATERNO:
                fileName = "apellidos.json";
                break;
            case InfoAleatoria.ENTIDAD:
                fileName = "entidades.json";
                break;
        }
        try{
            readTextFile(fileName, new DomainModelCallBack() {
                @Override
                public void onSuccess(String response) {
                    callback.onSuccess(response);
                }

                @Override
                public void onError(String msg) {
                    callback.onError(msg);
                }
            });
        } catch (Exception e){
            Utils.printLogError(e.getMessage(), true, false);
            callback.onError(e.getMessage());
        }
    }

    /**
     * LEER ARCHIVO PLANO
     * @param fileName
     * @param callBack
     */
    public void readTextFile(String fileName, final DomainModelCallBack callBack){
        FileSystem fileSystem = new FileSystem(context);
        try {
            fileSystem.readFileFromAssets(fileName, new FileSystem.FileSystemCallback() {
                @Override
                public void onSuccess(Object object) {
                    if(object != null) {
                        String fileString = (String) object;
                        callBack.onSuccess(fileString);
                    } else {
                        callBack.onError("JSON_NULL");
                    }
                }

                @Override
                public void onError(String error) {
                    callBack.onError(error);
                }
            });
        } catch (IOException e) {
            Utils.printLogError(e.getMessage(), true, false);
        }
    }

    public interface DomainModelCallBack{
        void onSuccess(String response);
        void onError(String msg);
    }

}
