package basicsolutionsoftware.com.empleadoaleatorio.Domain.UsesCases;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import basicsolutionsoftware.com.empleadoaleatorio.Commons.InfoAleatoria;
import basicsolutionsoftware.com.empleadoaleatorio.Commons.Utils;
import basicsolutionsoftware.com.empleadoaleatorio.Domain.DomainModel;
import basicsolutionsoftware.com.empleadoaleatorio.Domain.Objects.Entidad;
import basicsolutionsoftware.com.empleadoaleatorio.Domain.TaskInterface.Task;

public class GetEntidadAleatoria implements Task {

    private GetEntidadAleatoriaCallBack callBack;
    private Context context;
    private int sexo;

    public GetEntidadAleatoria(Context context, GetEntidadAleatoriaCallBack callBack){
        this.callBack = callBack;
        this.context = context;
    }

    @Override
    public void execute() {
        DomainModel.getDomainModelInstance(context).getInformacionAleatoriaDiccionario(sexo, InfoAleatoria.ENTIDAD, new DomainModel.DomainModelCallBack() {
            @Override
            public void onSuccess(String response) {
                List<Entidad> entidades = new Gson().fromJson(response, new TypeToken<List<Entidad>>() {}.getType());
                callBack.onSuccess(entidades.get(Utils.getRandomInt(0, 31)));
            }

            @Override
            public void onError(String msg) {
                callBack.onError(msg);
            }

        });
    }

    public interface GetEntidadAleatoriaCallBack {
        void onSuccess(Entidad entidad);
        void onError(String message);
    }

}
