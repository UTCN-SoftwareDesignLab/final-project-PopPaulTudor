package com.lab4.demo.email;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.lab4.demo.UrlMapping.EMAIL;

@RestController
@RequestMapping(EMAIL)
@RequiredArgsConstructor
public class EmailController {

    public final EmailService emailService;

    @PostMapping
    public String sendEmail(@RequestBody String id) throws Exception {
        id = id.substring(0, id.length() - 1);
        long idLong = Long.valueOf(id);
        emailService.sendMail(idLong);
        return "Email sent";
    }
}
