/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.drive.DriveCommand;
import frc.robot.drive.DriveSubsystem;
import edu.wpi.first.cameraserver.CameraServer;

public class Robot extends TimedRobot {
  public static DriveSubsystem driveSys;
  double leftEncoderStartValue, rightEncoderStartValue;
  private final double safeDistance = 30.0;

  // Use this function for all initialization code
  @Override
  public void robotInit() {
    
    driveSys = new DriveSubsystem();
    CameraServer.getInstance().startAutomaticCapture();
  }

  // Called periodically regardless of the game period
  @Override
  public void robotPeriodic() {
    Scheduler.getInstance().run();
  }


  /* Sandstorm Period */
  // Called at the beginning of the Sandstorm
  @Override
  public void autonomousInit() {

  }

  // Called periodically during the Sandstorm
  @Override
  public void autonomousPeriodic() {
    double distance = Robot.driveSys.getFrontUltrasonic();

    if (distance < safeDistance) {
      Robot.driveSys.drive.tankDrive(-0.33, -0.33);
    } else if (distance > safeDistance) {
      Robot.driveSys.drive.tankDrive(0.33, 0.33);
    } else {
      Robot.driveSys.drive.tankDrive(0.0, 0.0);
    }

  }


  /* Teleop Period */
  // Called at the beginning of the Teleop period
  @Override
  public void teleopInit() {
  }
  
  // Called periodically during the Teleop period
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().removeAll();
    Scheduler.getInstance().add(new DriveCommand());
    Scheduler.getInstance().run();


  }



  @Override
  public void testInit() {
    
  }
  @Override
  public void testPeriodic() {

  }
}