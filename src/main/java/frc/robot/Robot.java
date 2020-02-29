/*                                                                                              
      @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@            @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      
      @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@            @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      
     @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@            @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      
     @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@           @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      
     @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@           @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      
     @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@          @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      
     @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@          @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      
     @@@@@@@@@@@@@@@           @@@@@@@@@@@@@          @@@@@@@@@@@@@            @@@@@@@@@@@@@@@     
     @@@@@@@@@@@@@@@            @@@@@@@@@@@@          @@@@@@@@@@@@@            @@@@@@@@@@@@@@@     
    @@@@@@@@@@@@@@@@            @@@@@@@@@@@@@         @@@@@@@@@@@@@            @@@@@@@@@@@@@@@     
    @@@@@@@@@@@@@@@@            @@@@@@@@@@@@@         @@@@@@@@@@@@@            @@@@@@@@@@@@@@@     
    @@@@@@@@@@@@@@@@            @@@@@@@@@@@@@        @@@@@@@@@@@@@@            @@@@@@@@@@@@@@@     
    @@@@@@@@@@@@@@@@            @@@@@@@@@@@@@        @@@@@@@@@@@@@@            @@@@@@@@@@@@@@@     
    @@@@@@@@@@@@@@@@            @@@@@@@@@@@@@        @@@@@@@@@@@@@@            @@@@@@@@@@@@@@@     
    @@@@@@@@@@@@@@@@@@@@@@@@    @@@@@@@@@@@@@        @@@@@@@@@@@@@    @@@@@@@@@@@@@@@@@@@@@@@@@    
    @@@@@@@@@@@@@@@@@@@@@@@@    @@@@@@@@@@@@@@       @@@@@@@@@@@@@    @@@@@@@@@@@@@@@@@@@@@@@@@    
   @@@@@@@@@@@@@@@@@@@@@@@@@    @@@@@@@@@@@@@@       @@@@@@@@@@@@@    @@@@@@@@@@@@@@@@@@@@@@@@@    
   @@@@@@@@@@@@@@@@@@@@@@@@@    @@@@@@@@@@@@@@       @@@@@@@@@@@@@    @@@@@@@@@@@@@@@@@@@@@@@@@    
    @@@@@@@@@@@@@@@@@@@@@@@@    @@@@@@@@@@@@@@      @@@@@@@@@@@@@@    @@@@@@@@@@@@@@@@@@@@@@@@@    
         @@@@@@@@@@@@@@@@@@@@   @@@@@@@@@@@@@@      @@@@@@@@@@@@@@    @@@@@@@@@@@@@@@@@@@          
             @@@@@@@@@@@@@@@    @@@@@@@@@@@@@@      @@@@@@@@@@@@@@    @@@@@@@@@@@@@@@              
            @@@@@@@@@@@@@@@     @@@@@@@@@@@@@@      @@@@@@@@@@@@@@     @@@@@@@@@@@@@@@             
           @@@@@@@@@@@@@@@@     @@@@@@@@@@@@@@@     @@@@@@@@@@@@@@      @@@@@@@@@@@@@@@            
           @@@@@@@@@@@@@@@      @@@@@@@@@@@@@@@     @@@@@@@@@@@@@@       @@@@@@@@@@@@@@@           
          @@@@@@@@@@@@@@@       @@@@@@@@@@@@@@@     @@@@@@@@@@@@@@       @@@@@@@@@@@@@@@@          
         @@@@@@@@@@@@@@@        @@@@@@@@@@@@@@@    @@@@@@@@@@@@@@@        @@@@@@@@@@@@@@@          
        @@@@@@@@@@@@@@@@         @@@@@@@@@@@@@@    @@@@@@@@@@@@@@@         @@@@@@@@@@@@@@@         
       @@@@@@@@@@@@@@@@          @@@@@@@@@@@@@@    @@@@@@@@@@@@@@@          @@@@@@@@@@@@@@@        
      @@@@@@@@@@@@@@@@           @@@@@@@@@@@@@@    @@@@@@@@@@@@@@@          @@@@@@@@@@@@@@@@       
     @@@@@@@@@@@@@@@@            @@@@@@@@@@@@@@    @@@@@@@@@@@@@@@           @@@@@@@@@@@@@@@@      
     @@@@@@@@@@@@@@@             @@@@@@@@@@@@@@@   @@@@@@@@@@@@@@@            @@@@@@@@@@@@@@@@     
    @@@@@@@@@@@@@@@@             @@@@@@@@@@@@@@@   @@@@@@@@@@@@@@@             @@@@@@@@@@@@@@@@    
   @@@@@@@@@@@@@@@@              @@@@@@@@@@@@@@@  @@@@@@@@@@@@@@@              @@@@@@@@@@@@@@@@    
  @@@@@@@@@@@@@@@@               @@@@@@@@@@@@@@@  @@@@@@@@@@@@@@@               @@@@@@@@@@@@@@@@   
 @@@@@@@@@@@@@@@@                @@@@@@@@@@@@@@@  @@@@@@@@@@@@@@@                @@@@@@@@@@@@@@@@  
                                                                                                   
*/




package frc.robot;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.Delivery;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.auto.AutoExecuter;


import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import java.util.Scanner;

import edu.wpi.cscore.CameraServerJNI;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.shuffleboard.*;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.subsystems.LED;
import frc.robot.subsystems.PixyCam;
import frc.robot.subsystems.Drivetrain.DriveGear;
import frc.robot.auto.AutoExecuter;


public class Robot extends TimedRobot  {

  //Initialize main parts of the robot
  Setup mSetup;
  Drivetrain mDrivetrain;
  LED mLED;
  Delivery mDelivery;
  ControlPanel mControlPanel;
  Intake mIntake;
  Climber mClimber;
  SmartDashboardInteractions mSmartDashboardInteractions;
  PixyCam mPixycam = new PixyCam(0);

  AutoExecuter mAutoExecuter = null;
  
  public void updateAllSubsystems(){
		
    mDrivetrain.updateSubsystem();
    mControlPanel.updateSubsystem();
    mIntake.updateSubsystem();
    mClimber.updateSubsystem();
    //mLED.updateSubsystem();
    mDelivery.updateSubsystem();
    mPixycam.updateSubsystem();
    
  }
  
  public void stopAllSubsystems(){
    //System.out.println("Robot Stopping");
		mDrivetrain.stop();
    mDrivetrain.lowGear();
    mControlPanel.stop();
    mClimber.stop();
    mIntake.stop();
    //mLED.stop();
    mDelivery.stop();
    
  }

  
/**
   * This is the Manual Control Method. 
   * The reason this is in a method is so then if you want you can call it in different areas like Auto or Tele-Op
   * Each one of the Subsystems on the Robot are split into sections. 
   * The Controls are being called from the mSetup file. This has bothh of the controllers and the switch pad. 
   * Each control needs a comment about what it needs to accomplish. 
   */  

public void manual()
{
    
    //Controls

    //Primary Driver  
    //--------------------------------------------------


    //Drive train Gears 
    /**
   * Here are the controls for the Drive Train Gears
   * The Gears on our robot are like gears on a car. Low gear is slower and has more touque.
   * High gear has more speed but less touque (it can cause wheel spin if on surfaces without alot of friction)
   */  
		if(mSetup.getDriverRb()) {
			mDrivetrain.highGear();
		}
		if(mSetup.getDriverLb()) {
			mDrivetrain.lowGear();
    }
    
    //D Pad Up
    if(mSetup.getDriverPov() == 0 || mSetup.getDriverPov() == 45 || mSetup.getDriverPov() == 315)
    {
      mIntake.IntakeArmUp();
    }

    //D Pad Down
    else if(mSetup.getDriverPov() == 180 || mSetup.getDriverPov() == 225 || mSetup.getDriverPov() == 135)
    {
      mIntake.IntakeArmDown();
    }

    //D Pad Right
    else if(mSetup.getDriverPov() == 90 || mSetup.getDriverPov() == 45 || mSetup.getDriverPov() == 135)
    {
      mDelivery.BrechPremitier();
      //mClimber.MoveRight();
    }

    //D Pad Left
    else if(mSetup.getDriverPov() == 270 || mSetup.getDriverPov() == 225 || mSetup.getDriverPov() == 315)
    {
      mDelivery.AssumeThePostition();
      //mClimber.MoveLeft();
    }

    
    //PixyCam Controls
     if(mSetup.getDriverAButton())
     {
       mDrivetrain.setTankDriveSpeed(-1*mSetup.getDriverLeftY(), mSetup.getDriverRightY(), 1);
       mDrivetrain.autoAlign(mPixycam, 1);
     }

     else if(mSetup.getDriverXButton())
     {
       mDrivetrain.setTankDriveSpeed(-1*mSetup.getDriverLeftY(), mSetup.getDriverRightY(), 1);
       mDrivetrain.chaseBall(mPixycam, 1);
     }
     else
     {  
       if(mDrivetrain.getDriveGear() == DriveGear.LOW )
       {
        mDrivetrain.setTankDriveSpeed(-1*mSetup.getDriverLeftY(), mSetup.getDriverRightY(), .80);
       }
       else
       {
        mDrivetrain.setTankDriveSpeed(-1*mSetup.getDriverLeftY(), mSetup.getDriverRightY(), 1);
       }
       
      
     }

    

    //Intake
    //--------------------------------------------------
     /**
   * Here are the controls for the Intake Mech. These controls use the triggers as a boolean value. 
   * This means that if the trigger is pushed after a dead zone it will return true. 
   * You can also get the analog value of the trigger if needed. 
   * The reason for using a else if statement means only one of the methods can be called at once. 
   * And remember that you need to stop the intake when there is no button being pressed!
   */  
    if(mSetup.getDriverLtBoolean())
    {
      mIntake.IntakePowercell();
     
    }
    else if(mSetup.getDriverBbutton())
    {
      mIntake.OuttakePowercell();
    }
    else
    {
      mIntake.StopIntaking();
      
    }

    

    //Delivery
    //--------------------------------------------------
     /**
   * Here are the controls for the Delivery.  
   */  
    if(mSetup.getDriverRtBoolean())
    {
      mSetup.mDriverStick.setRumble(RumbleType.kLeftRumble, 1);
      mSetup.mDriverStick.setRumble(RumbleType.kRightRumble, 1);
      mDelivery.Deliver();
    }
     else if(mSetup.getDriverYbutton())
     {
      mSetup.mDriverStick.setRumble(RumbleType.kLeftRumble, 1);
      mSetup.mDriverStick.setRumble(RumbleType.kRightRumble, 1);
       mDelivery.Swallow();
     }
     else 
     {
      mSetup.mDriverStick.setRumble(RumbleType.kLeftRumble, 0);
      mSetup.mDriverStick.setRumble(RumbleType.kRightRumble, 0);
       mDelivery.AutoMove();
     }
    

    //Secondary Driver
    //--------------------------------------------------


    //Climber 
    //--------------------------------------------------
    /**
   *   
   */
  
  

    if(mSetup.getSecondaryDriverRbButton())
    {
      mClimber.Climb();
    }

    else if(mSetup.getSecondaryDriverLbButton())
    {
      mClimber.Fall();
    }
    else 
    {
      mClimber.stopClimbing();
    }

     if(mSetup.getSecondaryDriverXButton())
     {
       mClimber.locked();
       
     }

     else if(mSetup.getSecondaryDriverAButton())
     {
       mClimber.unlocked();
       
     }

     if(mSetup.getSecondaryDriverRtButton())
     {
       mClimber.MoveRight();
     }

     if(mSetup.getSecondaryDriverLtButton())
     {
       mClimber.MoveLeft();
     }



     //Control Panel Controller 
    //--------------------------------------------------
    /**
   * Here are the controls for the control panel controller. 
   * It has a few different methods like turning to the FMS color and turning the wheel a certain amount of times.
   * 
   */  
    if(mSetup.getSecondaryDriverAButton())
    {
      mControlPanel.TurnToFMSColor();
    }
    else if(mSetup.getSecondaryDriverYButton())
    {
      mControlPanel.TurnThisManyTimes(1);
    }
    else
    {
      mControlPanel.stop();
    }

     //LED Lights


    updateAllSubsystems();

}

public void GetFMSData() {
  String colorData = DriverStation.getInstance().getGameSpecificMessage();

      if(colorData.length() > 0){
          switch(colorData.charAt(0)){
              case 'R':
              mControlPanel.SetFMSColor("Red");
              SmartDashboard.putString("color", "Red");
              break;

              case 'G':
              mControlPanel.SetFMSColor("Green");
              SmartDashboard.putString("color", "Green");
              break;

              case 'B':
              mControlPanel.SetFMSColor("Blue");
              SmartDashboard.putString("color", "Blue");
              break;

              case 'Y':
              mControlPanel.SetFMSColor("Yellow");
              SmartDashboard.putString("color", "Yellow");
              break;
          }
      }
}



  @Override
  public void robotInit() {
 
    System.out.println("Robot Init");

    mSetup = Setup.getInstance();
    mDrivetrain = Drivetrain.getInstance();
    //mLED = LED.getInstance();
    mDelivery = Delivery.getInstance();
    mControlPanel = ControlPanel.getInstance();
    mIntake = Intake.getInstance();
    mSmartDashboardInteractions = new SmartDashboardInteractions();
    mSmartDashboardInteractions.initWithDefaults();
    mClimber = Climber.getInstance();

    stopAllSubsystems();

  }

  @Override
	public void autonomousInit() {

    if (mAutoExecuter != null) {
            mAutoExecuter.stop();
        }
        mAutoExecuter = null;
        
        mAutoExecuter = new AutoExecuter();
        mAutoExecuter.setAutoRoutine(mSmartDashboardInteractions.getSelectedAutonMode());
        mAutoExecuter.start();
		
		stopAllSubsystems();
		updateAllSubsystems();
		
	}
		
	@Override
	public void autonomousPeriodic() {
    manual();
		updateAllSubsystems();
	}

  @Override
	public void disabledInit(){
      if (mAutoExecuter != null) {
            mAutoExecuter.stop();
        }
        mAutoExecuter = null;
        
        //TODO Add ResetEncoders to drivetrain
        //mDrivetrain.resetEncoders(); 
        mDrivetrain.highGear();
		
		stopAllSubsystems();
		updateAllSubsystems();
	}
  
  
  @Override
	public void disabledPeriodic() {

  //System.out.println("Disabled Periodic");
  }

  @Override
	public void teleopInit() {
		stopAllSubsystems();
    mDrivetrain.lowGear();
    System.out.println("Tele Init");
    updateAllSubsystems();
    
  }
  
  @Override
  public void teleopPeriodic() {
    manual();
    updateAllSubsystems();
  }
 
}
