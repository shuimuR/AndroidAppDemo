package com.example.ysu.message;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private final String TAG="MessageTest";
    private int ButtonCount = 0;
    private Thread myThread;

    class MyRunable implements Runnable
    {
        public void run()
        {
            int count = 0;
            for(;;)
            {
                count++;
                Log.d(TAG, "MyThread " + count);
                try {
                    Thread.sleep(3000);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    class MyThread extends Thread
    {
        @Override
        public void run() {
            super.run();
            int count = 0;
            for(;;)
            {
                count++;
                Log.d(TAG, "MyThread2 " + count);
                try {
                    Thread.sleep(3000);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button)findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                ButtonCount++;
                Log.d(TAG, "Send message " + ButtonCount);
            }
        });

        myThread = new Thread(new MyRunable(), "MessageThread");
        myThread.start();

        MyThread myThread2 = new MyThread();
        myThread2.start();
    }
}
