package com.example.projectdoomsday;

public class Item {

    String ID;
    String title;

    public Item(String ID, String title)
    {
        this.ID = ID;
        this.title = title;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
