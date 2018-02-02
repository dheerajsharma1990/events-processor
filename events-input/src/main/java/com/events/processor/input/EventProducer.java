package com.events.processor.input;

import com.events.processor.domain.Event;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static java.lang.Math.random;
import static java.util.UUID.randomUUID;

public class EventProducer {

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "com.events.processor.serializer.EventSerializer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        final String visitorTopicName = "VisitorsTracking";
        Producer<String, Event> eventProducer = new KafkaProducer<>(props);

        for (int i = 0; i < 1000; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < (random() * 10); j++) {
                map.put(getRandomExperiment(), getRandomVariant());
            }
            Event event = new Event(getRandomEventId(), getRandomVisitor(), map);
            ProducerRecord<String, Event> record = new ProducerRecord<>(visitorTopicName, event.getEventId(), event);
            eventProducer.send(record);
            Thread.sleep(50);
        }

        eventProducer.close();
    }

    private static String getRandomEventId() {
        return randomUUID().toString();
    }

    private static int getRandomExperiment() {
        return (int) (random() * 10);
    }

    private static String getRandomVisitor() {
        int randomIndex = (int) random() * 100;
        return VISITOR_IDS[randomIndex];
    }

    private static int getRandomVariant() {
        return (int) (1 + (random() * 10) % 3);
    }


    private static final String[] VISITOR_IDS = new String[]{
            "c42f7cde-e0ca-4859-9eee-a60065f08749",
            "e409fb68-ea3c-4af7-90f2-d7c37f1ec0d7",
            "6d8baa79-3777-4802-b69d-0b7b163750b2",
            "f3f3c179-3a6c-405f-9766-5d5403c063ee",
            "bf02330f-1d0f-408b-9270-adcd71b78b87",
            "97311aec-7f85-4c4a-98fc-46b6dca164f2",
            "57a1558b-6917-46df-9ad3-2c923ad1751d",
            "574bdc52-3a6c-4dce-bf73-95f820912bb3",
            "832a996d-a7a0-441d-93c8-515ee7e0b9cd",
            "ec600254-df0a-49f3-94f8-3f67e0491e3f",
            "79c1ac64-9210-42f8-899e-55a8b1a28edb",
            "2c1a2cf6-6ced-4927-ba4b-931bb7962123",
            "ce4b1d27-7e70-4eba-9e9e-6b33e383005e",
            "8dcac3ba-cf22-477e-96e2-2c27d61d9d6d",
            "9e3b2eae-7e10-4980-935c-386a5cf595e1",
            "dd65901f-fd7e-4c25-8f8e-54fc608c7a05",
            "c7843f3f-cf40-4669-b8ee-918d8382a5a5",
            "1b367335-f216-4a2a-84a4-3b717b749919",
            "361b6231-7de8-489e-a997-dd6739bcbfed",
            "e37629aa-3c54-41e8-adaa-c3da156d7fae",
            "4c3ef74c-3be7-4d14-adc7-b357af7dded8",
            "ed9d6554-9b64-4ad7-bd92-abfb7bc7c77a",
            "cf6fd67d-de40-4106-a28d-19d6b2714f79",
            "bbcd8248-a624-4a04-a749-1b19a12f40b0",
            "e8c1a959-11e2-4999-90f3-496b9a81fe5f",
            "53f901fe-b1db-4cb6-9803-03bd976aa9f7",
            "8a2d8deb-d9d1-425c-b528-108648948009",
            "9be347bf-b792-4d3d-aafe-3a846dfd3b6a",
            "fb2052b5-0568-43a1-8bfd-f823bd93718a",
            "8628ae45-e8fa-47c6-a10e-5246842b8457",
            "f58f84da-dc40-4989-9799-a3f0260243f3",
            "fe264c42-9f1b-44c3-9184-5d2de220e5a0",
            "6363fed1-ae12-499f-90d1-32a3d4b0f59a",
            "d4b97398-e3bc-4b7f-be47-02c41e07c634",
            "bf3b07a0-4032-49b4-8f4b-71fc2f965387",
            "f6857a1c-eec1-429a-bb6d-957e0ec108ad",
            "ea9edb94-d09e-497a-8e7f-004f1aa23478",
            "670b96ec-d958-4465-8c45-57a7b51a5592",
            "c613cd2f-3ed9-4ce2-b82d-a5bcda6980ad",
            "ef4b4013-8d9d-4b09-96a7-b18a0ac85a06",
            "57a30254-6e5c-4d54-b3db-e884a605aaf1",
            "f9e2fd89-8782-42e6-b049-847b9f171a9e",
            "ccfb472c-4716-446d-902c-a140d1d4137a",
            "bc36ed76-8f3e-401a-b195-9027395063e1",
            "b5154ac4-bad5-438b-b130-6afd1a76f13b",
            "aa4e8df5-846f-4a84-8d08-e334baf082eb",
            "3116a76e-7463-4740-9011-e3149d782f3f",
            "9f1423d6-6408-40a6-80f8-79b90ded931a",
            "ff38b6a8-63ec-4dca-989a-39b8bd1f3396",
            "ebdab242-a9a7-4156-b98f-c65e83fceba5",
            "566a41e3-ca83-4d43-9dcd-5c1cd08f5ae8",
            "fd1d07bd-df44-4a53-a4b3-11741133b626",
            "ea2ed3b0-a4c4-4226-bedb-6a5a18f7a10f",
            "835525f7-1ba3-4d8a-b8ba-8b2c1f426206",
            "ca656cba-95f4-4b25-bc87-4feeeb0cacfd",
            "feffb2a6-5eb1-46e9-9542-8d57e64b61eb",
            "0487a67a-f1f8-438a-bfd2-45d9d516f891",
            "1d70381f-deba-4b9a-b2ed-9e09d8b28ec8",
            "d8198e6b-d094-4073-afdb-bc605d6928f7",
            "23488924-152c-4c70-a713-2cac990336f2",
            "1fd23da4-8813-46de-a995-d273eb131f64",
            "57213f9d-cfbb-4817-a4fe-7cbe64e02bd0",
            "99826dd5-52bd-454e-b015-31a4f62751d5",
            "7a794359-64b3-44fc-b114-d0e45e4199ad",
            "e4039eea-ef39-43a2-9682-15756e30e997",
            "642c1e4c-4f7f-402e-9243-66736f4e0d39",
            "27d12465-8434-4f2e-8941-c684ed295dad",
            "a6256814-7c07-4742-b206-58766f3e73b5",
            "be62f306-a44c-4c67-905b-2181cb5c4a1f",
            "b4be1c70-5479-403b-ab39-a481bfbc769c",
            "cd8f4176-d6f5-4d17-95e5-f87b396e255e",
            "3b954082-1276-4d3e-b1a4-ae3806247752",
            "6a5f6542-af88-4fc7-908a-873366375bf4",
            "cfbe8826-f876-4a24-bd56-5686a7173335",
            "59fa4b4a-776f-4e3d-a95b-2c45a94e69c8",
            "3f9fa28a-47ad-4b7d-befe-3844b680effd",
            "488b9d74-a261-49e9-a982-0c6f77efbc8d",
            "91edcbda-5088-49e2-b44c-051a30360e85",
            "e75d9d72-fcd6-4b4b-b811-b044e65a5758",
            "206d6785-ec16-46b9-a51f-3ed7a791a159",
            "4e4d623c-e03f-4264-b9ec-7ec0cff52dcb",
            "1d3db869-14b3-4880-8625-2b3c507dc1a6",
            "55f26df0-9628-4a6b-b625-27e39aed03f1",
            "5b52b5cb-29f0-4256-b702-3298f7b155ad",
            "63bbed71-d447-4b25-8560-809ac9bff2b8",
            "cf7b80c6-563a-477d-8b8c-7c1911de3ba0",
            "5827dad3-033b-4986-9271-bc00998d74f5",
            "8662dd5e-6994-4c17-a5bf-7279a69a23c3",
            "f1fbe9b5-8cd7-4705-a0ac-c9366a6a96b6",
            "bc241c38-0f08-43c8-842b-4d08fe763401",
            "6d8f6c42-06d3-4578-b354-b1fc64bcc824",
            "e221f8b5-cba8-49be-8a9b-635e5c29875e",
            "fb8f424c-ba7a-469b-8bd2-b77e63d7ac50",
            "19766c95-49cc-4ffe-8171-7a59fc04642a",
            "4bd9080b-4616-4abb-a1c2-a026a542b5d9",
            "2615652b-54ff-48b6-b543-ce03978ddf2c",
            "044d0132-ee80-4e0b-9ad8-f0e1808b3f62",
            "bdb4d8ce-ebde-4970-b815-bf0ec5e38e86",
            "968d5838-05d5-4e96-a7c5-dcbe216e8405",
            "9552f7fe-ffe6-4289-9c9e-70d97bbff971"
    };
}
