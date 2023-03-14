using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace sudokuGUI
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();            
        }
        public int meret = 4;
        public int mezoSzam = 16;

        private void MeretModositas(object sender, RoutedEventArgs e)
        {
            if ((sender as Button).Content.ToString() == "-" && meret > 4)
            {
                meret--;
                MeretBox.Text = meret.ToString();
                mezoSzam = meret * meret;
            }
            else if ((sender as Button).Content.ToString() == "+" && meret < 9)
            {
                meret++;
                MeretBox.Text = meret.ToString();
                mezoSzam = meret * meret;
            }
        }

        private void HosszFrissites(object sender, TextChangedEventArgs e)
        {
            HosszCimke.Content = "Hossz: " + KezdoAllapot.Text.Length;
        }

        private void Ellenorzes(object sender, RoutedEventArgs e)
        {
            if (mezoSzam > KezdoAllapot.Text.Length)
            {
                int karakterKulonbseg = mezoSzam - KezdoAllapot.Text.Length;
                MessageBox.Show("A feladvány rövid: kell még " + karakterKulonbseg + " számjegy!");
            }
            else
            {
                if (mezoSzam < KezdoAllapot.Text.Length)
                {
                    int karakterKulonbseg = KezdoAllapot.Text.Length - mezoSzam;
                    MessageBox.Show("A feladvány hosszú: törlendő " + karakterKulonbseg + " számjegy!");
                }
                else
                {
                    MessageBox.Show("A feladvány megfelelő hosszúságú!");
                }
            }
        }
    }
}
