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

	Setup mSetup;
	
    private ColorSensor mColorSensor;
    private String currentColor;
    private String FMSColor;
    private TalonSRX mControlPanelWheel;
    private double mControlPanelWheelSpeed;
    private boolean mControlPanelExtenderBoolean;

    private Solenoid mControlPanelExtender;

    public ControlPanel()
    {
        mColorSensor = new ColorSensor();
        mControlPanelWheel = new TalonSRX(mSetup.kControlPanelWheelId);
        mControlPanelExtender = new Solenoid(mSetup.kControlPanelExtenderId);
    }


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

   public void TurnToColor(String Color)
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

   public void SetFMSColor(String fMString)
   {

    FMSColor = fMString; 

   }

   public void ExtendControlPanelController()
   {
     mControlPanelExtenderBoolean = true;
   }

   public void RetractControlPanelController()
   {
    mControlPanelExtenderBoolean = false;
   }
    
        
    @Override
    public void updateSubsystem() {
        // TODO Auto-generated method stub


        //Sets the Current Color Value
        currentColor = mColorSensor.getColor();

        //Set Motor Speeds and Solenoid Values
        mControlPanelWheel.set(ControlMode.PercentOutput, (mControlPanelWheelSpeed));
        mControlPanelExtender.set(mControlPanelExtenderBoolean);
    }

    @Override
    public void outputToSmartDashboard() {
        // TODO Auto-generated method stub
        SmartDashboard.putNumber("Control Panel Wheel Speed", mControlPanelWheelSpeed);
        SmartDashboard.putBoolean("Control Panel Extended", mControlPanelExtenderBoolean);
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub
        mControlPanelWheel.set(ControlMode.PercentOutput, (0));
    }

}