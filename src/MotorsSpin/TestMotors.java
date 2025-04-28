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

      while(true) {
         LCD.clear();
         LCD.drawString("Moving forward...", 0, 0);
         Motor.A.setSpeed(500);
         Motor.B.setSpeed(500);
         Motor.A.forward();
         Motor.B.forward();
         long var4 = System.currentTimeMillis();

         float var6;
         while(System.currentTimeMillis() - var4 < 10000L) {
            var2.fetchSample(var3, 0);
            var6 = var3[0];
            if ((double)var6 < 0.2) {
               Motor.A.stop(true);
               Motor.B.stop();
               LCD.clear();
               LCD.drawString("Obstacle ahead!", 0, 2);
               Delay.msDelay(500L);
               break;
            }

            Delay.msDelay(50L);
         }

         Motor.A.stop(true);
         Motor.B.stop();
         Delay.msDelay(500L);
         LCD.clear();
         LCD.drawString("Moving backward...", 0, 0);
         Motor.A.backward();
         Motor.B.backward();
         var4 = System.currentTimeMillis();

         while(System.currentTimeMillis() - var4 < 10000L) {
            var2.fetchSample(var3, 0);
            var6 = var3[0];
            if ((double)var6 < 0.2) {
               Motor.A.stop(true);
               Motor.B.stop();
               LCD.clear();
               LCD.drawString("Obstacle behind!", 0, 2);
               Delay.msDelay(500L);
               break;
            }

            Delay.msDelay(50L);
         }

         Motor.A.stop(true);
         Motor.B.stop();
         Delay.msDelay(500L);
      }
   }
}
