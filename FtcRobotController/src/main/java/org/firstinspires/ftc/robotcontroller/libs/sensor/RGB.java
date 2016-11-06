package org.firstinspires.ftc.robotcontroller.libs.sensor;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannelController;

/**
 * Created by Natalie on 11/6/16.
 */
public class RGB {

    private static ColorSensor rgbSensor;
    private static DeviceInterfaceModule cdim;

    private static float hsvValues[] = {0F,0F,0F};

    static final int LED_CHANNEL = 5;

    private static int BASELINE_RED;
    private static int BASELINE_BLUE;

    public static void init(ColorSensor sensor, DeviceInterfaceModule dim) {
        rgbSensor = sensor;
        cdim = dim;

        cdim.setDigitalChannelMode(LED_CHANNEL, DigitalChannelController.Mode.OUTPUT);
        cdim.setDigitalChannelState(LED_CHANNEL, true);
    }

    public static int[] calibrate() {

        return null;
    }

    public static boolean getSensorValue(String color) {
        switch(color) {
            case "red":
                int red = rgbSensor.red();
                if (red >= BASELINE_RED) {
                    return true;
                }
                break;
            case "blue":
                int blue = rgbSensor.blue();
                if (blue >= BASELINE_BLUE) {
                    return true;
                }
                break;
            default:
                break;
        }
        return false;
    }

    public static float[] normalize() {
        Color.RGBToHSV((rgbSensor.red() * 255) / 800, (rgbSensor.green() * 255) / 800, (rgbSensor
                .blue() * 255) / 800, hsvValues);
        return hsvValues;
    }

    public static void setLED(boolean state) {
        cdim.setDigitalChannelState(LED_CHANNEL, state);
    }
}
