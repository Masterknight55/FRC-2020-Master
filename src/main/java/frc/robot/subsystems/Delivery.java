package frc.robot.subsystems;
import frc.robot.Setup;

import java.util.Set;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Delivery extends Subsystem
{

    static Delivery mInstance = new Delivery();
    /**
     * Gets an instance of the delivery for the auto action
     * @return mInstance
     */
    public static Delivery getInstance() {
    	return mInstance;
    }

	Setup mSetup;
	TalonSRX mConveyor;
    DigitalInput mConveyorPhotoEye1;
    DigitalInput mConveyorPhotoEye2;
    DigitalInput mConveyorPhotoEye3;
    DigitalInput mConveyorPhotoEye4;

    Solenoid mDeliveryArm;


    public double SPEED = 0.75;


public Delivery() {
		
	mSetup = new Setup();

    mConveyor = new TalonSRX(7);
    mConveyor.setInverted(false);
    //mConveyor.set(ControlMode.PercentOutput, 0);

    mConveyorPhotoEye1 = new DigitalInput(Setup.kConveyorPhotoEye1);	
    mConveyorPhotoEye2 = new DigitalInput(Setup.kConveyorPhotoEye2);
    mConveyorPhotoEye3 = new DigitalInput(Setup.kConveyorPhotoEye3);
    mConveyorPhotoEye4 = new DigitalInput(Setup.kConveyorPhotoEye4);

    mDeliveryArm = new Solenoid(Setup.kConveyorLift);

    //System.out.println("Intake Done Initializing.");
}
    

    public double ConveryorSpeed = 0.0;
    boolean DeliveryArm; 

    /**
     * @param speed This is the speed of the conveyor
     */
    public void SetConveryorSpeed(double speed)
    {
        ConveryorSpeed = speed; 
    }

    public void AssumeThePostition()
    {
        DeliveryArm = true;
    }

    public void BrechPremitier()
    {
        DeliveryArm = false;
    }

    /**
     * This method returns the highest photoeye sensor to sense a ball.
     * This method is used by the PushBall method to make sure the conveyor
     * doesn't overflow
     */
    public int getMaxPhotoeyeNumber(){
        int num;

        if(mConveyorPhotoEye4.get()){
            num = 4;
        }
        else if (mConveyorPhotoEye3.get()) {
            num = 3;
        }
        else if(mConveyorPhotoEye2.get()){
            num = 2;
        }
        else if(mConveyorPhotoEye1.get()){
            num = 1;
        }
        else{
            num = 0;
        }
        return num;
    }

    public boolean getPhotoeyeBasedOffNumber(int num)
    {
        if(num == 1)
        {
            return mConveyorPhotoEye1.get(); 
        }
        else if(num == 2)
        {
            return mConveyorPhotoEye2.get(); 
        }
        else if(num == 3)
        {
            return mConveyorPhotoEye3.get(); 
        }
        else if(num == 4)
        {
            return mConveyorPhotoEye4.get(); 
        }
        else
        {
            return false;
        }
    }

    public void AutoMove()
    {

        if (!mConveyorPhotoEye4.get())
        {
            if(mConveyorPhotoEye1.get())
            {
                SetConveryorSpeed(1);
            }
            else
            {
                SetConveryorSpeed(0);
            }
        }
        else
        {
            SetConveryorSpeed(0);
        }
        
       
    }

    


    public void Deliver()
    {
        //mConveyor.set(ControlMode.PercentOutput, SPEED);
        SetConveryorSpeed(SPEED);
    }

    public void StopDelivering()
    {
        SetConveryorSpeed(0);
    }

    public void Swallow()
    {
        SetConveryorSpeed(-SPEED);
    }



//--------------------------------------------------------------------------------Important Robot Stuff ----------------------------------------------------------------------------------//
		
	@Override
	public void updateSubsystem()
	{
        mDeliveryArm.set(DeliveryArm);
        mConveyor.set(ControlMode.PercentOutput, ConveryorSpeed);
        outputToSmartDashboard();
        
	}


	@Override
	public void outputToSmartDashboard() {
        //SmartDashboard.putNumber("Convenyor SPEED", mConveyor.getMotorOutputPercent());
        SmartDashboard.putNumber("Delivery Conveyor Speed", ConveryorSpeed);
        SmartDashboard.putBoolean("Delivery Photo Eye 1", mConveyorPhotoEye1.get());
        SmartDashboard.putBoolean("Delivery Photo Eye 2", mConveyorPhotoEye2.get());
        SmartDashboard.putBoolean("Delivery Photo Eye 3", mConveyorPhotoEye3.get());
        SmartDashboard.putBoolean("Delivery Photo Eye 4", mConveyorPhotoEye4.get());

	}

	@Override
	public void stop(){
		
        //System.out.println("Stopping Intake");
        mConveyor.set(ControlMode.PercentOutput, 0);
	}

}