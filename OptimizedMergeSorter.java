import java.lang.Math;


public class OptimizedMergeSorter implements IntSorter{

    private int[] extraLagringsArray;


    private int M = 41;


    @Override
    public void sort(int[] a) {


        extraLagringsArray = new int[a.length];
        // arrayen (a) ska sorteras från början (0) till slut index ( a.lenght -1)
        mergeSort(a, 0, a.length -1);
    }

    //rekursiva sort metod för mergeSort

    private void mergeSort(int[] a, int lo, int hi){

        //om antal element är <= med M använd insertion sort. som är mer effektivare i mindre listor.
        if(hi-lo +1 <= M){
            insertionSort(a, lo, hi);
            return;
        }



        int mid = lo + Math.abs((hi-lo) /2); // t.ex. 2.88 avrundas nedåt. behgöve rinte använda .floor.
        mergeSort(a, lo, mid); // tar intervallet (lower - mid). körs tills vänstra halvan klart.
        mergeSort(a, mid +1, hi); // tar högra intervallet. körs när koden ovan klart.

        //sammanställer halvorna
        merge(a, lo, mid, hi);
    }

    // den tar lo och hi
    private void insertionSort(int[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && a[j] < a[j-1]; j--) {
                int x = a[j];
                a[j] = a[j-1];
                a[j-1] = x;
            }
        }
    }

    private void merge(int[] a, int lo, int mid, int hi){

        // elementen i "a" kopieras till "extralagringsArray"
        // (Kopiera över allt – från position lo till hi – till extralagringsarray.)
        for (int kopiaIndex = lo; kopiaIndex <= hi; kopiaIndex++){
            extraLagringsArray[kopiaIndex] = a[kopiaIndex];
        }

        // pekarna för start indexet i vänter (lo) och högra halvan (mid+1)
        // Låt i börja på lo (början på den vänstra delen) och j på mid + 1 (början på den högra).
        /**
         * (Uppgift)
         * "i" börjar på lo (början på den vänstra delen) och
         * "j" på mid + 1 (början på den högra).
         */
        int i = lo;
        int j = mid + 1;


        for (int k = lo; k <= hi; k++){

            // När vi inte har flera platser i vänstra delen,
            // dvs själva arrayens length var inte jämn då ena sidan av halvor hade extra element

            /**
             * (Uppgift)
             * Om i är större än mid (alltså vänstra delen är slut),
             * tar vi värdet från position j (och räknar upp j).
             */

            if (i >mid){
                a[k] = extraLagringsArray[j++];
            }
            else if (j > hi){ // samma metodik. vänstra delen tog slut, då läggs i den högra.
                a[k] = extraLagringsArray[i++];
            }
            /**
             * (Uppgift)
             * Annars jämför vi värdet på position i med värdet på position j,
             * och tar det minsta (och räknar upp antingen i eller j,
             * beroende på vilken del vi tog från).
             */
            else if( extraLagringsArray[i] < extraLagringsArray[j] ){
                a[k] = extraLagringsArray[i++];
            }
            else if( extraLagringsArray[j] < extraLagringsArray[i] || extraLagringsArray[j] == extraLagringsArray[i]){
                a[k] = extraLagringsArray[j++];
            }

        }

    }

}
