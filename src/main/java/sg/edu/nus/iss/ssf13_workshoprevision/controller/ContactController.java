package sg.edu.nus.iss.ssf13_workshoprevision.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sg.edu.nus.iss.ssf13_workshoprevision.model.Contact;
import sg.edu.nus.iss.ssf13_workshoprevision.service.ContactService;

@Controller
@RequestMapping("/api")
public class ContactController {

    @Value("${data.dir}")
    String dataDir;
    
    private ContactService service;
    
    @Autowired
    public ContactController(ContactService service) {
        this.service = service;
    }

    @GetMapping(path = "/home")
    public String getLandingPage(Model model) {

        model.addAttribute("contact", new Contact());
        return "home";
    }

    @PostMapping(path = "/contact")
    public String addNewContact(@Valid Contact contact, BindingResult binding, Model model) throws IOException {

        System.out.println("Name: " + contact.getName());
        System.out.println("Email: " + contact.getEmail());
        System.out.println("Phone Number: " + contact.getPhoneNumber());
        System.out.println("Date of Birth: " + contact.getDateOfBirth());
        System.out.println("Age: " + contact.getAge());
        System.out.println("Id: " + contact.getId());

        if (binding.hasErrors())
            return "home";

        service.saveContact(contact);
        model.addAttribute("successMsg", "Contact saved sucessfully " + HttpStatus.CREATED);
        model.addAttribute("contact", contact);

        return "contact";
    }

    @GetMapping("/contact/{contactId}")
    public String getContact(@PathVariable String contactId, Model model) throws IOException {

        model.addAttribute("contact", service.getContactById(contactId));
        return "contact";
    }
}
