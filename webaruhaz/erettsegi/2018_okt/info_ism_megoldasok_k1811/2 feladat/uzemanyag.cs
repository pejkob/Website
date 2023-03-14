using System;
using System.Collections.Generic;
using System.IO;

namespace Uzemanyag
{
    class Változás
    {
        public int Év { get; private set; }
        private int Hónap { get; set; }
        private int Nap { get; set; }
        private int BenzinÁr { get; set; }
        private int GázolajÁr { get; set; }
        private const double euroÁrfolyam = 307.7; // 7. feladat

        public Változás(string sor)
        {
            string[] m = sor.Split(';');
            Év = int.Parse(m[0].Split('.')[0]);
            Hónap = int.Parse(m[0].Split('.')[1]);
            Nap = int.Parse(m[0].Split('.')[2]);
            BenzinÁr = int.Parse(m[1]);
            GázolajÁr = int.Parse(m[2]);
        }

        public bool SzökőnapiVáltozás { get { return Év % 4 == 0 && Hónap == 2 && Nap == 24; } }

        public int Különbség { get { return Math.Abs(BenzinÁr - GázolajÁr); } }

        // 9. feladat:
        public int ElteltNapokElőzőVáltozástól(Változás előzőVáltozás)
        {
            int[] napokSzama = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
            if (Év % 4 == 0) napokSzama[1] = 29; // ha szökőév
            if (Hónap == előzőVáltozás.Hónap) return Nap - előzőVáltozás.Nap; // ha azonos a hónap
            return napokSzama[előzőVáltozás.Hónap - 1] - előzőVáltozás.Nap + Nap; // ha nem azonos a hónap
        }

        private string Átvált(int ár)
        {
            return ((double)ár / euroÁrfolyam).ToString("F2");
        }

        public override string ToString()
        {
            return $"{Év}.{Hónap}.{Nap};{Átvált(BenzinÁr)};{Átvált(GázolajÁr)}";
        }

    }
    class Uzemanyag
    {
        static void Main()
        {
            // 2. feladat: Adatok beolvasása, tárolása
            List<Változás> v = new List<Változás>();
            foreach (var i in File.ReadAllLines("uzemanyag.txt")) v.Add(new Változás(i));

            Console.WriteLine($"3. feladat: Változások száma: {v.Count}");

            // 4. feladat: A legkisebb különbség
            int minKülönbség = int.MaxValue;
            foreach (var i in v)
            {
                if (i.Különbség < minKülönbség) minKülönbség = i.Különbség;
            }
            Console.WriteLine($"4. feladat: A legkisebb különbség: {minKülönbség}");

            // 5. feladat: A legkisebb különbség hányszor fordult elő?
            int dbMinKül = 0;
            foreach (var i in v)
            {
                if (i.Különbség == minKülönbség) dbMinKül++;
            }
            Console.WriteLine($"5. feladat: A legkisebb különbség előfordulása: {dbMinKül}");

            // 6. feladat: Volt-e szökőnapon árváltozás?
            bool voltVáltozásSzökőnapon = false;
            foreach (var i in v)
            {
                if (i.SzökőnapiVáltozás)
                {
                    voltVáltozásSzökőnapon = true;
                    break;
                }
            }
            Console.WriteLine($"6. feladat: {(voltVáltozásSzökőnapon ? "Volt" : "Nem volt")} változás szökőnapon!");

            // 7. feladat: euro.txt
            List<string> ki = new List<string>();
            foreach (var i in v) ki.Add(i.ToString());
            File.WriteAllLines("euro.txt", ki);

            // 8. feladat: Évszám bekérése, ellenőrzése
            int évszám = 0;
            do
            {
                Console.Write("8. feladat: Kérem adja meg az évszámot [2011..2016]: ");
                évszám = int.Parse(Console.ReadLine());
            } while (évszám < 2011 || évszám > 2016);

            // 10. feladat: A leghosszabb időszak, két árváltozás között
            int maxIdőszak = 0;
            for (int i = 1; i < v.Count; i++)
            {
                if (v[i].Év == évszám && v[i - 1].Év == évszám)
                {
                    if (v[i].ElteltNapokElőzőVáltozástól(v[i - 1]) > maxIdőszak)
                    {
                        maxIdőszak = v[i].ElteltNapokElőzőVáltozástól(v[i - 1]);
                    }
                }
            }
            Console.WriteLine($"10. feladat: {évszám} évben a leghosszabb időszak {maxIdőszak} nap volt.");

            Console.ReadKey();
        }
    }
}