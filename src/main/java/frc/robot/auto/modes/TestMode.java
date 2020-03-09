package frc.robot.auto.modes;

import frc.robot.auto.AutoModeBase;
import frc.robot.auto.AutoModeEndedException;
import frc.robot.auto.actions.AlignOnGoal;
import frc.robot.auto.actions.DeliveryAction;
import frc.robot.auto.actions.DriveStraightActionDistance;
import frc.robot.auto.actions.DriveStraightActionTime;
import frc.robot.auto.actions.IntakeAction;
import frc.robot.auto.actions.IntakeArmDownAction;
import frc.robot.auto.actions.IntakeArmUpAction;
import frc.robot.auto.actions.StopDeliveryAction;
import frc.robot.auto.actions.StopIntakeAction;
import frc.robot.auto.actions.WaitAction;
import frc.robot.subsystems.PixyCam;


public class TestMode extends AutoModeBase {

	@Override
	protected void routine() throws AutoModeEndedException {

		//runAction(new DriveStraightActionTime(5, 5, true));
		runAction(new AlignOnGoal());

		/*
		runAction(new DeliveryAction());
		runAction(new WaitAction(1));
		runAction(new StopDeliveryAction());
		runAction(new WaitAction(1));
		runAction(new IntakeArmDownAction());
		runAction(new WaitAction(1));
		runAction(new IntakeArmUpAction());
		runAction(new WaitAction(1));
		runAction(new IntakeArmDownAction());
		runAction(new IntakeAction());
		runAction(new WaitAction(1));
		runAction(new StopIntakeAction());
		*/
		
		//runAction(new StopIntakeAction());

		/*

		runAction(new CameraAlign());

		runAction(new DriveStraightActionTime(2, false));

		runAction(new ExtendGearAction());

		runAction(new WaitAction(1));

		runAction(new DriveStraightActionTime(2, true));

		runAction(new RetractGearAction());

		*/
	}

}