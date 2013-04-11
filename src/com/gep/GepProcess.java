package com.gep;
import java.util.*;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : @gep
//  @ File Name : GepProcess.java
//  @ Date : 2013/4/5
//  @ Author : @shenzhan
//
//




/**
 * gep的主要过程
**/
public class GepProcess {
	/**
	 * 
	 */
	
	public Population Pop;   //种群
	public FunctionSet Fun;  //函数集
	public List<String> Feature;
	
	public int MaxGeneration; //
	public int PopulationSize;
	public int HeadLength;
	public int GeneCount;
	public static int GeneLength;
	public int ChromosomeLength;
	public double MutationRate;
	public double OnePRecomRate;
	public double TwoPRecomRate;
	public double GeneRecomRate;
	public double ISRate;
	public int[] ISElemLength;
	public double RISRate;
	public int[] RISElemLength;
	public double GeneTransRate;
	public double SelectionRange;
	public double Error;
	public double SuccessRate;
	
	public int FeatureNum;
	
	public String[] sFullSet;  //函数集合+特征集合
	public String[]  sFeatureSet; //特征集合
	
	/**
	 * 群体初始化
	**/
	public void InitialPopulation() {
		
		//产生必要的字符集合
		
		//特征
		 sFeatureSet=new String[this.FeatureNum];
		for(int i=0;i<this.FeatureNum;++i){
			sFeatureSet[i]=   String.format("%d", i);
		}
		
		int nTail=this.HeadLength*(Fun.MaxParamCount-1)+1; //尾部长度
		this.GeneLength=this.HeadLength+nTail;  //计算基因长度
		this.ChromosomeLength=this.GeneCount*this.GeneLength; //染色体长度
		
		String[] sFunSet=(String[]) Fun.sFunction.toArray(); //函数集合
	    sFullSet=new String[sFunSet.length+sFeatureSet.length];  //函数集合 +特征集合
		int nSetLen=sFunSet.length+sFeatureSet.length;
		for(int i=0;i<sFunSet.length;++i){
			sFullSet[i]=sFunSet[i];
		}
		for(int i=sFunSet.length;i<nSetLen;++i){
			sFullSet[i]=sFeatureSet[i];
		}
		
		Random random=new Random();
		String[] sGene=new String[this.GeneLength];
		for(int i=0;i<this.PopulationSize;++i){
			
			Individual Indiv=new Individual();//新个体
			for(int k=0;k<this.GeneCount;++k){
				
				//基因头部
				  int nIndex;
				  int j;
				  for( j=0;j<this.HeadLength;++j){
					    nIndex=random.nextInt(nSetLen);
					    sGene[j]=sFullSet[nIndex];
				  }
				  
				  //基因尾部
				  for(;j<this.GeneLength;++j){
					  nIndex=random.nextInt(sFeatureSet.length);
					  sGene[j]=sFeatureSet[j];
				  }
				  
				  Indiv.AddGene(sGene); //添加基因
			}
			this.Pop.AddIndivdual(Indiv) ;   	//添加个体
		}
		     
	}
	
	/**
	 * 每一代的评估函数
	**/
	public void EvalutePopulaton() {
	
	}
	
	/**
	 * 判断gep是否要结束
	**/
	public void IsTerminate() {
	
	}
	
	/**
	 * 选择在群体中进行选择
	**/
	public void Select() {
	
	}
	
	/**
	 * 变异
	 */
	public void Mutation(){
		 
		  Random random=new Random();
		  for(int i=0;i<this.PopulationSize;++i){
			     for(int j=0;j<this.ChromosomeLength;++j){
			    	     
			    	     if(random.nextDouble()<this.MutationRate){
			    	    	    int nIndex=j%this.GeneLength;
			    	    	    int  k;
			    	    	    //基因头部
			    	    	    if(nIndex<this.HeadLength){
			    	    	    	  k=random.nextInt(this.sFullSet.length);
			    	    	    	  Pop.Get(i).Set(j, sFullSet[k]);  //设置变异的基因
			    	    	    }
			    	    	    else{ //尾部
			    	    	    	  k=random.nextInt(this.sFeatureSet.length);
			    	    	    	  Pop.Get(i).Set(j, sFeatureSet[k]);  //设置变异的基因
			    	    	    }
			    	     }
			     }
		  }
	}
	/**
	 * IS 插串
	 */
   public void TransPosIS(){
	   Random random = new Random();
		double dRate;
		for(int i=0;i<this.PopulationSize;++i){
			  dRate=random.nextDouble();
			  if(dRate<this.ISRate){
				     int nIndivNO=random.nextInt(this.PopulationSize); //随机个体
		    	     Individual Indiv=this.Pop.Get(nIndivNO);
		    	     
		    	     int nGeneNO=random.nextInt(this.GeneCount);  //随机基因
		    	     int nStart=nGeneNO*this.GeneLength;  //开始位置
		    	     
		    	     int nSelLen=this.ISElemLength.length;
		    	     int nLength=this.ISElemLength[random.nextInt(nSelLen)]; //随机长度
		    	     
		    	     //随机源位置
		    	     int nSouPos=random.nextInt(this.GeneLength);
		    	     if(nSouPos+nLength >=this.GeneLength){
		    	    	  nSouPos=this.GeneLength-nLength;
		    	     }
		    	     nSouPos+=nStart;
		    	     
		    	     //随机目标位置
		    	     int nTarPos;
		    	     do{
		    	    	 nTarPos=random.nextInt(this.HeadLength);
		    	     }while(0!=nTarPos);
		    	     nTarPos+=nStart;
		    	     
		    	     //基因IS复制
		    	     List<String> listTemp=Indiv.Chrom.subList(nSouPos, nSouPos+nLength);
		    	     Indiv.Chrom.addAll(0, listTemp);
		    	     for(int j=this.HeadLength+nLength-1;j>=this.HeadLength;--j){
		    	    	   Indiv.Chrom.remove(j);
		    	     }
		    	     	     
			  }
		}
		
	}
	 /**
	  * RIS  插串
	  */
   public void TransPosRIS(){
	   Random random = new Random();
		double dRate;
		for(int i=0;i<this.PopulationSize;++i){
			  dRate=random.nextDouble();
			  if(dRate<this.ISRate){
				     int nIndivNO=random.nextInt(this.PopulationSize); //随机个体
		    	     Individual Indiv=this.Pop.Get(nIndivNO);
		    	     
		    	     int nGeneNO=random.nextInt(this.GeneCount);  //随机基因
		    	     int nStart=nGeneNO*this.GeneLength;  //开始位置
		    	     
		    	     int nSelLen=this.ISElemLength.length;
		    	     int nLength=this.ISElemLength[random.nextInt(nSelLen)]; //随机长度
		    	     
		    	   //随机源位置
		    	    int nHeadPos;
		    	    do{
		    	    	nHeadPos=random.nextInt(this.HeadLength); 
		    	    }while(nHeadPos!=0);
		    	    while(nHeadPos<this.GeneLength &&   !Fun.IsFunction(Indiv.Get(nHeadPos)) ){
		    	    	++nHeadPos;
		    	    }
		    	    if(nHeadPos==this.GeneLength){  //找不到函数符号
		    	    	continue;
		    	    }
		    	    //判断长度
		    	    if(  this.GeneLength-nHeadPos < nLength){
		    	    	nLength=this.GeneLength-nHeadPos;
		    	    }
		    	    
		    	    //基因插窜
		    	    List<String> listTemp=Indiv.Chrom.subList(nHeadPos,nHeadPos+nLength);
		    	    Indiv.Chrom.addAll(0,listTemp);
		    	    for(int j=this.HeadLength+nLength-1;j>=this.HeadLength;--j){
		    	    	   Indiv.Chrom.remove(j);
		    	     }
		    	     	     
			  }
		}
		
	}
	/**
	 * 基因 插串
	 */
	public void TransPosGene(){
		 Random random = new Random();
		double dRate;
		
		for(int i=0;i<this.PopulationSize;++i){
		      dRate=random.nextDouble();
		      if(dRate<this.GeneTransRate){
		    	      
		    	     int nIndivNO=random.nextInt(this.PopulationSize); //随机个体
		    	     Individual Indiv=this.Pop.Get(nIndivNO);
		    	     
		    	     int nGeneNO;  //随机基因
		    	     do{
		    	    	   nGeneNO=random.nextInt(this.GeneCount);
		    	     }while(nGeneNO!=0);
		    	     
		    	     //基因插串
		    	     int nStart=nGeneNO*this.GeneLength;
		    	     int nEnd=nStart+this.GeneLength;
		    	     List<String> listTemp=Indiv.Chrom.subList(nStart,nEnd);
		    	      //删除原位置的基因
		    	     for(int j=nStart;j<nEnd;++j){
		    	    	 Indiv.Chrom.remove(j);
		    	     }
		    	     Indiv.Chrom.addAll(0, listTemp);   //把基因插入到开始位置
		    	     
		      }
		}
		
	}
	/**
	 * 单点重组
	 */
	public void RecomOnePoint() {
		 int i=0;
		 int nFather;
		 int nMother;
		 int nPos;
	    Random random = new Random();
	    double dRate;
		for(i=0;i<this.PopulationSize;++i){
			
			  dRate= random.nextDouble();
			  
			  if(dRate <this.OnePRecomRate ){
				     //随机选取两个个体 和 交叉点
				  
					nFather = random.nextInt(this.PopulationSize);//随机选取交叉个体
					nMother=random.nextInt(this.PopulationSize);
					nPos=random.nextInt(this.ChromosomeLength);
					
					Individual Father=this.Pop.Get(nFather);  
					Individual Mother=this.Pop.Get(nMother);
					String temp;
				     //两个个体交叉重组
					for(int j=0;j<nPos;++j)
					{
						temp=Father.Get(j);
						Father.Set(j, Mother.Get(j));
						Mother.Set(j, temp);
					}
					
					
			  }
		}
	}
	/**
	 * 两点重组
	 */
	public void RecomTwoPoint(){
		 int i=0;
		 int nFather;
		 int nMother;
		 int nPosPre;
		 int nPosLast;
	    Random random = new Random();
	    double dRate;
	    for(i=0;i<this.PopulationSize;++i){
	    	  dRate=random.nextDouble();
	    	  if(dRate<this.RISRate){
	    		    
	    		    nFather = random.nextInt(this.PopulationSize);//随机选取交叉个体
					nMother=random.nextInt(this.PopulationSize);
					Individual Father=this.Pop.Get(nFather);
					Individual Mother=this.Pop.Get(nMother);
					
					nPosPre=random.nextInt(this.ChromosomeLength);
					nPosLast=random.nextInt(this.ChromosomeLength);
					
					if(nPosPre>nPosLast){
						int nTemp=nPosLast;
						nPosLast=nPosPre;
						nPosPre=nPosLast;
					}
					
					//基因交换
					String sTemp;
					for(int j=nPosPre;j<nPosLast;++j){
						 sTemp=Father.Get(j);
						 Father.Set(j,Mother.Get(j));
						 Mother.Set(j,sTemp);
					}
					
	    	  }
	    }
		
	}
	/**
	 * 基因重组
	 */
	public void ReComGene(){
		 int i=0;
		 int nFather;
		 int nMother;
	    Random random = new Random();
	    double dRate;
	    
	    for(i=0;i<this.PopulationSize;++i){
	    	
	    	dRate=random.nextDouble();
	    	
	    	if(dRate<this.GeneTransRate){
	    		
	    		   nFather = random.nextInt(this.PopulationSize);//随机选取交叉个体
					nMother=random.nextInt(this.PopulationSize);
					Individual Father=this.Pop.Get(nFather);
					Individual Mother=this.Pop.Get(nMother);
					
					int nGeneNo=random.nextInt(this.GeneCount);
					
					int nStart=nGeneNo*this.GeneLength;  //计算基因的开始  结束位置
					int nEnd=nStart+this.GeneLength;
					
					//基因交换
					String sTemp;
					for(int j=nStart;j<nEnd;++j){
						 sTemp=Father.Get(j);
						 Father.Set(j,Mother.Get(j));
						 Mother.Set(j,sTemp);
					}
	    	}
	    }
		
	}
	
}
