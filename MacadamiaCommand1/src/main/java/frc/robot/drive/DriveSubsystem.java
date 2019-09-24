
package frc.robot.drive;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Ports;
import edu.wpi.first.wpilibj.Ultrasonic;

public class DriveSubsystem extends Subsystem {
    private Talon leftTalon, rightTalon;
    private Ultrasonic frontUltrasonic, rearUltrasonic;
    public DifferentialDrive drive;
    
    private Encoder leftEnc, rightEnc;

  @SuppressWarnings("unused") private Subsystem accelUpdater;

  public DriveSubsystem(){
    leftTalon = new Talon(Ports.LEFT_TALON);
    rightTalon = new Talon(Ports.RIGHT_TALON);
    drive = new DifferentialDrive(leftTalon, rightTalon);

    leftEnc = new Encoder(Ports.L_ENCODER_A, Ports.L_ENCODER_B);
    rightEnc = new Encoder(Ports.R_ENCODER_A, Ports.R_ENCODER_B, true);
    leftEnc.reset();
    rightEnc.reset();

    frontUltrasonic = new Ultrasonic(Ports.FRONT_PING, Ports.FRONT_ECHO); // ping, echo

    rearUltrasonic = new Ultrasonic(Ports.REAR_PING, Ports.REAR_ECHO); // ping, echo

    frontUltrasonic.setAutomaticMode(true);
    frontUltrasonic.setEnabled(true);
    rearUltrasonic.setAutomaticMode(true);
    rearUltrasonic.setEnabled(true);

  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveCommand());
  }
  
  public double getRearUltrasonic() {
    System.out.println("rear: " + rearUltrasonic.getRangeInches());

    return rearUltrasonic.getRangeInches();
  }

  public double getFrontUltrasonic() {
    System.out.println("front: " + frontUltrasonic.getRangeInches());

    return frontUltrasonic.getRangeInches();
  }
  

}