package personicle.datagen.event;

import java.time.LocalDateTime;
import java.util.UUID;

import asterix.recordV2.events.PersonicleEvent;
import asterix.recordV2.wrapper.DateTime;
import asterix.recordV2.wrapper.Point;
import asterix.recordV2.wrapper.Uuid;

import java.io.*;
import java.util.*;

public class PersonicleEventGenerator {
    private static int eventCount = 1000;//0000;

    private static int userCount = 100;//0000;

    private static final int subEventPer = 10;

    private static int informationCount = 100;//00000;

    private static final int informationPerEvent = 5;

    private static int gran = eventCount / userCount;

    private static final double minY = 22.24;

    private static final double maxY = 22.52;

    private static final double minX = 113.46;

    private static final double maxX = 114.37;

    private static Random rand = new Random();

    private static Random randomnum = new Random();

    private static LocalDateTime baseTime = LocalDateTime.of(2011, 01, 01, 0, 0, 0, 0);

    public static void main(String[] args) throws IOException {
        if (args.length >= 3) {
            eventCount = Integer.parseInt(args[0]);
            userCount = Integer.parseInt(args[1]);
            informationCount = Integer.parseInt(args[2]);
            gran = eventCount / userCount;
        }
        System.out.println("Event: " + eventCount + " user: " + userCount + " per: " + gran + " info: " + informationCount);
        List<UUID> userSet = new ArrayList<>();
        for (int i = 0; i < userCount; i++) {
            userSet.add(UUID.randomUUID());
        }
        List<UUID> infoSet = new ArrayList<>();
        for (int i = 0; i < informationCount; i++) {
            infoSet.add(UUID.randomUUID());
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("./events.adm"));
        for (UUID user : userSet) {
            double minx = minX + rand.nextDouble() * 0.5;
            double maxx = maxX + rand.nextDouble() * 0.25;
            double miny = minY + rand.nextDouble() * 0.5;
            double maxy = maxY + rand.nextDouble() * 0.25;
            double delx = (maxx - minx) / gran;
            double dely = (maxy - miny) / gran;
            int second = rand.nextInt(8 * 365 * 24 * 60 * 60);
            if (second % 2 == 0) {
                second++;
            }
            LocalDateTime begin = baseTime.plusSeconds(second);
            for (int i = 0; i < gran; i++) {
                PersonicleEvent event = new PersonicleEvent();
                double x = minx + i * delx;
                double y = miny + i * dely;
                event.setEventId(new Uuid(UUID.randomUUID()));
                event.setUserId(new Uuid(user));
                begin.plusSeconds(1);
                event.setBeginAt(new DateTime(begin));
                event.setEndAt(new DateTime(begin.plusSeconds(10)));
                event.setLocation(new Point(x, y));
                event.setCategory("unknown");
                event.setName("Event-" + user.toString().substring(user.toString().length() - 10, user.toString().length()) + "-" + i);
                event.setInformation(new ArrayList<>());
                for (int j = 0; j < informationPerEvent; j++) {
                    event.getInformation().add(new Uuid(infoSet.get(rand.nextInt(infoSet.size()))));
                }
                //System.out.println(event.toJSONString());
                bw.write(event.toJSONString() + "\n");
            }
        }

        bw.close();
    }
}
