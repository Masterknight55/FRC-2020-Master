import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Solenoid;

public class Intake extends Subsystem
{
    private VictorSPX mIntake;
	private Solenoid mIntakeArmLeftSolenoid;
	private Solenoid mIntakeArmRightSolenoid;

 public Intake() 
 {
     

	mIntake = new VictorSPX(Constants.kIntakeId);
	mIntake.setInverted(false);

   	mIntakeArmLeftSolenoid = new Solenoid(Constants.kIntakeLeftSolenoidId);
	mIntakeArmRightSolenoid = new Solenoid(Constants.kIntakeRightSolenoidId);

    mIntake.set(ControlMode.PercentOutput,0);

 }

    public void IntakeArmUp()
    {
        mIntakeArmLeftSolenoid.set(true);
        mIntakeArmRightSolenoid.set(true);        
    }

     public void IntakeArmDown()
    {
        mIntakeArmLeftSolenoid.set(false);
        mIntakeArmRightSolenoid.set(false);

    }

    public void IntakeWheel()
    {
        mIntake.set(ControlMode.PercentOutput,0.25);

    }

     public void OuttakeWheel()
    {
        IntakeMotorSpeed = -.25;
    }

    @Override
    public void updateSubsystem()
    {
        mIntake.set(ControlMode.PercentOutput,IntakeMotorSpeed);
    }

    @Override
    public void outputToSmartDashobard()
    {
        SmartDashboard.putNumber("Intake_Speed" , mIntake.getSpeed());
    }
 }
    
 







 
	