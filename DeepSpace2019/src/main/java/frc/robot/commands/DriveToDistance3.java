/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class DriveToDistance3 extends Command {
  private static DriveToDistance3 instance = null;
  private double speed;
  private double distance;

  public DriveToDistance3() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_driveTrain);
  }

  public static DriveToDistance3 getInstance(double d, double s){
    if (instance == null){
      instance = new DriveToDistance3();
    }
    instance.speed = s;
    instance.distance = d;
    SmartDashboard.putNumber("getInstance Speed", instance.speed);
    SmartDashboard.putNumber("getInstance Distance", instance.distance);
    return instance;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_driveTrain.Stop();
    Robot.m_driveTrain.resetDistance();
    SmartDashboard.putNumber("Initialize Speed", instance.speed);
    SmartDashboard.putNumber("Initialize Distance", instance.distance);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putNumber("Execute Speed", instance.speed);
    Robot.m_driveTrain.TeleopArcadeDrive(instance.speed,0.0); 
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    SmartDashboard.putNumber("isFinished Distance", instance.distance);
    return(instance.distance - Robot.m_driveTrain.getLeftDistance()) < 0.5;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    instance.speed = 0.0;
    instance.distance = 0.0;
    Robot.m_driveTrain.Stop();

    //Put these here so that we can see transitions in values
    SmartDashboard.putNumber("getInstance Speed", instance.speed);
    SmartDashboard.putNumber("getInstance Distance", instance.distance);
    SmartDashboard.putNumber("Initialize Speed", instance.speed);
    SmartDashboard.putNumber("Initialize Distance", instance.distance);
    SmartDashboard.putNumber("Execute Speed", instance.speed);
    SmartDashboard.putNumber("isFinished Distance", instance.distance);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();
  }
}
