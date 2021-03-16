import java.io.*;
import java.util.*;

public class Main {

    private static final String FILE_SEPARATOR = "\\ ";
    private static ArrayList<Integer> money = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Initialize(br, bw);
        br.close();
        bw.close();
    }

    public static void Initialize(BufferedReader br, BufferedWriter bw) throws IOException {

        ArrayList<int[]> cases = new ArrayList<>();
        String arraySize = br.readLine();

        while (arraySize != null) {
            int size = Integer.parseInt(arraySize);
            int[] arrayInt = new int[size];
            String secondLine = br.readLine();
            String[] parts = secondLine.split(FILE_SEPARATOR);
            for (int i = 0; i < size; i++) {
                arrayInt[i] = Integer.parseInt(parts[i]);
            }
            Arrays.sort(arrayInt);
            cases.add(arrayInt);

            money.add(Integer.parseInt(br.readLine()));
            arraySize = br.readLine();

            if (arraySize != null) {
                arraySize = br.readLine();
            }
        }

        for (int i = 0; i < cases.size(); i++) {
            bw.write(getInfo(cases.get(i), money.get(i)));
            bw.flush();
        }

    }

    public static String getInfo(int[] ai, int result) {
        int bestPrice1 = 0;
        int bestPrice2 = 10000000;
        for (int i = 0; i < ai.length; i++) {
            int priceBook1 = ai[i];
            int priceBook2 = binarySearch(i + 1, (ai.length - 1), (result - priceBook1), ai);
            if (priceBook2 > -1) {
                if ((priceBook2 - priceBook1) < (bestPrice2 - bestPrice1)) {
                    bestPrice1 = priceBook1;
                    bestPrice2 = priceBook2;
                }
            }
        }
        return "Peter should buy books whose prices are " + bestPrice1 + " and " + bestPrice2 + "."+ "\n\n";
    }

    public static int binarySearch(int posMin, int posMax, int lackMoney, int[] ai) {
        if (posMin > posMax) {
            return -1;
        }
        int m = (posMin + posMax) / 2;
        if (ai[m] > lackMoney) {
            return binarySearch(posMin, m - 1, lackMoney, ai);
        } else if (ai[m] < lackMoney) {
            return binarySearch(m + 1, posMax, lackMoney, ai);
        } else {
            return lackMoney;
        }
    }
}
