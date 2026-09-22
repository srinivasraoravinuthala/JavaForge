package pkg20serialization;

import com.google.protobuf.Struct;
import com.google.protobuf.Value;
import com.google.protobuf.util.JsonFormat;

/*
 * serialization4ProtobufDemo.java — Protocol Buffers: compact binary schema-first format.
 * This demo uses protobuf Struct/Value (no protoc step required).
 */
public class serialization4ProtobufDemo {

    public static void main(String[] args) throws Exception {
        Struct struct = Struct.newBuilder()
                .putFields("name", Value.newBuilder().setStringValue("Ada").build())
                .putFields("id", Value.newBuilder().setNumberValue(42).build())
                .build();

        byte[] bytes = struct.toByteArray();
        System.out.println("Protobuf bytes length: " + bytes.length);
        System.out.println("JSON view: " + JsonFormat.printer().print(struct));

        Struct parsed = Struct.parseFrom(bytes);
        System.out.println("Parsed name: " + parsed.getFieldsOrThrow("name").getStringValue());
    }
}
