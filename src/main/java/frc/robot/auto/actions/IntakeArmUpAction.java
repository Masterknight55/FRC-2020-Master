package frc.robot.auto.actions;

import frc.robot.subsystems.Intake;

public class IntakeArmUpAction implements Action 
{
    Intake mIntake;

    public  IntakeArmUpAction() {
        mIntake = Intake.getInstance();
    }
    
    @Override
    public void start() {
        mIntake.IntakeArmUp();
    }

    @Override
    public void update() {
        

    }

    public void Stop() {

    }

    @Override
    public boolean isFinished() {
        
        return true;
    }

    @Override
    public void done() {

    }

    

}