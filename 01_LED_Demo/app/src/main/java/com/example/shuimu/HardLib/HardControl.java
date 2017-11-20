package com.example.shuimu.HardLib;

public class HardControl
{
    public static native int LEDControl(int which, int status);
    public static native int LEDOpen();
    public static native void LEDClose();

    static
    {
        try
        {
            System.loadLibrary("HardControl");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}