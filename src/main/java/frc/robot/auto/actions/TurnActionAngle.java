package frc.robot.auto.actions;

//import edu.wpi.first.wpilibj.ADXRS450_Gyro; //FRC gyroscope library
import com.kauailabs.navx.frc.AHRS;//NavX Library

import frc.robot.subsystems.Drivetrain;

public class TurnActionAngle implements Action {

	private Drivetrain mDrivetrain = Drivetrain.getInstance();
	//private ADXRS450_Gyro mGyro = Setup.getInstance().mGyro; //FRC gyroscope
	private AHRS mGyro = new AHRS(); //NavX
	
	private double mAngleSetpoint;
	private double mAngleCorrectionSpeed;
	
	private int mErrorCounts = 0;
	private int mRequiredErrorCounts = 50;
	
	private double mRotationalMaxSpeed = .65;
	private double mRotationalMinSpeed = .35;
	private double mRotationalDeadzone = 1.5;
	
    public TurnActionAngle(double angle) {
		//mAngleSetpoint = mGyro.getAngle() + angle; //FRC gyroscope
		mAngleSetpoint = mGyro.getYaw() + angle; //NavX
    }
    
    public TurnActionAngle(double angle, double rotationalDeadzone) {
		//mAngleSetpoint = mGyro.getAngle() + angle; //FRC gyroscope
		mAngleSetpoint = mGyro.getYaw() + angle; //NavX
        mRotationalDeadzone = rotationalDeadzone;
    }
     
    public TurnActionAngle(double angle, int requiredErrorCounts) {
		//mAngleSetpoint = mGyro.getAngle() + angle; //FRC gyroscope
		mAngleSetpoint = mGyro.getYaw() + angle; //NavX
        mRequiredErrorCounts = requiredErrorCounts;
    }
    
    public TurnActionAngle(double angle, double RotationalMaxSpeed, double RotationalMinSpeed, double RotationalDeadzone) {
		//mAngleSetpoint = mGyro.getAngle() + angle; //FRC gyroscope
		mAngleSetpoint = mGyro.getYaw() + angle; //NavX
        
        mRotationalMaxSpeed = RotationalMaxSpeed;
    	mRotationalMinSpeed = RotationalMinSpeed;
    	mRotationalDeadzone = RotationalDeadzone;
    }
    
    @Override
	public void start() {
		mDrivetrain.lowGear();
	}
    
    @Override
	public void update() {
    	calcGyroSpeed();
		mDrivetrain.setTankDriveSpeed(mAngleCorrectionSpeed, -mAngleCorrectionSpeed,1);
		//System.out.println("error = " + calcGyroError() + " deadzone is " + mRotationalDeadzone + " correction speed = " + mAngleCorrectionSpeed );
		//System.out.println(mGyro.getAngle()); //FRC gyroscope
		System.out.println(mGyro.getYaw()); //NavX
	}
    
    @Override
	public boolean isFinished() {
		if (isGyroOnTarget()) {
			return true;
		}
		return false;
	}
    
	@Override
	public void done() {
		mDrivetrain.setTankDriveSpeed(0, 0,1);
		System.out.println("finished with turn (angle) action");
	}
	
	private double calcGyroError() {
		//return mAngleSetpoint - mGyro.getAngle(); //FRC gyroscope
		return mAngleSetpoint - mGyro.getYaw(); //NavX
	}
	
	private void calcGyroSpeed() {
		mAngleCorrectionSpeed = calcGyroError() * .175;
		if (mAngleCorrectionSpeed < mRotationalMinSpeed && mAngleCorrectionSpeed > 0 ) {
			mAngleCorrectionSpeed = mRotationalMinSpeed;
		} else if (mAngleCorrectionSpeed > -mRotationalMinSpeed && mAngleCorrectionSpeed < 0 ) {
			mAngleCorrectionSpeed = -mRotationalMinSpeed;
		}
		
		if (mAngleCorrectionSpeed > mRotationalMaxSpeed ) {
			mAngleCorrectionSpeed = mRotationalMaxSpeed;
		} else if (mAngleCorrectionSpeed < -mRotationalMaxSpeed ) {
			mAngleCorrectionSpeed = -mRotationalMaxSpeed;
		}
	}
	
	private boolean isGyroOnTarget() {
		if( Math.abs(calcGyroError()) < mRotationalDeadzone) {
			mErrorCounts++;
		} else {
			mErrorCounts = 0;
		}
		if(mErrorCounts >= mRequiredErrorCounts) {
			return true;
		} else {
			return false;
		}
	}
}