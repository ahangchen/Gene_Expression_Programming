/**
 * 
 */
package com.gep;

/**
 * �������� 
 * ������Ӧֵ����
 * @author shenzhan
 *
 */
public class FitnessFunFactory {

	/**
	 * 
	 */
	public FitnessFunFactory() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 *������Ӧֵ���� 
	 * @param sFitnessType
	 * @return
	 */
	public static FitnessFunction GetFitnessFun(String sFitnessType){
		
		if(sFitnessType.equals("SampleClassify")){
			return new SampleClassify();
		}
		else if(sFitnessType.equals("SenSepClassify")){
			return new SenSepClassify();
		}
		else if(sFitnessType.equals("ConciseClassify")){
			return new ConciseClassify();
		}
		else if(sFitnessType.equals("AbsoluteFitness")){
			return new AbsoluteFitness();
		}
		else if(sFitnessType.equals("RelativeFitness")){
			return new RelativeFitness();
		}
		
		return null;
		
	}

}
