package frc.robot;

import frc.robot.auto.AutoModeBase;
import frc.robot.auto.modes.BasicCross;
import frc.robot.auto.modes.BasicDeliver;
import frc.robot.auto.modes.StandStillMode;
import frc.robot.auto.modes.TestMode;
import frc.robot.auto.modes.MiddleStartPickUpAndDeliver;
import frc.robot.auto.modes.TrenchRunDeliver;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashboardInteractions {
	
	
	//chooser object to send to smartdashboard
	private SendableChooser<AutonOption> mAutoChooser;
	
	//default autonomous mode in case one is not selected
    private static final AutonOption DEFAULT_MODE = AutonOption.STAND_STILL;
    
    
    
    //happens when the class is first created
	public void initWithDefaults() {
		mAutoChooser = new SendableChooser<AutonOption>();
		
        // mAutoChooser.addObject(AutonOption.MIDDLE_GEAR_STAY.name, AutonOption.MIDDLE_GEAR_STAY);
        // mAutoChooser.addObject(AutonOption.LEFT_GEAR_STAY.name, AutonOption.LEFT_GEAR_STAY);
        // mAutoChooser.addObject(AutonOption.RIGHT_GEAR_STAY.name, AutonOption.RIGHT_GEAR_STAY);
        // mAutoChooser.addObject(AutonOption.SHOOT.name, AutonOption.SHOOT);
        mAutoChooser.addObject(AutonOption.BasicCross.name, AutonOption.BasicCross);
        mAutoChooser.addObject(AutonOption.STAND_STILL.name, AutonOption.STAND_STILL);
        mAutoChooser.addObject(AutonOption.TEST.name, AutonOption.TEST);
        mAutoChooser.addDefault("Stand_Still", AutonOption.STAND_STILL);
        mAutoChooser.addObject(AutonOption.TrenchRunDeliver.name, AutonOption.TrenchRunDeliver);
        

        
        
        SmartDashboard.putData("Auto Mode Chooser", mAutoChooser);
    }
	
	//compares selected auto mode to AutonOptions and returns the created mode
    public AutoModeBase getSelectedAutonMode() {
        AutonOption selectedOption = DEFAULT_MODE;
        for (AutonOption autonOption : AutonOption.values()) {
            if (autonOption == mAutoChooser.getSelected()) {
                selectedOption = autonOption;
                break;
            }
        }
        return createAutoMode(selectedOption);
    }
    
    
    //enum to hold all possible auto modes
   private enum AutonOption {
        
        BasicCross("Basic Cross", new BasicCross()),
        STAND_STILL("Stand Still", new StandStillMode()),//
        TEST("test (do not use at comp)", new TestMode()),
        BasicDeliver("Basic Deliver", new BasicDeliver()),
        TrenchRunDeliver("Trench Run Deliver", new TrenchRunDeliver()); //
    
        
        public final String name;
        public final AutoModeBase autoMode;

        AutonOption(String name, AutoModeBase autoMode) {
            this.name = name;
            this.autoMode = autoMode;
        }
    }
    
    //method to create auto mode based on chosen mode
    private AutoModeBase createAutoMode(AutonOption autonOption) {
        switch (autonOption) {
         case BasicCross:
             return autonOption.autoMode;
        case TEST:
            return autonOption.autoMode;
        case STAND_STILL:
        default:
            System.out.println("ERROR: unexpected auto mode: " + autonOption);
            return new StandStillMode();
        }
    }
}
