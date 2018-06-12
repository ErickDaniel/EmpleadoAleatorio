package basicsolutionsoftware.com.empleadoaleatorio.Service;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.Volley;

/**
 * Created by ED on 15/05/17.
 */
public class VolleyHelper {

    private static VolleyHelper mVolleyS = null;

    private RequestQueue mRequestQueue;

    private VolleyHelper(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }
    public static VolleyHelper getInstance(Context context) {
        if (mVolleyS == null) {
            mVolleyS = new VolleyHelper(context);
        }
        return mVolleyS;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }


}