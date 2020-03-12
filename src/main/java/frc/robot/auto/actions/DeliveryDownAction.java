package frc.robot.auto.actions;

import frc.robot.subsystems.Delivery;
import frc.robot.subsystems.Intake;

public class DeliveryDownAction implements Action 
{
    Delivery mDelivery;

    public  DeliveryDownAction() {
        mDelivery = Delivery.getInstance();
    }
    
    @Override
    public void start() {
        mDelivery.AssumeThePostition();
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