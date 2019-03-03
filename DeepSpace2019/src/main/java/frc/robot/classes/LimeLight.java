/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.classes;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class LimeLight {
    private static final double TX_MAX = 54.0;
    private static final double TY_MAX = 41.0;
  
    //private static final double KP_DIST = 0.1;
    //private static final double KP_AIM = 0.1;
    private static final double X_TOLERANCE = 0.5;
    private static final double Y_TOLERANCE = 0.25;
    private static final double LIMIT = 0.8;
  
    private static double leftSpeed;
    private static double rightSpeed;
  
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry tv = table.getEntry("tv");
   // Put methods for controlling this subsystem
    // here. Call these from Commands.
  
    public double getTX(){
      return tx.getDouble(0.0);
      //return 15;
    }

    public double getTY(){
      return ty.getDouble(0.0);
      //return -5;
    }

    public boolean getTV(){
      return tv.getDouble(0)%2 != 0;
      //return false;
    }
  
    public void setLEDOn(boolean on){
      table.getEntry("ledMode").setNumber(on?3:1);
    }

    public void setCameraMode(boolean drive){
      //drive = true will set mode to stream, false to vision
      table.getEntry("camMode").setNumber(drive?1:0);
      //Turn LEDs On/Off based on camMode. stream = LEDs Off, vision = LEDs On
      setLEDOn(!drive);
  
      //Start speed at 0.  May need to adjust based on testing
      leftSpeed = 0.0;
      rightSpeed = 0.0;
    }
  
    public double getLeftSpeed(){
      return leftSpeed;
    }

    public double getRightSpeed(){
      return rightSpeed;
    }
  
    public boolean withinTolerance(){
      return ((Math.abs(getTX()) < X_TOLERANCE) && (Math.abs(getTY()) < Y_TOLERANCE));
    }

    //New version of calcSpeeds.  The old one looks like the speeds will never slow down.
    public boolean calcSpeeds(){
//Test 4: Comment out following 2 lines
      // leftSpeed  = 0.5;
      // rightSpeed = 0.5;
//Test 4: Uncomment lines to always calculate headingAdj, distanceAdj, leftSpeed
//        and rightSpeed, and always return true
//Test 5: Uncomment if code to implement checking for target acquisition and return
//        value of getTV() instead of always true

      boolean validTarget = getTV();
      double headingAdj;
      double distanceAdj;
      if (validTarget){
        headingAdj  = (Math.abs(getTX()) > X_TOLERANCE) ? (getTX()/TX_MAX) : 0.0;
        distanceAdj = (Math.abs(getTY()) > Y_TOLERANCE) ? (getTY()/TY_MAX) : 0.0;
      }else{
        //Alert the operator to no target and set speed adjustments to 0
        SmartDashboard.putString("Valid Target: ", "No Valid Target");
        headingAdj = 0.0;
        distanceAdj = 0.0;
      }
      SmartDashboard.putNumber("Heading  Adj: ", headingAdj);
      SmartDashboard.putNumber("Distance Adj: ", distanceAdj);
      //Calculate the speeds
      leftSpeed  = (distanceAdj-headingAdj) * LIMIT;
      rightSpeed = (distanceAdj+headingAdj) * LIMIT;
      return validTarget;
         //return true;
    }
}