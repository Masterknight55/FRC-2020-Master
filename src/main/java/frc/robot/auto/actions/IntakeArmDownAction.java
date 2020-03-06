package frc.robot.auto.actions;

import frc.robot.subsystems.Intake;

public class IntakeArmDownAction implements Action 
{
    Intake mIntake;

    public  IntakeArmDownAction() {
        mIntake = Intake.getInstance();
    }
    
    @Override
    public void start() {
        mIntake.IntakeArmDown();
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