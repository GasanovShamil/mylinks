package utils;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Named
@ApplicationScoped
public class MailUtil {

	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;

	

	public void sendEmail(String email, String name, String confirmToken, int check) {
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		try {
			generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			generateMailMessage.setSubject("MYLINKS");
			String emailBody;
			if (check == 0) {
				emailBody = "<h1>Confirmation email from Mylinks.</h1><br>"
						+ "<h2>Click <a href=\"http://localhost:8080/mylinks/createUser?email="
						+ email + "&confirmToken=" + confirmToken + "\">here</a> to confirm your email.</h2>";
			} else {
				emailBody = "<h1>Bonjour " + name + "!</h1>" + "<br><P>Voici votre nouveau mot de passe : " + confirmToken
						+ "</p>" + "</html> ";
			}
			generateMailMessage.setContent(emailBody, "text/html");

			Transport transport = getMailSession.getTransport("smtp");

			transport.connect("smtp.gmail.com", "mylinks.mailer@gmail.com", "mylinksuser");
			transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
			transport.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
