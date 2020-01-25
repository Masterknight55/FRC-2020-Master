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
    // stopping pneumatic
    Solenoid  mClimbeStopSolenoid;
    // Digital input
    DigitalInput mClimberBottom;
    
    public Climber(){
	
    	mClimber1 = new VictorSPX(Setup.kClimber1Id);
        mClimber1.setInverted(false);
        
        mClimber2 = new VictorSPX(Setup.kClimber2Id);
        mClimber2.setInverted(false);
        
        mClimbeStopSolenoid = new Solenoid(Setup.kClimberSolenoidId);

        mClimberBottom = new DigitalInput(Setup.kConveyorClimberPhotoEye);

        }

    private ClimberState mClimberState;
    private boolean mClimerSolenoid;
    private double mClimber1MotorSpeed;
    private double mClimber2MotorSpeed;
    
    public enum ClimberState {
    	Nothing, Climbing, Falling, Hanging
    }
    
    public ClimberState getClimberStates(){
    	return mClimberState;
    }

    public void Climb(){

        mClimberState = ClimberState.Climbing;
        
        mClimber1MotorSpeed = 1;
        mClimber2MotorSpeed = 1;

    }

    public void Fall(){
        
        mClimberState = ClimberState.Falling;
        
        if(mClimberBottom.get() == true)
        {
            mClimber1MotorSpeed = 0;
            mClimber2MotorSpeed = 0;
        }
        else
        {
            mClimber1MotorSpeed = -1;
            mClimber2MotorSpeed = -1;
        }
     
    }

    public void locked(){

        mClimberState = ClimberState.Hanging;

        mClimerSolenoid = true;
    }

    public void unlocked(){

        mClimerSolenoid = false;

    }

    //Stop
    @Override
    public void stop(){

    	mClimberState = ClimberState.Nothing;
        
        mClimber1MotorSpeed = 0;
        mClimber2MotorSpeed = 0;
        
    }
   
    //Update
	@Override
	public void updateSubsystem() {
        
        mClimber1.set(ControlMode.PercentOutput,mClimber1MotorSpeed);
        mClimber2.set(ControlMode.PercentOutput,mClimber2MotorSpeed);
        mClimbeStopSolenoid.set(mClimerSolenoid);

		outputToSmartDashboard();
		
	}
	
	@Override
	public void outputToSmartDashboard() {
        SmartDashboard.putString("Climber State", mClimberState.toString());
	}


}
