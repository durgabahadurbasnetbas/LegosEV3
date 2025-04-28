// Source code is decompiled from a .class file using FernFlower decompiler.
package MotorsSpin;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class TestMotors {
   public TestMotors() {
   }

   public static void main(String[] var0) {
      EV3UltrasonicSensor var1 = new EV3UltrasonicSensor(SensorPort.S1);
      SampleProvider var2 = var1.getDistanceMode();
      float[] var3 = new float[var2.sampleSize()];
      LCD.clear();
      LCD.drawString("Press button", 0, 0);
      LCD.drawString("to start...", 0, 1);
      Button.waitForAnyPress();

        // Set motor speed first
        Motor.A.setSpeed(500);
        Motor.B.setSpeed(500);

        LCD.drawString("Motors spinning...", 0, 1);
        Motor.A.forward();
        Motor.B.forward();

        Delay.msDelay(10000);  // Spin for 10 seconds

        Motor.A.stop(true);  // Stop motors
        Motor.B.stop();

            Delay.msDelay(50L);
         }

         Motor.A.stop(true);
         Motor.B.stop();
         Delay.msDelay(500L);
      }
   }
}
