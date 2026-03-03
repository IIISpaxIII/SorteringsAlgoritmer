import java.util.TreeMap;

// Key är själva talet medan Value är hur många gånger det stöttes.
public class TreeMapSorter implements IntSorter {
    @Override
    public void sort(int[] a) {
        //TreeMap är en symbolltabell.
        // DEn sorterar automatiskt alla nycklar i storleksordning.
        TreeMap<Integer, Integer> counts = new TreeMap<Integer, Integer>();

        // jag räknar förekomsten av varje tal genom att iterera genom hela arrayen.
        for (int i = 0; i < a.length; i++) {
            int tal = a[i];

            if (counts.containsKey(tal) == false) {

                counts.put(tal, 1);
            } else {
                int gammalAntal = counts.get(tal);
                counts.put(tal, gammalAntal + 1); //ökar antalet förekomsten för denna tal, dvs key.
            }
        }

        // skriver tillbaka i ordning.
        int i = 0;

        // counts.keySet() innehåller alla Key i treeMap.
        // jag går igenom alla nycklar i treeMap
        for (Integer Key : counts.keySet()) {
            int antalFörekomst = counts.get(Key);
            // en loop till för att skriva keys som element i en array.
            for (int j = 0; j < antalFörekomst; j++) {
                a[i] = Key;
                i++;
            }
        }

    }
}