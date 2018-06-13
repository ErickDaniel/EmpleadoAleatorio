package basicsolutionsoftware.com.datosaleatorios.Service;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import basicsolutionsoftware.com.datosaleatorios.Commons.Constants;
import basicsolutionsoftware.com.datosaleatorios.Commons.Utils;
import basicsolutionsoftware.com.datosaleatorios.R;

/**
 * Created by OscarAyestaran on 11/05/17.
 */

public class Request {

    private VolleyHelper volley;
    protected RequestQueue requestQueue;
    protected RequestQueue fRequestQueue;
    private static Request requestInstance;

    /**
     * Factory de Request
     * @return requestInstance
     */
    public static Request getRequestInstance(){
        if(requestInstance == null){
            requestInstance = new Request();
        }
        return  requestInstance;
    }

    /**
     * INTERFACE DE RESPUESTA DE SERVICIO
     */
    public interface WsCallBack {
        void onSuccess(JSONObject jsonObject);
        void onFailed(String mensaje);
    }

    public void getURLFotografia(Context context, int sexo, WsCallBack callBack){
        String url = "https://randomuser.me/api/?gender=";
        if(sexo == 5){
            url = url.concat("male");
        } else {
            url = url.concat("female");
        }
        doRequest(context, new JSONObject(), url, callBack);
    }

    private void doRequest(final Context context, JSONObject parameters, String url, final WsCallBack serviceCallStatus){
        Utils.printLogInfo("URL: " + url + "\n PARAMS: " + parameters.toString(),true,false);

        JsonObjectRequest request = new JsonObjectRequest(com.android.volley.Request.Method.GET, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Utils.printLogInfo(jsonObject.toString(), true, true);
                serviceCallStatus.onSuccess(jsonObject);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.printLogError(error.toString(), true, false);

                if(error instanceof TimeoutError){
                    serviceCallStatus.onFailed(context.getString(R.string.numero_de_intentos_fallidos_verifica_conexion_internet));
                    Utils.printLogError("LIMITE DE INTENTOS EXCEDIDO: "+error.toString(), true, false);
                } else if(error instanceof NoConnectionError){
                    serviceCallStatus.onFailed(context.getString(R.string.verifica_conexion_internet));
                    Utils.printLogError("SIN CONEXIÒN A INTERNET: "+error.toString(), true, false);
                } else if(error instanceof ServerError){
                    serviceCallStatus.onFailed(context.getString(R.string.error_de_servidor_reintento));
                    Utils.printLogError("ERROR DEL SERVIDOR: "+error.toString(), true, false);
                } else {
                    serviceCallStatus.onFailed(context.getString(R.string.error_de_sistema));
                    Utils.printLogError("ERROR DESCONOCIDO: "+error.toString(), true, false);
                }
            }
        });
        volley = VolleyHelper.getInstance(context);
        fRequestQueue = volley.getRequestQueue();
        if (fRequestQueue == null) {
            fRequestQueue = volley.getRequestQueue();
        }
        request.setRetryPolicy(new DefaultRetryPolicy(
                Constants.TIMEOUT, Constants.RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        );
        fRequestQueue.add(request);

    }

}