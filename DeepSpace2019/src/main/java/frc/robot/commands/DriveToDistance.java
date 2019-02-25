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
  //KP term for heading correction PID
  private static final double KP_HDG_ADJ = 0.05;

  //Allowable distance error for determining isFinished
  private static final double DIST_TOLERANCE = 0.5;

  //Target distance and speed as set by the constructor
  private double speed;
  private double distance;

  //Currently travelled distances as measured by the encoders
  private double leftDist;
  private double rightDist;

  public DriveToDistance(double d, double s) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_driveTrain);

    //Store the target distance and speed
    distance = d;
    speed = s;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //Make sure the robot is stopped and set the encoders to 0
    Robot.m_driveTrain.Stop();
    Robot.m_driveTrain.resetDistance();
    leftDist  = 0.0;
    rightDist = 0.0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double headingError;
    
    //Get the current distances and send to dashboard for troubleshooting
    leftDist  = Robot.m_driveTrain.getLeftDistance();
    rightDist = Robot.m_driveTrain.getRightDistance();
    SmartDashboard.putNumber("Left  Distance: ", leftDist);
    SmartDashboard.putNumber("Right Distance: ", rightDist);

    //Calculate the error in the left and right encoders and adjust heading
    //KP for error correction will need to be determined by trial and error
    headingError = (leftDist-rightDist)  * KP_HDG_ADJ;

    //Use heading error as rotation speed in Arcade Drive
    //Robot.m_driveTrain.TeleopArcadeDrive(speed,-headingError); 
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    //Use the average distance travelled to determine is finished
    return(distance - ((leftDist+rightDist)/2.0)) < DIST_TOLERANCE;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    //Make sure the robot is completely stopped.
    Robot.m_driveTrain.Stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();
  }
}
