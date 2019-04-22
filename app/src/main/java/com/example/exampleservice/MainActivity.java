package com.example.exampleservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MyService myService;
    private ServiceConnection myConnection;
    private Intent serviceintent;
    private boolean isbind=true;
    private Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findId();
        moveToMyService();
        mybindService();
        callingWork();

        Log.i("maincreate", "onCreate: ");

    }

    private void unbind() {
        unbindService(myConnection);
    }

    private void findId() {
        b=findViewById(R.id.service_button);
    }

    private void moveToMyService() {
        //moving to myservice
        serviceintent=new Intent(this,MyService.class);
    }

    public  void mybindService() {
        //receiving a binder to typecate to this binder and getting service on service connection
        myConnection = new ServiceConnection() {
            public void onServiceConnected(ComponentName className, IBinder binder) {
                Log.d("ServiceConnection", "connected");
                MyService.MyServiceBinder myServiceBinder =(MyService.MyServiceBinder)binder;
                myService=myServiceBinder.getservice();
                Log.i("tag","MY_SERVICE");
                // myService.work();
            }


            public void onServiceDisconnected(ComponentName name) {
                Log.d("ServiceConnection", "disconnected");
                myService = null;
                isbind=false;
            }
        };

        bindService(serviceintent,myConnection,Context.BIND_AUTO_CREATE);
        Log.i("tag","bindService");

    }


    private void callingWork() {
        //calling our cide work method which is bind
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myService.work();
            }
        });

    }



    @Override
    protected void onResume() {
        super.onResume();
        // myService.work();
        Log.i("tag",""+myService);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("tag", "onStart: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("tag", "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("tag", "onStop: ");
        unbind();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("tag", "onDestroy: ");

    }
}
