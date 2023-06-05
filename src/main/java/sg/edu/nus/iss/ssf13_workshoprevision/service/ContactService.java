package sg.edu.nus.iss.ssf13_workshoprevision.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.ssf13_workshoprevision.model.Contact;

@Service
public class ContactService {
    
    @Value("${data.dir}")
    private String dataDir;

    public void saveContact(Contact contact) throws IOException {
        
        createNewFile(contact.getId());
        FileWriter fw = new FileWriter(new File(getFilePath(contact.getId())));
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write(contact.getId());
        bw.newLine();
        bw.write(contact.getName());
        bw.newLine();
        bw.write(contact.getEmail());
        bw.newLine();
        bw.write(contact.getPhoneNumber());
        bw.newLine();
        bw.write(contact.getDateOfBirth().toString());

        bw.flush();
        bw.close();
        fw.close();
    }

    public void createNewFile(String id) throws IOException {

        File newFile = new File(getFilePath(id));
        newFile.createNewFile();
    }

    public String getFilePath(String id) {
        return dataDir + File.separator + id + ".txt";
    }

    public Contact getContactById(String id) throws IOException {

        Contact contact = new Contact();
        FileReader fr = new FileReader(new File(getFilePath(id)));
        BufferedReader br = new BufferedReader(fr);
        
        contact.setId(br.readLine());
        contact.setName(br.readLine());
        contact.setEmail(br.readLine());
        contact.setPhoneNumber(br.readLine());
        contact.setDateOfBirth(LocalDate.parse(br.readLine()));

        return contact;
    }
}
