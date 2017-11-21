package com.example.ysu.message;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.os.HandlerThread;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private final String TAG="MessageTest";
    private int ButtonCount = 0;
    private Thread myThread;
    private Handler myHandler;
    private int mMessageCount;
    private HandlerThread mThread3;
    private Handler myHandler3;
    private int mMessageCount3;

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
                Message msg3 = new Message();
                myHandler3.sendMessage(msg3);
            }
        }
    }

    class MyThread extends Thread
    {
        private Looper mLooper;
        @Override
        public void run() {
            super.run();
            Looper.prepare();
            synchronized (this)
            {
                mLooper = Looper.myLooper();
                notifyAll();
            }
            Looper.loop();
        }

        public Looper getLooper()
        {
            if(!isAlive())
            {
                return null;
            }

            synchronized (this)
            {
                while(isAlive() && mLooper == null)
                {
                    try
                    {
                        wait();
                    }
                    catch(InterruptedException e)
                    {

                    }
                }
            }
            return mLooper;
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
                Message msg = new Message();
                myHandler.sendMessage(msg);
            }
        });

        myThread = new Thread(new MyRunable(), "MessageThread");
        myThread.start();

        MyThread myThread2 = new MyThread();
        myThread2.start();

        myHandler = new Handler(myThread2.getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                mMessageCount++;
                Log.d(TAG, "Get msg " + mMessageCount);
                return false;
            }
        });

        mThread3 = new HandlerThread("MessageThread3");
        mThread3.start();
        myHandler3 = new Handler(mThread3.getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                mMessageCount3++;
                Log.d(TAG, "Get msg for thread 3 " + mMessageCount3);
                return false;
            }
        });
    }
}
