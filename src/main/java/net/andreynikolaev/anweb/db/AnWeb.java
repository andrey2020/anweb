package net.andreynikolaev.anweb.db;

import net.andreynikolaev.anweb.db.auto._AnWeb;

public class AnWeb extends _AnWeb {

    private static AnWeb instance;

    private AnWeb() {}

    public static AnWeb getInstance() {
        if(instance == null) {
            instance = new AnWeb();
        }

        return instance;
    }
}
