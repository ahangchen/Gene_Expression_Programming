/**
 * 
 */
package com.gep;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

  

/**
 * @author shenzhan
 *
 */
public class FunctionSet {
  
	
	  public List<String> sFunction;
	  private Map<String, Integer>  FunMap;
	  public int MaxParamCount;
	  
	/**
	 *   ��ʼ�� ���ú�����
	 */
	public FunctionSet() {
		
		  this.MaxParamCount=2;
		  sFunction=new LinkedList<String>();
		  String[] temp={"+","-","*","/","sin","cos","sqrt","tan","pow2","log","abs"};  //���ú�����
		  for(int i=0;i<temp.length;++i){
			  sFunction.add(temp[i]);
		  }
		  
		  //��������� ����ӳ���ϵ
		  FunMap=new HashMap<String, Integer>();
		  for(int i=0;i<4;++i){
			  FunMap.put(temp[i], 2);
		  }
		  for(int i=4;i<temp.length;++i){
			  FunMap.put(temp[i],1);
		  }
		 	  
	}
	/**
	 * �ж��Ƿ��Ǻ���
	 * @param Operator
	 * @return
	 */
	public boolean IsFunction(String Operator){
		    return this.sFunction.contains(Operator);
	}

	/**
	 * ���� ����� ����������
	 */
	public int GetParamCount(String Operator) {
		 Integer Inte= FunMap.get(Operator);
		if(Inte==null){
			return 0;
		}
		else{
			return Inte.intValue();
		}
	}

	/**
	 * ��ȡ������
	 */
	public double GetResult(String Operator, double[] Data) {
		
		  if("+"==Operator){
			    return Data[0]+Data[1];
		  }
		  else if("-"==Operator){
			    return Data[0]-Data[1];
		  }
		  else if("*"==Operator){
			    return Data[0]*Data[1];
		  }
		  else if("/"==Operator){
			    return Data[0]/Data[1];  //--------------------------------
		  }
		  else if("sin"==Operator){
			    return Math.sin(Data[0]);
		  }
		  else if("cos"==Operator){
			     return Math.cos(Data[0]);
		  }
		  else if("sqrt"==Operator){
			  return Math.sqrt(Data[0]);
		  }
		  else if("tan"==Operator){
			  return Math.tan(Data[0]);
		  }
		  else if("pow2"==Operator){
			    return Math.pow(Data[0], 2);
		  }
		  else if("log"==Operator){
			    return Math.log(Data[0]);
		  }
		  else if("abs"==Operator){
			  return Math.abs(Data[0]);
		  }
		  
		return 0;
	}

}
