package basicsolutionsoftware.com.empleadoaleatorio.Commons;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by OscarAyestaran on 28/10/17.
 */

public class FileSystem  {
    private Context context;
    private Bundle bundle;

    public FileSystem(Context context){
        this.context = context;
    }
    public interface FileSystemCallback {
        void onSuccess(Object object);
        void onError(String error);
    }

    /**
     * LEER ARCHIVO DESDE ASSETS
     * @param fileName
     * @param callback
     * @throws IOException
     */
    public void readFileFromAssets(String fileName, final FileSystemCallback callback) throws IOException {

        Handler handler = new Handler(Looper.getMainLooper()){

            @Override
            public void handleMessage(Message msg) {
                String readedFile = "";
                bundle = msg.getData();
                readedFile = bundle.getString("FILE");
                callback.onSuccess(readedFile);
            }

        };

        new Thread(new MyRunnable(fileName,context,handler)).start();

    }


    /**
     *  ELIMINAR ARCHIVO DEL SISTEMA DE ARCHIVOS
     * @param filePath
     */
    public boolean deleteFileFromFileSystem(String filePath){
        File file = new File(filePath);
        return file.delete();
    }

}

class MyRunnable implements Runnable {
    private Handler h2;
    private Context context;
    private String file;

    public MyRunnable(String file, Context context, Handler h) {
        this.h2 = h;
        this.context = context;
        this.file = file;
    }

    @Override
    public void run() {
        Message message = Message.obtain();
        String resultFile = "";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int ctr = 0;

        try {
            InputStream inputStream = null;
            inputStream = context.getAssets().open(file);
            ctr = inputStream.read();
            while (ctr != -1) {
                outputStream.write(ctr);
                ctr = inputStream.read();
            }
            inputStream.close();
            resultFile = outputStream.toString();
        }catch (IOException e){
            h2.sendEmptyMessage(99);
        }
        message = Message.obtain();
        Bundle bundle = new Bundle();
        bundle.putString("FILE",resultFile);
        message.setData(bundle);
        h2.sendMessage(message);
    }
}
