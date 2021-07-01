package com.lab4.demo;

import com.google.type.DateTime;
import com.lab4.demo.announcement.AnnouncementRepository;
import com.lab4.demo.announcement.model.Announcement;
import com.lab4.demo.gather.GatherRepository;
import com.lab4.demo.gather.model.Gather;
import com.lab4.demo.pair.PairRepository;
import com.lab4.demo.pair.model.Pair;
import com.lab4.demo.security.AuthService;
import com.lab4.demo.security.dto.SignupRequest;
import com.lab4.demo.user.RoleRepository;
import com.lab4.demo.user.UserRepository;
import com.lab4.demo.user.model.ERole;
import com.lab4.demo.user.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import org.springframework.stereotype.Component;

import java.util.Calendar;

import static com.lab4.demo.utills.CalendarUtills.getStringOfMillis;


@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;

    private final GatherRepository gatherRepository;

    private final UserRepository userRepository;

    private final PairRepository pairRepository;

    private final AnnouncementRepository announcementRepository;

    private final AuthService authService;


    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (bootstrap) {
            userRepository.deleteAll();
            roleRepository.deleteAll();
            gatherRepository.deleteAll();
            pairRepository.deleteAll();
            announcementRepository.deleteAll();

            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }
            authService.register(SignupRequest.builder()
                    .email("email@gmail.com")
                    .username("username")
                    .password("password")
                    .number("+40755476663")
                    .build());
            authService.register(SignupRequest.builder()
                    .email("email2@email.com")
                    .username("username2")
                    .password("password")
                    .number("+40755476663")
                    .build());
            gatherRepository.save(Gather.builder()
                    .dateCreated(getStringOfMillis(Calendar.getInstance().getTimeInMillis()))
                    .subject("Group Subject 1")
                    .title("Group Title 1")
                    .build());

            gatherRepository.save(Gather.builder()
                    .dateCreated(getStringOfMillis(Calendar.getInstance().getTimeInMillis()))
                    .subject("Group Subject 2")
                    .title("Group Title 2")
                    .build());

            announcementRepository.save(Announcement.builder()
                    .content("Content 1")
                    .dateSent(getStringOfMillis(Calendar.getInstance().getTimeInMillis()))
                    .expeditorId(1L)
                    .gatherId(1L)
                    .subject("Subject 1")
                    .title("Title 1")
                    .build());

            announcementRepository.save(Announcement.builder()
                    .content("Content 2")
                    .dateSent(getStringOfMillis(Calendar.getInstance().getTimeInMillis()))
                    .expeditorId(1L)
                    .gatherId(1L)
                    .subject("Subject 2")
                    .title("Title 2")
                    .build());

            pairRepository.save(Pair.builder()
                    .gatherId(1L)
                    .points(5L)
                    .userId(1L)
                    .userRole("Admin")
                    .build());
        }
    }


}
