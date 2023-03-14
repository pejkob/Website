using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;

namespace sudokuCLI
{
    class Feladvany
    {
        public string Kezdo { get; private set; }
        public int Meret { get; private set; }

        public Feladvany(string sor)
        {
            Kezdo = sor;
            Meret = Convert.ToInt32(Math.Sqrt(sor.Length));
        }

        public void Kirajzol()
        {
            for (int i = 0; i < Kezdo.Length; i++)
            {
                if (Kezdo[i] == '0')
                {
                    Console.Write(".");
                }
                else
                {
                    Console.Write(Kezdo[i]);
                }
                if (i % Meret == Meret - 1)
                {
                    Console.WriteLine();
                }
            }
        }
    }
    class sudokuCLI
    {
        static void Main(string[] args)
        {
            //3.feladat
            List<Feladvany> feladvanyok = new List<Feladvany>();
            foreach (string Seged in File.ReadAllLines("feladvanyok.txt"))
            {
                feladvanyok.Add(new Feladvany(Seged));
            }
            Console.WriteLine("3. feladat: Beolvasva {0} feladvány", feladvanyok.Count);

            //4.feladat
            int meret;
            do
            {
                Console.Write("\n4. feladat: Kérem a feladvány méretét [4..9]: ");
                meret = Convert.ToInt32(Console.ReadLine());
            } while (meret < 4 || meret > 9);
            int meretDb = 0;
            foreach (var Seged in feladvanyok)
            {
                if (Seged.Meret == meret)
                {
                    meretDb++;
                }
            }
            Console.WriteLine("{0}x{0} méretű feladványból {1} darab van tárolva", meret, meretDb);

            //5.feladat
            Random Veletlen = new Random();
            int kivalasztott;
            do
            {
                kivalasztott = Veletlen.Next(0, feladvanyok.Count);
            } while (feladvanyok[kivalasztott].Meret != meret);
            Console.WriteLine("\n5. feladat: A kiválasztott feladvány:\n{0}", feladvanyok[kivalasztott].Kezdo);

            //6.feladat
            int toltottDb = 0;
            foreach (char szamJegy in feladvanyok[kivalasztott].Kezdo)
            {
                if (szamJegy != '0')
                {
                    toltottDb++;
                }
            }
            double szazalekErtek = Math.Round((double)100 * toltottDb / feladvanyok[kivalasztott].Kezdo.Length, 0, MidpointRounding.AwayFromZero);
            Console.WriteLine("\n6. feladat: A feladvány kitöltöttsége: {0}%", szazalekErtek);

            //7.feladat
            Console.WriteLine("\n7. feladat: A feladvány kirajzolva:");
            feladvanyok[kivalasztott].Kirajzol();

            //8.feladat
            StreamWriter Ki = new StreamWriter("sudoku" + meret + ".txt");
            foreach (var seged in feladvanyok)
            {
                if (seged.Meret == meret)
                {
                    Ki.WriteLine(seged.Kezdo);
                }
            }
            Ki.Close();
            Console.WriteLine("\n8. feladat: sudoku{0}.txt állomány {1} darab feladvánnyal létrehozva", meret, meretDb);

            Console.ReadLine();
        }
    }
}
