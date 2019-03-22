/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class limeDriveArcade extends Command {

  private boolean noTarget;

  public limeDriveArcade() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    noTarget = false;
    Robot.m_driveTrain.setVisionMode();
    Robot.m_driveTrain.Stop();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    noTarget = !Robot.m_driveTrain.visionDriveArcade(); //visionDrive returns true if target found
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return noTarget || Robot.m_driveTrain.atTargetArcade();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_driveTrain.setDriverMode();
    Robot.m_driveTrain.Stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();
  }
}
