import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{

    // get binary gets the binary version of representation of each row in the truth table
    // this is import because each row needs to be a unique
    public ArrayList<String> getBinary(int vars)
    {
        ArrayList<String> list = new ArrayList<String>();

        int rows = (int)Math.pow(2, vars);

        for (int i = 0 ; i < rows ; i++)
        {
            String bin = Integer.toBinaryString(i);
            list.add(bin);
        }

        return list;
    }

    // getBinary() returns 1 if the value is 1, but for this we need 0's infront of the 1: 001
    public ArrayList<String> getListOfStrings(ArrayList<String> str, int vars)
    {
        ArrayList<String> list = new ArrayList<String>();
        for (String item : str)
        {
            String newItem = item;
            for (int i = 0 ; i < (vars - item.length()) ; i++)
            {
                newItem = "0" + newItem; 
            }

            list.add(newItem);
        }
        return list;
    }

    public void export(ArrayList<String> out, int vars, String name)
    {
        // create a file object for the current location
        try {
            // Creates a Writer using FileWriter
            BufferedWriter output = new BufferedWriter(new FileWriter(String.format("%s%d.txt", name, vars)));

            // write boiler plate
            String boilerPlate = String.format("""
                load %s.hdl,
                output-file %s.out,
                """, name, name);
            output.write(boilerPlate);
            System.out.println(out.size());
            
            // for items in array
            for (int i = 0 ; i < out.size() ; i++)
            {
                System.out.println("outer for");
                // for chars in items
                for (int j = 0 ; j < out.get(i).length() ; j++)
                {
                    char aB = 'a';
                    if (j != 0)
                    {
                        if (j % 2 != 0)
                        {
                            aB = 'b';
                        }
                    }
                    char[] chars = out.get(i).toCharArray();
                    // Writes the program to file
                    String binary = String.format("\nset %c %c, ", aB, chars[j]);
                    output.write(binary);
                }
                output.write("""
                        \neval,
                        output;
                        """);
            }
            System.out.println("Data is written to the file.");
     
            // Closes the writer
            output.close();
          }
          catch (IOException e) {
            e.getStackTrace();
          }


    }
    
    // get user input
    public void out()
    {
        try (Scanner myObj = new Scanner(System.in)) 
        {
            // get chipname
            System.out.print("Enter chip name: ");
            String name = myObj.next();
            
            //get inputs
            System.out.print("Enter number of inputs: ");
            int vars = myObj.nextInt();
            
            
            ArrayList<String> out = getBinary(vars);
            ArrayList<String> out1 = getListOfStrings(out, vars);
            export(out1, vars, name);
        }
    }

    public static void main(String[] args)
    {
        Main ts = new Main();
        ts.out();
    }
}