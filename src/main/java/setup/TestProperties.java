package setup;

import enums.PropertyFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

class TestProperties {

    Properties currentProps = new Properties();

    Properties getCurrentProps(PropertyFile prop) throws IOException {
        FileInputStream in = new FileInputStream(prop.getItem());
        currentProps.load(in);
        in.close();
        return currentProps;
    }

    protected String getProp(String propKey, PropertyFile prop) throws IOException {
        if (!currentProps.containsKey(propKey)) currentProps = getCurrentProps(prop);
        // "default" form used to handle the absence of parameter
        return currentProps.getProperty(propKey, null);
    }
}


