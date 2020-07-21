package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import java.util.Locale;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "OperatorDrive")
public class OperatorDrive extends OpMode {

    private DcMotor left;
    private DcMotor right;
    private DcMotor arm;
    private Servo front;
    private Servo back;
    private TouchSensor touch;
    private ColorSensor color;
    private DistanceSensor distance;
    
    private double servoPos;
    private boolean oldServoButton;

    @Override
    public void init() {
        left = hardwareMap.get(DcMotor.class, "leftMotor");
        right = hardwareMap.get(DcMotor.class, "rightMotor");
        arm = hardwareMap.get(DcMotor.class, "armMotor");
        front = hardwareMap.get(Servo.class, "frontServo");
        back = hardwareMap.get(Servo.class, "backServo");
        touch = hardwareMap.get(TouchSensor.class, "touchSensorHigh");
        color = hardwareMap.get(ColorSensor.class, "colorDistanceSensor");
        distance = hardwareMap.get(DistanceSensor.class, "colorDistanceSensor");
        
        right.setDirection(DcMotorSimple.Direction.REVERSE);
        
        servoPos = 0;
        oldServoButton = false;
    }
    
    @Override
    public void loop() {
        double speed = gamepad1.right_trigger - gamepad1.left_trigger;
        double turn = gamepad1.left_stick_x;
        double armSpeed = gamepad1.right_stick_y;
        boolean servoButton = gamepad1.x;

        if(touch.isPressed() && speed > 0) {
            left.setPower(0);
            right.setPower(0);
        }
        else {
            left.setPower(speed + turn);
            right.setPower(speed - turn);
        }


        arm.setPower(armSpeed);

        if(servoButton && !oldServoButton) {
            if(servoPos == 0) {
                front.setPosition(0);
                back.setPosition(1);
                servoPos = 1;
            }
            else {
                front.setPosition(0.5);
                back.setPosition(0.5);
                servoPos = 0;
            }
        }
        
        telemetry.addData("Distance (cm)", distance.getDistance(DistanceUnit.CM));
        telemetry.addData("Alpha", color.alpha());
        telemetry.addData("Red", color.red());
        telemetry.addData("Green", color.green());
        telemetry.addData("Blue", color.blue());
        telemetry.update();

        oldServoButton = servoButton;
    }
}