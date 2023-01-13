package ua.denis.jdbcproject.server.service;

import ua.denis.jdbcproject.server.db.DBHandler;
import ua.denis.jdbcproject.server.db.model.Session;
import ua.denis.jdbcproject.server.db.repository.impl.SessionRepository;
import ua.denis.jdbcproject.server.db.repository.impl.UserRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;

import static ua.denis.jdbcproject.client.loginapp.utils.ConstUtils.PATH_TO_KEY;


public class SessionService {
    private static SessionService INSTANCE = null;

    private SessionService(){}

    public static SessionService getInstance(){
        if (INSTANCE == null) INSTANCE = new SessionService();
        return INSTANCE;
    }

    public void create(String username) {
        Session session = new Session(UserRepository.getInstance().findByUsername(username).getId());
        try {
            createFileSessionKey(session);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DBHandler.getInstance().saveEntity(session);
    }

    public void delete() {
        Session session = SessionRepository.getInstance().getBySessionKey(getSkByFile());
        SessionRepository.getInstance().deleteEntity(session);
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

        var session = SessionRepository.getInstance().getBySessionKey(getSkByFile());
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
