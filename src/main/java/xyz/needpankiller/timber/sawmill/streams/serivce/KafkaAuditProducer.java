package xyz.needpankiller.timber.sawmill.streams.serivce;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.quarkus.kafka.client.serialization.ObjectMapperSerde;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ext.Provider;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.GlobalKTable;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua_parser.Parser;
import xyz.needpankiller.timber.sawmill.streams.rto.AuditLogMessageRto;

import static org.apache.kafka.common.serialization.Serdes.Integer;

@Provider
@ApplicationScoped
public class KafkaAuditProducer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaAuditProducer.class);
    private static final String TOPIC_AUDIT_API = "timber__topic-audit-api";
    private static final String STORE_AUDIT_API = "timber__store-audit-api";

    private static Parser uaParser = new Parser();
    private static ObjectMapper objectMapper = createObjectMapper();

    private static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

/*
    @Produces
    public Topology buildTopology() {
        StreamsBuilder streamsBuilder = new StreamsBuilder();

        ObjectMapperSerde<AuditLogMessageRto> serde = new ObjectMapperSerde<>(AuditLogMessageRto.class);

        KeyValueBytesStoreSupplier storeSupplier = org.apache.kafka.streams.state.Stores.persistentKeyValueStore(STORE_AUDIT_API);
        GlobalKTable<Integer, AuditLogMessageRto> table = streamsBuilder.globalTable(TOPIC_AUDIT_API,
                Consumed.with(Integer(), serde));


    }
*/


}

