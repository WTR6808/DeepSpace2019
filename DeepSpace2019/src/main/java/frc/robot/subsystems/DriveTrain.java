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
import frc.robot.RobotMap;

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

  public Encoder leftEncoder = new Encoder(RobotMap.encoderLeftA, RobotMap.encoderLeftB, true, CounterBase.EncodingType.k4X);
  public Encoder rightEncoder = new Encoder(RobotMap.encoderRightA, RobotMap.encoderRightB, false, CounterBase.EncodingType.k4X);


  public DriveTrain(){
    
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
