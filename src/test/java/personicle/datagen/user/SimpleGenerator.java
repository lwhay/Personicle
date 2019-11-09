package personicle.datagen.user;

import asterix.recordV1.Address;
import asterix.recordV1.EmailDetail;
import asterix.recordV1.PhoneDetail;
import asterix.recordV1.Users;
import com.alibaba.fastjson.JSONObject;
import javafx.util.Pair;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class SimpleGenerator {

    private static String datapath = "./user.dat";

    private static final String geopath = "./resources/geobasis/pcas-code.json";

    private static final String[] emailcategory = {"家庭", "工作", "临时"};

    public static class GeoEntitity {
        private String code;
        private String name;
        private List<GeoEntitity> children;

        public void setCode(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setChildren(List<GeoEntitity> children) {
            this.children = children;
        }

        public List<GeoEntitity> getChildren() {
            return children;
        }
    }

    private static List<Pair<String, String>> regionMap = null;

    private static Random regionRand = null;

    private static Pair<String, String> genGegion() throws IOException {
        if (regionMap == null) {
            regionMap = new ArrayList<>();
            String geoInfo = new String(Files.readAllBytes(Paths.get(geopath)));
            List<GeoEntitity> geoset = JSONObject.parseArray(geoInfo, GeoEntitity.class);
            for (GeoEntitity entity : geoset) {
                if (entity.name.equals("广东省"))
                    System.out.println(entity.code + ":" + entity.name);
                for (GeoEntitity city : entity.children) {
                    if (city.name.equals("深圳市")) {
                        System.out.println("\t" + city.code + ":" + city.name);
                        for (GeoEntitity region : city.children) {
                            System.out.println("\t\t" + region.code + ":" + region.name);
                            regionMap.add(new Pair<>(region.name, region.code));
                        }
                    }
                }
            }
            regionRand = new Random(regionMap.size());
        }
        return regionMap.get(regionRand.nextInt(regionMap.size()));
    }

    private static List<String> firstName = null;

    private static List<String> lastName = null;

    private static Random nameRand = null;

    private static String genName() throws IOException {
        if (firstName == null) {
            Set<String> firstChar = new HashSet<>();
            Set<String> lastChar = new HashSet<>();
            firstName = new ArrayList<>();
            lastName = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader("./resources/name/fname.txt"));
            String line = "";
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (line.length() < 2) {
                    String string = new String();
                    string += line.charAt(0);
                    firstChar.add(string);
                } else {
                    String string = new String();
                    string += line.charAt(0);
                    firstChar.add(string);
                    string = new String();
                    string += line.charAt(1);
                    lastChar.add(string);
                }
            }
            for (String fc : firstChar) {
                for (String lc : lastChar) {
                    String string = new String();
                    string += fc;
                    string += lc;
                    System.out.println(count++ + " " + string);
                    firstName.add(string);
                }
            }
            br.close();
            br = new BufferedReader(new FileReader("./resources/name/lname.txt"));
            while ((line = br.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    String fn = new String();
                    fn += line.charAt(i);
                    lastName.add(fn);
                }
            }
            br.close();
            nameRand = new Random((long) firstName.size() * lastName.size());
        }
        String name = new String();
        name += lastName.get(nameRand.nextInt(lastName.size()));
        name += firstName.get(nameRand.nextInt(firstName.size()));
        return name;
    }

    private static Random numberGen = new Random();

    private static String randomEmail() {
        int count = numberGen.nextInt(10) + 3;
        String prefix = new String();
        for (int i = 0; i < count; i++) {
            prefix += (char) ('a' + numberGen.nextInt(26));
        }
        String suffix = new String();
        for (int i = 0; i < 3; i++) {
            suffix += (char) ('a' + numberGen.nextInt(26));
        }
        return prefix + "@" + suffix;
    }

    private static String randomPhone() {
        String phone = "1";
        for (int i = 0; i < 10; i++) {
            phone += (char) ('0' + numberGen.nextInt(9));
        }
        return phone;
    }

    public static void main(String[] args) throws IOException {
        long total_users = 10000;
        if (args.length > 0) {
            total_users = Long.parseLong(args[0]);
        }
        //List<Users> users = new ArrayList<>();
        BufferedWriter bw = new BufferedWriter(new FileWriter("./pseudo_users.adm"));
        for (int i = 0; i < total_users; i++) {
            String name = genName();
            Pair<String, String> addr = genGegion();
            Users user = new Users();
            user.setUserName(name);
            Address address = new Address();
            address.setCity("深圳市");
            address.setState("广东省");

            List<Address> addresses = new ArrayList<>();
            address.setAddress(
                    addr.getKey() + firstName.get(numberGen.nextInt(firstName.size())) + "路" + numberGen.nextInt(100)
                            + "号");
            address.setPostalCode(addr.getValue());
            addresses.add(address);
            user.setAddresses(addresses);

            List<EmailDetail> emails = new ArrayList<>();
            EmailDetail email = new EmailDetail();
            email.setCategory(emailcategory[numberGen.nextInt(3)]);
            email.setEmail(randomEmail());
            emails.add(email);
            user.setEmails(emails);

            List<PhoneDetail> phones = new ArrayList<>();
            PhoneDetail phone = new PhoneDetail();
            phone.setCatetory(emailcategory[numberGen.nextInt(3)]);
            phone.setPhone(randomPhone());
            phones.add(phone);
            user.setPhones(phones);
            //System.out.println(user.toJSONString());
            //users.add(user);
            bw.write(user.toJSONString() + "\n");
        }
        //String aus = JSONObject.toJSONString(users);
        //bw.write(aus);
        bw.flush();
        bw.close();
    }
}
