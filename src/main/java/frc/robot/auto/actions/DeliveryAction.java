package frc.robot.auto.actions;

import frc.robot.subsystems.Delivery;

public class DeliveryAction implements Action 
{
    Delivery mDelivery;
    /**
     * Gets an instance of the delivery subsystem for the Delivery auto action 
     */
    public DeliveryAction() {
        mDelivery = Delivery.getInstance();
    }
    
    @Override
    /**
     * Sets Conveyor Speed (SPEED)
     */
    public void start() {
        mDelivery.Deliver();
    }

    @Override
    /**
     * Updates the Position of the Delivery Arm and the Motor Settings
     */
    public void update() {
        mDelivery.updateSubsystem();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
     
    public void done() {
        mDelivery.StopDelivering();
    }

    

}