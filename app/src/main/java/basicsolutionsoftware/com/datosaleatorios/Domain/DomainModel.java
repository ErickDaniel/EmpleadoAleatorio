package basicsolutionsoftware.com.datosaleatorios.Domain;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import basicsolutionsoftware.com.datosaleatorios.Commons.FileSystem;
import basicsolutionsoftware.com.datosaleatorios.Commons.InfoAleatoria;
import basicsolutionsoftware.com.datosaleatorios.Commons.Utils;
import basicsolutionsoftware.com.datosaleatorios.Service.Request;

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
            case InfoAleatoria.CALLE:
                fileName = "calles.json";
                break;
            case InfoAleatoria.COLONIA:
                fileName = "colonias.json";
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

    public void getURLFotografia(int sexo, final DomainModelCallBack domainModelCallBack) {
        Request.getRequestInstance().getURLFotografia(context, sexo, new Request.WsCallBack() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                try {
                    JSONArray results = jsonObject.getJSONArray("results");
                    JSONObject arrayData = results.getJSONObject(0);
                    JSONObject picture = arrayData.getJSONObject("picture");
                    String pictureURL = picture.getString("large");
                    domainModelCallBack.onSuccess(pictureURL);
                } catch (JSONException e) {
                    e.printStackTrace();
                    domainModelCallBack.onError(e.getMessage());
                }
            }

            @Override
            public void onFailed(String mensaje) {
                domainModelCallBack.onError(mensaje);
            }
        });
    }

    public interface DomainModelCallBack{
        void onSuccess(String response);
        void onError(String msg);
    }

}
