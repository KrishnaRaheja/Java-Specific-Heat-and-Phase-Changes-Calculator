import java.util.*; import java.lang.Math;
import javax.imageio.plugins.tiff.TIFFDirectory;
import javax.sound.sampled.SourceDataLine;
import java.math.*; import java.text.*;

public class SpecHeatPhaseChange {public static void main(String[]args) {
        
double SHice = 2.03;
double SHwater = 4.18;
double SHsteam = 1.99;

double Hfusion = 334;
double Hvaporizatoin = 2259;

Scanner gram = new Scanner(System.in);
System.out.println("How many grams: ");
double grams = gram.nextDouble();

Scanner T_start = new Scanner(System.in);
System.out.println("What was the initial temprature in C: ");
double T_initial = T_start.nextDouble();

Scanner T_end = new Scanner(System.in);
System.out.println("What was the final temprature in C: ");
double T_final = T_end.nextDouble();

double Normal_T = T_final - T_initial;
/**
 * if the object is ice heated to ice
 * elif the object heated is water to water
 * elif the oject heated is steam to steam
 * elif the object goes from ice to water
 * elif the object goes from ice to steam
 * elif the object goes from water to steam 
 */
//if no phase change
if (T_initial <= 0 && T_final <= 0) { 
    double mcat0 = grams * SHice * Normal_T;
    System.out.println(mcat0 + " Joules");
} 
    else if (T_initial > 0 && T_final > 0 && T_initial < 100 && T_final < 100) {
        double mcat1 = grams * SHwater * Normal_T;
        System.out.println(mcat1 + " Joules");
    }
//100 because it is boiling point
    else if (T_initial > 100 && T_final > 100) { 
        double mcat2 = grams * SHsteam * Normal_T;
        System.out.println(mcat2 + " Joules");
    }

    else if (T_initial < 0 && T_final < 100 && T_final > 0) {
        double DistanceFrom0C = Math.abs(T_initial); 
        //This takes the absolute value of the initial temprature, the distance from 0. This is for the first phase change.
        double mcat_to_0C = grams * SHice * DistanceFrom0C;
        double Phasechange0C = Hfusion * grams; //In Joules
        double mcat3 = grams * SHwater * (T_final - 0); 
        System.out.println(mcat3 + Phasechange0C + mcat_to_0C + " Joules");
    }

    else if (T_initial < 0 && T_final >= 100) {
        double DistanceFrom0C = Math.abs(T_initial); 
        //This takes the absolute value of the initial temprature, the distance from 0. This is for the first phase change.
        double mcat_to_0C = grams * SHice * DistanceFrom0C;
        double Phasechange0C = Hfusion * grams; //In Joules
        double Phasechange100C = Hvaporizatoin * grams;
        double mcat4 = grams * SHsteam * (T_final - 100); 
        System.out.println(mcat4 + Phasechange0C + Phasechange100C + mcat_to_0C + " Joules");
    }
    
    else if (T_initial > 0 && T_final > 100) {
        double DistanceFrom100C = 100 - T_initial;
        double mcat5 = grams * SHwater * DistanceFrom100C;
        double Phasechange100C = Hvaporizatoin * grams; //In Joules
        double mcat6 = grams * SHsteam * (T_final - 100);
        System.out.println(DistanceFrom100C + mcat5 + Phasechange100C + mcat6 + " Joules");
    }
}   
}      