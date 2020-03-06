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
        mDelivery.Deliver();;
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