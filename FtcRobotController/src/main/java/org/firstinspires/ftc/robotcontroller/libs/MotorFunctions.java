package org.firstinspires.ftc.robotcontroller.libs;

import com.qualcomm.robotcore.util.Range;

/**
 * Created by Team 10794 on 10/22/16.
 * This is a library class that controls the motors
 */
public class MotorFunctions {

    //These are the control variables for the function. They are set in the constructor.
    private static int motorMin, motorMax, servoMin, servoMax;
    private static double servoDelta;

    /**
     * Constructor initializing the library class with the desired max and min ranges and servo
     * delta
     * @param motorMin the minimum value of the DC motor
     * @param motorMax the maximum value of the DC motor
     * @param servoMin the minimum value of the servo
     * @param servoMax the maximum value of the servo
     * @param servoDelta the amount by which to change the servo position
     */
    public MotorFunctions(int motorMin, int motorMax, int servoMin, int servoMax, double servoDelta) {
        this.motorMin = motorMin;
        this.motorMax = motorMax;
        this.servoMin = servoMin;
        this.servoMax = servoMax;

        this.servoDelta = servoDelta;
    }

    /**
     * Normalizes the power level from the joystick value
     * @param joystickValue The value of the joystick
     * @return float power level
     */
    public static float dcMotor(float joystickValue) {
        joystickValue = Range.clip(joystickValue, motorMin, motorMax);
        joystickValue = (float)scaleInput(joystickValue);
        return joystickValue;
    }

    /**
     * Normalizes the power level from the joystick value
     * @param joystickValue The value of the joystick
     * @param minRange The minimum value of the joystick
     * @param maxRange The maximum value of the joystick
     * @return float power level
     */
    public static float dcMotor(float joystickValue, int minRange, int maxRange) {
        joystickValue = Range.clip(joystickValue, minRange, maxRange);
        joystickValue = (float)scaleInput(joystickValue);
        return joystickValue;
    }

    /**
     * Normalizes the servo position
     * @param position The current servo position
     * @return double servo position
     */
    public static double servo(double position) {
        position += servoDelta;
        position = Range.clip(position, servoMin, servoMax);
        return position;
    }

    /**
     * Normalizes the servo position
     * @param position The current servo position
     * @param servoDelta the amount the current servo changes
     * @return double servo position
     */
    public static double servo(double position, double servoDelta) {
        position += servoDelta;
        position = Range.clip(position, servoMin, servoMax);
        return position;
    }

    /**
     * Normalizes the servo position
     * @param position The current servo position
     * @param minRange The minimum servo position
     * @param maxRange The maximum servo position
     * @return double servo position
     */
    public static double servo(double position, int minRange, int maxRange ) {
        position += servoDelta;
        position = Range.clip(position, minRange, maxRange);
        return position;
    }

    /**
     * Normalizes the servo position
     * @param position The current servo position
     * @param servoDelta The amount the current servo changes
     * @param minRange The minimum servo position
     * @param maxRange The maximum servo position
     * @return double servo position
     */
    public static double servo(double position, double servoDelta, int minRange, int maxRange) {
        position += servoDelta;
        position = Range.clip(position, minRange, maxRange);
        return position;
    }

    /*
    * This method scales the joystick input so for low joystick values, the
    * scaled value is less than linear.  This is to make it easier to drive
    * the robot more precisely at slower speeds.
    */
    private static double scaleInput(double dVal)  {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);

        // index should be positive.
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.
        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        // return scaled value.
        return dScale;
    }
}
