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
import frc.robot.subsystems.Delivery;
import frc.robot.subsystems.Drivetrain;


import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import java.util.Scanner;

import edu.wpi.cscore.CameraServerJNI;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.shuffleboard.*;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.subsystems.LED;
import frc.robot.auto.AutoExecuter;



public class Robot extends IterativeRobot  {

  //Initialize main parts of the robot
  Setup mSetup;
  Drivetrain mDrivetrain;
  LED mLED;
  Delivery mDelivery;

  AutoExecuter mAutoExecuter = null;
  
  public void updateAllSubsystems(){
		
		mDrivetrain.updateSubsystem();
	  
    mLED.updateSubsystem();
  }
  
  public void stopAllSubsystems(){
    //System.out.println("Robot Stopping");
		mDrivetrain.stop();
		mDrivetrain.lowGear();
	
    
  }
  
public void manual()
{
    
    //Controls

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
    
    if(mSetup.getDriverPov() == 0 || mSetup.getDriverPov() == 45 || mSetup.getDriverPov() == 315){
      mDrivetrain.setTankDriveSpeed(-dpadSpeed, -dpadSpeed);
    }
    else if(mSetup.getDriverPov() == 180 || mSetup.getDriverPov() == 225 || mSetup.getDriverPov() == 135){
      mDrivetrain.setTankDriveSpeed(dpadSpeed, dpadSpeed);
    }
    else if(mSetup.getDriverPov() == 90 || mSetup.getDriverPov() == 45 || mSetup.getDriverPov() == 135){
      mDrivetrain.setTankDriveSpeed(dpadSpeed, -dpadSpeed);
    }
    else if(mSetup.getDriverPov() == 270 || mSetup.getDriverPov() == 225 || mSetup.getDriverPov() == 315){
      mDrivetrain.setTankDriveSpeed(-dpadSpeed, dpadSpeed);
    }
    else
    {
      mDrivetrain.setTankDriveSpeed(mSetup.getDriverLeftY(), mSetup.getDriverRightY());
    }


    //Climber

    
    //Cargo Intake
		

		
     //LED Lights


    updateAllSubsystems();

}



  @Override
  public void robotInit() {
 
    mSetup = Setup.getInstance();
    mDrivetrain = Drivetrain.getInstance();
    mLED = LED.getInstance();
    mDelivery = Delivery.getInstance();


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
    System.out.println("Auto Init");
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
      System.out.println("Disabled Init");
      mDrivetrain.highGear();
		  stopAllSubsystems();
		  updateAllSubsystems();
  }
  
  @Override
	public void disabledPeriodic() {

  System.out.println("Disabled Periodic");

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
