package com.lab4.demo.sms;

import com.twilio.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static com.lab4.demo.UrlMapping.URL_SMS;


@RestController
@RequestMapping(URL_SMS)
@RequiredArgsConstructor
public class SMSController {

    private final SMSService service;
    private final SimpMessagingTemplate webSocket;
    private final String TOPIC_DESTINATION = "/topic/sms";

    @PostMapping
    public String smsSubmit(@RequestBody Map<String, Long> users) {
        try {
            service.send(users.get("userId"));
        } catch (ApiException e) {
            return "Failed";
        }
        return "Sent with success";


    }

    @RequestMapping(value = "/smscallback", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void smsCallback(@RequestBody MultiValueMap<String, String> map) {
        service.receive(map);
        webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ": Twilio has made a callback request! Here are the contents: " + map.toString());
    }

    private String getTimeStamp() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
    }
}