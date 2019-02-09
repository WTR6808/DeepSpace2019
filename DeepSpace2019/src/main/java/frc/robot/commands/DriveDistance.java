/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveDistance extends Command {
  private double distance = 0.0;
  private double speed = 0.0;
  public DriveDistance(double d, double s) {
    requires(Robot.m_driveTrain);
    distance = d;
    speed = s;
    // Use requires() here to declare subsystem dependencies
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_driveTrain.stop();
    Robot.m_driveTrain.resetDistance();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_driveTrain.TeleopArcadeDrive(speed, 0.0);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return(distance - Robot.m_driveTrain.getLeftDistance()) < 0.5;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    speed = 0.0;
    distance = 0.0;
    Robot.m_driveTrain.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();
  }
}
