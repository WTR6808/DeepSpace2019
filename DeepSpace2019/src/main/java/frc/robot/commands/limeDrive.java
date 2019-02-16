/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class limeDrive extends Command {
  private boolean noTarget;

  public limeDrive() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_limeLight);
    requires(Robot.m_driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    noTarget = false;
    Robot.m_limeLight.setCameraMode(false);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.m_limeLight.calcSpeeds()){
      Robot.m_driveTrain.tankDrive(Robot.m_limeLight.getLeftSpeed(), Robot.m_limeLight.getRightSpeed());
    }
    //Robot.m_driveTrain.tankDrive(.5, .5);
    //Timer.delay(.5);
    //Robot.m_driveTrain.tankDrive(0,0);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return noTarget;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_limeLight.setCameraMode(false);
    Robot.m_driveTrain.Stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();
  }
}
