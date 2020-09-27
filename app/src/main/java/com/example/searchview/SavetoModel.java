package com.example.searchview;

public class SavetoModel {

    String savename;
    String saveage;
    int saveroll;

    public SavetoModel() {
    }

    public SavetoModel(String savename, String saveage, int saveroll) {
        this.savename = savename;
        this.saveage = saveage;
        this.saveroll = saveroll;
    }

    public String getSavename() {
        return savename;
    }

    public void setSavename(String savename) {
        this.savename = savename;
    }

    public String getSaveage() {
        return saveage;
    }

    public void setSaveage(String saveage) {
        this.saveage = saveage;
    }

    public int getSaveroll() {
        return saveroll;
    }

    public void setSaveroll(int saveroll) {
        this.saveroll = saveroll;
    }
}
