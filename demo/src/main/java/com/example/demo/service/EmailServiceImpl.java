package com.example.demo.service;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Base64;
import javax.activation.DataHandler;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.email.EmailDetails;
import com.example.email.EmailService;

// Annotation
@Service
// Class
// Implementing EmailService interface
public class EmailServiceImpl implements EmailService {
 
    @Autowired private JavaMailSender javaMailSender;
 
    @Value("${spring.mail.username}") private String sender;

    public EmailServiceImpl() {
    }

    // Method 1
    // To send a simple email
    public String sendSimpleMail(EmailDetails details)
    {
 
        // Try block to check for exceptions
        try {
 
            // Creating a simple mail message
            SimpleMailMessage mailMessage
                = new SimpleMailMessage();
 
            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());
 
            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }
 
        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }
 
    // Method 2
    // To send an email with attachment
    public String
    sendMailWithAttachment(EmailDetails details)
    {
        // Creating a mime message
        MimeMessage mimeMessage
            = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
 
        try {
 
            // Setting multipart as true for attachments to
            // be send
            mimeMessageHelper
                = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(
                details.getSubject());
 
            // Adding the attachment
            FileSystemResource file
                = new FileSystemResource(
                    new File(details.getAttachment()));
 
            mimeMessageHelper.addAttachment(
                file.getFilename(), file);
 
            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }
 
        // Catch block to handle MessagingException
        catch (MessagingException e) {
 
            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    }

    public void sendActivationEmail(String toEmail, String activationCode) {
        String subject = "Activate Your Account";
        String message = "<html><body>" +
                "<h2>Activate Your Account</h2>" +
                "<p>Click the following link to activate your account:</p>" +
                "<a href=\"http://localhost:8081/api/user/activate/" + activationCode + "\">Activate Account</a>" +
                "</body></html>";

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        try {
            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(message, true); // Set the second parameter to true for HTML content
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    public void successActivation(String toEmail) {
        String subject = "Account Activation";
        String message = "<html><body>" +
                "<h2>Account Activation</h2>" +
                "<p>Your account is successfully activated. You can login by clicking the link below:</p>" +
                "<a href=\"http://localhost:4200/login\"><b>Login</b></a>" +
                "</body></html>";

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        try {
            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(message, true); // Set the second parameter to true for HTML content
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    public void sendQRCodeEmailWithAttachment(String recipientEmail, Long appointmentId, String QRCodePath) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        try {
            mimeMessageHelper.setTo(recipientEmail);
            mimeMessageHelper.setSubject("QR Code for appointment");

            String messageText = "This email contains a QR code with details related to the appointment.";
            mimeMessageHelper.setText(messageText);

            String imagePath = QRCodePath;

            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                // Create a MimeMultipart to hold text and attachment
                MimeMultipart multipart = new MimeMultipart();

                // Create a MimeBodyPart for the text
                MimeBodyPart textPart = new MimeBodyPart();
                textPart.setText(messageText);

                // Create a MimeBodyPart for the attachment (image)
                MimeBodyPart imagePart = new MimeBodyPart();
                imagePart.setDataHandler(new DataHandler(imageFile.toURI().toURL()));
                imagePart.setFileName(imageFile.getName());

                // Add parts to the multipart
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(imagePart);

                // Set the multipart as the content of the MimeMessage
                mimeMessage.setContent(multipart);

                // Send the email
                javaMailSender.send(mimeMessage);
            } else {
                // Handle the case when the image file does not exist
                System.out.println("Image file does not exist for appointment ID: " + appointmentId);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


}
