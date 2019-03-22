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

public class Drive extends Command {
  double speed;
  boolean val = false;
  public Drive(double speed) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_driveTrain);
    this.speed = speed;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    val = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double speed = SmartDashboard.getNumber("Arcade Speed", 0);
    double rotation = SmartDashboard.getNumber("Arcade Rotation", 0);
    if(this.speed == 0){
      Robot.m_driveTrain.TeleopArcadeDrive(speed, rotation);
    }else{
      Robot.m_driveTrain.Stop();
      this.end();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return val;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    val = true;
    Robot.m_driveTrain.Stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
