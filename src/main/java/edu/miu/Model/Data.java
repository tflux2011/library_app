package edu.miu.Model;

import java.util.ArrayList;
import java.util.List;

import edu.miu.Model.Auth;

public class Data {
    public static final Data INSTANCE = new Data();
    public static final String MESSIAH_OF_DUNE = "Messiah Of Dune";
    public static final String GONE_WITH_THE_WIND = "Gone With The Wind";
    public static final String GARDEN_OF_RAMA = "Garden of Rama";
    public static List<String> bookTitles = new ArrayList<String>(){
        {
            this.add(Data.MESSIAH_OF_DUNE);
            this.add(Data.GONE_WITH_THE_WIND);
            this.add(Data.GARDEN_OF_RAMA);
        }
    };
    public static Auth currentAuth = null;
    public static List<User> logins = new ArrayList<User>(){
        {
            this.add(new User("Joe", "111", Auth.LIBRARIAN));
            this.add(new User("Ann", "101", Auth.ADMIN));
            this.add(new User("Dave", "102", Auth.BOTH));
        }
    };

    private Data() {
    }

    public static void addBookTitle(String title) {
        bookTitles.add(title);
    }
}

