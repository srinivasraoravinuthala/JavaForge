package pkg20serialization;

import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * serialization1JacksonDemo.java — JSON with Jackson (de-facto Java standard).
 * Run all demos: mvn -q exec:java -f pkg20serialization/pom.xml -Dexec.mainClass=pkg20serialization.serialization1JacksonDemo
 */
public class serialization1JacksonDemo {

    record User(int id, String name, boolean active) {}

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User(1, "Ada", true);

        String json = mapper.writeValueAsString(user);
        System.out.println("Jackson JSON:\n" + json);

        User back = mapper.readValue(json, User.class);
        System.out.println("Deserialized: " + back);
    }
}
