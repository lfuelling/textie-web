package net.k40s;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * Created by jsiebert on 28.11.14.
 */
public class Configs extends BasicDBObject {
    private String name;
    private String mist;

    public String getMist() {
        return mist;
    }

    public void setMist(String mist) {
        this.mist = mist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
