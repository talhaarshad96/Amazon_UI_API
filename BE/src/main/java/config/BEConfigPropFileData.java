package config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang.RandomStringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BEConfigPropFileData {
    private static final Logger LOGGER = LoggerFactory.getLogger(BEConfigPropFileData.class);

    //    private static BEConfigPropFileData configdata;
    private static String firstName;
    private String lastName;
    private String totalPrice;
    private Boolean depositPaid;
    private String bookingDateCheckIn;
    private String bookingDateCheckOut;
    private String additionalNeeds;
    private String server;
    private String endpoint;

    public BEConfigPropFileData() {

        this.firstName = readPropertiesFile().getProperty("firstname");
        this.server = readPropertiesFile().getProperty("server");
        this.endpoint = readPropertiesFile().getProperty("endpoint");
        this.lastName = readPropertiesFile().getProperty("lastname");
        this.totalPrice = readPropertiesFile().getProperty("totalprice");
        this.depositPaid = readPropertiesFile().getProperty("depositpaid").isEmpty();
        this.bookingDateCheckIn = readPropertiesFile().getProperty("bookingdatesCheckin");
        this.bookingDateCheckOut = readPropertiesFile().getProperty("bookingdatesCheckOut");
        this.additionalNeeds = readPropertiesFile().getProperty("additionalneeds");
    }

//    public static BEConfigPropFileData getInstance() {
//        if (null == configdata) {
//            configdata = new BEConfigPropFileData();
//        }
//        return configdata;
//    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public Boolean getDepositPaid() {
        return depositPaid;
    }

    public String getBookingDateCheckIn() {
        return bookingDateCheckIn;
    }

    public String getBookingDateCheckOut() {
        return bookingDateCheckOut;
    }

    public String getAdditionalNeeds() {
        return additionalNeeds;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getServer() {
        return server;
    }

    public Properties readPropertiesFile() {
        FileInputStream fileInputStream;
        Properties prop = null;
        try {
            String filepath = System.getProperty("user.dir") + "/data/commondata.properties";
            File fileName = new File(filepath);
            fileInputStream = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fileInputStream);
        } catch (IOException ioException) {
            LOGGER.error("Error while reading properties file", ioException);
        }
        return prop;
    }
}