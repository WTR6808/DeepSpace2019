/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  public static int driveLeftFrontMotor = 2;
  public static int driveLeftRearMotor = 3;
  public static int driveRightFrontMotor = 0;
  public static int driveRightRearMotor = 1;

  public static int encoderLeftA = 8;
  public static int encoderLeftB = 9;
  public static int encoderRightA = 6;
  public static int encoderRightB = 7;
   
  public static int driverJoystick = 0;
  public static int operatorJoystick = 1;

  public static int armLimitFront = 4;
  public static int armLimitBack = 5;

  public static int solenoidA = 0;
  public static int solenoidB = 1;
  
  public static int armMotorRight = 4;
  public static int armMotorLeft = 5;

  //costants E14 encoders CPR = 620 x 4 = 1440 pulses /revolution 
  public static final double ENCODER_PPR = 1440.0;//360 CPR * 4pulses/rev
  public static final double  WHEEL_DIAM = 6.0;//inches
  public static final double DIST_PER_PULSE = ((WHEEL_DIAM*Math.PI)/ENCODER_PPR)*(12/6.5);
}