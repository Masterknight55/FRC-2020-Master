package frc.robot.auto.actions;

import frc.robot.subsystems.Delivery;

public class DeliveryAction implements Action 
{
    Delivery mDelivery;
<<<<<<< HEAD
    /**
     * Returns an instance of the delivery subsystem for the Delivery auto action 
     */
    public DeliveryAction() {
=======

    public  DeliveryAction() {
>>>>>>> master
        mDelivery = Delivery.getInstance();
    }
    
    @Override
<<<<<<< HEAD
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
=======
    public void start() {
        mDelivery.Deliver();;
    }

    @Override
    public void update() {
        

    }

    public void Stop() {

>>>>>>> master
    }

    @Override
    public boolean isFinished() {
<<<<<<< HEAD
        return false;
    }

    @Override
     
    public void done() {
        mDelivery.StopDelivering();
=======
        return true;
    }

    @Override
    public void done() {

>>>>>>> master
    }

    

}