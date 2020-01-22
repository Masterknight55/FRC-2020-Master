package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Setup;


public class Climber extends Subsystem {

    static Climber mInstance = new Climber();

    public static Climber getInstance() {
        return mInstance;
	}
	
    //Drive Motors
    VictorSPX mClimber1;
    VictorSPX mClimber2;
    VictorSPX mClimber3;
    
    public Climber(){
	
    	mClimber1 = new VictorSPX(Setup.kClimber1Id);
        mClimber1.setInverted(false);
        
        mClimber2 = new VictorSPX(Setup.kClimber2Id);
        mClimber2.setInverted(false);
        
      
		
		}
    
    private ClimberState mClimberState;
    
    public enum ClimberState {
    	Nothing, Climbing, Falling
    }
    
    public ClimberState getClimberStates(){
    	return mClimberState;
    }

    public void Climb(){

        mClimberState = ClimberState.Climbing;
        
        mClimber1.set(ControlMode.PercentOutput, 1);
        mClimber2.set(ControlMode.PercentOutput, 1);
        
    	
    }

    public void Fall(){
        
        mClimberState = ClimberState.Falling;
        
        mClimber1.set(ControlMode.PercentOutput, -1);
        mClimber2.set(ControlMode.PercentOutput, -1);
        
    }
    

    //Stop
    @Override
    public void stop(){

    	mClimberState = ClimberState.Nothing;
        
        mClimber1.set(ControlMode.PercentOutput, 0);
        mClimber2.set(ControlMode.PercentOutput, 0);
        
    }
    
    //Update
	@Override
	public void updateSubsystem() {
        
		outputToSmartDashboard();
		
	}
	
	@Override
	public void outputToSmartDashboard() {
		SmartDashboard.putString("Climber State", mClimberState.toString());
	}


}
