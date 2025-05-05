package SpringBoot.College_Management.Security_Section.Sending_Emails;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class Send_Email  {

    private final JavaMailSender javaMailSender;

    public void sendEmailForNewGeneratedSecretCode(String toEmail, String subject, String body) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();


            simpleMailMessage.setTo(toEmail);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(body);

            javaMailSender.send(simpleMailMessage);
            log.info("Email sent Successfully");
        }catch (Exception e){
            log.info("email cannot send "+e.getMessage());
        }
    }


    public void sendEmailToMultiple(String[] toEmail, String subject, String body) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setTo(toEmail);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(body);

            javaMailSender.send(simpleMailMessage);
            log.info("Email sent Successfully");
        }catch (Exception e){
            log.info("email cannot send "+e.getMessage());
        }
    }
}
