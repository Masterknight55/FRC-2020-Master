package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Setup;


public class Climber extends Subsystem {
	
 
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
    	Nothing, Climbing, Falling, Hanging, Locked
    }
    
    public ClimberState getClimberStates(){
    	return mClimberState;
    }

    
    public void StopClimb()
    {
        mClimberState = ClimberState.Hanging;

        mClimberMotorSpeed = 0;

    }

    public void Climb(){

        mClimberState = ClimberState.Climbing;
        
        mClimberMotorSpeed = 1;

    }

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

    public void locked(){

        mClimberState = ClimberState.Locked;
        mClimberSolenoid = true;
    }

    public void unlocked(){

        mClimberSolenoid = false;

    }

    //Climber Move on Bar Code
    
    public void MoveLeft()
    {
        mClimberMoveSpeed = -1;

    }

    
    public void MoveRight()
    {
        mClimberMoveSpeed = 1;
        
    }

    public void DontMove()
    {
        mClimberMoveSpeed = 0;

    }

    //Stop
    @Override
    public void stop(){

    	mClimberState = ClimberState.Nothing;
        mClimberMotorSpeed = 0;
        mClimberSolenoid = true;
        
    }
   
    //Update
	@Override
	public void updateSubsystem() {
        
        mClimber1.set(ControlMode.PercentOutput,mClimberMotorSpeed);
        mClimber2.set(ControlMode.PercentOutput,mClimberMotorSpeed);
        mClimbeStopSolenoid.set(mClimberSolenoid);

        mClimberMove.set(ControlMode.PercentOutput, mClimberMoveSpeed);
		outputToSmartDashboard();
		
	}
	
	@Override
	public void outputToSmartDashboard() {
        SmartDashboard.putString("Climber State", mClimberState.toString());
	}


}
