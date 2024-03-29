package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class AddContact extends AppiumConfig {

    @BeforeClass
    public void precondition(){
        new AuthenticationScreen(driver)
                // .fillLoginRegistrationForm(Auth.builder().email("john@gmail.com").password("Aa12345!").build())
                .fillLoginRegistrationForm(Auth.builder().email("pokh@1i.ua").password("Yyp12345!").build())
                .submitLogin()
                .isContactListActivityDisplayed();
    }

    @Test
    public void addNewContactSuccess(){
        // if contacts>6 ---> delete all
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Homer")
                .lastName("Simpson")
                .email("homer"+i+"@gmail.com")
                .phone("1234512345"+i)
                .address("Springfield")
                .description("Friend").build();

                 new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByNameLastName(contact.getName(), contact.getLastName())
                .isContactAddedByPhone(contact.getPhone());

    }

    @Test
    public void addNewContactEmptyName() {
        Contact contact = Contact.builder()
                .lastName("Simpson")
                .email("homer@gmail.com")
                .phone("123451234500")
                .address("Springfield")
                .description("Friend").build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorMessageDisplayed("name=must not be blank")
               .returnToContactList()
                .logout();


    }
    @Test
    public void addNewContactEmptyLastName() {
        Contact contact = Contact.builder()
                .name("Homer")
                .email("homer@gmail.com")
                .phone("123451234500")
                .address("Springfield")
                .description("Friend").build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorMessageDisplayed("lastName=must not be blank")
                 .returnToContactList()
                .logout();


    }

    @AfterClass
    public void postCondition(){
        new ContactListScreen(driver)
                .logout();
    }

}