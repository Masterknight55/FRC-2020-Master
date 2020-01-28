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
	public DigitalInput mConveyorTop;

public Delivery() {
		
	mSetup = new Setup();

    mConveyor= new VictorSPX(Setup.kDeliveryConveyorMotor);
    mConveyor.setInverted(false);
    mConveyor.set(ControlMode.PercentOutput, 0);
		
    mConveyorTop = new DigitalInput(Setup.kConveyorTopPhotoEye);
        
		//System.out.println("Intake Done Initializing.");
    }

    public double ConveryorSpeed = 0.0;

    public void SetConveryorSpeed(double speed)
    {
        ConveryorSpeed = speed; 
    }

    public void PushBallToTop()
    {
        if(mConveyorTop.get() == false)
        {
            SetConveryorSpeed(.25);
        }
        else
        {
            SetConveryorSpeed(0);
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