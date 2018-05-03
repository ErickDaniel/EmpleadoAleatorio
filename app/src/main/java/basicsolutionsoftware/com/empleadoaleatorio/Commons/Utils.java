package basicsolutionsoftware.com.empleadoaleatorio.Commons;

import android.util.Log;

import com.orhanobut.logger.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

public class Utils {

    /**
     * IMPRIMIR LOG DE DEBUG
     * @param message
     */
    public static void printLogDebug(String message, boolean prettyMode, boolean jsonMobe){
        if(Constants.ALLOWING_LOGS) {

            if(jsonMobe){
                Logger.json(message);
            }else {
                if (prettyMode) {
                    Logger.d(message);
                } else {
                    Log.d(Constants.TAG_LOG, message);
                }
            }
        }
    }
    /**
     * IMPRIMIR LOG DE INFO
     * @param message
     */
    public static void printLogInfo(String message, boolean prettyMode, boolean jsonMobe){
        if(Constants.ALLOWING_LOGS) {
            if(jsonMobe){
                Logger.json(message);
            }else {
                if(prettyMode){
                    Logger.i(message);
                }else{
                    Log.i(Constants.TAG_LOG, message);
                }
            }
        }
    }
    /**
     * IMPRIMIR LOG DE VERBOSE
     * @param message
     */
    public static void printLogVerbose(String message, boolean prettyMode, boolean jsonMobe){
        if(Constants.ALLOWING_LOGS) {
            if(jsonMobe){
                Logger.json(message);
            }else {
                if(prettyMode){
                    Logger.v(message);
                }else{
                    Log.v(Constants.TAG_LOG, message);
                }
            }
        }
    }
    /**
     * IMPRIMIR LOG DE ERROR
     * @param message
     */
    public static void printLogError(String message, boolean prettyMode, boolean jsonMobe){
        if(Constants.ALLOWING_LOGS) {
            if(jsonMobe){
                Logger.json(message);
            }else {
                if(prettyMode){
                    Logger.e(message);
                }else {
                    Log.e(Constants.TAG_LOG, message);
                }
            }
        }
    }
    /**
     * IMPRIMIR LOG DE WARNING
     * @param message
     */
    public static void printLogWarning(String message, boolean prettyMode, boolean jsonMobe){
        if(Constants.ALLOWING_LOGS) {
            if(jsonMobe){
                Logger.json(message);
            }else {
                if(prettyMode){
                    Logger.w(message);
                }else {
                    Log.w(Constants.TAG_LOG, message);
                }
            }
        }
    }

    public static boolean validateDate(String dateString){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(dateString);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

    public static int getRandomInt(int min, int max){
        final Random random = new Random();
        return min + random.nextInt((max - min) + 1);
    }

}
