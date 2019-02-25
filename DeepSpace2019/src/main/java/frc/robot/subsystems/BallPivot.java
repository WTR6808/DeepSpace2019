/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class BallPivot extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  Spark intakePivot = new Spark(10);
	DigitalInput fullUp = new DigitalInput(0);
	DigitalInput fullDown = new DigitalInput(1);
	private int isUp;
	private int isDown;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public void drivePivot(double speed) {
   // System.out.println(fullUp.get());
    if(!fullUp.get() && speed > 0.2) {
        intakePivot.setSpeed(speed);
        //System.out.println("Pivot Up");
        //System.out.println(speed*.4);
        
    }else if(!fullDown.get() && speed < -0.2)
      {
        intakePivot.setSpeed(speed);
        //System.out.println("Pivot Down");
        //System.out.println(speed);
    }else if(fullUp.get() && fullDown.get()) {
        intakePivot.setSpeed(speed);
        //System.out.println("Pivot Transition");
        //System.out.println(speed);
    }else {
        intakePivot.setSpeed(0);
        //System.out.println("Pivot Stopped");
      }
      
  }
  
  public void PivotArm(double speed, double time) {
    intakePivot.setSpeed(speed);
    Timer.delay(time);
    intakePivot.setSpeed(0);
  }
}
