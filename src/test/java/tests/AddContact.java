package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class AddContact extends AppiumConfig {

    @BeforeMethod
    public void precondition(){
        new AuthenticationScreen(driver)
                // .fillLoginRegistrationForm(Auth.builder().email("john@gmail.com").password("Aa12345!").build())
                .fillLoginRegistrationForm(Auth.builder().email("pokh@1i.ua").password("Nnoa12345$").build())
                .submitLogin()
                .isContactListActivityDisplayed();
    }

    @Test
    public void addNewContactSuccess(){
        // if contacts>6 ---> delete all
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Lisa")
                .lastName("Simpson")
                .email("lisa"+i+"@gmail.com")
                .phone("1234512345"+i)
                .address("Springfield")
                .description("Friend").build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByNameLastName(contact.getName(),contact.getLastName())
                .isContactAddedByPhone(contact.getPhone())
                .logout();
    }

    @Test
    public void addNewContactEmptyName() {
        Contact contact = Contact.builder()
                .lastName("Simpson")
                .email("lisa@gmail.com")
                .phone("123451234500")
                .address("Springfield")
                .description("Friend").build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative();


    }
    @Test
    public void addNewContactEmptyLastName() {

    }

}