package sg.edu.nus.iss.ssf13_workshoprevision.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;

import sg.edu.nus.iss.ssf13_workshoprevision.model.Contact;

@Service
public class ContactService {
    
    public void saveContact(Contact contact, String dataDir) throws IOException {
        
        String filePath = createNewFile(contact.getId(), dataDir);
        FileWriter fw = new FileWriter(new File(filePath));
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

    public String createNewFile(String id, String dataDir) throws IOException {

        String filePath = dataDir + File.separator + id + ".txt";
        File newFile = new File(filePath);
        newFile.createNewFile();

        return filePath;
    }
}
