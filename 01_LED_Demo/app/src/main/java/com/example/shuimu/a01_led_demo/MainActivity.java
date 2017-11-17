package com.example.shuimu.a01_led_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button = null;
    private boolean AllLEDOn = false;
    private CheckBox checkLED1 = null;
    private CheckBox checkLED2 = null;
    private CheckBox checkLED3 = null;
    private CheckBox checkLED4 = null;

    class MyButtonListener implements View.OnClickListener
    {
        public void onClick(View view)
        {
            AllLEDOn = !AllLEDOn;
            if(AllLEDOn)
            {
                button.setText("ALL OFF");
                checkLED1.setChecked(true);
                checkLED2.setChecked(true);
                checkLED3.setChecked(true);
                checkLED4.setChecked(true);
            }
            else
            {
                button.setText("ALL ON");
                checkLED1.setChecked(false);
                checkLED2.setChecked(false);
                checkLED3.setChecked(false);
                checkLED4.setChecked(false);
            }
        }
    }

    public void onCheckboxClicked(View view)
    {
        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId())
        {
            case R.id.LED1:
                if(checked)
                    Toast.makeText(getApplicationContext(), "LED1 on",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "LED1 off",Toast.LENGTH_SHORT).show();;
                break;
            case R.id.LED2:
                if(checked)
                    Toast.makeText(getApplicationContext(), "LED2 on",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "LED2 off",Toast.LENGTH_SHORT).show();;
                break;
            case R.id.LED3:
                if(checked)
                    Toast.makeText(getApplicationContext(), "LED3 on",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "LED3 off",Toast.LENGTH_SHORT).show();;
                break;
            case R.id.LED4:
                if(checked)
                    Toast.makeText(getApplicationContext(), "LED4 on",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "LED4 off",Toast.LENGTH_SHORT).show();;
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.BUTTON);
        checkLED1 = (CheckBox) findViewById(R.id.LED1);
        checkLED2 = (CheckBox) findViewById(R.id.LED2);
        checkLED3 = (CheckBox) findViewById(R.id.LED3);
        checkLED4 = (CheckBox) findViewById(R.id.LED4);

        button.setOnClickListener(new MyButtonListener());
        /*
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AllLEDOn = !AllLEDOn;
                if(AllLEDOn)
                    button.setText("ALL OFF");
                else
                    button.setText("ALL ON");

            }
        });
        */
    }
}
