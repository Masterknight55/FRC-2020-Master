package frc.robot.auto.actions;

import frc.robot.subsystems.Intake;

public class StopIntakeAction implements Action 
{
    Intake mIntake;

    public  StopIntakeAction() {
        mIntake = Intake.getInstance();
    }
    
    @Override
    public void start() {

        mIntake.StopIntaking();
        
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