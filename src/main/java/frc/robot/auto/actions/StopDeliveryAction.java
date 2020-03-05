package frc.robot.auto.actions;

import frc.robot.subsystems.Delivery;

public class StopDeliveryAction implements Action 
{
    Delivery mDelivery;

    public  StopDeliveryAction() {
        mDelivery = Delivery.getInstance();
    }
    
    @Override
    public void start() {
        mDelivery.StopDelivering();
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