 package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.w3c.dom.ls.LSException;

import com.revrobotics.CANEncoder;
import java.lang.Math;

import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.Compressor;
//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.MotionProfile;
import frc.robot.S_CurveMotionProfile;
import edu.wpi.first.wpilibj.PIDSourceType;
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
	private PixyCam pixyUpdater = PixyCam.getInstance(0);
	
	//Motion Profile
	MotionProfile mDriveTrainMotionProfile;

	//Encoders
	public CANEncoder mLeftEncoder;//This encoder is connected to the port of the motor controller of the front Left motor.
	public CANEncoder mRightEncoder;//This encoder is connected to the port of the motor controller of the front Right motor.

	//Encoder Variables
	final double radius = 3;//Wheel Radius (I don't know what unit they are in, but I don't think it matters. If I had to guess I think it's probabaly inches.)
	double LeftEncoderSubtract = 0, RightEncoderSubtract = 0;//These are the numbers to subtract from the encoders when they are reset.

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

		//Encoders
		mLeftEncoder = new CANEncoder(mLeftFrontDrive);//Left Encoder
		mRightEncoder = new CANEncoder(mRightFrontDrive);//Right Encoder

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
	public void setTankDriveSpeed(double left, double right){
    	
		mLeftSpeed = left;
		mRightSpeed = -right;
		
	}


	/**
	 * This funciton will align the robot on the pixy cam's trained object
	 * If it sees no object, it will remain stationary
	 * @param pixy This pixy will be the pixy that you want to use to target the object
	 * @param scale This it the Cosine Motion Profile Scale. It is multiplied by the input to make the graph steeper.
	 */

	private int goalPixyDelay = 0;
	public boolean goalPixyDisable = false;
	public boolean hasSeen = false;
	public boolean isFinished = false;
	public double zeroAgo = 0;
	public double oneAgo = 0;
	public double twoAgo = 0;
	public double reading = 0;
	public int chill = 0;
	public void autoAlign(PixyCam pixy, double scale)
	{
		setLowGear();
		if((pixy.blockDetected() && pixy.analogPortNumber() == 0) && !goalPixyDisable)
		{
			hasSeen = true;
			goalPixyDelay = 60;

			reading = ((twoAgo + oneAgo + zeroAgo) / 3);
			if(Math.abs(twoAgo - zeroAgo) > .2)
			{
					goalPixyDisable = true;
			}
			if(reading < 0.1)
			{
				mLeftSpeed = -reading * .5 + 0.2;
				mRightSpeed = reading * .5 + 0.2;
			}
			else
			{
				mLeftSpeed = -reading;
				mRightSpeed = reading;
			}
		}
		else if((pixy.blockDetected() && pixy.analogPortNumber() == 1) && !goalPixyDisable)
		{
			goalPixyDelay = 12;

			mLeftSpeed = -pixy.value();
			mRightSpeed = pixy.value();
			if(pixy.inDeadzone())
			{
				mLeftSpeed = -mLeftSpeed * 0.5 - 0.8;
				mRightSpeed = mRightSpeed * 0.5 - 0.8;
			}
		}
		else if(goalPixyDelay > 0 && pixy.analogPortNumber() == 0 && hasSeen)
		{
			goalPixyDisable = true;
			goalPixyDelay--;
			mLeftSpeed = 0.2;
			mRightSpeed = 0.2;
			if(goalPixyDelay == 0)
			{
				goalPixyDisable = false;
			}
		}
		else if(goalPixyDelay > 0 && pixy.analogPortNumber() == 1)
		{
			goalPixyDisable = true;
			goalPixyDelay--;
			mLeftSpeed = -0.4;
			mRightSpeed = -0.4;
		}
		else
		{
			isFinished = true;
			mLeftSpeed = 0;
			mRightSpeed = 0;
		}
	}

	public void chaseBall(PixyCam pixy, double scale)
	{
		if(pixy.blockDetected() && !pixy.inDeadzone())
		{
			mLeftSpeed += -BasicCosineMotionProfile(pixy.value(), scale) + .5;
			mRightSpeed += BasicCosineMotionProfile(pixy.value(), scale) + .5;
		}
		else if(pixy.blockDetected() && pixy.inBigDeadzone())
		{
			mLeftSpeed += -BasicCosineMotionProfile(pixy.value(), scale) + 1;
			mRightSpeed += BasicCosineMotionProfile(pixy.value(), scale) + 1;
		}
		else
		{
			mLeftSpeed += 0;
			mRightSpeed += -0;
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

	public void resetEncoders(){
		LeftEncoderSubtract = mLeftEncoder.getPosition();
		RightEncoderSubtract = mRightEncoder.getPosition();
	}

	public double getDistance(){
    	return (((mLeftEncoder.getPosition()-LeftEncoderSubtract)*(radius*2))+((mRightEncoder.getPosition()-RightEncoderSubtract)*(radius*2)))/2;
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

		twoAgo = oneAgo;
		oneAgo = zeroAgo; 
		zeroAgo = pixyUpdater.value();

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

		SmartDashboard.putNumber("DriveTrain_LeftMotorSpeeds", mLeftSpeed * 100);
		SmartDashboard.putNumber("DriveTrain_RightMotorSpeeds", mRightSpeed * 100);
		SmartDashboard.putNumber("DriveTrain_MoveValue", mMoveSpeed);
		SmartDashboard.putNumber("DriveTrain_RotateValue", mRotateSpeed);

		SmartDashboard.putString("Drive_Gear", mDriveGear.toString());
		SmartDashboard.putNumber("GoalPixyDelay", goalPixyDelay);
		SmartDashboard.putBoolean("GoalPixyDisable", goalPixyDisable);
		SmartDashboard.putNumber("ReadingPixy", reading);
		SmartDashboard.putNumber("OneAgoPixy", oneAgo);
		SmartDashboard.putNumber("TwoAgoPixy", twoAgo);
	}


}
