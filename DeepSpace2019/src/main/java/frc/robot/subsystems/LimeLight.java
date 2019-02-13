/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.LimeLightOutput;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * Add your docs here.
 */
public class LimeLight extends Subsystem {

  private static final double TX_MAX = 54.0;
  private static final double TY_MAX = 41.0;

  private static final double KP_DIST = 0.1;
  private static final double KP_AIM = 0.1;
  private static final double X_TOLERANCE = 0.5;
  private static final double LIMIT = 0.8;

  private static double leftSpeed;
  private static double rightSpeed;

  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry tv = table.getEntry("tv");
  double x = tx.getDouble(0.0);
  double y = ty.getDouble(0.0);
  double v = tv.getDouble(0.0);
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new LimeLightOutput());
  }
  public double getTX(){
    return tx.getDouble(0.0);
  }
  public double getTY(){
    return ty.getDouble(0.0);
  }
  public boolean getTV(){
    return tv.getDouble(0)%2 != 0;
  }
  public void setLEDOn(boolean on){
    table.getEntry("ledMode").setNumber(on?3:1);

  }
  public void setCameraMode(boolean drive){
    table.getEntry("camMode").setNumber(drive?1:0);
    setLEDOn(!drive);
    leftSpeed = 0;
    rightSpeed = 0;
  }
  public double getLeftSpeed(){
    return leftSpeed;
  }
  public double getRightSpeed(){
    return rightSpeed;
  }
  public boolean calcSpeeds(){
    double headingAdj;
    double distanceAdj;
    if(getTV()){
      SmartDashboard.putString("Valid Target", "Valid Target");
      distanceAdj = (getTY()/TY_MAX)*KP_DIST;
      headingAdj = (getTX()/TX_MAX)*KP_DIST;
      leftSpeed += (distanceAdj-headingAdj)*LIMIT;
    }else{
      SmartDashboard.putString("Valid Target", "No valid target");
      leftSpeed = 0;
      rightSpeed = 0;
    }
    return getTV();
  }
}