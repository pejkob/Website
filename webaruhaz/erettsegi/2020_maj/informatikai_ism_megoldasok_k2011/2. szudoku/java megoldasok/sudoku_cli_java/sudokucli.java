import java.io.*;
import java.util.*;

class Feladvany {
    public String kezdo;
    private int meret;
    public int getMeret(){
        return meret;
    }
    public void setMeret(int ertek){
        meret = ertek;
    }

    public Feladvany(String sor) {
        kezdo = sor;
        meret = (int) (Math.sqrt(sor.length()));
    }

    public void kirajzol() {
        for (int i = 0; i < kezdo.length(); i++) {
            if (kezdo.charAt(i) == '0') {
                System.out.print(".");
            } else {
                System.out.print(kezdo.charAt(i));
            }
            if (i % meret == meret - 1) {
                System.out.println();
            }
        }
    }
}

public class SudokuCLI {

    public static void main(String[] args) {
        //3.feladat
        List<Feladvany> feladvanyok = new ArrayList<Feladvany>();
        File inputFile = new File("./feladvanyok.txt");
        try (Scanner scanner = new Scanner(inputFile)) {
            while (scanner.hasNextLine()) {
                String aktualisSor = scanner.nextLine();
                Feladvany sorsolas = new Feladvany(aktualisSor);
                feladvanyok.add(sorsolas);
            }
        } catch (FileNotFoundException exception) {
            System.err.print("Fájl nem található!");
        }
        System.out.println("2.feladat: Beolvasva " + feladvanyok.size() + " feladvány.");

        //4.feladat
        int beolvasottMeret;
        Scanner consoleScanner = new Scanner(System.in);
        do {
            System.out.println("3.feladat: Kérem a feladvány méretét [4..9]:");
            beolvasottMeret = Integer.parseInt(consoleScanner.next());
        } while (beolvasottMeret < 4 || beolvasottMeret > 9);
        int meretDb = 0;
        for (Feladvany feladvany : feladvanyok) {
            if (feladvany.getMeret() == beolvasottMeret) {
                meretDb++;
            }
        }
        System.out.println(beolvasottMeret + "x" + beolvasottMeret + " méretű feladványból " + meretDb + "darab van tárolva.");

        //5.feladat
        List<Feladvany> szurtLista = new ArrayList<Feladvany>();
        Random rand = new Random();

        for (Feladvany feladvany : feladvanyok) {
            if (feladvany.getMeret() == beolvasottMeret) {
                szurtLista.add(feladvany);
            }
        }

        int veletlenSzam = rand.nextInt(szurtLista.size());
        Feladvany kivalasztott = szurtLista.get(veletlenSzam);
        System.out.println("4.feladat: A kiválasztott feladvány: " + kivalasztott.kezdo);

        //6.feladat
        double uresDb = 0;
        for (char c : kivalasztott.kezdo.toCharArray()) {
            if (c != '0') {
                uresDb++;
            }
        }
        double megoldas = (uresDb / (kivalasztott.getMeret() * kivalasztott.getMeret())) * 100;
        int megoldasKerekitve =  (int) Math.round(megoldas);

        System.out.println("5.feladat: A feladvány kitöltöttsége: " + megoldasKerekitve + "%");

        //7.feladat
        System.out.println("6.feladat: A feladvány kirajzolva:");
        kivalasztott.kirajzol();

        //8.feladat
        String fajlnev = "sudoku" + beolvasottMeret + ".txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fajlnev, false));
            for (Feladvany feladvany : feladvanyok) {
                if (feladvany.getMeret() == beolvasottMeret) {
                    writer.write(feladvany.kezdo);
                    writer.newLine();
                }
            }
            writer.close();
        } catch (IOException e) {
            System.err.print("Hiba a fájlírás során!");
        }
    }
}
