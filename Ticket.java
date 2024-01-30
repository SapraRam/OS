class BusReservationSystem {
    private int seatsAvailable;

    public BusReservationSystem(int totalSeats) {
        this.seatsAvailable = totalSeats;
    }

    public void reserveSeat(String user) 
    {
        try 
        {
            if (seatsAvailable > 0) 
            {
                System.out.println(user + " is reserving a seat.");
                try {
                    Thread.sleep(500); // Simulating some processing time
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                seatsAvailable--;
                System.out.println("Seat reserved successfully for " + user + ". Remaining seats: " + seatsAvailable);
            } 
            else 
            {
                System.out.println("No seats available for " + user + ".");
            }
        }
        finally {
            System.out.println("DONE");
        }
    }
}

class UserThread extends Thread {
    private final BusReservationSystem busSystem;
    private final String user;

    public UserThread(BusReservationSystem busSystem, String user) {
        this.busSystem = busSystem;
        this.user = user;
    }

    @Override
    public void run() {
        busSystem.reserveSeat(user);
    }
}
 
public class Ticket {
    public static void main(String[] args) {
        BusReservationSystem abcBus = new BusReservationSystem(2);

        Thread user1Thread = new UserThread(abcBus, "User1");
        Thread user2Thread = new UserThread(abcBus, "User2");
        Thread user3Thread = new UserThread(abcBus, "User3");

        user1Thread.start();
        user2Thread.start();
        user3Thread.start();
    }
}
