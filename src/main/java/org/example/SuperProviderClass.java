package org.example;

public class SuperProviderClass {
    String name,food,songs,design,decor,music;

    public SuperProviderClass(String name, String food, String songs, String design, String decor, String music) {
        this.name = name;
        this.food = food;
        this.songs = songs;
        this.design = design;
        this.decor = decor;
        this.music = music;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public void setSongs(String songs) {
        this.songs = songs;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public void setDecor(String decor) {
        this.decor = decor;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getFood() {
        return food;
    }

    public String getSongs() {
        return songs;
    }

    public String getDesign() {
        return design;
    }

    public String getDecor() {
        return decor;
    }

    public String getMusic() {
        return music;
    }
    @Override
    public String toString() {
        return "(Name) :  " + name +"\n"+ " , ( Food):  " + food + "\n"+" , ( Song) : " + songs +"\n"+ " , ( Design ):  " + design +"\n"+ " , ( Decor) : " + decor +"\n"+ " , ( Music) : " + music;
    }
}
