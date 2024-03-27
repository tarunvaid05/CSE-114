/*
Tarun Vaidhyanathan
115510562
R02
 */
import java.util.Scanner;
/**
 * This class represents an amusement park called Seven Flags.
 */
public class SevenFlags{
    /**
     * Main method running queues at the amusement park.
     * @param args
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int bsodRideCount = 0;
        int kkRideCount = 0;
        int totRideCount = 0;
        int gfRideCount = 0;
        int goldRideCount = 0;
        int silverRideCount = 0;
        int regularRidecount = 0;
        System.out.println("Welcome to Seven Flags!");
        System.out.print("Please enter the number of regular customers: ");
        int regularCustomers = input.nextInt();
        System.out.print("Please enter the number of silver customers: ");
        int silverCustomers = input.nextInt();
        System.out.print("Please enter the number of gold customers: ");
        int goldCustomers = input.nextInt();
        System.out.print("Please enter simulation length: ");
        int simulationLength = input.nextInt();
        System.out.print("Please enter the duration of Blue Scream of Death (minutes): ");
        int bsodDuration = input.nextInt();
        System.out.print("Please enter the capacity of Blue Scream of Death: ");
        int bsodCapacity = input.nextInt();
        System.out.print("Please enter the holding queue size for Blue Scream of Death: ");
        int bsodHoldingQueueSize = input.nextInt();
        System.out.print("Please enter the duration of Kingda Knuth (minutes): ");
        int kkDuration = input.nextInt();
        System.out.print("Please enter the capacity of Kingda Knuth: ");
        int kkCapacity = input.nextInt();
        System.out.print("Please enter the holding queue size for Kingda Knuth: ");
        int kkHoldingQueueSize = input.nextInt();
        System.out.print("Please enter the duration of i386 Tower of Terror (minutes): ");
        int totDuration = input.nextInt();
        System.out.print("Please enter the capacity of i386 Tower of Terror: ");
        int totCapacity = input.nextInt();
        System.out.print("Please enter the holding queue size for i386 Tower of Terror: ");
        int totHoldingQueueSize = input.nextInt();
        System.out.print("Please enter the duration of GeForce (minutes): ");
        int gfDuration = input.nextInt();
        System.out.print("Please enter the capacity of GeForce: ");
        int gfCapacity = input.nextInt();
        System.out.print("Please enter the holding queue size for GeForce: ");
        int gfHoldingQueueSize = input.nextInt();

        Person[] regular = new Person[regularCustomers];
        for(int i = 0; i < regularCustomers; i++){
            Person person = new Person(i + 1, 1);
            regular[i] = person;
        }
        Person[] silver = new Person[silverCustomers];
        for(int i = 0; i < silverCustomers; i++){
            Person person = new Person(i + 1, 2);
            silver[i] = person;
        }
        Person[] gold = new Person[goldCustomers];
        for(int i = 0; i < goldCustomers; i++){
            Person person = new Person(i + 1, 3);
            gold[i] = person;
        }

        Ride bsod = new Ride(bsodDuration,bsodCapacity, "BSOD");
        bsod.getHoldingQueue().setMaxSize(bsodHoldingQueueSize);
        Ride kk = new Ride(kkDuration, kkCapacity, "KK");
        kk.getHoldingQueue().setMaxSize(kkHoldingQueueSize);
        Ride tot = new Ride(totDuration,totCapacity,"ToT");
        tot.getHoldingQueue().setMaxSize(totHoldingQueueSize);
        Ride gf = new Ride(gfDuration, gfCapacity, "GF");
        gf.getHoldingQueue().setMaxSize(gfHoldingQueueSize);

        Ride[] rides = {bsod, kk, tot, gf};

        for(int i = 0; i < gold.length; i++){
            Ride[] temp = new Ride[1];
            Ride random = RandomGenerator.selectRide(rides);
            temp[0] = random;
            random.getVirtualLine().enqueue(gold[i]);
            gold[i].setLine(temp);
        }
        for(int i = 0; i < silver.length; i++){
            Ride[] temp = new Ride[1];
            Ride random = RandomGenerator.selectRide(rides);
            temp[0] = random;
            random.getVirtualLine().enqueue(silver[i]);
            silver[i].setLine(temp);
        }
        for(int i = 0; i < regular.length; i++){
            Ride[] temp = new Ride[1];
            Ride random = RandomGenerator.selectRide(rides);
            temp[0] = random;
            random.getVirtualLine().enqueue(regular[i]);
            regular[i].setLine(temp);
        }

        for(int i = 0; i < gold.length; i++){
            Ride[] temp = new Ride[2];
            Ride[] transfer = gold[i].getLine();
            temp[0] = transfer[0];
            Ride random = RandomGenerator.selectRide(rides);
            temp[1] = random;
            random.getVirtualLine().enqueue(gold[i]);
            gold[i].setLine(temp);
        }
        for(int i = 0; i < silver.length; i++){
            Ride[] temp = new Ride[2];
            Ride[] transfer = silver[i].getLine();
            temp[0] = transfer[0];
            Ride random = RandomGenerator.selectRide(rides);
            temp[1] = random;
            random.getVirtualLine().enqueue(silver[i]);
            silver[i].setLine(temp);
        }

        for(int i = 0; i < gold.length; i++){
            Ride[] temp = new Ride[3];
            Ride[] transfer = gold[i].getLine();
            temp[0] = transfer[0];
            temp[1] = transfer[1];
            Ride random = RandomGenerator.selectRide(rides);
            temp[2] = random;
            random.getVirtualLine().enqueue(gold[i]);
            gold[i].setLine(temp);
        }

        while(bsod.getVirtualLine().virtualLine.size() > 0 && bsod.getHoldingQueue().virtualLine.size() < bsodHoldingQueueSize) {
            bsod.getHoldingQueue().enqueue(bsod.getVirtualLine().dequeue());
            bsod.getHoldingQueue().virtualLine.get(0).setStatus(Status.Holding);
        }
        while(kk.getVirtualLine().virtualLine.size() > 0 && kk.getHoldingQueue().virtualLine.size() < kkHoldingQueueSize) {
            kk.getHoldingQueue().enqueue(kk.getVirtualLine().dequeue());
            kk.getHoldingQueue().virtualLine.get(0).setStatus(Status.Holding);
        }
        while(tot.getVirtualLine().virtualLine.size() > 0 && tot.getHoldingQueue().virtualLine.size() < totHoldingQueueSize) {
            tot.getHoldingQueue().enqueue(tot.getVirtualLine().dequeue());
            tot.getHoldingQueue().virtualLine.get(0).setStatus(Status.Holding);
        }
        while(gf.getVirtualLine().virtualLine.size() > 0 && gf.getHoldingQueue().virtualLine.size() < gfHoldingQueueSize) {
            gf.getHoldingQueue().enqueue(gf.getVirtualLine().dequeue());
            gf.getHoldingQueue().virtualLine.get(0).setStatus(Status.Holding);
        }

        Person[] bsodPeopleOnRide = new Person[bsodCapacity];
        for(int i = 0; i < bsodPeopleOnRide.length; i++){
            for(int j = 0; j < bsod.getHoldingQueue().virtualLine.size(); j++){
                bsodPeopleOnRide[i] = bsod.getHoldingQueue().dequeue();
                bsodPeopleOnRide[i].setStatus(Status.OnRide);
            }
        }
        bsod.setPeopleOnRide(bsodPeopleOnRide);

        Person[] kkPeopleOnRide = new Person[kkCapacity];
        for(int i = 0; i < kkPeopleOnRide.length; i++){
            for(int j = 0; j < kk.getHoldingQueue().virtualLine.size(); j++){
                kkPeopleOnRide[i] = kk.getHoldingQueue().dequeue();
                kkPeopleOnRide[i].setStatus(Status.OnRide);
            }
        }
        kk.setPeopleOnRide(kkPeopleOnRide);

        Person[] totPeopleOnRide = new Person[totCapacity];
        for(int i = 0; i < totPeopleOnRide.length; i++){
            for(int j = 0; j < tot.getHoldingQueue().virtualLine.size(); j++){
                totPeopleOnRide[i] = tot.getHoldingQueue().dequeue();
                totPeopleOnRide[i].setStatus(Status.OnRide);
            }
        }
        tot.setPeopleOnRide(totPeopleOnRide);

        Person[] gfPeopleOnRide = new Person[gfCapacity];
        for(int i = 0; i < gfPeopleOnRide.length; i++){
            for(int j = 0; j < gf.getHoldingQueue().virtualLine.size(); j++){
                gfPeopleOnRide[i] = gf.getHoldingQueue().dequeue();
                gfPeopleOnRide[i].setStatus(Status.OnRide);
            }
        }
        gf.setPeopleOnRide(gfPeopleOnRide);


        while(bsod.getVirtualLine().virtualLine.size() > 0 && bsod.getHoldingQueue().virtualLine.size() < bsodHoldingQueueSize) {
            bsod.getHoldingQueue().enqueue(bsod.getVirtualLine().dequeue());
            bsod.getHoldingQueue().virtualLine.get(0).setStatus(Status.Holding);
        }
        while(kk.getVirtualLine().virtualLine.size() > 0 && kk.getHoldingQueue().virtualLine.size() < kkHoldingQueueSize) {
            kk.getHoldingQueue().enqueue(kk.getVirtualLine().dequeue());
            kk.getHoldingQueue().virtualLine.get(0).setStatus(Status.Holding);
        }
        while(tot.getVirtualLine().virtualLine.size() > 0 && tot.getHoldingQueue().virtualLine.size() < totHoldingQueueSize) {
            tot.getHoldingQueue().enqueue(tot.getVirtualLine().dequeue());
            tot.getHoldingQueue().virtualLine.get(0).setStatus(Status.Holding);
        }
        while(gf.getVirtualLine().virtualLine.size() > 0 && gf.getHoldingQueue().virtualLine.size() < gfHoldingQueueSize) {
            gf.getHoldingQueue().enqueue(gf.getVirtualLine().dequeue());
            gf.getHoldingQueue().virtualLine.get(0).setStatus(Status.Holding);
        }

        int time = 0;
        System.out.println("------------------------------------------------------------------------------------------");
        while(time < simulationLength){
            System.out.println("At Time " + time + ":");
            System.out.println("Blue Scream of Death - Time remaining: " + (bsodDuration - (time % bsodDuration)) + " min");
            System.out.print("On Ride: ");
            for(int i = 0; i < bsod.getPeopleOnRide().length && bsod.getPeopleOnRide()[i] != null; i++){
                Person[] temp = bsod.getPeopleOnRide();
                System.out.print(temp[i].customerType() + " " + temp[i].getNumber() + ", ");
            }
            System.out.println();
            System.out.print("Holding Queue: ");
            for(int i = 0; i < bsod.getHoldingQueue().virtualLine.size() && bsod.getHoldingQueue().virtualLine.get(i) != null; i++){
                System.out.print(bsod.getHoldingQueue().virtualLine.get(i).customerType() + " " + bsod.getHoldingQueue().virtualLine.get(i).getNumber() + ", ");
            }
            System.out.println();
            System.out.print("Virtual Queue: ");
            for(int i = 0; i < bsod.getVirtualLine().virtualLine.size() && bsod.getVirtualLine().virtualLine.get(i) != null; i++){
                System.out.print(bsod.getVirtualLine().virtualLine.get(i).customerType() + " " + bsod.getVirtualLine().virtualLine.get(i).getNumber() + ", ");
            }
            System.out.println();
            System.out.println("----------------");
            System.out.println("Kingda Knuth - Time remaining: " + (kkDuration - (time % kkDuration)) + " min");
            System.out.print("On Ride: ");
            for(int i = 0; i < kk.getPeopleOnRide().length && kk.getPeopleOnRide()[i] != null; i++){
                Person[] temp = kk.getPeopleOnRide();
                System.out.print(temp[i].customerType() + " " + temp[i].getNumber() + ", ");
            }
            System.out.println();
            System.out.print("Holding Queue: ");
            for(int i = 0; i < kk.getHoldingQueue().virtualLine.size() && kk.getHoldingQueue().virtualLine.get(i) != null; i++){
                System.out.print(kk.getHoldingQueue().virtualLine.get(i).customerType() + " " + kk.getHoldingQueue().virtualLine.get(i).getNumber() + ", ");
            }
            System.out.println();
            System.out.print("Virtual Queue: ");
            for(int i = 0; i < kk.getVirtualLine().virtualLine.size() && kk.getVirtualLine().virtualLine.get(i) != null; i++){
                System.out.print(kk.getVirtualLine().virtualLine.get(i).customerType() + " " + kk.getVirtualLine().virtualLine.get(i).getNumber() + ", ");
            }
            System.out.println();
            System.out.println("----------------");

            System.out.println("i386 Tower of Terror - Time remaining: " + (totDuration - (time % totDuration)) + " min");
            System.out.print("On Ride: ");
            for(int i = 0; i < tot.getPeopleOnRide().length && tot.getPeopleOnRide()[i] != null; i++){
                Person[] temp = tot.getPeopleOnRide();
                System.out.print(temp[i].customerType() + " " + temp[i].getNumber() + ", ");
            }
            System.out.println();
            System.out.print("Holding Queue: ");
            for(int i = 0; i < tot.getHoldingQueue().virtualLine.size() && tot.getHoldingQueue().virtualLine.get(i) != null; i++){
                System.out.print(tot.getHoldingQueue().virtualLine.get(i).customerType() + " " + tot.getHoldingQueue().virtualLine.get(i).getNumber() + ", ");
            }
            System.out.println();
            System.out.print("Virtual Queue: ");
            for(int i = 0; i < tot.getVirtualLine().virtualLine.size() && tot.getVirtualLine().virtualLine.get(i) != null; i++){
                System.out.print(tot.getVirtualLine().virtualLine.get(i).customerType() + " " + tot.getVirtualLine().virtualLine.get(i).getNumber() + ", ");
            }
            System.out.println();
            System.out.println("----------------");

            System.out.println("GeForce - Time remaining: " + (gfDuration - (time % gfDuration)) + " min");
            System.out.print("On Ride: ");
            for(int i = 0; i < gf.getPeopleOnRide().length && gf.getPeopleOnRide()[i] != null; i++){
                Person[] temp = gf.getPeopleOnRide();
                System.out.print(temp[i].customerType() + " " + temp[i].getNumber() + ", ");
            }
            System.out.println();
            System.out.print("Holding Queue: ");
            for(int i = 0; i < gf.getHoldingQueue().virtualLine.size()&& gf.getHoldingQueue().virtualLine.get(i) != null; i++){
                System.out.print(gf.getHoldingQueue().virtualLine.get(i).customerType() + " " + gf.getHoldingQueue().virtualLine.get(i).getNumber() + ", ");
            }
            System.out.println();
            System.out.print("Virtual Queue: ");
            for(int i = 0; i < gf.getVirtualLine().virtualLine.size() && gf.getVirtualLine().virtualLine.get(i) != null; i++){
                System.out.print(gf.getVirtualLine().virtualLine.get(i).customerType() + " " + gf.getVirtualLine().virtualLine.get(i).getNumber() + ", ");
            }
            System.out.println();
            System.out.println("----------------");

            System.out.println("Regular Customers:");
            System.out.println("Num Line Status");
            System.out.println("----------------");
            for(int i = 0; i < regular.length; i++){
                System.out.print((i + 1) + ". ");
                System.out.print(regular[i].getLine()[0].getName() + "  " + regular[i].getStatus());
                System.out.println();
            }
            System.out.println();

            System.out.println("Silver Customers:");
            System.out.println("Num Line 1 Line 2 Status");
            System.out.println("------------------------");
            for(int i = 0; i < silver.length; i++){
                System.out.print((i + 1) + ". ");
                System.out.print(silver[i].getLine()[0].getName() + "  " + silver[i].getLine()[1].getName() + "  " + silver[i].getStatus());
                System.out.println();
            }
            System.out.println();

            System.out.println("Gold Customers:");
            System.out.println("Num Line 1 Line 2 Line 3 Status");
            System.out.println("-------------------------------");
            for(int i = 0; i < gold.length; i++){
                System.out.print((i + 1) + ". ");
                System.out.print(gold[i].getLine()[0].getName() + "  " + gold[i].getLine()[1].getName() + "  " + gold[i].getLine()[2].getName() + "  " + gold[i].getStatus());
                System.out.println();
            }
            System.out.println();

            System.out.println("------------------------------------------------------------------------------------------");
            time++;

            if((bsodDuration - (time % bsodDuration)) == 1){
                bsodRideCount++;
                for(int i = 0; i < bsod.getPeopleOnRide().length; i++){
                    if(bsod.getPeopleOnRide()[i] != null){
                        if(bsod.getPeopleOnRide()[i].getMaxLines() == 3){
                            goldRideCount++;
                        }
                        if(bsod.getPeopleOnRide()[i].getMaxLines() == 2){
                            silverRideCount++;
                        }
                        if(bsod.getPeopleOnRide()[i].getMaxLines() == 1){
                            regularRidecount++;
                        }
                    }
                }
                for(int i = 0; i < bsod.getPeopleOnRide().length && bsodPeopleOnRide[i] != null; i++){
                    bsod.getVirtualLine().enqueue(bsod.getPeopleOnRide()[i]);
                    bsod.getVirtualLine().virtualLine.get(0).setStatus(Status.Available);
                }
                for(int i = 0; i < bsodPeopleOnRide.length && bsodPeopleOnRide[i] != null; i++){
                    for(int j = 0; j < bsod.getHoldingQueue().virtualLine.size(); j++){
                        bsodPeopleOnRide[i] = bsod.getHoldingQueue().dequeue();
                        if(bsodPeopleOnRide[i] != null){
                            bsodPeopleOnRide[i].setStatus(Status.OnRide);
                        }
                    }
                }
                while(bsod.getVirtualLine().virtualLine.size() > 0 && bsod.getHoldingQueue().virtualLine.size() < bsodHoldingQueueSize) {
                    bsod.getHoldingQueue().enqueue(bsod.getVirtualLine().dequeue());
                    bsod.getHoldingQueue().virtualLine.get(0).setStatus(Status.Holding);
                }
            }
            if((kkDuration - (time % kkDuration)) == 1){
                kkRideCount++;
                for(int i = 0; i < kk.getPeopleOnRide().length; i++){
                    if(kk.getPeopleOnRide()[i] != null){
                        if(kk.getPeopleOnRide()[i].getMaxLines() == 3){
                            goldRideCount++;
                        }
                        if(kk.getPeopleOnRide()[i].getMaxLines() == 2){
                            silverRideCount++;
                        }
                        if(kk.getPeopleOnRide()[i].getMaxLines() == 1){
                            regularRidecount++;
                        }
                    }
                }
                for(int i = 0; i < kk.getPeopleOnRide().length && kkPeopleOnRide[i] != null; i++){
                    kk.getVirtualLine().enqueue(kk.getPeopleOnRide()[i]);
                    kk.getVirtualLine().virtualLine.get(0).setStatus(Status.Available);
                }
                for(int i = 0; i < kkPeopleOnRide.length && kkPeopleOnRide[i] != null; i++){
                    for(int j = 0; j < kk.getHoldingQueue().virtualLine.size(); j++){
                        kkPeopleOnRide[i] = kk.getHoldingQueue().dequeue();
                        if(kkPeopleOnRide[i] != null){
                            kkPeopleOnRide[i].setStatus(Status.OnRide);
                        }
                    }
                }
                while(kk.getVirtualLine().virtualLine.size() > 0 && kk.getHoldingQueue().virtualLine.size() < kkHoldingQueueSize) {
                    kk.getHoldingQueue().enqueue(kk.getVirtualLine().dequeue());
                    kk.getHoldingQueue().virtualLine.get(0).setStatus(Status.Holding);
                }
            }
            if((totDuration - (time % totDuration)) == 1){
                totRideCount++;
                for(int i = 0; i < tot.getPeopleOnRide().length; i++){
                    if(tot.getPeopleOnRide()[i] != null){
                        if(tot.getPeopleOnRide()[i].getMaxLines() == 3){
                            goldRideCount++;
                        }
                        if(tot.getPeopleOnRide()[i].getMaxLines() == 2){
                            silverRideCount++;
                        }
                        if(tot.getPeopleOnRide()[i].getMaxLines() == 1){
                            regularRidecount++;
                        }
                    }
                }
                for(int i = 0; i < tot.getPeopleOnRide().length && totPeopleOnRide[i] != null; i++){
                    tot.getVirtualLine().enqueue(tot.getPeopleOnRide()[i]);
                    tot.getVirtualLine().virtualLine.get(0).setStatus(Status.Available);
                }
                for(int i = 0; i < totPeopleOnRide.length && totPeopleOnRide[i] != null; i++){
                    for(int j = 0; j < tot.getHoldingQueue().virtualLine.size(); j++){
                        totPeopleOnRide[i] = tot.getHoldingQueue().dequeue();
                        if(totPeopleOnRide[i] != null){
                            totPeopleOnRide[i].setStatus(Status.OnRide);
                        }
                    }
                }
                while(tot.getVirtualLine().virtualLine.size() > 0 && tot.getHoldingQueue().virtualLine.size() < totHoldingQueueSize) {
                    tot.getHoldingQueue().enqueue(tot.getVirtualLine().dequeue());
                    tot.getHoldingQueue().virtualLine.get(0).setStatus(Status.Holding);
                }
            }
            if((gfDuration - (time % gfDuration)) == 1){
                gfRideCount++;
                for(int i = 0; i < gf.getPeopleOnRide().length; i++){
                    if(gf.getPeopleOnRide()[i] != null){
                        if(gf.getPeopleOnRide()[i].getMaxLines() == 3){
                            goldRideCount++;
                        }
                        if(gf.getPeopleOnRide()[i].getMaxLines() == 2){
                            silverRideCount++;
                        }
                        if(gf.getPeopleOnRide()[i].getMaxLines() == 1){
                            regularRidecount++;
                        }
                    }
                }
                for(int i = 0; i < gf.getPeopleOnRide().length && gfPeopleOnRide[i] != null; i++){
                    gf.getVirtualLine().enqueue(gf.getPeopleOnRide()[i]);
                    gf.getVirtualLine().virtualLine.get(0).setStatus(Status.Available);
                }
                for(int i = 0; i < gfPeopleOnRide.length && gfPeopleOnRide[i] != null; i++){
                    for(int j = 0; j < gf.getHoldingQueue().virtualLine.size(); j++){
                        gfPeopleOnRide[i] = gf.getHoldingQueue().dequeue();
                        if(gfPeopleOnRide[i] != null){
                            gfPeopleOnRide[i].setStatus(Status.OnRide);
                        }
                    }
                }
                while(gf.getVirtualLine().virtualLine.size() > 0 && gf.getHoldingQueue().virtualLine.size() < gfHoldingQueueSize) {
                    gf.getHoldingQueue().enqueue(gf.getVirtualLine().dequeue());
                    gf.getHoldingQueue().virtualLine.get(0).setStatus(Status.Holding);
                }
            }
        }

        System.out.println("...........At the end of the simulation:......");
        System.out.println("On average, Gold customers have taken " + (goldRideCount/gold.length) + " rides");
        System.out.println("On average, Silver customers have taken " + (silverRideCount/silver.length) + " rides");
        System.out.println("On average, regular customers have taken " + (regularRidecount/regular.length) + " rides");
        System.out.println("BSOD has completed rides for " + bsodRideCount + " people");
        System.out.println("KK has completed rides for " + kkRideCount + " people");
        System.out.println("ToT has completed rides for " + totRideCount + " people");
        System.out.println("GF has completed rides for " + gfRideCount + " people");
    }
}

