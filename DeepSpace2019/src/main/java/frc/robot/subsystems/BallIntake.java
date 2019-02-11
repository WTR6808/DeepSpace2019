/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.ballIntake;

/**
 * Add your docs here.
 */
public class BallIntake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  Spark armRight = new Spark(11);
	Spark armLeft = new Spark(12);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ballIntake());
  }
  public void Intake(double d) {
    armRight.setSpeed(d);
    armLeft.setSpeed(d);
    //System.out.println(X.getPOV());
  }
  public void Stop() {
    armRight.setSpeed(0);
    armLeft.setSpeed(0);
}
  
}
