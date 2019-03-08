/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ballIntake extends Command {
  public ballIntake() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_ballIntake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
        	//Robot.driveTrain.testMotor(motorSpeed);
          Joystick joystickX = Robot.m_oi.getOp();
          //Joystick joystickX = Robot.m_oi.getopX();
          if(joystickX.getRawAxis(2) > 0.1 ) {
            Robot.m_ballIntake.Intake(joystickX.getRawAxis(2)*.6);
          }else if(joystickX.getRawAxis(3) > 0.1) {
            Robot.m_ballIntake.Intake(-joystickX.getRawAxis(3)*.6);
            
          }else {
            Robot.m_ballIntake.Intake(0);
          }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
