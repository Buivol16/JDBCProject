package ua.denis.jdbcproject.loginapp.session.service;

import ua.denis.jdbcproject.db.DBHandler;
import ua.denis.jdbcproject.db.DbHelper;
import ua.denis.jdbcproject.db.repository.SessionRepository;
import ua.denis.jdbcproject.db.repository.UserRepository;
import ua.denis.jdbcproject.loginapp.session.model.Session;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

import static ua.denis.jdbcproject.loginapp.utils.ConstUtils.PATH_TO_KEY;
import static ua.denis.jdbcproject.loginapp.utils.DatabaseConstUtils.SessionColumnConsts.dtf;

public class SessionService {
  private static SessionService INSTANCE = null;

  private SessionService() {}

  public static SessionService getInstance() {
    if (INSTANCE == null) INSTANCE = new SessionService();
    return INSTANCE;
  }

  public void create(String username) throws SQLException {
    Session session = new Session(UserRepository.getUserByUsername(username).getId());
    try {
      createFileSessionKey(session);
    } catch (IOException e) {
      e.printStackTrace();
    }
    DBHandler.getInstance().saveEntity(session);
  }

  public void delete() {
    SessionRepository.deleteBySessionKey(getSkByFile());
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
    try {
      String dateFromBase = DbHelper.getInstance().getSessionExpired_at(getSkByFile());
      String dateNow = LocalDateTime.now().format(dtf);
      if (dateFromBase.compareTo(dateNow) > 0) {
        result = false;
      } else {
        result = true;
      }
    } catch (SQLException exception) {
    }
    return result;
  }

  private void createFileSessionKey(Session sessionObject) throws IOException {
    File file = new File(PATH_TO_KEY);
    PrintWriter pw = new PrintWriter(file);
    pw.println(sessionObject.getUserId() + " = " + sessionObject.getSessionKey());
    pw.close();
    System.out.println("File with session key is created.");
  }

  private void deleteFileSessionKey(){
    if (new File(PATH_TO_KEY).delete()){
      System.out.println("The key.sk is successfully deleted.");
    }
  }
}
