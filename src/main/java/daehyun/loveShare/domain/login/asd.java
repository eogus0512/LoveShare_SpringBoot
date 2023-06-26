
public enum Direction (UP, DOWN)
public enum MotorStatus (MOVING, STOPPING)
public abstract class Motor (
        private MotorStatus motorStatus;
        public Motor() (
            motorStatus = MotorStatus.STOPPED;
        public MotorStatus getMotorStatus()
            return motorStatus;
        private void setMotorStatus(MotorStatus motorStatus) f
            this.motorStatus = motorStatus;
        private void move(Direction direction) (
            MotorStatus motorStatus = getMotorStatus();
            if (motorStatus == MotorStatus.MOVING)
                return;
            moveMotor(direction);
            setMotorStatus (MotorStatus.MOVING);
        }

        public class LGMotor extends Motor (
            protected void moveMotor(Direction direction) (
            System.out.println("move LG Motor ' + direction);
        public class HyundaiMotor extends Motor (
            protected void moveMotor(Direction direction) (
            System.out.println("move Hyundai Motor " + direction);
        public class ElevatorController (
            private int id;
            private int curFloor = 1;
            private Motor motor;
            public ElevatorController(int id, Motor motor) (
                this.id= id;
                this.motor=motor;
            }
            public void gotoFloor(int destination) (
                if (destination = curfloor)
                    return;

                Direction direction;
                if (destination > curFloor)
                    direction = Direction.UP;
                else
                    Direction.DOWN;

                motor.move(direction);
                System. out.print("Elevator [" + id + "] floor:" + curFloor);
                curFloor = destination;
                System.out.println(" ==> " + curFloor + " with x + motor.getclass().getName());
                motor.stop();

        public class Client (
            public static void maint(String[] args) (
                Motor lgMotor = new LGMotor();
                Elevatorcontroller controller1 = new Elevatorcontroller(1, lgMotor);
                controller1.gotoFloor(5);
                controller1.gotoFloor(3);

                Motor hyundaiMotor = new HyundaiMotor();
                Elevatorcontroller controller2 = new Elevatorcontroller(1, hyundaiMotor);
                controller2.gotoFloor(4);
                controller2.gotoFloor(6);
            }
        }