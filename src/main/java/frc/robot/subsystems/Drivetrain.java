package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import java.lang.Math;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.MotionProfile;
import frc.robot.Setup;


public class Drivetrain extends Subsystem {

    static Drivetrain mInstance = new Drivetrain();

    public static Drivetrain getInstance() {
        return mInstance;
	}
	
	
    //Shifter
    Solenoid mSolenoid;
   
    //Drive Motors
    CANSparkMax mRightFrontDrive;
    CANSparkMax mRightRearDrive;
    CANSparkMax mLeftFrontDrive;
	CANSparkMax mLeftRearDrive;
	
	//Motion Profile
	MotionProfile mDriveTrainMotionProfile;

    public Drivetrain(){
	
		mSolenoid = new Solenoid(Setup.kShifterSolenoidId);
		
    	mLeftFrontDrive = new CANSparkMax(Setup.kRightFrontMotorId, MotorType.kBrushless);
    	mLeftFrontDrive.set(0);
		mLeftFrontDrive.setInverted(true);
		//mLeftFrontDrive.setRampRate(.2);
    	
        mRightFrontDrive = new CANSparkMax(Setup.kLeftFrontMotorId, MotorType.kBrushless);
    	mRightFrontDrive.set(0);
		mRightFrontDrive.setInverted(false);
		//mRightFrontDrive.setRampRate(.2);
    	
    	mLeftRearDrive = new CANSparkMax(Setup.kRightRearMotorId, MotorType.kBrushless);
    	mLeftRearDrive.set(0);
    	mLeftRearDrive.setInverted(true);
		//mLeftRearDrive.setRampRate(.2);
		
    	mRightRearDrive = new CANSparkMax(Setup.kLeftRearMotorId, MotorType.kBrushless);
    	mRightRearDrive.set(0);
		mRightRearDrive.setInverted(false);
		//mRightRearDrive.setRampRate(.2);
    
		}
    
    private DriveGear mDriveGear;
    private double mLeftSpeed;
    private double mRightSpeed;
    private double mMoveSpeed;
    private double mRotateSpeed;
    
    public enum DriveGear {
    	LOW, HIGH
    }
    
    public DriveGear getDriveGear(){
    	return mDriveGear;
    }
	

	/**
   * This Method sets the Tank Drive Speed for the Left and Right
   * This Method also uses a Baic Cosine Motion Profile that's Scale can be set 
   * @param left This is the Left Speed
   * @param right This is the Right Speed
   * @param scale This is the Cosine Motion Profile Scale 
   */
    public void setTankDriveSpeed(double left, double right, double scale){
    	
		mLeftSpeed = BasicCosineMotionProfile(left, scale);
		mRightSpeed = -BasicCosineMotionProfile(right, scale);
		
	}


	/**
	 * This funciton will align the robot on the pixy cam's trained object
	 * If it sees no object, it will remain stationary
	 * @param pixy This pixy will be the pixy that you want to use to target the object
	 * @param scale This it the Cosine Motion Profile Scale. It is multiplied by the input to make the graph steeper.
	 */
	public void autoAlign(PixyCam pixy, double scale)
	{
		if(pixy.blockDetected() && !pixy.inDeadzone())
		{
			mLeftSpeed += -BasicCosineMotionProfile(pixy.value(), scale);
			mRightSpeed += BasicCosineMotionProfile(pixy.value(), scale);
		}
		else
		{
			mLeftSpeed += 0;
			mRightSpeed += 0;
		}
	}

	/**
   * This double returns values based on a Cosine Motion Profile graph.
   * Here is the equation: (2 * (Math.cos(input * scale + Math.PI) + 1)
   * @param input This is x value put into the equation. For example with a drive train it would be the analog input.
   * @param scale This it the Cosine Motion Profile Scale. It is multiplied by the input to make the graph steeper. 
   * @return This will return the y value of the equation
   */
	
	public double BasicCosineMotionProfile(double input, double scale)
    {
        if(input < 0)
			{
				return -1 * (2 * (Math.cos(input * scale + Math.PI) + 1));
			}
			if (input > 0)
			{
				return 2 * (Math.cos(input * scale + Math.PI) + 1);	
			}
			else
			{
				return 0;
			}
     
	}
	


    

    //Stop
    @Override
    public void stop(){
		
    	mLeftFrontDrive.set(0);
    	mLeftRearDrive.set(0);
    	mRightFrontDrive.set(0);
    	mRightRearDrive.set(0);
    }
    

    //Gears
    private void setLowGear() {
    	
    	mSolenoid.set(false);
    	
    }
	
    private void setHighGear() {

    	mSolenoid.set(true);
    	
    }
    
    
    //Update
	@Override
	public void updateSubsystem() {

		mLeftFrontDrive.set(mLeftSpeed);
    	mLeftRearDrive.set(mLeftSpeed);
    	mRightFrontDrive.set(mRightSpeed);
    	mRightRearDrive.set(mRightSpeed);
        
		switch(mDriveGear){
		case HIGH:
			setHighGear();
			break;
		case LOW:
			setLowGear();
			break;
		default:
			highGear();
			break;
		}
		
		outputToSmartDashboard();
		
	}
	
	public void highGear(){
		mDriveGear = DriveGear.HIGH;
	}
	
	public void lowGear(){
		mDriveGear = DriveGear.LOW;
	}

	@Override
	public void outputToSmartDashboard() {

		SmartDashboard.putNumber("DriveTrain_LeftMotorSpeeds", mLeftSpeed);
		SmartDashboard.putNumber("DriveTrain_RightMotorSpeeds", mRightSpeed);
		SmartDashboard.putNumber("DriveTrain_MoveValue", mMoveSpeed);
		SmartDashboard.putNumber("DriveTrain_RotateValue", mRotateSpeed);

		SmartDashboard.putString("Drive_Gear", mDriveGear.toString());
	}


}
