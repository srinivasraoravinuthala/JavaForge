package pkg20serialization;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/*
 * serialization5AvroDemo.java — Apache Avro: schema-evolvable binary serialization.
 */
public class serialization5AvroDemo {

    static final String SCHEMA_JSON = """
            {"type":"record","name":"User","fields":[
              {"name":"id","type":"int"},
              {"name":"name","type":"string"}
            ]}""";

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema.Parser().parse(SCHEMA_JSON);
        GenericRecord record = new GenericData.Record(schema);
        record.put("id", 7);
        record.put("name", "Linus");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DatumWriter<GenericRecord> writer = new GenericDatumWriter<>(schema);
        Encoder encoder = EncoderFactory.get().binaryEncoder(out, null);
        writer.write(record, encoder);
        encoder.flush();

        byte[] bytes = out.toByteArray();
        System.out.println("Avro binary length: " + bytes.length);

        DatumReader<GenericRecord> reader = new GenericDatumReader<>(schema);
        Decoder decoder = DecoderFactory.get().binaryDecoder(new ByteArrayInputStream(bytes), null);
        GenericRecord parsed = reader.read(null, decoder);
        System.out.println("Avro parsed: id=" + parsed.get("id") + ", name=" + parsed.get("name"));
    }
}
