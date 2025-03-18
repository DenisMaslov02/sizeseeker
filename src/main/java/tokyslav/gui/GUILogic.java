package tokyslav.gui;

import java.util.ArrayList;
import java.util.List;

import tokyslav.Fileobject;

public class GUILogic {
    
    public static int[] calculatePercentage(Fileobject[] p_listOfObjects){
        Fileobject[] listToSort = p_listOfObjects;
        int highestSize = 0;
        List<Integer> listOfPercentages = new ArrayList<>();
        
        for (Fileobject i : listToSort) {
            // ich muss hier einfach alle Größen Addieren um Phillips Idee umzusetzten
            // int actualNumber = i.getSize();
            int actualNumber = 10000;
            highestSize += actualNumber;
        }

        for (Fileobject i : listToSort) {
            // calculatePercentage(highestSize, i.getSize());
            listOfPercentages.add(ruleOfThree(highestSize, 6));
        }
      
        // GPT Answere:
        int[] myIntegerArray = listOfPercentages.stream()
                  .mapToInt(Integer::intValue)
                  .toArray();
        return myIntegerArray;
    }

    // der Dreisatz^^
    private static int ruleOfThree(int maxNumber, int numberToCalculate){
        int a = numberToCalculate * 100;
        int b = a/maxNumber;
        return b;
    }
}
