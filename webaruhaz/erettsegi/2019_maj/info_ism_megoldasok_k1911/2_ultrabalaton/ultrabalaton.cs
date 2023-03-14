using System;
using System.Collections.Generic;
using System.Linq;
using System.IO;

namespace Ultrabalaton
{
    class Versenyző
    {
        public string Név { get; private set; }
        public int Rajtszám { get; private set; }
        public string Kategória { get; private set; }
        public string Idő { get; private set; }
        public int TavSzázalék { get; private set; }

        public Versenyző(string sor)
        {
            string[] m = sor.Split(';');
            Név = m[0];
            Rajtszám = int.Parse(m[1]);
            Kategória = m[2];
            Idő = m[3];
            TavSzázalék = int.Parse(m[4]);
        }

        public double IdőÓrában // 6. feladat
        {
            get
            {
                string[] m = Idő.Split(':');
                int óra = int.Parse(m[0]);
                int perc = int.Parse(m[1]);
                int mp = int.Parse(m[2]);
                return óra + perc / 60.0 + mp / 3600.0;
            }
        }

        public override string ToString()
        {
            return $"{Név} ({Rajtszám}.) - {Idő}";
        }
    }

    class Ultrabalaton
    {
        static void Main()
        {
            List <Versenyző> v = new List<Versenyző>();
            foreach (var i in File.ReadAllLines("ub2017egyeni.txt").Skip(1))
            {
                v.Add(new Versenyző(i));
            }
            Console.WriteLine($"3. feladat: Egyéni indulók: {v.Count} fő");

            // 4. feladat: Teljes távot lefutó nők száma
            int céligNők = 0;
            foreach (var i in v)
            {
                if (i.TavSzázalék == 100 && i.Kategória == "Noi") céligNők++;
            }
            Console.WriteLine($"4. feladat: Célba érkező női sportolók: {céligNők} fő");

            Console.Write("5. feladat: Kérem a sportoló nevét: ");
            string iNév = Console.ReadLine();
            bool indult = false;
            bool teljesítette = false;
            foreach (var i in v)
            {
                if (i.Név == iNév)
                {
                    indult = true;
                    if (i.TavSzázalék == 100) teljesítette = true;
                    break;
                }
            }
            Console.WriteLine($"\tIndult egyéniben a sportoló? {(indult ? "Igen" : "Nem")}");
            if (indult)
            {
                Console.WriteLine($"\tTeljesítette a teljes távot? {(teljesítette ? "Igen" : "Nem")}");
            }

            // 7. feladat: A teljes távot teljesítő férfi sportolók átlagos ideje órában meghatározva
            double összIdőÓra = 0;
            int céligFérfiak = 0;
            foreach (var i in v)
            {
                if (i.Kategória == "Ferfi" && i.TavSzázalék == 100)
                {
                    összIdőÓra += i.IdőÓrában;
                    céligFérfiak++;
                }
            }
            Console.WriteLine($"7. feladat: Átlagos idő: {összIdőÓra / céligFérfiak} óra");

            Console.WriteLine("8. feladat: Verseny győztesei");
            double győztesNőiIdő = double.MaxValue;
            Versenyző győztesNő = null;
            double győztesFérfiIdő = double.MaxValue;
            Versenyző győztesFérfi = null;
            foreach (var i in v)
            {
                if (i.Kategória == "Noi" && i.TavSzázalék == 100 && i.IdőÓrában<győztesNőiIdő)
                {
                    győztesNőiIdő = i.IdőÓrában;
                    győztesNő = i;
                }
                if (i.Kategória == "Ferfi" && i.TavSzázalék == 100 && i.IdőÓrában < győztesFérfiIdő)
                {
                    győztesFérfiIdő = i.IdőÓrában;
                    győztesFérfi = i;
                }
            }

            Console.Write("\tNők: ");
            Console.WriteLine(győztesNő);
            Console.Write("\tFérfiak: ");
            Console.WriteLine(győztesFérfi);
            Console.ReadKey();
        }
    }
}
