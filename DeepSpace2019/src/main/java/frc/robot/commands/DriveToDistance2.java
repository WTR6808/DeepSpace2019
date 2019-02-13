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

public class DriveToDistance2 extends Command {
  private double speed;
  private double distance;

  public DriveToDistance2(double d, double s) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_driveTrain);
    distance = d;
    speed = s;
    SmartDashboard.putNumber("Constructor Speed", speed);
    SmartDashboard.putNumber("Constructor Distance", distance);
   }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_driveTrain.Stop();
    Robot.m_driveTrain.resetDistance();
    SmartDashboard.putNumber("Initialize Speed", speed);
    SmartDashboard.putNumber("Initialize Distance", distance);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putNumber("Execute Speed", speed);
    Robot.m_driveTrain.TeleopArcadeDrive(speed,0.0); 
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    SmartDashboard.putNumber("isFinished Distance", distance);
    return(distance - Robot.m_driveTrain.getLeftDistance()) < 0.5;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    speed = 0.0;
    distance = 0.0;
    Robot.m_driveTrain.Stop();

    //Put these here so that we can see transitions in values
    SmartDashboard.putNumber("Constructor Speed", speed);
    SmartDashboard.putNumber("Constructor Distance", distance);
    SmartDashboard.putNumber("Initialize Speed", speed);
    SmartDashboard.putNumber("Initialize Distance", distance);
    SmartDashboard.putNumber("Execute Speed", speed);
    SmartDashboard.putNumber("isFinished Distance", distance);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();
  }
}
