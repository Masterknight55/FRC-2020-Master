package frc.robot.auto.actions;

import frc.robot.subsystems.Intake;

public class IntakeAction implements Action 
{
    Intake mIntake;

    public  IntakeAction() {
        mIntake = mIntake.getInstance();
    }
    
    @Override
    public void start() {
        mIntake.IntakePowercell();
    }

    @Override
    public void update() {
        

    }

    public void Stop() {

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void done() {

    }

    

}