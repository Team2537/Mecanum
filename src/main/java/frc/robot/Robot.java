package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import edu.wpi.first.wpilibj.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/*
 * Simplest program to drive a robot with mecanum drive using a single Logitech
 * Extreme 3D Pro joystick and 4 drive motors connected as follows:
 *     - PWM 0 - Connected to front left drive motor
 *     - PWM 1 - Connected to rear left drive motor
 *     - PWM 2 - Connected to front right drive motor
 *     - PWM 3 - Connected to rear right drive motor
 */

public class Robot extends TimedRobot {
     //Create a robot drive object using PWMs 0, 1, 2 and 3
      TalonSRX frontLeftMotor = new TalonSRX(3);	
		  TalonSRX rearLeftMotor = new TalonSRX(4);
		  TalonSRX frontRightMotor = new TalonSRX(1);	
      TalonSRX rearRightMotor = new TalonSRX(2);
      

     //Define joystick being used at USB port 1 on the Driver Station
      Joystick joystick = new Joystick(0);
      double forward;
      double right;
      double clockwise;
      


     public void teleopPeriodic() 
		  {
        //frontLeftMotor.configReverseSoftLimitEnable(true);

        forward = -joystick.getY();
        right = joystick.getX();
        clockwise = joystick.getZ();

        //front_left = forward + clockwise + right;
        //front_right = forward - clockwise - right;
        //rear_left = forward + clockwise - right;
        //rear_right = forward - clockwise + right;
        
        
        SmartDashboard.putNumber("joystick X value:", right);
        SmartDashboard.putNumber("joystick Y value:", -forward);
        SmartDashboard.putNumber("joystick Z value:", clockwise);


        frontLeftMotor.set(ControlMode.PercentOutput, -(forward + clockwise + right));
        frontRightMotor.set(ControlMode.PercentOutput, -(forward - clockwise - right));
        rearLeftMotor.set(ControlMode.PercentOutput, (forward + clockwise - right));
        rearRightMotor.set(ControlMode.PercentOutput, (forward - clockwise + right));
      }
}