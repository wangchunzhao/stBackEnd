package com.qhc.order.service;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.qhc.order.domain.Mail;

@Component
public class MailService {
	private static Logger logger = LoggerFactory.getLogger(MailService.class);

	@Value("${mail.debug}")
	private String debug;

	@Value("${mail.host}")
	private String host;
	@Value("${mail.username}")
	private String username;
	@Value("${mail.password}")
	private String password;
	@Value("${mail.smtp.from}")
	private String from;
	@Value("${mail.smtp.auth}")
	private String auth;
	@Value("${mail.smtp.timeout}")
	private String timout;
	@Value("${mail.smtp.port}")
	private String port;

	@Resource
	private MailService mailService;

	// Mail Server
	public boolean send(Mail mail) {
		boolean result = send(mail.getTo(), mail.getCc(), mail.getBcc(), mail.getFrom(),
				mail.getSubject(), mail.getBody(), mail.getHead(), mail.getAttachments());
		return result;
	}

	public boolean send(String to, String cc, String bcc, String from, String subject, String body, String head, Map<String, File> attachments) {
		if (head == null) {
			head = "text/html;charset=UTF-8";
		}
		boolean sendFlag = false;
		Properties properties = new Properties();
		properties.put("mail.debug", debug);
		properties.put("mail.host", host);
		properties.put("mail.username", username);
		properties.put("mail.password", password);
		properties.put("mail.smtp.from", from == null ? this.from : from);
		properties.put("mail.smtp.auth", auth);
		properties.put("mail.smtp.timeout", timout);
		properties.put("mail.smtp.port", port);

		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		if (this.debug.equals("true")) {
			session.setDebug(true);
		}
		Address[] toAddr = convert2InternetAddress(to);
		Address[] ccAddr = convert2InternetAddress(cc);
		Address[] bccAddr = convert2InternetAddress(bcc);
		MimeMessage msg = new MimeMessage(session);

		try {
			if (from == null || from.trim().length() == 0) {
				from = this.from;
			}
			msg.setFrom((Address) new InternetAddress(from));
			if (toAddr != null) {
				msg.setRecipients(Message.RecipientType.TO, toAddr);
			}
			if (bccAddr != null) {
				msg.setRecipients(Message.RecipientType.BCC, bccAddr);
			}
			if (ccAddr != null) {
				msg.setRecipients(Message.RecipientType.CC, ccAddr);
			}
			msg.setSubject(cleanHTMLTag(subject), "UTF-8");
			msg.setSentDate(new Date());
			Multipart multipart = createMultipart(body, head, attachments);
			if (multipart == null) {
				msg.setContent(body, head);
			} else {
				msg.setContent(multipart);
			}
			send(to, session, msg);
			sendFlag = true;
		} catch (Throwable e) {
			logger.error("send email failed", (Throwable) e);
			sendFlag = false;
		}
		return sendFlag;
	}

	private void send(String to, Session session, MimeMessage msg) throws MessagingException {
		Transport.send((Message) msg);
	}

	private Multipart createMultipart(String body, String head, Map<String, File> attachments)
			throws MessagingException {
		if (attachments != null && attachments.size() > 0) {
			MimeMultipart mimeMultipart = new MimeMultipart();
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setContent(body, head);
			mimeMultipart.addBodyPart((BodyPart) textBodyPart);
			for (Map.Entry<String, File> entry : attachments.entrySet()) {
				String name = entry.getKey();
				File f = entry.getValue();
				if (f.exists()) {
					MimeBodyPart messageBodyPart = new MimeBodyPart();
					String file = f.getAbsolutePath();
					DataSource source = new FileDataSource(file);
					messageBodyPart.setDataHandler(new DataHandler(source));
					try {
						messageBodyPart.setFileName(MimeUtility.encodeText(name));
					} catch (UnsupportedEncodingException e) {
						messageBodyPart.setFileName(name);
					}
					mimeMultipart.addBodyPart((BodyPart) messageBodyPart);
					continue;
				}
				logger.error("attachment file not found:" + f.getAbsolutePath());
			}

			return (Multipart) mimeMultipart;
		}
		return null;
	}

	private String cleanHTMLTag(String subject) {
		String noHTMLString = subject.replaceAll("\\<[^<>]*?>", "");
		return noHTMLString;
	}

	private Address[] convert2InternetAddress(String address) {
		Object[] arrayOfObject = null;
		if (address != null) {
			String[] addresses = address.split("[\\,;\\s]+");
			try {
				arrayOfObject = (Object[]) new InternetAddress[addresses.length];
				for (int i = 0; i < addresses.length; i++) {
					arrayOfObject[i] = new InternetAddress(addresses[i]);
				}
			} catch (AddressException e) {
				logger.error("convert2InternetAddress failed", (Throwable) e);
			}
		}

		return (Address[]) arrayOfObject;
	}
	
	/**
	 * 利用thremyleaf渲染邮件内容模板
	 * <p>
	 * ${contractCode}
	 * 
	 * @param templateName
	 * @param mode HTML5
	 * @param variables
	 * @return
	 */
	public static String render(String templateName, String mode, Map<String, Object> variables) {
		try (InputStream in = "".getClass().getResourceAsStream(templateName)) {
			byte[] data = new byte[5192];
			int size = 0;
			StringBuilder content = new StringBuilder(1024);
			while ((size = in.read(data)) > 0) {
				content.append(new String(data, "UTF-8"));
			}
			String h = content.toString().trim();
			for (Map.Entry<String, Object> e : variables.entrySet()) {
				String k = "\\$\\{" + e.getKey() + "\\}";
				String v = StringUtils.trimToEmpty((String)e.getValue());
				h = h.replaceAll(k, v);
//				while (h.indexOf(k) >= 0) {
//					h = h.re
//				}
			}
			
			logger.debug(h);
			
			return h;
		} catch (Exception e) {
			logger.error("render", e);
		} finally {
			
		}
		
		return "";
	}
}
