package frc.robot.subsystems;
import frc.robot.Setup;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Delivery extends Subsystem
{

    static Delivery mInstance = new Delivery();

    public static Delivery getInstance() {
    	return mInstance;
    }

	Setup mSetup;
	TalonSRX mConveyor;
    DigitalInput mConveyorPhotoEye1;
    DigitalInput mConveyorPhotoEye2;
    DigitalInput mConveyorPhotoEye3;
    DigitalInput mConveyorPhotoEye4;

public Delivery() {
		
	mSetup = new Setup();

    mConveyor = new TalonSRX(Setup.kDeliveryConveyorMotor);
    mConveyor.setInverted(false);
    mConveyor.set(ControlMode.PercentOutput, 0);

    mConveyorPhotoEye1 = new DigitalInput(Setup.kConveyorPhotoEye1);	
    mConveyorPhotoEye2 = new DigitalInput(Setup.kConveyorPhotoEye2);
    mConveyorPhotoEye3 = new DigitalInput(Setup.kConveyorPhotoEye3);
    mConveyorPhotoEye4 = new DigitalInput(Setup.kConveyorPhotoEye4);
    //System.out.println("Intake Done Initializing.");
}
    

    public double ConveryorSpeed = 0.0;

    /**
     * @param speed This is the speed of the conveyor
     */
    public void SetConveryorSpeed(double speed)
    {
        ConveryorSpeed = speed; 
    }

    
    /**
     * This method returns the highest photoeye sensor to sense a ball.
     * This method is used by the PushBall method to make sure the conveyor
     * doesn't overflow
     */
    public int getPhotoeyeNumber(){
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

    /**
     * The PushBall method is used to make room for the next
     * incoming ball until the last photosensor is activated
     */
    public void PushBall()
    {
        if(!mConveyorPhotoEye4.get())
        {
            SetConveryorSpeed(.25);
            int moveTo = getPhotoeyeNumber() + 1;
            if(moveTo == getPhotoeyeNumber()){
               SetConveryorSpeed(0);
           }
            
        }
    }

    public void Deliver()
    {
        SetConveryorSpeed(.25);
    }

    public void Swallow()
    {
        SetConveryorSpeed(-.25);
    }



//--------------------------------------------------------------------------------Important Robot Stuff ----------------------------------------------------------------------------------//
		
	@Override
	public void updateSubsystem()
	{
        mConveyor.set(ControlMode.PercentOutput, ConveryorSpeed);
		outputToSmartDashboard();
	}


	@Override
	public void outputToSmartDashboard() {

        SmartDashboard.putNumber("Delivery Conveyor Speed", ConveryorSpeed);
	}

	@Override
	public void stop(){
		
        //System.out.println("Stopping Intake");
        mConveyor.set(ControlMode.PercentOutput,0);
	}

}