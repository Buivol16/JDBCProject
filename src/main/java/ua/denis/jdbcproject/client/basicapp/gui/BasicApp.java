package ua.denis.jdbcproject.client.basicapp.gui;

import ua.denis.jdbcproject.client.exception.BlankFieldException;
import ua.denis.jdbcproject.client.loginapp.common.LabelHandler;
import ua.denis.jdbcproject.client.model.Car;
import ua.denis.jdbcproject.client.service.impl.FieldValidatorService;
import ua.denis.jdbcproject.server.db.repository.impl.CarRepository;
import ua.denis.jdbcproject.server.db.repository.impl.SessionRepository;
import ua.denis.jdbcproject.server.service.SessionService;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.util.List;

public class BasicApp extends JFrame {
  private int backPageIndex = 0;

  private static BasicApp INSTANCE = null;
  private JPanel mainPanel;
  private JTabbedPane pages;
  private JButton newOfferButton;
  private JPanel mainPage;
  private JPanel createPage;
  private JPanel profilePage;
  private JTextField textField1;
  private JButton myProfileButton;
  private JButton newOfferBackButton;
  private JButton profileBackButton;
  private JButton letSSeeButton;
  private JButton uploadPhotoButton;
  private JList carList;
  private JTextField textField4;
  private JButton createButton;
  private JButton createANewCarButton;
  private JTextField powerField;
  private JTextField brandField;
  private JTextField modelField;
  private JTextField seatsField;
  private JList fuelList;
  private JTextField volumeField;
  private JList transmissionList;
  private JTextField baggageMassField;
  private JPanel creatingNewCar;
  private JList bodyList;
  private JButton createCarButton;
  private JLabel errorLabel;
  private JButton backCarButton;
  private JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView());
  private FileNameExtensionFilter imageFilter =
      new FileNameExtensionFilter(
          "Image files", "tif, tiff, bmp, jpg, jpeg, png, raw, cr2, nef, orf, sr2");

  private CarRepository carRepository = CarRepository.getInstance();
  private FieldValidatorService fieldHandlerService = FieldValidatorService.getInstance();
  private SessionRepository sessionRepository = SessionRepository.getInstance();
  private SessionService sessionService = SessionService.getInstance();

  private BasicApp() {
    setSize(1400, 1000);
    setTitle("Car rent app");
    setContentPane(mainPanel);
    newOfferButton.addActionListener(e -> toPage(1));
    myProfileButton.addActionListener(e -> toPage(2));
    createANewCarButton.addActionListener(e -> toPage(3));
    newOfferBackButton.addActionListener(e -> back(0));
    profileBackButton.addActionListener(e -> back());
    backCarButton.addActionListener(e -> back());
    uploadPhotoButton.addActionListener(e -> choosePhoto());
    createCarButton.addActionListener(
        e -> {
          try {
            fieldHandlerService.checkField(baggageMassField, brandField, modelField, powerField, seatsField, volumeField);
            fieldHandlerService.checkList(bodyList, fuelList, transmissionList);
            Car car = Car.builder()
                    .brand(brandField.getText())
                    .seatsCount(Integer.parseInt(seatsField.getText()))
                    .bodyType(Car.BodyType.valueOf(bodyList.getSelectedValue().toString()))
                    .fuelType(Car.FuelType.valueOf(fuelList.getSelectedValue().toString()))
                    .volume(Integer.parseInt(volumeField.getText()))
                    .baggageMass(Integer.parseInt(baggageMassField.getText()))
                    .model(modelField.getText())
                    .transmission(Car.Transmission.valueOf(transmissionList.getSelectedValue().toString()))
                    .power(Integer.parseInt(powerField.getText()))
                    .build();
          } catch (BlankFieldException exception) {
            LabelHandler.setError(errorLabel, "Not all is blank");
            exception.printStackTrace();
          }
        });
    setDefaultCloseOperation(3);
    setVisible(true);
  }

  public static BasicApp getInstance() {
    if (INSTANCE == null) INSTANCE = new BasicApp();
    return INSTANCE;
  }

  private void toPage(int indexPage) {
    backPageIndex = pages.getSelectedIndex();
    pages.setSelectedIndex(indexPage);
    if (indexPage == 1) getCarsList();
  }

  private void back() {
    pages.setSelectedIndex(backPageIndex);
    if (pages.getSelectedIndex() == 1) getCarsList();
  }

  private void back(int index) {
    pages.setSelectedIndex(index);
    if (pages.getSelectedIndex() == 1) getCarsList();
  }

  private void choosePhoto() {
    jFileChooser.showSaveDialog(null);
    jFileChooser.addChoosableFileFilter(imageFilter);
  }

  private void getCarsList() {
    //todo make like http request to server. Meaning to make server-side and client-side correctly
    String sk = sessionService.getSkByFile();
    long userId = sessionRepository.getBySessionKey(sk).getUserId();
    List<Car> cars = carRepository.findByAuthorId(userId);
    DefaultListModel<String> dfl = new DefaultListModel<>();
    for (Car car : cars) {
      dfl.addElement(car.getCar());
    }
    carList.setModel(dfl);
  }
}
