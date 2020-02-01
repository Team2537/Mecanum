/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.XboxController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  // For use with Xbox controller
  private XboxController xbox;

  private final int FRONT_LEFT_TALON = 4;
  private final int FRONT_RIGHT_TALON = 3;
  private final int REAR_LEFT_TALON = 2;
  private final int REAR_RIGHT_TALON = 1;

  // motor control 4 wheel drive - tank drive
  private WPI_TalonSRX frontLeft;
  private WPI_TalonSRX rearLeft;

  private WPI_TalonSRX frontRight;
  private WPI_TalonSRX rearRight;

  private MecanumDrive mDrive;



  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    xbox = new XboxController(0);

    // Motors on left side of robot grouped together
    frontLeft = new WPI_TalonSRX(FRONT_LEFT_TALON);
    rearLeft = new WPI_TalonSRX(REAR_LEFT_TALON);

    // Motors on right side of robot grouped together
    frontRight = new WPI_TalonSRX(FRONT_RIGHT_TALON);
    rearRight = new WPI_TalonSRX(REAR_RIGHT_TALON);

   mDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString line to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional comparisons to the
   * switch structure below with additional strings. If using the SendableChooser
   * make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
    case kCustomAuto:
      // Put custom auto code here
      break;
    case kDefaultAuto:
    default:
      // Put default auto code here
      break;
    }


  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

   
   if (xbox.getYButton()) { // drive left front forward
      SmartDashboard.putString("Direction", "DriveForward");
      mDrive.drivePolar(0.3, 0, 0);
    } else {
      double xSpeed = -0.3 * xbox.getX(Hand.kRight);
     double ySpeed = -0.3 * xbox.getY(Hand.kRight); 
     double zRotation = -0.3 * xbox.getY(Hand.kLeft);
      mDrive.driveCartesian(ySpeed, xSpeed, zRotation);
  } /*
    if (xbox.getAButtonPressed()%) { //drive right front forward
      mDrive.drivePolar(0, 0, 0); */
    }
  
     /* leftjoystick turns right
     rightjoystick - 
     left (-x) is backward
     right (+x) is forward
    up and down (y) turns

    }

    /*if (xbox.getYButtonPressed()) { // drive left front forward
      SmartDashboard.putString("Wheel", "left front");
      frontLeft.set(0.3);
    } else if (xbox.getBButtonPressed()) { //drive right front forward
      SmartDashboard.putString("Wheel", "right front");
     frontRight.set(0.3);
    } else if (xbox.getAButtonPressed()) { // drive left rear forward 
      SmartDashboard.putString("Wheel", "left rear");
      rearLeft.set(0.3);
    } else if (xbox.getXButtonPressed()) { // drive right rear forward
      SmartDashboard.putString("Wheel", "right rear");

      rearRight.set(0.3);     
    }
    else if (xbox.getStartButtonPressed()) {
      frontLeft.set(0);
      frontRight.set(0);
      rearLeft.set(0);
      rearRight.set(0);    
    }*/


  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
