package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Setup;


public class Climber extends Subsystem {
	
    static Climber mInstance = new Climber();

    public static Climber getInstance() {
    	return mInstance;
    }

    // Drive Motors
    VictorSPX mClimber1;
    VictorSPX mClimber2;

    //Movement
    VictorSPX mClimberMove;

    // stopping pneumatic
    Solenoid  mClimbeStopSolenoid;
    // Digital input
    DigitalInput mClimberBottomPhotoEye;
    DigitalInput mClimberTopPhotoEye;
    
    /**
     * This Method is a Constructor for the Motors and Sensors in the Climber Subsystem
     */
    public Climber(){
	
    	mClimber1 = new VictorSPX(Setup.kClimber1Id);
        mClimber1.setInverted(false);
        
        mClimber2 = new VictorSPX(Setup.kClimber2Id);
        mClimber2.setInverted(false);
        
        mClimbeStopSolenoid = new Solenoid(Setup.kClimberSolenoidId);

        mClimberBottomPhotoEye = new DigitalInput(Setup.kClimberPhotoEyeBottom);
        mClimberTopPhotoEye = new DigitalInput(Setup.kClimberPhotoEyeTop);

        mClimberMove = new VictorSPX(Setup.kClimberMoveMotor);

        }

    private ClimberState mClimberState;

    private boolean mClimberSolenoid;

    private double mClimberMotorSpeed;
    private double mClimberMoveSpeed;
    
    public enum ClimberState {
    	Climbing, Falling, Hanging, Locked
    }
    
    /**
     * This Method Gets the States of the Climber:
     * (Climbing, Falling, Hanging, or Locked) 
     * @return mClimberState
     */
    public ClimberState getClimberStates(){
    	return mClimberState;
    }

    /**
     * This Method Sets mClimberState = ClimberState.Hanging
     * and the Motor Speed to 0
     */
    public void StopClimb()
    {
        mClimberState = ClimberState.Hanging;

        mClimberMotorSpeed = 0;

    }
    /**
     * This Method Sets mClimberState = ClimberState.Climbing
     * and the Motor Speed to 1
     */
    public void Climb(){

        mClimberState = ClimberState.Climbing;
        
        mClimberMotorSpeed = 1;

    }
    /**
     * This Method Sets mClimberState = ClimberState.Falling.          
     * If mClimberBottomPhotoEye is False, the Motor Speed is Set to -1. Otherwise, the Climber is Stopped.
     */
    public void Fall(){
        
        mClimberState = ClimberState.Falling;
        
        if(mClimberBottomPhotoEye.get() == true)
        {
            mClimberMotorSpeed = 0;
            
        }
        else
        {
            mClimberMotorSpeed = -1;
        }
     
    }

    /**
     * This Method Sets mClimberState = ClimberState.Locked.
     * This Method also Engages mClimberSolenoid
     */
    public void locked(){

        mClimberState = ClimberState.Locked;
        mClimberSolenoid = true;
    }

    /**
     * This Method Disengages mClimberSolenoid
     */
    public void unlocked(){

        mClimberSolenoid = false;

    }

    /**
     * This Method Sets mClimberMoveSpeed = -1, Moving the Robot to the Left
     */
    public void MoveLeft()
    {
        mClimberMoveSpeed = -1;

    }

     /**
     * This Method Sets mClimberMoveSpeed = 1, Moving the Robot to the Right
     */
    public void MoveRight()
    {
        mClimberMoveSpeed = 1;
        
    }

    /**
     * This Method Sets mClimberMoveSpeed = 0, Stopping all Latteral Movement
     */
    public void DontMove()
    {
        mClimberMoveSpeed = 0;

    }

    /**
     * This Method Sets mClimberMotorSpeed = 0.
     * This Method also Engages mClimberSolenoid.
     */
    @Override
    public void stop(){

        // mClimberMotorSpeed = 0;
        // mClimberSolenoid = true;
        
    }
   
    /**
     * This Method Updates the Subsystem and Outputs the Updates to Smart Dashboard
     */
	@Override
	public void updateSubsystem() {
        
        mClimber1.set(ControlMode.PercentOutput,mClimberMotorSpeed);
        mClimber2.set(ControlMode.PercentOutput,mClimberMotorSpeed);
        mClimbeStopSolenoid.set(mClimberSolenoid);

        mClimberMove.set(ControlMode.PercentOutput, mClimberMoveSpeed);
		outputToSmartDashboard();
		
	}
	/**
     * This Method Formats the Climber State into a String Format to be Displayed on Smart Dashboard
     */
	@Override
	public void outputToSmartDashboard() {
        SmartDashboard.putString("Climber State", mClimberState.toString());
	}


}
