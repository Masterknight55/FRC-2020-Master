package frc.robot.auto.actions;

import frc.robot.subsystems.Delivery;

public class DeliveryAction implements Action 
{
    Delivery mDelivery;

    public  DeliveryAction() {
        mDelivery = Delivery.getInstance();
    }
    
    @Override
    public void start() {
        mDelivery.SetConveryorSpeed(1.0);
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