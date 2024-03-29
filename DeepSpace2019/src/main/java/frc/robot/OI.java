/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ArmGrab;
import frc.robot.commands.DriveToDistance;
import frc.robot.commands.armMove;
import frc.robot.commands.ballPivot;
import frc.robot.commands.ballShoot;
import frc.robot.commands.changeDir;
import frc.robot.commands.changeMode;
import frc.robot.commands.limeDrive;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  public Joystick driverX = new Joystick(RobotMap.driverJoystick);
  public Joystick opX = new Joystick(RobotMap.operatorJoystick);

  Button driverX_A            = new JoystickButton(driverX, 1);
  Button driverX_B            = new JoystickButton(driverX, 2);
  Button driverX_X            = new JoystickButton(driverX, 3);
  Button driverX_Y            = new JoystickButton(driverX, 4);
  Button driverX_LeftBumper   = new JoystickButton(driverX, 5);
  Button driverX_RightBumper  = new JoystickButton(driverX, 6);
  Button driverX_Back         = new JoystickButton(driverX, 7);
  Button driverX_Start        = new JoystickButton(driverX, 8);
  Button driverX_L3           = new JoystickButton(driverX, 9);
  Button driverX_R3           = new JoystickButton(driverX, 10);

  Button opX_A                = new JoystickButton(opX, 1);
  Button opX_B                = new JoystickButton(opX, 2);
  Button opX_X                = new JoystickButton(opX, 3);
  Button opX_Y                = new JoystickButton(opX, 4);
  Button opX_LeftBumper       = new JoystickButton(opX, 5);
  Button opX_RightBumper      = new JoystickButton(opX, 6);
  Button opX_Back             = new JoystickButton(opX, 7);
  Button opX_Start            = new JoystickButton(opX,  8);
  Button opX_L3               = new JoystickButton(opX, 9);
  Button opX_R3               = new JoystickButton(opX, 10);

  public OI(){
    driverX_A.whenPressed(new ArmGrab(DoubleSolenoid.Value.kForward));
    driverX_B.whenPressed(new ArmGrab(DoubleSolenoid.Value.kReverse));
    opX_X.whenPressed(new ballPivot(1));
	  opX_X.whenReleased(new ballPivot(0));
		opX_Y.whenPressed(new ballPivot(-1));
    opX_Y.whenReleased(new ballPivot(0));
    driverX_LeftBumper.whenPressed(new armMove(-0.15));
    driverX_LeftBumper.whenReleased(new armMove(0));
    driverX_RightBumper.whenPressed(new armMove(0.15));
    driverX_RightBumper.whenReleased(new armMove(0));
    driverX_Start.whenPressed(new changeDir());
//    SmartDashboard.putNumber("Drive Distance", 0);
//    SmartDashboard.putNumber("Drive Speed", 0);
    opX_Back.whenPressed(new ballShoot(.6));
    //driverX_Back.whenPressed(new limeDrive());
    driverX_L3.whenPressed(new changeMode());
  }






  public double getDriverX(){
    return driverX.getX();
  }
  
  public double getDriverY(){
    return driverX.getY();
  }

  public double getOpX(){
    return opX.getX();
  }
  
  public double getOpY(){
    return opX.getY();
  }
  public Joystick getOp(){
    return opX;
  }
  public Joystick getDriver(){
    return driverX;
  }




}
