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

  private static double distance = 0.0;
  private static double speed = 0.0;

  public Drive(double d, double s) {
      requires (Robot.m_driveTrain);
      this.distance =d;
      this.speed =s;
      SmartDashboard.putNumber("Constructor Speed", speed);
      SmartDashboard.putNumber("Constructor Distance", distance);
  }

  @Override
  protected void initialize(){
    Robot.m_driveTrain.Stop();
    Robot.m_driveTrain.resetDistance();
    SmartDashboard.putNumber("Initialize Speed", speed);
      SmartDashboard.putNumber("Initialize Distance", distance);
  }
  @Override
  protected void execute(){
    //Robot.m_driveTrain.TeleopArcadeDrive(speed,0.0); 
    Robot.m_driveTrain.TeleopArcadeDrive(speed,-(Robot.m_driveTrain.getLeftDistance()-Robot.m_driveTrain.getRightDistance())*0.05);
  }

  @Override
  protected boolean isFinished (){
    return(distance - (Robot.m_driveTrain.getLeftDistance()+Robot.m_driveTrain.getRightDistance())/2) < 0.5;

  }
  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_driveTrain.Stop();
    Robot.m_driveTrain.resetDistance();
  }
  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();
  }
}
