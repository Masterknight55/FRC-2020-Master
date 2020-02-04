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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.shuffleboard.*;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.subsystems.LED;
import frc.robot.subsystems.PixyCam;
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
    //mControlPanel.updateSubsystem();
    //mIntake.updateSubsystem();
    //mClimber.updateSubsystem();
    mLED.updateSubsystem();
    
  }
  
  public void stopAllSubsystems(){
    //System.out.println("Robot Stopping");
		mDrivetrain.stop();
    mDrivetrain.lowGear();
    //mControlPanel.stop();
    //mClimber.stop();
   // mIntake.stop();
    mLED.stop();

    
  }
  
public void manual()
{
    
    //Controls

    //Primary Driver

    //Drive train Gears 
		if(mSetup.getDriverRb()) {
			mDrivetrain.highGear();
		}
		if(mSetup.getDriverLb()) {
			mDrivetrain.lowGear();
    }
    
    //Dpad and Tank Driver Controls
    double dpadSpeed = .25;

    if(mSetup.getDriverRtBoolean())
    {
      dpadSpeed = dpadSpeed * 2;

    }
    else
    {
      dpadSpeed = .25;
    }
    
    //D Pad Up
    if(mSetup.getDriverPov() == 0 || mSetup.getDriverPov() == 45 || mSetup.getDriverPov() == 315){
      
      mIntake.IntakeArmUp();
      //mDrivetrain.setTankDriveSpeed(-dpadSpeed, -dpadSpeed);
    }

    //D Pad Down
    else if(mSetup.getDriverPov() == 180 || mSetup.getDriverPov() == 225 || mSetup.getDriverPov() == 135){
      
      mIntake.IntakeArmDown();
      //mDrivetrain.setTankDriveSpeed(dpadSpeed, dpadSpeed);
    }

    //D Pad Right
    else if(mSetup.getDriverPov() == 90 || mSetup.getDriverPov() == 45 || mSetup.getDriverPov() == 135){
      
      //TODO Add Climber Strafe
      //mDrivetrain.setTankDriveSpeed(dpadSpeed, -dpadSpeed);
    }

    //D Pad Left
    else if(mSetup.getDriverPov() == 270 || mSetup.getDriverPov() == 225 || mSetup.getDriverPov() == 315){
      
      //TODO Add Climber Strafe
      //mDrivetrain.setTankDriveSpeed(-dpadSpeed, dpadSpeed);
    }

    else if(mSetup.getDriverYbutton())
    {
      mDrivetrain.setTankDriveSpeed(-1*mSetup.getDriverLeftY(), mSetup.getDriverRightY(), 1);
      mDrivetrain.autoAlign(mPixycam, 1);
    }
    else
    {
      mDrivetrain.setTankDriveSpeed(-1*mSetup.getDriverLeftY(), mSetup.getDriverRightY(), 1);
      
    }

    //Intake

    if(mSetup.getDriverLtBoolean())
    {
      mIntake.IntakePowercell();
    }

    if(mSetup.getDriverBbutton())
    {
      mIntake.OuttakePowercell();
    }

    //Delivery

    if(mSetup.getDriverLtBoolean())
    {
      mDelivery.Deliver();
    }

    if(mSetup.getDriverYbutton())
    {
      mDelivery.Swallow();
    }


    //Secondary Driver


    //Climber 

    if(mSetup.getSecondaryDriverRbButton())
    {
      mClimber.Climb();
    }

    if(mSetup.getSecondaryDriverLbButton())
    {
      mClimber.Fall();
    }

    if(mSetup.getSecondaryDriverXButton())
    {
      mClimber.locked();
    }

    if(mSetup.getSecondaryDriverAButton())
    {
      mControlPanel.TurnToFMSColor();
    }

    if(mSetup.getSecondaryDriverBButton())
    {
      mControlPanel.TurnThisManyTimes(1);
    }

     //LED Lights


    updateAllSubsystems();

}



  @Override
  public void robotInit() {
 
    mSetup = Setup.getInstance();
    mDrivetrain = Drivetrain.getInstance();
    mLED = LED.getInstance();
    mDelivery = Delivery.getInstance();
    mControlPanel = ControlPanel.getInstance();
    mSmartDashboardInteractions = new SmartDashboardInteractions();
    mSmartDashboardInteractions.initWithDefaults();



    System.out.println("Robot Init");

    //Camera Setup
    /*
    HttpCamera httpCamera = new HttpCamera("CoprocessorCamera", "frcvision.local:1181/stream.mjpg");
    CameraServer.getInstance().addCamera(httpCamera);
    Shuffleboard.getTab("Tab")
    .add(httpCamera);
    */

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

 
  
  public void GetFMSData(){
    String colorData = DriverStation.getInstance().getGameSpecificMessage();

        if(colorData.length() > 0){
            switch(colorData.charAt(0)){
                case 'R':
                mControlPanel.SetFMSColor("Red");
                SmartDashboard.putString("color", "R");
                break;

                case 'G':
                mControlPanel.SetFMSColor("Green");
                SmartDashboard.putString("color", "G");
                break;

                case 'B':
                mControlPanel.SetFMSColor("Blue");
                SmartDashboard.putString("color", "B");
                break;

                case 'Y':
                mControlPanel.SetFMSColor("Yellow");
                SmartDashboard.putString("color", "Y");
                break;
            }
        }
  }

  @Override
	public void teleopInit(){
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
