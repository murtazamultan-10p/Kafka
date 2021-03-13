package com.linklabs.UplinkCoapTester.listener;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "uplinkEventMessage-ingest", groupId= "group_id",
            containerFactory = "kafkaListenerContainerFactory")

    public void consume(String message) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonConsumedMessage = (JSONObject) parser.parse(message);
        JSONObject jsonMessagePortion = (JSONObject) jsonConsumedMessage.get("message");
        JSONObject jsonDataKey = (JSONObject) jsonMessagePortion.get("key");

        System.out.println("\n***********************************************************\n");
        System.out.println("Id: " + jsonDataKey.get("id"));
        System.out.println("\nmSecs: " + jsonDataKey.get("time"));
        System.out.println("\nLinkAddress: " + jsonMessagePortion.get("sourceLinkAddress"));
        System.out.println("\nMsgSeq: " + jsonMessagePortion.get("messageSequence"));
        System.out.println("\npld: " + jsonMessagePortion.get("encryptedPayloadHex"));
    }

//    @KafkaListener(topics = "uplinkEventMessage-ingest", groupId = "group_json",
//            containerFactory = "dataModelKafkaListenerFactory")
//
//    public void consumeJson(DataModel dataModel) {
//        System.out.println("Consumed JSON Message: " + dataModel);
//    }
}
