// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.autos.exampleAuto;
import frc.robot.commands.DriveCommands;
import frc.robot.commands.TeleopSwerve;
import frc.robot.subsystems.Swerve;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  /* Controllers */
  private final CommandJoystick driver = new CommandJoystick(0);

  /* Drive Controls */
  // private final int translationAxis = XboxController.Axis.kLeftY.value;
  // private final int strafeAxis = XboxController.Axis.kLeftX.value;
  // private final int rotationAxis = XboxController.Axis.kRightY.value;

  /* Driver Buttons */
 // private final JoystickButton zeroGyro =
 //     new JoystickButton(driver, XboxController.Button.kY.value);
 // private final JoystickButton robotCentric =
 //     new JoystickButton(driver, XboxController.Button.kLeftBumper.value);

  /* Subsystems */
  private final Swerve s_Swerve = new Swerve();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    s_Swerve.setDefaultCommand(
        new TeleopSwerve(
            s_Swerve,
            () -> driver.getRawAxis(1) * driver.getRawAxis(1) * driver.getRawAxis(1),
            () -> driver.getRawAxis(0) * driver.getRawAxis(0) * driver.getRawAxis(0),
            () -> driver.getRawAxis(2) * driver.getRawAxis(2) * driver.getRawAxis(2) * .5,
    //        () -> robotCentric.get()));
    () -> true));

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    /* Driver Buttons */

    //zeroGyro.whenPressed(new InstantCommand(() -> s_Swerve.zeroGyro()));

    driver.button(8).whileTrue(
      new TeleopSwerve(
            s_Swerve,
            () -> .3,
            () -> 0,
            () -> 0,
            () -> true));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new exampleAuto(s_Swerve);
    // return DriveCommands.driveOne(
    //   s_Swerve,
    //   () -> .3,
    //   () -> 0,
    //   () -> 0,
    //   () -> true)
    // .withTimeout(5)
    // .andThen(DriveCommands.driveOne(    
    //   s_Swerve,
    //   () -> -.3,
    //   () -> 0,
    //   () -> 0,
    //   () -> true));
  }

}