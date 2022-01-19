using System;

namespace Clicker
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Welcum to the Clicker Game");
            Console.WriteLine("Today we go to the GYM");
            Console.WriteLine("You need make CUM");
            Console.WriteLine("--------------------------");
            Console.WriteLine("Enter *ENTER* to make CUM");
            Console.WriteLine("Enter *exit* to leave the GYM");
            Console.WriteLine("Enter *-c upg* tto get new CUM");
            Console.WriteLine("Enter *-d upg* tto get new DICK");
            Console.WriteLine("Enter *-g upg* tto get new GYM");
            CumClicker cumClicker = new CumClicker();
            string line = "";
            while (line != "exit")
            {
                line = Console.ReadLine();
                switch (line)
                {
                    case "":
                        cumClicker.CumCount += cumClicker.CumPlus;
                        Console.WriteLine($"Cum count: {cumClicker.CumCount}");
                        break;
                    case "-c upg":
                        try
                        {
                            cumClicker.UpgradeCum();
                            Console.WriteLine($"Your DICK can be upgrade");
                            Console.WriteLine($"Cum Count: {cumClicker.CumCount}");
                            Console.WriteLine($"DICK power: {cumClicker.CumPlus}");
                            Console.WriteLine($"Need for new CUM: {cumClicker.CumUpgrade}");
                        }
                        catch (Exception ex)
                        {
                            Console.WriteLine(ex.Message);
                            Console.WriteLine($"Need for new CUM: {cumClicker.CumUpgrade}");
                        }
                        break;
                    case "-d upg":
                        try
                        {
                            cumClicker.UpgradeDick();
                            Console.WriteLine($"Your DICK can be upgrade");
                            Console.WriteLine($"Cum Count: {cumClicker.CumCount}");
                            Console.WriteLine($"DICK power: {cumClicker.CumPlus}");
                            Console.WriteLine($"Need for new DICK: {cumClicker.DickUpgrade}");
                        }
                        catch (Exception ex)
                        {
                            Console.WriteLine(ex.Message);
                            Console.WriteLine($"Need for new DICK: {cumClicker.DickUpgrade}");
                        }
                        break;
                    case "-g upg":
                        try
                        {
                            cumClicker.UpgradeGym();
                            Console.WriteLine($"Your DICK can be upgrade");
                            Console.WriteLine($"Cum Count: {cumClicker.CumCount}");
                            Console.WriteLine($"DICK power: {cumClicker.CumPlus}");
                            Console.WriteLine($"Need for new GYM: {cumClicker.GymUpgrade}");
                        }
                        catch (Exception ex)
                        {
                            Console.WriteLine(ex.Message);
                            Console.WriteLine($"Need for new GYM: {cumClicker.GymUpgrade}");
                        }
                        break;
                    default:
                        Console.WriteLine("You SLAVE");
                        break;
                }
            }
        }
    }

    class CumClicker
    {
        public double CumCount { get; set; } = 0;
        public double CumPlus { get; set; } = 1;
        public double CumUpgrade { get; set; } = 50;
        public double DickUpgrade { get; set; } = 1000;
        public double GymUpgrade { get; set; } = 50000;

        public void UpgradeCum()
        {
            if (CumCount >= CumUpgrade)
            {
                CumCount -= CumUpgrade;
                CumUpgrade *= 1.2;
                CumPlus += 1;
            }
            else
            {
                throw new Exception("You're not the boss of the gym yet");
            }
        }

        public void UpgradeDick()
        {
            if (CumCount >= DickUpgrade)
            {
                CumCount -= DickUpgrade;
                DickUpgrade *= 1.2;
                CumPlus += 5;
            }
            else
            {
                throw new Exception("You're not the boss of the gym yet");
            }
        }
        public void UpgradeGym()
        {
            if (CumCount >= GymUpgrade)
            {
                CumCount -= GymUpgrade;
                GymUpgrade *= 1.2;
                CumPlus *= 1.5;
            }
            else
            {
                throw new Exception("You're not the boss of the gym yet");
            }
        }
    }
}