package com.gep;

import java.util.List;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : @gep
//  @ File Name : GeneExpress.java
//  @ Date : 2013/4/5
//  @ Author : @shenzhan
//
//

/**
 * 基因表达
 * 计算基因的值
**/
public class Expression {
	 
   private FunctionSet Fun;
   private double[] Data;
   private double[] GeneData;  //填充数据 的
   
   private int nValidLen;
  
	
   
   public Expression(){
	   Fun=new FunctionSet();
	   
   }
	/**
	 * 获取基因表达的计算值
	**/
	public void GetValue(Individual Indiv ,double[] Data) {
		GeneData=new double[Indiv.Chrom.size()];
	   this.Data=Data;
		
	
	}
	
	private void GetGeneValue() {
	
	}
	
	/**
	 * 计算基因的有效长度
	 * @return
	 */
	private  void GetValidLength(List<String> Gene){
		 int i=0;
		 int nValidLen=1;
		 int nParam;
		 do{
			   nParam=Fun.GetParamCount(Gene.get(i));
			   nValidLen+=nParam;
			   ++i;
		 }while(i<nValidLen);
		 this.nValidLen= nValidLen;
	}
	
	/**
	 * 填充数据
	 * @param Gene
	 */
	private void FillData(List<String> Gene) {
	      for(int i=0;i<Gene.size();++i){
	    	    int nParam=Fun.GetParamCount(Gene.get(i));
	    	    if(0==nParam){
	    	    	  String sNum=Gene.get(i);
	    	    	  int nIndex=Integer.parseInt(sNum);
	    	    	  GeneData[i]=Data[nIndex];
	    	    }
	      }
	}
}
