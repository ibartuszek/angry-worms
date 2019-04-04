package hu.elte.angryworms;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import processing.core.PApplet;

/**
 * The purpose of this type is to
 */
public class Visualization extends PApplet {

    public static void main(String[] args) {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "app.properties";
        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(appConfigPath));
            String backgroundColor = appProps.getProperty("background-color");
            // System.out.println("background color: " + backgroundColor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // PApplet.main("hu.elte.angryworms.Visualization");
    }

    @Override
    public void settings() {
        size(640, 480);
    }

    @Override
    public void setup() {
        fill(120,50,240);
    }

    @Override
    public void draw(){
        ellipse(width/2,height/2,second(),second());
    }
}