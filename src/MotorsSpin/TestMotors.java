// Infinite forward-backward motion with object 
package MotorsSpin;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class TestMotors {
    public static void main(String[] args) {
        // Setup ultrasonic sensor
        EV3UltrasonicSensor usSensor = new EV3UltrasonicSensor(SensorPort.S1);
        SampleProvider distanceProvider = usSensor.getDistanceMode();
        float[] sample = new float[distanceProvider.sampleSize()];

        LCD.clear();
        LCD.drawString("Press button", 0, 0);
        LCD.drawString("to start...", 0, 1);
        Button.waitForAnyPress();

        while (true) { // Infinite loop
            // Move Forward
            LCD.clear();
            LCD.drawString("Moving forward...", 0, 0);
            Motor.A.setSpeed(500);
            Motor.B.setSpeed(500);
            Motor.A.forward();
            Motor.B.forward();

            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < 10000) { // Up to 10 seconds
                distanceProvider.fetchSample(sample, 0);
                float distance = sample[0];

                if (distance < 0.2) { // Object detected within 20 cm
                    Motor.A.stop(true);
                    Motor.B.stop();
                    LCD.clear();
                    LCD.drawString("Obstacle ahead!", 0, 2);
                    Delay.msDelay(500);
                    break;
                }
                Delay.msDelay(50);
            }

            Motor.A.stop(true);
            Motor.B.stop();
            Delay.msDelay(500);

            // Move Backward
            LCD.clear();
            LCD.drawString("Moving backward...", 0, 0);
            Motor.A.backward();
            Motor.B.backward();

            startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < 10000) { // Up to 10 seconds
                distanceProvider.fetchSample(sample, 0);
                float distance = sample[0];

                if (distance < 0.2) { // Object detected within 20 cm
                    Motor.A.stop(true);
                    Motor.B.stop();
                    LCD.clear();
                    LCD.drawString("Obstacle behind!", 0, 2);
                    Delay.msDelay(500);
                    break;
                }
                Delay.msDelay(50);
            }

            Motor.A.stop(true);
            Motor.B.stop();
            Delay.msDelay(500);

            // After backward motion, loop again (forward-backward infinitely)
        }
    }
}
