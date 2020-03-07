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
      
        if(mDriverStick.getRawAxis(2) > 0.5)
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
      if(mDriverStick.getRawAxis(3) > 0.5)
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

    public boolean getDriverBbutton()
    {
      return mDriverStick.getRawButton(2);
    }

    public boolean getDriverYbutton()
    {
      return mDriverStick.getRawButton(4);
    }

    public boolean getDriverXButton()
    {
      return mDriverStick.getRawButton(3);
    }
    public boolean getDriverAButton()
    {
      return mDriverStick.getRawButton(1);
    }

    public boolean getDriverLeftStickButton(){
      return mDriverStick.getRawButton(9);
    }

    public boolean getDriverRightStickButton(){
      return mDriverStick.getRawButton(10);
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

    public boolean getSecondaryDriverLtBoolean()
    {
      if(mSecondaryDriverStick.getRawAxis(2) > 0.5)
        {
          return true;
       }
        else
        {
          return false;
        }
    }

    public boolean getSecondaryDriverRtBoolean()
    {
      if(mSecondaryDriverStick.getRawAxis(3) > 0.5)
        {
          return true;
       }
        else
        {
          return false;
        }
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

    
    public double getSecondaryDriverLt()
    {
      return mSecondaryDriverStick.getRawAxis(2);
    }

    public double getSecondaryDriverRt()
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
public static boolean IntakeAutoMode = false;
//-----------------------------------------------------------------------------------Constants----------------------------------------------------------------------------------//

//-static port assignments-//

//CAN

//Drive Train
public static int kLeftFrontMotorId = 1;
public static int kLeftRearMotorId = 2;
public static int kRightFrontMotorId = 3;
public static int kRightRearMotorId = 4;

//Intake
public static int kIntakeId = 5;
public static int kIntakeId2 = 11;

//Control Panel
public static int kControlPanelWheelId = 6;

//Delivery
public static int kDeliveryConveyorMotor = 7;

//Climber
public static int kClimber1Id = 8;
public static int kClimber2Id = 9;
public static int kClimberMoveMotor = 10;


//SOLENOIDS (0-7) 


//Shifters
public static int kShifterSolenoidId = 0;

//SubSystems
public static int kIntakeLeftSolenoidId = 1;
public static int kIntakeRightSolenoidId = 2;
public static int kClimberSolenoidId = 3;
public static int kControlPanelExtenderId = 4;
public static int kConveyorLift = 5;



//ANALOG (0-4) 




//DIO
public static int kConveyorPhotoEye1 = 1;
public static int kConveyorPhotoEye2 = 2;
public static int kConveyorPhotoEye3 = 3;
public static int kConveyorPhotoEye4 = 4;

public static int kClimberPhotoEyeBottom = 6;
public static int kClimberPhotoEyeTop = 5;

public static int kLEDPower = 9;


//PWM
public static int kLEDPWMR = 8;
public static int kLEDPWMG = 7;
public static int kLEDPWMB = 9;



}