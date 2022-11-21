package com.example.javamavenjunithelloworld;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
/*
 * Author : SAMPATH K V
 */
public class TestData 
{
	static int  flag=0;
	static int  flag1=0;
	public static void main(String args[])
	{
		 try {
			
			 createFileData();
		} catch (FileNotFoundException e) {
       	e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
		}
	}

	
	//var argument Method  to handle the file creation if filename is given or 
	//to generate the default file 
	// This Method will call content creation Methods
	public static void  createFileData(String ... filename) throws IOException
	{
		flag=0;
		 flag1=0;
		 FileWriter  file = null ;
		
		 if(filename.length>0) 
		 {
	     file = new FileWriter(System.getProperty("user.dir")+File.separator+"src/"+filename[0]+".txt");
		 }
		 else
		 {
		     file = new FileWriter(System.getProperty("user.dir")+File.separator+"src/"+"Test File Finance.txt");
 
		 }
		 //To generate randon page numbers
		int pagenum = getRandomlineNumber(605,1500)/55;
		//Variable used for random Finance word write
		int randomFinance=getRandomlineNumber(pagenum,0);
		//Iterate for each page & call content creation Methods(header,body,Footer)
		for(int i=1;i<=pagenum;i++)	
		{
			file=writeHeader(file);
			if(i==randomFinance)
			{
				file=writeBody(file,true);
			}
			else
				file=writeBody(file,false);
			//"empty lines to be used to fill between last line of text and last page header"
			if(i!=pagenum)
			{
				
				file=writeFooter(file,i,false);
			}

			else if(i==pagenum){	
	
		  file=writeFooter(file,i,true);
			}
		
	 }
		file.close();
	}
	//This Method will generate Random word contains characters from A-Z, a-z
	static String getWord(int n)
	 {
	  String word = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+ "abcdefghijklmnopqrstuvxyz";
	  StringBuilder sb = new StringBuilder(n);	 
	  for (int i = 0; i < n; i++) 
	  {
	   int index= getRandomInteger(word.length()-1,0);
	   sb.append(word.charAt(index));
	   }
	  
	  return sb.toString();
	  }
	
	//This Method will generate Random word contains characters from $ 0-9
	static String getNumber(int n)
	 {
	  String word = "0123456789";
	  StringBuilder sb = new StringBuilder(n);	 
	  for (int i = 0; i < n; i++) 
	  {
		  for(int k=0;k<3;k++)
		  {
	   int index= getRandomInteger(word.length()-1,0);
		
	   sb.append(word.charAt(index));
		  }
		  if(i!=n-1)
		  {
			  sb.append(",");
		  }
		  
	   
	   }
	  
	  return "$"+sb.toString();
	  }
	//This Method will generate Random integer between two range.
	public static int getRandomInteger(int maximum, int minimum)
	{ 
		return ((int) (Math.random()*(maximum - minimum))) + minimum;
		
	}
	//Method to create  Header of each page
	public static FileWriter writeHeader(FileWriter f1) throws IOException
	{
		for(int i=1;i<=80;i++)
		{ 
			if(i<80)
			{
			f1.write("=");
			}
		else if(i==80)
			{
				f1.write("\n");
			}
		}
		f1.write("Sample Company \n");
		
		return f1;
	}
	
	//Method to create  body of each page
	public static FileWriter writeBody(FileWriter f1,boolean isFinance) throws IOException
	{
		StringBuilder line  = new StringBuilder();
		int lineSize=getRandomInteger(120, 60);
		int  insrtRandNumber =getRandomInteger(lineSize, 1);
		int k=0;

		int inst_Finance2=getRandomInteger(lineSize, 1);
		for(int i=1;i<=50;i++)
		{
			//To get the Randon Line size

		//To get the line 
			
		while(line.length()<=lineSize)
		{
			
			if(k==inst_Finance2 )
			{
				line.append("testing");
				
				if(line.length()<lineSize)
				{	line.append(" ");
				}
				
			}
		line.append(getWord(getRandomInteger(10,1)));
		if(line.length()<lineSize)
		{	line.append(" ");
		}
		
		if(insrtRandNumber==i)
		{
			line.append(" ");
		line.append(getNumber(getRandomInteger(10,1)));
		insrtRandNumber =getRandomInteger(lineSize, i);
		if(line.length()<lineSize)
		{	line.append(" ");
		}
		
		}
		if(flag==0)
		{
			line.append("Finance");
			flag++;
			isFinance=false;
			if(line.length()<lineSize)
			{	line.append(" ");
			}
				
		}
		if(i==inst_Finance2&& flag1<=1)
		{
			line.append("finance");
			flag1++;
			isFinance=false;
			if(line.length()<lineSize)
			{	line.append(" ");
			}
				
		}

		k++;
	
		}
		
		f1.write(line+"\n");

		line.delete(0, line.length()-1);
		}
		return f1;
	}
	
	//Method to create  footer of each page
	public static FileWriter writeFooter(FileWriter f1,int pagenum, boolean isLastpage) throws IOException
	{
	  if(isLastpage==true)
	  {
		  f1.write("\n");  
	  }
				for(int word=1;word<65;word++)
				{
					f1.write("-");
				}
	          f1.write("\n");
	          
			    for(int word=1;word<=65;word++)
				{
				if(word==32)
				 {
					f1.write("page Number "+pagenum);
				 }
				  else	f1.write(" ");
				}
			   f1.write("\n");
			   
				for(int word=1;word<=65;word++)
					{
					if(word==65)
					   {
					f1.write("S a m p l e C o m p a n y");
					    }
				else  f1.write(" ");
					
						}
						f1.write("\n");
						 if(isLastpage==true)
						  {
							  f1.write("<EOF>");  
						  }
			
		return f1;
	}
	
	public static int getRandomlineNumber(int maximum, int minimum)
	{ 
		int multiple = 55;
		int rangeStart =  minimum;
		int rangeEnd = maximum;

		int calcRangeStart = rangeStart / multiple;
		int calcRangeEnd = rangeEnd / multiple;


		
		return getRandomInteger(calcRangeEnd*multiple, calcRangeStart*multiple);
	}
	
}
