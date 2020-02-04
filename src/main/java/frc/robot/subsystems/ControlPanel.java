package frc.robot.subsystems;

import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Setup;
import frc.robot.subsystems.ColorSensor;



public class ControlPanel extends Subsystem
{

    static ControlPanel mInstance = new ControlPanel();

    public static ControlPanel getInstance() {
    	return mInstance;
    }

	
    private ColorSensor mColorSensor;
    private String currentColor;
    private String FMSColor;
    private TalonSRX mControlPanelWheel;
    private double mControlPanelWheelSpeed;

    private boolean mControlPanelExtenderBoolean;
    Setup mSetup;
    

    private Solenoid mControlPanelExtender;

    public ControlPanel()
    {
        mSetup = new Setup();
        mColorSensor = new ColorSensor();

        mControlPanelWheel = new TalonSRX(Setup.kControlPanelWheelId);
        mControlPanelExtender = new Solenoid(Setup.kControlPanelExtenderId);
    }

/**
   * This method turns the wheel a certain amount of times. This is accomplished by creating a array of colors and adding new colors to it. 
   * It then compares the int times to the length of the array and stops turning it when the array reaches the times * 8
   * @param times The wheel will turn until the array is greater than times times 8. 
   */
    public void TurnThisManyTimes(int times)
    {

        ArrayList<String> colorTable = new ArrayList<String>();
        
        colorTable.add(currentColor);


        if(! (colorTable.get(colorTable.size()).equals(currentColor)))
        {

            //This will add the color if it is not the last seen color by the sensor
            colorTable.add(currentColor);
        } 
        else
        {

            //If it was the last seen color by the senor then we will turn the wheel
            //Checks to see if the array is too big meaning that we have turned it the correct amount of times
            if(colorTable.size() < times * 8)
            {
                mControlPanelWheelSpeed = .25;
            }
            else
            {
                mControlPanelWheelSpeed = 0;
            }
        }
    }

/**
   * This method turns the wheel to the FMS Color which is set through the Set FMSColor Method. 
   */
   public void TurnToFMSColor()
   {

    if(currentColor == FMSColor)
    {
        mControlPanelWheelSpeed = 0;
    }
    else
    {
        mControlPanelWheelSpeed = .25;
    }

   }

   
/**
   * This method is called in Tele-op Periodic to update the FMS Color. 
   * @param fmsString This String is set by the Set FMS Color method which is called Tele-Op Periodic.
   */
   public void SetFMSColor(String fMString)
   {
    FMSColor = fMString; 
   }


 /**
   * This Extends The Control Panel Controller by setting the mControlPanelExtenderBoolean to true
   */
   public void ExtendControlPanelController()
   {
     mControlPanelExtenderBoolean = true;
   }
/**
   * This Retracts The Control Panel Controller by setting the mControlPanelExtenderBoolean to false
   */
   public void RetractControlPanelController()
   {
    mControlPanelExtenderBoolean = false;
   }
    
        

   /**
   * This will update the Current Color from the color sensor, the wheel speed, and the extender value 
   * */
    @Override
    public void updateSubsystem() {
    
        //Sets the Current Color Value
        currentColor = mColorSensor.getColor();

        //Set Motor Speeds and Solenoid Values
        mControlPanelWheel.set(ControlMode.PercentOutput, (mControlPanelWheelSpeed));
        mControlPanelExtender.set(mControlPanelExtenderBoolean);
    }

    /**
   * Outputs the Control Panel Wheel Speed and Control Panel Exendor Status 
   */
    @Override
    public void outputToSmartDashboard() {
        
        SmartDashboard.putNumber("Control Panel Wheel Speed", mControlPanelWheelSpeed);
        SmartDashboard.putBoolean("Control Panel Extended", mControlPanelExtenderBoolean);

    }
/**
   * Sets the Wheel to 0 for percent output
   */
    @Override
    public void stop() {
        
        mControlPanelWheel.set(ControlMode.PercentOutput, (0));
    }

}