package com.example.exampleservice;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

    private IBinder mbinder=new MyServiceBinder();

    class MyServiceBinder extends Binder{
        public MyService getservice(){
            //for getting context of MyService
            return MyService.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        //binding data from user to service
        Log.i("tag", "onBind");
        return mbinder;

    }

    @Override
    public void onCreate() {
        //create service
        super.onCreate();
        Log.i("tag","in create");
    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId)
//    {
//        //starting service
//        Log.i("tag","onstart");
//        return super.onStartCommand(intent, flags, startId);
//    }

    @Override
    public void onDestroy() {
        //destroy service
        Log.i("tag","stop");

        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("unbind", "onUnbind: ");
        return super.onUnbind(intent);

    }

    public void work(){

        //our code to bind with service
        Log.i("tag", "work: ");
        Toast.makeText(this, "working in bind", Toast.LENGTH_SHORT).show();
    }
}
