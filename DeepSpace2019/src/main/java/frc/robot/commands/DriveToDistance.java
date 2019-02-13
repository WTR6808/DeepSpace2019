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

public class DriveToDistance extends Command {
  private static DriveToDistance instance = null;
  private static double speed;
  private static double distance;

  public DriveToDistance() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_driveTrain);
  }

  public static DriveToDistance getInstance(double d, double s){
    if (instance == null){
      instance = new DriveToDistance();
    }
    DriveToDistance.speed = s;
    DriveToDistance.distance = d;
    SmartDashboard.putNumber("getInstance Speed", DriveToDistance.speed);
    SmartDashboard.putNumber("getInstance Distance", DriveToDistance.distance);
    return instance;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_driveTrain.Stop();
    Robot.m_driveTrain.resetDistance();
    SmartDashboard.putNumber("Initialize Speed", DriveToDistance.speed);
    SmartDashboard.putNumber("Initialize Distance", DriveToDistance.distance);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putNumber("Execute Speed", DriveToDistance.speed);
    Robot.m_driveTrain.TeleopArcadeDrive(DriveToDistance.speed,0.0); 
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    SmartDashboard.putNumber("isFinished Distance", DriveToDistance.distance);
    return(DriveToDistance.distance - Robot.m_driveTrain.getLeftDistance()) < 0.5;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    DriveToDistance.speed = 0.0;
    DriveToDistance.distance = 0.0;
    Robot.m_driveTrain.Stop();

    //Put these here so that we can see transitions in values
    SmartDashboard.putNumber("getInstance Speed", DriveToDistance.speed);
    SmartDashboard.putNumber("getInstance Distance", DriveToDistance.distance);
    SmartDashboard.putNumber("Initialize Speed", DriveToDistance.speed);
    SmartDashboard.putNumber("Initialize Distance", DriveToDistance.distance);
    SmartDashboard.putNumber("Execute Speed", DriveToDistance.speed);
    SmartDashboard.putNumber("isFinished Distance", DriveToDistance.distance);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();
  }
}
