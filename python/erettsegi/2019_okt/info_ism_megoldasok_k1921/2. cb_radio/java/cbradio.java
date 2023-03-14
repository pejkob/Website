import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

class Bejegyzes {
    Integer ora;
    Integer perc;
    Integer adasDarab;
    String becenev;

    Bejegyzes(String nyersBemenet) {
        String[] darabolt = nyersBemenet.split(";");
        ora = Integer.parseInt(darabolt[0]);
        perc = Integer.parseInt(darabolt[1]);
        adasDarab = Integer.parseInt(darabolt[2]);
        becenev = darabolt[3];
    }
}

public class Cbradio {

    private static List<Bejegyzes> naplo = new ArrayList<Bejegyzes>();

    public static void main(String[] args) {
        // 2. feladat
        File inputFile = new File("./cb.txt");
        try (Scanner scanner = new Scanner(inputFile)) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String aktualisSor = scanner.nextLine();
                Bejegyzes bejegyzes = new Bejegyzes(aktualisSor);
                naplo.add(bejegyzes);
            }
        } catch (FileNotFoundException exception) {
            System.err.printf("Fájl nem található!");
        }

        // 3. feladat
        System.out.println("3. feladat: Bejegyzések száma: " + naplo.size());

        // 4. feladat
        Boolean voltAdas = false;
        for (Bejegyzes bejegyzes : naplo) {
            if (bejegyzes.adasDarab == 4) {
                voltAdas = true;
                break;
            }
        }
        System.out.println("4. feladat: " + (voltAdas ? "Volt " : "Nem volt ") + "négy adást indító sofőr.");

        // 5. feladat
        Scanner consoleScanner = new Scanner(System.in);
        System.out.print("5. feladat: Kérek egy nevet: ");
        String inputNev = consoleScanner.next();
        Integer hivasokSzama = naplo.stream()
                .filter(bejegyzes -> bejegyzes.becenev.equals(inputNev))
                .map(bejegyzes -> bejegyzes.adasDarab)
                .reduce(0, (a, b) -> a + b);
        System.out.println(hivasokSzama > 0 ? ("       " + inputNev + " " + hivasokSzama + "x használta a CB-rádiót") : ("      Nincs ilyen nevű sofőr!"));

        // 7. feladat
        try {
            Files.write(Paths.get("./cb2.txt"), ("Kezdes;Nev;AdasDb\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            for (Bejegyzes bejegyzes : naplo) {
                String sor = atszamolPercre(bejegyzes.ora, bejegyzes.perc) + ";" + bejegyzes.becenev + ";" + bejegyzes.adasDarab + "\n";
                Files.write(Paths.get("./cb2.txt"), sor.getBytes(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            System.err.printf("Fájlírás hiba!");
        }

        // 8. feladat
        Long soforokSzama = naplo.stream().map(bejegyzes -> bejegyzes.becenev).distinct().count();

        System.out.println("8. feladat: Sofőrök száma: " + soforokSzama);

        // 9. feladat
        HashMap<String, Integer> szummak = new HashMap<String, Integer>();
        for (Bejegyzes bejegyzes : naplo) {
            szummak.merge(bejegyzes.becenev, bejegyzes.adasDarab, (a, b) -> a + b);
        }
        Map.Entry<String, Integer> legtobb = szummak.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get();

        System.out.println("9. feladat: Legtöbb adást indító sofőr");
        System.out.println("       " + legtobb.getKey());
        System.out.println("       " + "Adások száma: " + legtobb.getValue() + " alkalom");
    }

    // 6. feladat
    private static Integer atszamolPercre(Integer ora, Integer perc) {
        return ora * 60 + perc;
    }
}

//******************************************
// Alternatív megoldás az 5. feladathoz Stream api használata nélkül
//    Integer hivasokSzama = 0;
//    for(Bejegyzes bejegyzes: naplo){
//        if(bejegyzes.becenev.equals(inputNev)){
//            hivasokSzama += bejegyzes.adasDarab;
//            }
//    }
//******************************************
// Alternatív megoldás a 8. feladathoz Set használatával, Stream api nélkül
//     Set<String> nevek = new HashSet<String>();
//     for(Bejegyzes bejegyzes: naplo){
//        nevek.add(bejegyzes.becenev);
//     }
//
//     System.out.println("8. feladat: Sofőrök száma: " + nevek.size());
//******************************************
// Alternatív megoldás a 9. feladathoz, a HashMap-be beillesztés Merge nélkül
//    for(Bejegyzes bejegyzes: naplo){
//        if (szummak.get(bejegyzes.becenev) == null) {
//           szummak.put(bejegyzes.becenev, bejegyzes.adasDarab);
//        }
//        else {
//           szummak.put(bejegyzes.becenev, szummak.get(bejegyzes.becenev) + bejegyzes.adasDarab);
//        }
//    }
//******************************************
// Alternatív megoldás a 9. feladathoz, maximum érték kiválasztása Stream api nélkül
//    String maxNev = "";
//    int maxAdas = 0;
//    for (Map.Entry<String, Integer> bejegyzes : szummak.entrySet()) {
//        if (bejegyzes.getValue() > maxAdas) {
//            maxAdas = bejegyzes.getValue();
//            maxNev = bejegyzes.getKey();
//        }
//    }
//
//    System.out.println("9. feladat: Legtöbb adást indító sofőr");
//    System.out.println("       " + maxNev);
//    System.out.println("       " + "Adások száma: " + maxAdas + " alkalom");
//******************************************
