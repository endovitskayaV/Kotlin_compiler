using System;

public class CSharpFunctions
{
    public static void readKey()
    {
         Console.ReadKey();
    }

    //--------------PRINT-------------//

    public static void print(string str)
    {
        Console.Write(str);
    }

    public static void print(int str)
    {
        Console.Write(str);
    }

    public static void print(bool str)
    {
        Console.Write(str);
    }

    public static void print(double str)
    {
        Console.Write(str);
    }

    public static void print(char str)
    {
        Console.Write(str);
    }


    //----------PRINTLN------------------//

    public static void println(string str)
    {
         Console.WriteLine(str);
    }

    public static void println(int str)
    {
        Console.WriteLine(str);
    }

    public static void println(bool str)
    {
         Console.WriteLine(str);
    }

    public static void println(double str)
    {
        Console.WriteLine(str);
    }

    public static void println(char str)
    {
         Console.WriteLine(str);
    }


    //--------------READ----------------//

    public static int readInt()
    {
          return Console.Read();
    }

    public static double readDouble()
    {
         return Convert.ToDouble(Console.Read());
    }

    public static char readChar()
    {
         return Convert.ToChar(Console.Read());
    }

    public static string readString()
    {
         return Convert.ToString(Console.Read());
    }

    public static bool readBoolean()
    {
         return Convert.ToBoolean(Console.Read());
    }


    //-----------READLN---------//

    public static string readlnString()
    {
         return Console.ReadLine();
    }

    public static int readlnInt()
    {
          return Convert.ToInt32(Console.ReadLine());
    }

    public static double readlnDouble()
    {
          return Convert.ToDouble(Console.ReadLine());
    }

    public static char readlnChar()
    {
          return Convert.ToChar(Console.ReadLine());
    }

    public static bool readlnBoolean()
    {
          return Convert.ToBoolean(Console.ReadLine());
    }


    //-------------MATH-----------------//

    public static double sqrt(double number)
    {
         return Math.Sqrt(number);
    }

    public static double power(double numberBase, double power)
    {
          return Math.Pow(numberBase, power);
    }

    public static double e()
    {
         return Math.E;
    }

    public static double pi()
    {
         return Math.PI;
    }

    public static double abs(double number)
    {
         return Math.Abs(number);
    }

    public static int abs(int number)
    {
         return Math.Abs(number);
    }

    public static double round(double number)
    {
          return Math.Round(number);
    }

    public static double trunk(double number)
    {
         return Math.Truncate(number);
    }

    public static double remainder(int divider, int dividend)
    {
         int result;
         return Math.DivRem(divider, dividend, out result);
    }

    public static double exp(double number)
    {
         return Math.Exp(number);
    }

    public static double cos(double number)
    {
         return Math.Cos(number);
    }

    public static double sin(double number)
    {
          return Math.Sin(number);
    }

    public static double tan(double number)
    {
          return Math.Tan(number);
    }

    public static double factorial(double number)
    {
         if (number > 1) return number * factorial(number - 1);
         else return 1;
    }

    //------------------------------------------------------//

    public static int size(Array arr)
    {
        return arr.Length;
    }

}