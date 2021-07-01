package com.lab4.demo.sms;

import com.lab4.demo.sms.model.SMS;
import com.lab4.demo.user.UserRepository;
import com.lab4.demo.user.model.User;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class SMSService {

    private final UserRepository userRepository;


    public void send(Long userId) {
        User user = userRepository.findById(userId).get();
        SMS sms = SMS.builder()
                .to(user.getNumber())
                .message("Welcome to your new gather")
                .build();
        Twilio.init("ACd967e6215bbafa46acab0d4e21365351", "e110727d7e6f0136352adbe7d6401851");

        Message message = Message.creator(new PhoneNumber(sms.getTo()), new PhoneNumber("+40755476663"), sms.getMessage())
                .create();
        System.out.println("here is my id:" + message.getSid());// Unique resource ID created to manage this transaction

    }

    public void receive(MultiValueMap<String, String> smscallback) {
    }
}