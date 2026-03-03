public class QuickSorter implements IntSorter{

    @Override
    public void sort(int[] a){
        // 1. Blanda om arrayen (för att undvika urartningsfall)
        ArrayUtil.shuffle(a); // om arrayen ordered då O(N**2) StackoverflowError
        // 2. Kör quicksort på ”delen” av arrayen från 0 till N − 1 (hela arrayen)
        quickSort(a,0,a.length -1);
    }

    private void quickSort(int[] a, int lo, int hi){
        // 3. Om hi inte är större än lo har vi högst ett element...
        if (lo >= hi) {
            return;
        }
        // 4. Annars, välj elementet på position lo som partitioneringselement (p) pivot .
        int j = partition(a, lo,hi);

        // 11. Kör quicksort rekursivt på vänstra delen från lo till j − 1.
        quickSort(a, lo, j - 1);

        // 12. Kör quicksort rekursivt på högre delen från j + 1 till hi.
        quickSort(a, j + 1, hi);

    }

    private int partition(int[] a, int lo, int hi) {
        // 5. Välj elementet på position lo som partitioneringselement. Vi kallar det p.
        // väljer första elementet som pivot
        int p = a[lo];

        // 6. Låt i börja på lo och j på hi + 1.
        int i = lo;
        int j = hi + 1;

        // Vi ska räkna upp i och räkna ner j tills de möts.
        while (true) {

            // 7. Räkna upp i, först en gång, och fortsätt så länge i < hi och a[i] < p.
            while (a[++i] < p) {
                if (i == hi) break;
            }

            // 8. Räkna ner j, först en gång, och fortsätt så länge a[j] > p.
            while (a[--j] > p) {
                // p finns på a[lo], så loopen stannar senast där.
            }

            // 9. Om i och j har mötts, alltså i ≥ j, bryt loopen.
            if (i >= j) break;

            // 10. Annars, byt värdena på position i och j, och fortsätt.
            swap(a, i, j);
        }

        // 10. Slutför partitioneringen genom att lägga in p på rätt plats:
        // byt värdena på position lo och j.
        // Nu vet vi att inga värden till vänster om j är mindre än p,
        // och inga värden till höger om j är större än p, och p ligger på sin slutgiltiga position.
        swap(a, lo, j);

        // j är nu positionen där p hamnade.
        return j;
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}

