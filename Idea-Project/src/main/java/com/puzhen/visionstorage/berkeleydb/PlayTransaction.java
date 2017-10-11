package com.puzhen.visionstorage.berkeleydb;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.PrimaryIndex;
import com.sleepycat.persist.StoreConfig;

import java.io.File;

public class PlayTransaction {

    private static final int NUM_OF_PIXELS = 900;

    public static void main(String[] args) {
        Environment myEnv;
        EntityStore store;

        try {
            EnvironmentConfig myEnvConfig = new EnvironmentConfig();
            StoreConfig storeConfig = new StoreConfig();

            myEnvConfig.setAllowCreate(true);
            storeConfig.setAllowCreate(true);

            myEnv = new Environment(new File("30x30Image"), myEnvConfig);
            store = new EntityStore(myEnv, "EntityStore", storeConfig);

            PrimaryIndex<String, Distance> index = store.getPrimaryIndex(String.class, Distance.class);
            //index.put(new Distance("123", 1));
            //System.out.println(index.get("123").toString());
            for (int i = 0; i < NUM_OF_PIXELS - 1; i++) {
                for (int j = i + 1; j < NUM_OF_PIXELS; j++) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(i);
                    sb.append(" ");
                    sb.append(j);
                    String key = sb.toString();
                    System.out.println("Putting " + key);
                    index.put(new Distance(key, 123));
                }
            }
            store.close();
            myEnv.close();
        } catch (DatabaseException dbe) {
            dbe.printStackTrace();
        }
    }
}
