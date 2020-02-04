package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Setup;

public class Intake extends Subsystem
{
    private VictorSPX mIntake;
	private Solenoid mIntakeArmLeftSolenoid;
	private Solenoid mIntakeArmRightSolenoid;


 public Intake() 
 {
    mIntake = new VictorSPX(Setup.kIntakeId);
	mIntake.setInverted(false);

   	mIntakeArmLeftSolenoid = new Solenoid(Setup.kIntakeLeftSolenoidId);
	mIntakeArmRightSolenoid = new Solenoid(Setup.kIntakeRightSolenoidId);

    mIntake.set(ControlMode.PercentOutput,0);

 }

 private double mIntakeMotorSpeed;
 private boolean IntakeArmSolenoid; 

    public void IntakeArmUp()
    {
        IntakeArmSolenoid = true;
    }

     public void IntakeArmDown()
    {
        IntakeArmSolenoid = false;

    }

    public void IntakePowercell()
    {
        mIntakeMotorSpeed = .25;

    }

     public void OuttakePowercell()
    {
        mIntakeMotorSpeed = -.25;
    }

    @Override
    public void updateSubsystem()
    {
        mIntake.set(ControlMode.PercentOutput,mIntakeMotorSpeed);
        mIntakeArmLeftSolenoid.set(IntakeArmSolenoid);
        mIntakeArmRightSolenoid.set(IntakeArmSolenoid);

        outputToSmartDashboard();
    }

    @Override
    public void outputToSmartDashboard() {

        SmartDashboard.putNumber("Intake_Speed" , mIntakeMotorSpeed);
        SmartDashboard.putBoolean("Intake Arm", IntakeArmSolenoid);
        SmartDashboard.putBoolean("Left Intake Arm", mIntakeArmLeftSolenoid.get());
        SmartDashboard.putBoolean("Right Intake Arm", mIntakeArmRightSolenoid.get());
    }

    @Override
    public void stop() {

        mIntake.set(ControlMode.PercentOutput, 0);

    }
 }
    
 







 
	