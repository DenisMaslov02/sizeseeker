package tokyslav.gui;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import tokyslav.Fileobject;

public class GUILogic {
    
    public static int[] calculatePercentage(Fileobject[] p_listOfObjects){
        Fileobject[] listToSort = p_listOfObjects;
        long highestSize = 0;
        List<Integer> listOfPercentages = new ArrayList<>();
        
        for (Fileobject i : listToSort) {
            long actualNumber = i.getSize();
            highestSize += actualNumber;
        }
        for (Fileobject i : listToSort) {
            listOfPercentages.add(ruleOfThree(highestSize, i.getSize()));
        }
      
        // GPT Answere:
        int[] myIntegerArray = listOfPercentages.stream()
                  .mapToInt(Integer::intValue)
                  .toArray();
        return myIntegerArray;
    }

    // der Dreisatz^^
    private static int ruleOfThree(long maxNumber, long numberToCalculate){
        long a = numberToCalculate * 100;
        long b = a/maxNumber;
        return (int) b;
    }

    public static String calculateSizeDisplayNumber(long fileSizeToCalculate){
        float size = fileSizeToCalculate / 1024;
        String textToAdd = "KB";
        
        // System.out.println();
        if(isFourDigitsNumber(size)){
            textToAdd = "MB";
            size = size / 1024;
        }
        if(isFourDigitsNumber(size)){
            textToAdd = "GB";
            size = size / 1024;
        }
        DecimalFormat df = new DecimalFormat("0.00");
        String textToDisplay = df.format(size) + textToAdd;
        return textToDisplay;
    }
    private static boolean isFourDigitsNumber(float numberToCheck){
        int wholeNumber = (int) Math.abs(numberToCheck); // Vorkommastellen extrahieren
        int amountOfDigits = (int) Math.log10(wholeNumber) + 1;
        return amountOfDigits >= 4;
    }

    public static Color evaluateColor(int percentageOfSize){
        Color returnColor = null;
        if(percentageOfSize <= 10){
            returnColor = Color.green;
        }
        if(percentageOfSize > 10){
            returnColor = Color.cyan;
        }
        if(percentageOfSize >= 80){
            returnColor = Color.red;
        }
        return returnColor;
    }
}
