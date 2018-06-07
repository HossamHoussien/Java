import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;
import java.util.Vector;

import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) throws IOException {
		
	Vector <Integer> Image =readImage("lena.jpg");
	int imageWidth = getImageDimensions("lena.jpg","width");
	int imageHeight = getImageDimensions("lena.jpg","height");
		
	int No_Levels;
	Vector <Integer> QInverse = new Vector <Integer>();
	Vector <Integer> Q = new Vector <Integer>();
	Vector <Range> Ranges = new Vector <Range>();
	Vector <Integer> CompressedImage = new Vector<Integer>();
	Scanner read = new Scanner (System.in);
	
	System.out.print("Enter Number Of Quantization Levels: ");
	No_Levels = read.nextInt();
	
//**********************************************************************************************************
//CREATING Q COLUMN
	for (int i = 0; i < No_Levels; i++) 
	{
		Q.add(i);
	}
	
//**********************************************************************************************************
//CREATING Q-INVERSE COLUMN
	float temp = average(Image);
	QInverse = split(Image,temp,No_Levels);
//**********************************************************************************************************
//CREATING RANGES COLUMN
//FIRST ITERATION
	
	Range newRange = new Range(0,(QInverse.get(0)+QInverse.get(1))/2);
	Ranges.add(newRange);
	for (int i = 1; i < No_Levels; i++)
	{
		if (i+1 < No_Levels)
		{
			newRange = new Range(Ranges.get(i-1).getUpper(),(QInverse.get(i)+QInverse.get(i+1))/2);
			Ranges.add(newRange);
		}
		else
		{
			newRange = new Range(Ranges.get(i-1).getUpper(),256);
			Ranges.add(newRange);
		}
	
	} 
	
//**********************************************************************************************************
//CREATING COMPRESSED IMAGE	
	for (int i = 0; i < Image.size(); i++) {
		CompressedImage.add(findRange(Ranges, Image.get(i)));
	}
	
//**********************************************************************************************************
//SAVING COMPRESSED DATA

	writeTofile(Ranges,Q,QInverse,CompressedImage,imageWidth,imageHeight);
	Vector <Integer> DecompressedImage = new Vector <Integer>();
	readFromfile(DecompressedImage);
		
			
	read.close();
	}//MAIN
	
	
	
/****************************************************************************************************/
public static int getImageDimensions(String imagePath,String dimension)
{

    int width=0;
	int height=0;
    File file=new File(imagePath);
    BufferedImage image=null;
    try
    {
        image=ImageIO.read(file);
    }
    catch (IOException e)
    {
        e.printStackTrace();
    }

      width=image.getWidth();
      height=image.getHeight();
      if (dimension.equalsIgnoreCase("width"))
      {
    	  return width;
      }
      else if (dimension.equalsIgnoreCase("height"))
      {
    	  return height;
      }
      else 
      {
    	  return -1;
      }
     
}
/****************************************************************************************************/	
 public static Vector<Integer> readImage(String filePath)
	    {
		    int width=0;
			int height=0;
	        File file=new File(filePath);
	        Vector <Integer> pixelsVector = new Vector <Integer> ();
	        BufferedImage image=null;
	        try
	        {
	            image=ImageIO.read(file);
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }

	          width=image.getWidth();
	          height=image.getHeight();
	        int[][] pixels=new int[height][width];

	        for(int x=0;x<width;x++)
	        {
	            for(int y=0;y<height;y++)
	            {
	                int rgb=image.getRGB(x, y);
	               // int alpha=(rgb >> 24) & 0xff;
	                int r = (rgb >> 16) & 0xff;
	               // int g = (rgb >> 8) & 0xff;
	               // int b = (rgb >> 0) & 0xff;

	                pixels[y][x]=r;
	                pixelsVector.add(r);
	            }
	        }

	        return pixelsVector;
	    }
/********************************************************************************************************/

public static void writeImage(Vector<Integer> pix,String outputFilePath,int width,int height)
{
    File fileout=new File(outputFilePath);
    BufferedImage image2=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB );

    int[][] pixels = new int[width][height];

    int i=0;
    for(int x=0;x<width ;x++)
    {
        for(int y=0;y<height;y++)
        {
        	pixels[y][x] = pix.get(i);
            image2.setRGB(x,y,(pixels[y][x]<<16)|(pixels[y][x]<<8)|(pixels[y][x]));
            i++;
        }
    }
    try
    {
        ImageIO.write(image2, "jpg", fileout);
    }
    catch (IOException e)
    {
        e.printStackTrace();
    }
}
/****************************************************************************************************/
public static float average(Vector <Integer> list)
	    {
	    	int count = 0;
	    	int sum = 0;
	    	for (int i=0; i<list.size();i++)
	    	{
	    		sum += list.get(i);
	    		count +=1;
	    	}
	    	return (float)sum / (float)count;
	    }
/****************************************************************************************************/

/********************************************************************************************************************/
public static Vector<Integer> split(Vector <Integer> image,float average,int n)
{
	
	
	int lowerAverage = (int)average - 1;
	int upperAverage = (int)average + 1;
	Vector<Integer> averages = new Vector<Integer>();
	Vector <Integer> leftData = new Vector <Integer>();
	Vector <Integer> rightData = new Vector <Integer>();
	
	averages.add(lowerAverage);
	averages.add(upperAverage);
	
	
	//FIND ALL AVERAGES
		while (averages.size()<n)
		{
			DataSet temp = new DataSet();
			
			for (int i = 0; i < image.size(); i++) 
			{
				
				if (Math.abs(image.get(i)-lowerAverage) <= Math.abs(image.get(i)-upperAverage))
				{
					temp.leftDataSet.Data.add(image.get(i));
					
				}
				else
				{
					rightData.add(image.get(i));
				}
				
				
				
			}
			if (leftData.size() == 0 ||rightData.size()==0)
			{
				break;
			}
			lowerAverage = (int)average(leftData) - 1;
			upperAverage = (int)average (leftData) + 1;
			averages.add(lowerAverage);
			averages.add(upperAverage);
			
			lowerAverage = (int)average(rightData) - 1;
			upperAverage = (int)average (rightData) + 1;
			averages.add(lowerAverage);
			averages.add(upperAverage);
		}
//*****************************************************************************************
//CREATE DATASETS WITH NUMBER == AVERAGES.SIZE()
		Vector <DataSet> v = new Vector <DataSet>();
		for (int i = 0; i < averages.size(); i++) {
			DataSet temp = new DataSet();
			temp.setName(averages.get(i));
			v.add(temp);
		}
//*****************************************************************************************
//SPLIT ORIGINAL DATA ACCORDING TO AVERAGES
		float name;
		for (int i = 0; i < image.size(); i++) 
		{
			float min =Math.abs(image.get(i)-averages.get(0));
			name = averages.get(0);
			for (int j = 1; j < averages.size(); j++) 
			{
				if (Math.abs(image.get(i)-averages.get(j)) < min)
						{
							min = Math.abs(image.get(i)-averages.get(j));
							name = averages.get(j);
						}
			}
			getDataset(v, name).addData(image.get(i));
			
		}
//*****************************************************************************************
//CALCULATE EACH DATASET AVERAGE
		for (int i = 0; i < v.size(); i++) 
		{
			v.get(i).UpdateAverage();
			
		}
//*****************************************************************************************
//THIS IS JUST FOR TESTING
	Vector<Integer> Qinv = new Vector<Integer>();
	for (int i = 0; i < v.size(); i++) 
	{
		Qinv.add(v.get(i).getAverage());
	}
	
	
	return Qinv;
	

}

/**
 * @throws IOException **************************************************************************************************/

public static void writeTofile (Vector<Range> ranges , Vector<Integer> Q , Vector<Integer> Inv , Vector<Integer> compressed, int width,int height ) throws IOException
{
	
//*************************************************************************************
//FILE-1
	FileWriter  writer = new FileWriter(new File("Q-Table.txt"),false);
	writer.write(width+";"+height);
	writer.write(System.lineSeparator());
	for (int i = 0; i < ranges.size(); i++) 
	{
		
		writer.write(ranges.get(i).getLower()+";"+ ranges.get(i).getUpper());
		writer.write(";"+Q.get(i));
		writer.write(";"+Inv.get(i));
		writer.write(System.lineSeparator());
		
	}
	writer.close();
//*************************************************************************************
//FILE-2
	FileWriter dataWriter = new FileWriter(new File ("CompressedImage.txt"),false);
	for (int i = 0; i < compressed.size(); i++) 
	{
		
		dataWriter.write(compressed.get(i)+"");
	}
	
	
	dataWriter.close();
}
/****************************************************************************************************/

public static int findRange(Vector<Range> ranges, int value)
{
	int index=0;
	for (int i = 0; i < ranges.size(); i++) {
		if (value >= ranges.get(i).getLower() && value<ranges.get(i).getUpper())
		{
			index =  i;
			break;
		}
	}
	return index;
	
}
/**
 * @throws FileNotFoundException ******************************************************************************************/
public static void readFromfile(Vector<Integer> decompressed) throws FileNotFoundException 
{
	Scanner table = new Scanner (new File("Q-Table.txt"));
	Vector <Pair> pairs = new Vector <Pair>();
	Vector<Integer> decompressedImage = new Vector <Integer>();
	
//READING DIMENSIONS
	String line = table.nextLine();
	String[] arr = line.split(";");
	int imageWidth = Integer.parseInt(arr[0]);
	int imageHeight = Integer.parseInt(arr[1]);
	//System.out.println(imageWidth + " : "+imageHeight);
	
//READING TABLE
	while(table.hasNextLine())
	{
		line = table.nextLine();
		arr = line.split(";");
		int tempQ = Integer.parseInt(arr[2]);
		int tempInv = Integer.parseInt(arr[3]);

		Pair pair = new Pair(tempQ,tempInv);
		pairs.add(pair);
		
	}
	table.close();
	
//READING COMPRESSED IMAGE
	
	int ascii;
	int pixel;
	InputStream in = new FileInputStream(new File("CompressedImage.txt"));
    Reader reader = new InputStreamReader(in);
    Reader buffer = new BufferedReader(reader) ;
	try {
		while((ascii = buffer.read()) != -1)
		{
			
				
					
					 
					//ascii = buffer.read();
					char ch = (char)ascii;
					pixel = Integer.parseInt(Character.toString(ch));					
					decompressedImage.add(searchPairs(pixel,pairs));
					
			    
			
		}
		buffer.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
//WRITING DECOMPRESSED IMAGE
	writeImage(decompressedImage,"Output.jpg",imageWidth,imageHeight);
	
	
}

/****************************************************************************************************/
public static DataSet getDataset(Vector<DataSet> v, float name)
{
	DataSet d = new DataSet();
	for (int i = 0; i < v.size(); i++) 
	{
		if (v.get(i).getName() == name)
		{
			d = v.get(i);
			break;
		}
		
	}
	return d;
}
/****************************************************************************************************/
public static int searchPairs(int q, Vector <Pair> pairs)
{
	int inv = -1;
	for (int i = 0; i < pairs.size(); i++) 
	{
		if(pairs.get(i).Q == q)
		{
			inv =  pairs.get(i).getInverse(q);
			break;
		}
	}
	
	return inv;
}


























}
