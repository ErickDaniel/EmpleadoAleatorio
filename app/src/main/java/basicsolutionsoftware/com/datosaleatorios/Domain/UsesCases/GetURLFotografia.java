package basicsolutionsoftware.com.datosaleatorios.Domain.UsesCases;

import android.content.Context;

import basicsolutionsoftware.com.datosaleatorios.Domain.DomainModel;
import basicsolutionsoftware.com.datosaleatorios.Domain.TaskInterface.Task;

public class GetURLFotografia implements Task {

    private GetURLFotografiaCallBack callBack;
    private Context context;
    private int sexo;

    public GetURLFotografia(Context context, int sexo, GetURLFotografiaCallBack callBack){
        this.callBack = callBack;
        this.context = context;
        this.sexo = sexo;
    }

    @Override
    public void execute() {
        DomainModel.getDomainModelInstance(context).getURLFotografia(sexo, new DomainModel.DomainModelCallBack(){

            @Override
            public void onSuccess(String response) {
                callBack.onSuccess(response);
            }

            @Override
            public void onError(String msg) {
                callBack.onError(msg);
            }

        });
    }

    public interface GetURLFotografiaCallBack {
        void onSuccess(String url);
        void onError(String message);
    }

}
