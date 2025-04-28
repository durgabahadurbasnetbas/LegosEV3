package line;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class linefollower {

    public static void main(String[] args) {

        // Initialize the color sensor on port S2
        EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S2);
        SampleProvider redMode = colorSensor.getRedMode();  // Reflected light intensity

        float[] sample = new float[redMode.sampleSize()];

        // Set base motor speed
        int baseSpeed = 250;
        int turnSpeed = 150; // Speed when adjusting

        Motor.A.setSpeed(baseSpeed);
        Motor.B.setSpeed(baseSpeed);

        // Start motors
        Motor.A.forward();
        Motor.B.forward();

        // Threshold for black detection
        float blackThreshold = 0.25f; // You might need to calibrate this value!

        LCD.clear();
        LCD.drawString("Following Line", 0, 0);
        Delay.msDelay(1000); // Startup delay

        while (!Button.ESCAPE.isDown()) {
            // Get current light intensity
            redMode.fetchSample(sample, 0);
            float lightIntensity = sample[0];

            // Display the light intensity on screen
            LCD.clear();
            LCD.drawString("Light:", 0, 0);
            LCD.drawString((int)(lightIntensity * 100) + "%", 0, 1);

            // Line following behavior
            if (lightIntensity < blackThreshold) {
                // On black line → Go straight
                Motor.A.setSpeed(baseSpeed);
                Motor.B.setSpeed(baseSpeed);
            } else {
                // Off black line → correct back
                // Here we make the robot turn left
                Motor.A.setSpeed(turnSpeed);  // Left slower
                Motor.B.setSpeed(baseSpeed);  // Right normal
            }

            // Move motors forward
            Motor.A.forward();
            Motor.B.forward();

            Delay.msDelay(30); // Small delay for smoother operation
        }

        // Stop motors and close sensor
        Motor.A.stop();
        Motor.B.stop();
        colorSensor.close();
 }
}
