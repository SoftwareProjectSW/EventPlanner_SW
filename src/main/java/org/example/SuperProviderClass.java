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

   
    @Override
    public String toString() {
        return "(Name) :  " + name +"\n"+ " , ( Food):  " + food + "\n"+" , ( Song) : " + songs +"\n"+ " , ( Design ):  " + design +"\n"+ " , ( Decor) : " + decor +"\n"+ " , ( Music) : " + music;
    }
}
