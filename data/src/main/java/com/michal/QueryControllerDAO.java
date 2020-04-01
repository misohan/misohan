package com.michal;

import java.util.Properties;

public interface QueryControllerDAO {

    void retrieveData(String statement);
    Properties readProperties();
}
