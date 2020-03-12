package frc.robot.auto.actions;

import frc.robot.subsystems.Intake;

public class IntakeAction implements Action 
{
    Intake mIntake;
    /**
     * Gets an Instance of the Intake Class
     */
    public IntakeAction() {
        mIntake = Intake.getInstance();
    }
    
    @Override
    /**Starts the Intake Motors */
    public void start() {
        mIntake.IntakePowercell();
    }
    
    /**This Method Updates the Intakes Percent output, and Motor Speed.
     * It also updates Both Intake Arm Solenoids by Setting them Equal to IntakeArmSolenoid.
     * The Updates are Sent to Smart Dashboard
     */
    @Override
    public void update() {
        mIntake.updateSubsystem();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void done() {
        //mIntake.StopIntaking();
    }

    

}