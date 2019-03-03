/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */

 
public class HatchIntake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  DoubleSolenoid sol = new DoubleSolenoid(RobotMap.solenoidA, RobotMap.solenoidB);
  PWMVictorSPX arm = new PWMVictorSPX(RobotMap.armMotorRight);
  PWMVictorSPX arm2 = new PWMVictorSPX(RobotMap.armMotorLeft);
  DigitalInput fullBack = new DigitalInput(RobotMap.armLimitFront);
	DigitalInput fullFront = new DigitalInput(RobotMap.armLimitBack);
	private int isFront;
  private int isBack;
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void closeArm(DoubleSolenoid.Value value){
    sol.set(value);
  }
  public void stop(){
    sol.set(DoubleSolenoid.Value.kOff);
  }
  public void moveArm(double speed){
    if(getIsFullUp() && speed < -0.2) {
      arm.setSpeed(speed);
      arm2.setSpeed(speed);
      //System.out.println("Pivot Up");
      //System.out.println(speed*.4);
      
  }else if(getIsFullDown() && speed > 0.2)
  {
      arm.setSpeed(speed);
      arm2.setSpeed(speed);
      //System.out.println("Pivot Down");
      //System.out.println(speed);
  }else if(getIsFullDown() && getIsFullUp()) {
      arm.setSpeed(speed);
      arm2.setSpeed(speed);
      //System.out.println("Pivot Transition");
      //System.out.println(speed);
  }else {
      arm.setSpeed(0);
      arm2.setSpeed(0);
      //System.out.println("Pivot Stopped");
    }
    
  }
  public boolean getIsFullUp() {
    if(!fullFront.get()) {
      isFront = 1;
    }
    else {
      isFront = 0;
      
    }
    return fullFront.get();
  }
  public boolean getIsFullDown() {
    if(!fullBack.get()) {
      isBack = 1;
    }
    else {
      isBack = 0;
      
    }
    return fullBack.get();
  }
}
