package utils;

import com.github.javafaker.Faker;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class Utils {
    public static void setEnvVariable(String key, String value) throws ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/config.properties");
        config.setProperty(key, value);
        config.save();
    }
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void generateRandomUser(){
        Faker faker = new Faker();
        setName(faker.name().fullName());
        setEmail(faker.internet().emailAddress());

    }
    public String generatePhoneNumber(){
        String prefix = "01844";
        int min = 100000;
        int max = 999999;
        int randomNumber =  (int)Math.round(Math.random()*(max-min)+min);
        return prefix+randomNumber;
    }
}
