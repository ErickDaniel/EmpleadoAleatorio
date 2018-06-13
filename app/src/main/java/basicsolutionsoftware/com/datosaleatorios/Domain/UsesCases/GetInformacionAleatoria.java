package basicsolutionsoftware.com.datosaleatorios.Domain.UsesCases;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import basicsolutionsoftware.com.datosaleatorios.Commons.Utils;
import basicsolutionsoftware.com.datosaleatorios.Domain.TaskInterface.Task;
import basicsolutionsoftware.com.datosaleatorios.Commons.InfoAleatoria;
import basicsolutionsoftware.com.datosaleatorios.Domain.DomainModel;

public class GetInformacionAleatoria implements Task {

    private GetGetInformacionAleatoriaCallBack callBack;
    private Context context;
    private int info;
    private int sexo;

    public GetInformacionAleatoria(Context context, int sexo, int info, GetGetInformacionAleatoriaCallBack callBack){
        this.callBack = callBack;
        this.context = context;
        this.info = info;
        this.sexo = sexo;
    }

    @Override
    public void execute() {
        DomainModel.getDomainModelInstance(context).getInformacionAleatoriaDiccionario(sexo, info, new DomainModel.DomainModelCallBack() {
            @Override
            public void onSuccess(String response) {
                List<String> nombresList = new Gson().fromJson(response, new TypeToken<List<String>>() {}.getType());
                int max = 0;//Tamaño del diccionario
                switch (info){
                    case InfoAleatoria.NOMBRE:
                        if(sexo == InfoAleatoria.HOMBRE) {
                            max = 893;
                        } else {
                            max = 582;
                        }
                        break;
                    case InfoAleatoria.SEGUNDO_NOMBRE:
                        if(sexo == InfoAleatoria.HOMBRE) {
                            max = 893;
                        } else {
                            max = 582;
                        }
                        break;
                    case InfoAleatoria.APELLIDO_PATERNO:
                        max = 107;
                        break;
                    case InfoAleatoria.APELLIDO_MATERNO:
                        max = 107;
                        break;
                    case InfoAleatoria.CALLE:
                        max = 69;
                        break;
                    case InfoAleatoria.COLONIA:
                        max = 46;
                        break;
                }
                callBack.onSuccess(nombresList.get(Utils.getRandomInt(0, max)));
            }

            @Override
            public void onError(String msg) {
                callBack.onError(msg);
            }
        });
    }

    public interface GetGetInformacionAleatoriaCallBack {
        void onSuccess(String object);
        void onError(String message);
    }

}
