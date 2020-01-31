package frc.robot.subsystems;
import frc.robot.Setup;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Delivery extends Subsystem
{

    static Delivery mInstance = new Delivery();

    public static Delivery getInstance() {
    	return mInstance;
    }

	Setup mSetup;
	private VictorSPX mConveyor;
    public DigitalInput mConveyorPhotoEye1;
    public DigitalInput mConveyorPhotoEye2;
    public DigitalInput mConveyorPhotoEye3;
    public DigitalInput mConveyorPhotoEye4;

public Delivery() {
		
	mSetup = new Setup();

    mConveyor= new VictorSPX(Setup.kDeliveryConveyorMotor);
    mConveyor.setInverted(false);
    mConveyor.set(ControlMode.PercentOutput, 0);

    mConveyorPhotoEye1 = new DigitalInput(Setup.kConveyorPhotoEye1);	
    mConveyorPhotoEye2 = new DigitalInput(Setup.kConveyorPhotoEye2);
    mConveyorPhotoEye3 = new DigitalInput(Setup.kConveyorPhotoEye3);
    mConveyorPhotoEye4 = new DigitalInput(Setup.kConveyorPhotoEye4);


   
    
        
		//System.out.println("Intake Done Initializing.");
    }
    

    public double ConveryorSpeed = 0.0;

    public void SetConveryorSpeed(double speed)
    {
        ConveryorSpeed = speed; 
    }

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
        SetConveryorSpeed(1);
    }

    public void Swallow()
    {
        SetConveryorSpeed(-1);
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
	}

}