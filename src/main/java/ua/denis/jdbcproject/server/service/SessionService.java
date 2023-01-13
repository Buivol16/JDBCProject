package ua.denis.jdbcproject.server.service;

import ua.denis.jdbcproject.server.db.DBHandler;
import ua.denis.jdbcproject.server.db.model.Session;
import ua.denis.jdbcproject.server.db.repository.impl.SessionRepository;
import ua.denis.jdbcproject.server.db.repository.impl.UserRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;

import static ua.denis.jdbcproject.client.loginapp.utils.ConstUtils.PATH_TO_KEY;

@Singleton
public class SessionService {
    @Inject
    private UserRepository userRepository;
    @Inject
    private SessionRepository sessionRepository;

    public void create(String username) {
        Session session = new Session(userRepository.findByUsername(username).getId());
        try {
            createFileSessionKey(session);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DBHandler.getInstance().saveEntity(session);
    }

    public void delete() {
        Session session = sessionRepository.getBySessionKey(getSkByFile());
        sessionRepository.deleteEntity(session);
        deleteFileSessionKey();
    }

    public String getSkByFile() {
        String sk = "";
        try {
            Scanner reader = new Scanner(new File(PATH_TO_KEY));
            while (reader.hasNext()) {
                sk = reader.next();
            }
            sk.substring(sk.indexOf("=") + 1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return sk;
    }

    public boolean isExpired() {
        boolean result = false;

        var session = sessionRepository.getBySessionKey(getSkByFile());
        Timestamp dateNow = Timestamp.valueOf(LocalDateTime.now());
        return session.getExpiredAt().after(dateNow);

    }

    private void createFileSessionKey(Session sessionObject) throws IOException {
        File file = new File(PATH_TO_KEY);
        PrintWriter pw = new PrintWriter(file);
        pw.println(sessionObject.getUserId() + " = " + sessionObject.getSessionKey());
        pw.close();
        System.out.println("File with session key is created.");
    }

    private void deleteFileSessionKey() {
        if (new File(PATH_TO_KEY).delete()) {
            System.out.println("The key.sk is successfully deleted.");
        }
    }
}
