package personicle.datagen.nosqlcomp.invertedindex;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.Set;
import java.util.HashSet;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.TimestampSynchronizer;
import com.fasterxml.uuid.ext.FileBasedTimestampSynchronizer;
import com.fasterxml.uuid.impl.NameBasedGenerator;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

public class DistributedUUIDTest {
    private static String verifyPath = "./verfy.dat";

    @SuppressWarnings("unused")
    private static void standardUUID() {
        UUID rawUUID = UUID.randomUUID();
        System.out.println(rawUUID);
        UUID uuid000 = UUID.nameUUIDFromBytes(new byte[]{00, 00, 00});
        System.out.println(uuid000 + "\n");
        uuid000 = UUID.nameUUIDFromBytes(new byte[]{00, 00, 00});
        System.out.println(uuid000);
        UUID uuid001 = UUID.nameUUIDFromBytes(new byte[]{00, 00, 01});
        System.out.println(uuid001);
        UUID uuid010 = UUID.nameUUIDFromBytes(new byte[]{00, 01, 00});
        System.out.println(uuid010);
        UUID uuid011 = UUID.nameUUIDFromBytes(new byte[]{00, 01, 01});
        System.out.println(uuid011);
        UUID uuid100 = UUID.nameUUIDFromBytes(new byte[]{01, 00, 00});
        System.out.println(uuid100);
        UUID uuid101 = UUID.nameUUIDFromBytes(new byte[]{01, 00, 01});
        System.out.println(uuid101);
        UUID uuid110 = UUID.nameUUIDFromBytes(new byte[]{01, 01, 00});
        System.out.println(uuid110);
        UUID uuid111 = UUID.nameUUIDFromBytes(new byte[]{01, 01, 01});
        System.out.println(uuid111);
        Set<UUID> set000 = new HashSet<>();
        for (int i = 0; i < (1 << 24); i++) {
            set000.add(UUID.nameUUIDFromBytes(new byte[]{00, 00, 00}));
        }
        System.out.println(set000.size() + "\n");
    }

    @SuppressWarnings("unused")
    private static void nameBasedUUID() {
        Set<UUID> set000 = new HashSet<>();
        set000.clear();
        NameBasedGenerator nbg = Generators.nameBasedGenerator(NameBasedGenerator.NAMESPACE_URL);
        for (int i = 0; i < (1 << 24); i++) {
            set000.add(nbg.generate(new byte[]{00, 00, 00}));
        }
        System.out.println(set000.size());
        System.out.println(set000.iterator().next());
    }

    @SuppressWarnings("unchecked")
    private static void globalUUID() throws IOException {
        TimeBasedGenerator gen = null;

        synchronized (DistributedUUIDTest.class) {
            if (gen == null) {
                gen = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
            }
        }

        Set<UUID> setLocal = new HashSet<>();
        for (int i = 0; i < (1 << 24); i++) {
            setLocal.add(gen.generate());
            if (i == 0) {
                System.out.println(setLocal.iterator().next());
            }
        }
        System.out.println(setLocal.size() + "<->" + (1 << 24));
        BufferedWriter bw = new BufferedWriter(new FileWriter(verifyPath));
        for (UUID uuid : setLocal) {
            bw.write(uuid + "\n");
        }
        bw.close();
    }

    @SuppressWarnings("unchecked")
    private static void verifyUnique() throws IOException {
        Set<UUID> setLocal = new HashSet<>();
        BufferedReader br = new BufferedReader(new FileReader(verifyPath));
        String line;
        int count = 0;
        while ((line = br.readLine()) != null) {
            setLocal.add(UUID.fromString(line.trim()));
            if (count++ == 0) {
                System.out.println(setLocal.iterator().next());
            }
        }
        TimeBasedGenerator gen = null;

        Set<UUID> setRetry = new HashSet<>();
        synchronized (DistributedUUIDTest.class) {
            if (gen == null) {
                gen = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
            }
        }
        for (int i = 0; i < (1 << 24); i++) {
            setRetry.add(gen.generate());
            if (i == 0) {
                System.out.println(setRetry.iterator().next());
            }
        }
        int duplicate = 0;
        for (UUID uuid : setRetry) {
            if (setLocal.contains(uuid)) duplicate++;
        }
        System.out.print(setRetry.size() + "<->" + duplicate);
    }

    public static void main(String[] args) throws IOException {
        globalUUID();
        verifyUnique();
    }
}
