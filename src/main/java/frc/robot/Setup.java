package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
//import edu.wpi.first.wpilibj.Joystick; 
//never used
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Setup
{

    private static Setup mInstance = new Setup();

    public static Setup getInstance() {
        return mInstance;
    }
    
public Setup()
{
this.ControlBoard();

}
//----------------------------------------------------------------------------Controls-----------------------------------------------------------------------------------------//
    
    //Creates Joystick Object
    Joystick mDriverStick;
    Joystick mSecondaryDriverStick;
    Joystick mSwitchboard;
 

    //Initialize Joystick Object
    void ControlBoard() {
    	  mDriverStick = new Joystick(0);
        mSecondaryDriverStick = new Joystick(1);
        mSwitchboard = new Joystick(2);
    }

     //DRIVER CONTROLLER
    
    //Drive Controls

    public double getDriverLeftX(){
    	return mDriverStick.getRawAxis(0);
    }
    
    public double getDriverLeftY(){
    	return -mDriverStick.getRawAxis(5)*-1;
    }
    
    public double getDriverRightX(){
    	return mDriverStick.getRawAxis(4);
    }
    
    public double getDriverRightY(){
    	return -mDriverStick.getRawAxis(1)*-1;
    }
    
    public int getDriverPov(){
    	return mDriverStick.getPOV(0);
    }

    
    public boolean getDriverRb(){
    	return mDriverStick.getRawButton(6);
    }
    
    public boolean getDriverLb(){
        return mDriverStick.getRawButton(5);
    }

    public boolean getDriverLtBoolean()
    {
      if(mDriverStick.getRawAxis(2) > 0)
      {
        return true;
      }
      else
      {
        return false;
      }
    }

    public boolean getDriverRtBoolean()
    {
      if(mDriverStick.getRawAxis(2) > 0)
      {
        return true;
      }
      else
      {
        return false;
      }
    }

    public double getDriverRt()
    {
      return mDriverStick.getRawAxis(3);
    }

    public double getDriverLt()
    {
      return mDriverStick.getRawAxis(2);
    }


    //SECONDARY CONTROLLER


    //Face Buttons
    public boolean getSecondaryDriverAButton()
    {
      return mSecondaryDriverStick.getRawButton(1);
    }

    
    public boolean getSecondaryDriverBButton()
    {
      return mSecondaryDriverStick.getRawButton(2);
    }

    
    public boolean getSecondaryDriverXButton()
    {
      return mSecondaryDriverStick.getRawButton(3);
    }

    public boolean getSecondaryDriverYButton()
    {
      return mSecondaryDriverStick.getRawButton(4);
    }

    public boolean getSecondaryDriverRbButton()
    {
      return mSecondaryDriverStick.getRawButton(5);
    }

    public boolean getSecondaryDriverLbButton()
    {
      return mSecondaryDriverStick.getRawButton(6);
    }

    

    
    //Analog Sticks
    public double getSecondaryDriverLeftStickX()
    {
      return mSecondaryDriverStick.getRawAxis(0);
    }

    
    public double getSecondaryDriverLeftStickY()
    {
      return mSecondaryDriverStick.getRawAxis(1);
    }
    
    public double getSecondaryDriverRightStickX()
    {
      return mSecondaryDriverStick.getRawAxis(4);
    }

    public double getSecondaryDriverRightStickY()
    {
      return mSecondaryDriverStick.getRawAxis(5);
    }

    
    public double getSecondaryLt()
    {
      return mSecondaryDriverStick.getRawAxis(2);
    }

    public double getSecondaryRt()
    {
      return mSecondaryDriverStick.getRawAxis(3);
    }

    

    //Other Buttons
    public int getSecondaryDriverPov(){
    	return mSecondaryDriverStick.getPOV(0);
    }
    
    

    //Switch Board
   public boolean GetSwitchBoardButton1(){
    return mSwitchboard.getRawButton(1);
  }
  public boolean GetSwitchBoardButton2(){
    return mSwitchboard.getRawButton(2);
  }
  public boolean GetSwitchBoardButton3(){
    return mSwitchboard.getRawButton(3);
  }
  public boolean GetSwitchBoardButton4(){
    return mSwitchboard.getRawButton(4);
  }
  public boolean GetSwitchBoardButton5(){
    return mSwitchboard.getRawButton(5);
  }
  public boolean GetSwitchBoardButton6(){
    return mSwitchboard.getRawButton(6);
  }
  public boolean GetSwitchBoardButton7(){
    return mSwitchboard.getRawButton(7);
  }
  public boolean GetSwitchBoardButton8(){
    return mSwitchboard.getRawButton(8);
  }

  public boolean GetLEDNoButton(){
    if ( (GetSwitchBoardButton1() == false) && (GetSwitchBoardButton2() == false) && (GetSwitchBoardButton3() == false) && (GetSwitchBoardButton4() == false) && (GetSwitchBoardButton5() == false) && (GetSwitchBoardButton6() == false) && (GetSwitchBoardButton7() == false) && (GetSwitchBoardButton8() == false))
    {
      return true;
    }
    else
    {
      return false;
    }
  }


//----------------------------------------------------------------------------Hardware Map------------------------------------------------------------------------------------//

//Speed Controllers
public VictorSPX mIntakeHardware;
public VictorSPX mMrHuckHardware;

public TalonSRX mSpoolHardware;
public TalonSRX mIntakeRotaryHardware;

//Pneumatics
public Compressor mCompressorHardware;
public Solenoid mLeftShifterHardware;
public Solenoid mRightShifterHardware;

//Sensos
public ADXRS450_Gyro mGyro;	


void HardwareMap() {
    //test
    try
    {
        //Speed Controllers
        mIntakeHardware = new VictorSPX(Setup.kIntakeId);
        mMrHuckHardware = new VictorSPX(Setup.kMrHuckId);
        
        
        mIntakeRotaryHardware = new TalonSRX(Setup.kIntakeRotaryId);

        //Pneumatics
        mLeftShifterHardware = new Solenoid(Setup.kShifterSolenoidId);
        mRightShifterHardware = new Solenoid(Setup.kShifterSolenoidId);
        mCompressorHardware = new Compressor(0);

        //Sensors
        mGyro = new ADXRS450_Gyro();
        mGyro.calibrate();

    }
    catch(Exception e)
    {
        
    }
}
//-----------------------------------------------------------------------------------Not a Constant----------------------------------------------------------------------------------//
public boolean AutoRunning = false;
//-----------------------------------------------------------------------------------Constants----------------------------------------------------------------------------------//


//-subsystem speed motor speeds--//

//Intake
public static double kIntakeSpeed = 1;
public static double kIntakeReverseSpeed = -1;
public static double kSpoolSpeed = .25;


//-static port assignments-//

//CAN

//Drive Train
public static int kLeftFrontMotorId = 0;
public static int kLeftRearMotorId = 1;
public static int kRightFrontMotorId = 2;
public static int kRightRearMotorId = 3;

//Intake
public static int kIntakeId = 5;
public static int kIntakeRotaryId = 6;
public static int kMrHuckId = 7;
public static int kMrHuckJrId = 8;

//Delivery
public static int kDeliveryConveyorMotor = 9;


//Climber
public static int kClimber1Id = 11;
public static int kClimber2Id = 11;



//SOLENOIDS (0-7)
//Shifters
public static int kShifterSolenoidId = 1;


//ANALOG (0-4) 

//Lasers
// public static int kLeftAllignLaserId = 1;
// public static int kRightAllignLaserId = 2;

//DIO
public static int kElevatorBottomProx = 0;
public static int kElevatorTopProx = 1;
public static int kIntakeCargoLimit = 2;
public static int kIntakeHatchLimit = 3;
public static int kElevatorLaser = 4;
public static int kConveyorTopPhotoEye = 5;

public static int kLEDPower = 9;


//PWM
public static int kLEDPWMR = 0;
public static int kLEDPWMG = 1;
public static int kLEDPWMB = 2;
public static int kIntakeLeftSolenoidId = 3;
public static int kIntakeRightSolenoidId = 4;
public static int kClimberSolenoidId = 5;
public static int kConveyorClimberPhotoEye = 6;

}