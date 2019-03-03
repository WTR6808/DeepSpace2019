
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.classes.LimeLight;
import frc.robot.commands.ArcadeDrive;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private Spark leftFront = new Spark(RobotMap.driveLeftFrontMotor);
  private Spark leftRear = new Spark(RobotMap.driveLeftRearMotor);
  private Spark rightFront = new Spark(RobotMap.driveRightFrontMotor);
  private Spark rightRear = new Spark(RobotMap.driveRightRearMotor);
  private SpeedControllerGroup leftGroup = new SpeedControllerGroup(leftFront, leftRear);
  private SpeedControllerGroup rightGroup = new SpeedControllerGroup(rightFront, rightRear);
  private DifferentialDrive driveTrain = new DifferentialDrive(leftGroup, rightGroup);

  private Encoder leftEncoder = new Encoder(RobotMap.encoderLeftA, RobotMap.encoderLeftB, true, CounterBase.EncodingType.k4X);
  private Encoder rightEncoder = new Encoder(RobotMap.encoderRightA, RobotMap.encoderRightB, false, CounterBase.EncodingType.k4X);

  private LimeLight limeLight = new LimeLight();

  private int dir = 1;


  public DriveTrain(){
    //Program the decoders
    leftEncoder.setDistancePerPulse(RobotMap.DIST_PER_PULSE);
    rightEncoder.setDistancePerPulse(RobotMap.DIST_PER_PULSE);

    //Set camera to driver mode
    this.setDriverMode();
  }

  public void setDriverMode(){
    limeLight.setCameraMode(true);
  }

  public void setVisionMode(){
    limeLight.setCameraMode(false);
  }

  public boolean visionDrive(){
//Test 3: Comment out the following two lines and
//        uncomment the if ... else block
    //this.tankDrive(0.5,0.5);
    //return true;
    if (limeLight.calcSpeeds()){
      this.tankDrive(limeLight.getLeftSpeed(), limeLight.getRightSpeed());
      SmartDashboard.putNumber("TX", limeLight.getTX());
      SmartDashboard.putNumber("TY", limeLight.getTY());
      SmartDashboard.putNumber("Left  Speed", limeLight.getLeftSpeed());
      SmartDashboard.putNumber("Right Speed", limeLight.getRightSpeed());
      return true;
    } 
    else {
      return false;
    }
  }

  public boolean atTarget(){
    return limeLight.withinTolerance();
  }

  public void resetDistance(){
    leftEncoder.reset();
    rightEncoder.reset();
  }  

  public double getLeftDistance(){
    return leftEncoder.getDistance();
  }
  
  public double getRightDistance(){
    return rightEncoder.getDistance();
  }
 
 
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ArcadeDrive());
    driveTrain.setSafetyEnabled(true);
  }

  public void TeleopArcadeDrive(double x, double y){
    driveTrain.arcadeDrive(dir*x, y);
//    SmartDashboard.putNumber("Left Encoder", leftEncoder.getRaw());
//    SmartDashboard.putNumber("Right Encoder", rightEncoder.getRaw());
    SmartDashboard.putNumber("Right Distance", rightEncoder.getDistance());
    SmartDashboard.putNumber("Left Distance", leftEncoder.getDistance());
    
  }
  public void tankDrive(double x, double y){
    driveTrain.tankDrive(x, y);
  }

  public void Stop(){
    driveTrain.arcadeDrive(0,0);
  }
  public boolean isStopped(){
    return leftEncoder.getStopped() && rightEncoder.getStopped();
  }
  public void changeFront(){
    dir *= -1;
    SmartDashboard.putNumber("Direction", dir);
    this.TeleopArcadeDrive(Robot.m_oi.getDriverX(), Robot.m_oi.getDriverY());
  }
}
