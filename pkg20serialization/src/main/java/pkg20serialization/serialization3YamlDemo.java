package pkg20serialization;

import org.yaml.snakeyaml.Yaml;

import java.util.Map;

/*
 * serialization3YamlDemo.java — YAML with SnakeYAML (config files, K8s manifests).
 */
public class serialization3YamlDemo {

    public static void main(String[] args) {
        Yaml yaml = new Yaml();
        String yamlText = """
                app:
                  name: orders-service
                  port: 8080
                features:
                  - metrics
                  - tracing
                """;

        Map<String, Object> data = yaml.load(yamlText);
        System.out.println("Parsed YAML app.name: " + ((Map<?, ?>) data.get("app")).get("name"));

        String roundTrip = yaml.dump(data);
        System.out.println("Round-trip:\n" + roundTrip);
    }
}
