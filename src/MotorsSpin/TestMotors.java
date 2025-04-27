// Source code fixed for proper motor control
package MotorsSpin;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.utility.Delay;

public class TestMotors {
    public static void main(String[] args) {
        LCD.clear();
        LCD.drawString("Setting speed...", 0, 0);

        // Set motor speed first
        Motor.A.setSpeed(500);
        Motor.B.setSpeed(500);

        LCD.drawString("Motors spinning...", 0, 1);
        Motor.A.forward();
        Motor.B.forward();

        Delay.msDelay(10000);  // Spinning for 10 seconds

        Motor.A.stop(true);  // Stop motors
        Motor.B.stop();

        LCD.drawString("Motors stopped.", 0, 2);
        LCD.drawString("Press button.", 0, 3);

        Button.waitForAnyPress();
    }
}
